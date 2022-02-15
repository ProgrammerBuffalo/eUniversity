import { Component, OnInit } from '@angular/core';
import { saveAs } from 'file-saver';
import { MaterialFile } from 'src/app/core/models/admin/material-file';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { DDL } from 'src/app/core/models/ddl';
import { refreshSelectPicker } from 'src/app/core/util/select-picker';
import { GroupService } from 'src/app/services/group.service';
import { MaterialService } from 'src/app/services/material.service';

@Component({
  selector: 'app-material-student-list',
  templateUrl: './material-student-list.component.html',
  styleUrls: ['./material-student-list.component.scss']
})
export class MaterialStudentListComponent implements OnInit {

  groupId: number;
  disciplineId: number;
  studentId: number;

  groupsDDL: DDL<number>[];
  disciplinesDDL: DDL<number>[];
  studentsDDL: DDL<number>[];

  materials: MaterialFile[];

  constructor(
    private groupService: GroupService,
    private materialService: MaterialService
  ) {
    this.groupId = 0;
    this.disciplineId = 0;
    this.studentId = 0;

    this.groupsDDL = [];
    this.disciplinesDDL = [];
    this.studentsDDL = [];

    this.materials = [];
  }

  ngOnInit(): void {
    this.groupService.getGroupsDDL().subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.groupsDDL = res.data;
    })
  }

  groupChanged() {
    this.groupService.getGroupDisciplinesDDL(this.groupId).subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.disciplinesDDL = res.data;
      refreshSelectPicker();
    })

    this.groupService.getStudentsByGroupDDL(this.groupId).subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.studentsDDL = res.data;
      refreshSelectPicker();
    })

    this.disciplineId = 0;
    this.studentId = 0;
    this.materials = [];
  }

  changeMaterial() {
    if (this.groupId != 0 && this.disciplineId != 0 && this.studentId != 0) {
      this.materialService.getStudentMaterials(this.groupId, this.disciplineId, this.studentId).subscribe((res: BaseResponse<MaterialFile[]>) => {
        this.materials = res.data;
      })
    }
  }

  downloadMaterial(material: MaterialFile) {
    this.materialService.downloadFile(material.id).subscribe((res: Blob) => {
      saveAs(res, material.fileName);
    })
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
}
