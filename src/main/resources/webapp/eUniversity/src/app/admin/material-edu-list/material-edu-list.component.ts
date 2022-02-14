import { Component, OnInit } from '@angular/core';
import { EduMaterial } from 'src/app/core/models/admin/material/edu-material';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { DDL } from 'src/app/core/models/ddl';
import { GroupService } from 'src/app/services/group.service';
import { MaterialService } from 'src/app/services/material.service';
import { ScheduleService } from 'src/app/services/schedule.service';
import { refreshSelectPicker } from 'src/app/core/util/select-picker'
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { saveAs } from 'file-saver';


@Component({
  selector: 'app-material-edu-list',
  templateUrl: './material-edu-list.component.html',
  styleUrls: ['./material-edu-list.component.scss', '../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class MaterialEduListComponent implements OnInit {

  groupId: number;
  disciplineId: number;

  groupsDDL: DDL<number>[];
  disciplinesDDL: DDL<number>[];
  eduProccessesDDL: DDL<number>[];

  materials: EduMaterial[];

  showAddPopup: boolean;

  addForm: FormGroup;

  constructor(
    private groupService: GroupService,
    private materialService: MaterialService,
    private sheduleService: ScheduleService,
    private authService: AuthService
  ) {
    this.groupId = 0;
    this.disciplineId = 0;

    this.groupsDDL = [];
    this.disciplinesDDL = [];
    this.eduProccessesDDL = [];

    this.materials = [];

    this.showAddPopup = false;

    this.addForm = new FormGroup({
      educationalProcessId: new FormControl(0, Validators.required),
      description: new FormControl(''),
      order: new FormControl('1', Validators.required),
      fileName: new FormControl('')
    });
  }

  get EducationalProcessId() { return this.addForm.get('educationalProcessId'); }

  get Description() { return this.addForm.get('description') }

  get Order() { return this.addForm.get('order'); }

  get FileName() { return this.addForm.get('fileName'); }

  ngOnInit(): void {
    this.groupService.getGroupsDDL().subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.groupsDDL = res.data;
      refreshSelectPicker();
    })

    this.sheduleService.getEduProccessesDDL().subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.eduProccessesDDL = res.data;
      refreshSelectPicker();
    })
  }

  groupChanged() {
    this.groupService.getGroupDisciplinesDDL(this.groupId).subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.materials = [];
      this.disciplineId = 0;
      this.disciplinesDDL = res.data;
      refreshSelectPicker();
    })
  }

  showAddModal() {
    if (this.groupId != 0 && this.disciplineId != 0) {
      this.addForm.reset();
      this.showAddPopup = true;
    }
    else
      alert('select group and discipline');
  }

  closeEditModal() {
    this.showAddPopup = false;
  }

  changeMaterial() {
    this.getMaterilas();
  }

  getMaterilas() {
    this.materialService.getEduMaterials(this.groupId, this.disciplineId).subscribe((res: any) => {
      this.materials = res.data;
    });
  }

  addMaterial() {
    if (this.addForm.valid) {
      let formData = new FormData();
      formData.append('groupId', this.groupId.toString());
      formData.append('disciplineId', this.disciplineId.toString());
      formData.append('accountId', this.authService.getAccountId());
      formData.append('educationalProcessId', this.EducationalProcessId?.value);
      formData.append('description', this.Description?.value);
      formData.append('order', this.Order?.value);
      formData.append('multipartFile', this.getFiles()![0]);

      this.materialService.uploadMaterial(formData).subscribe((res: any) => {
        this.showAddPopup = false;
        this.getMaterilas();
      });
    }
  }

  removeMaterial(id: number) {
    this.materialService.removeFile(id).subscribe((res: any) => {
      for (let i = 0; i < this.materials.length; i++) {
        if (this.materials[i].id == id) {
          this.materials.splice(i, 1);
          break;
        }
      }
    });
  }

  downloadMaterial(material: EduMaterial) {
    this.materialService.downloadFile(material.id).subscribe((res: Blob) => {
      //saveAs(res, 'aa.jpg');
    })
  }

  handleClick() {
    document.getElementById('upload-file')!.click();
  }

  addAttachment(fileInput: any) {
    const fileName = this.getFiles()![0].name;
    (document.getElementById('file-text')! as HTMLInputElement).value = fileName;
  }

  getFiles() {
    return (document.getElementById('upload-file')! as HTMLInputElement).files;
  }
}
