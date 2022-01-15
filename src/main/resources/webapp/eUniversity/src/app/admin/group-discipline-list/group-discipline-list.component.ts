import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { GroupDisciplineDTO } from 'src/app/core/DTOs/admin/group-discipline-dto';
import { GroupDiscipline } from 'src/app/core/models/admin/group-discipline';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { DDL } from 'src/app/core/models/ddl';
import { DisciplineService } from 'src/app/services/discipline.service';
import { GroupService } from 'src/app/services/group.service';
import { TeacherService } from 'src/app/services/teacher.service';

@Component({
  selector: 'app-group-discipline-list',
  templateUrl: './group-discipline-list.component.html',
  styleUrls: ['../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class GroupDisciplineListComponent implements OnInit {

  groupDisciplines: GroupDiscipline[]

  groupsDDL: DDL<number>[];
  teachersDDL: DDL<number>[];
  disciplinesDDL: DDL<number>[];

  selectedGroupId;
  selectedTeacherId;
  selectedDisciplineId;

  // addForm: FormGroup;
  // editForm: FormGroup;

  showAddPopup: boolean;
  //showEditPopup: boolean;

  constructor(
    private groupService: GroupService,
    private disciplineService: DisciplineService,
    private teacherService: TeacherService
  ) {
    this.groupDisciplines = [];

    this.groupsDDL = [];
    this.teachersDDL = [];
    this.disciplinesDDL = [];

    this.selectedGroupId = 0;
    this.selectedDisciplineId = 0;
    this.selectedTeacherId = 0;

    // this.addForm = new FormGroup({
    //   discipline: new FormControl(''),
    //   teacher: new FormControl('')
    // });

    this.showAddPopup = false;
    //this.showEditPopup = false;

    // this.editForm = new FormGroup({
    //   teacher: new FormControl('')
    // });
  }

  // get addDiscipline() { return this.addForm.get('discipline')?.value }

  // get addTeacher() { return this.addForm.get('teacher')?.value }

  // get editTeacher() { return this.editTeacher.get('teacher')?.value }

  ngOnInit(): void {
    this.groupService.getAllGroupsDDL().subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.groupsDDL = res.data;
    });

    this.disciplineService.getDisciplinesDLL().subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.disciplinesDDL = res.data;
    })
  }

  groupChange() {
    this.groupService.getTeacherWithDisciplines(this.selectedGroupId).subscribe((res: any) => {
      this.groupDisciplines = res.data;
    });
  }

  diciplineChanged(id: number) {
    this.teacherService.getTeacherDisciplinesDDL(id).subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.teachersDDL = res.data;
    });
  }

  attachTeacherWithDiscipline() {
    let dto = new GroupDisciplineDTO(this.selectedGroupId, this.selectedDisciplineId, this.selectedTeacherId);
    this.groupService.addTeacherWithDiscipline(dto).subscribe({
      next: (res: any) => {
        this.groupDisciplines.push(res.data);
      },
      error: (res: any) => {
        alert(res.data);
      }
    })
  }

  detachTeacherWithDiscipline(groupDiscipline: GroupDiscipline) {
    let dto = new GroupDisciplineDTO(this.selectedGroupId, groupDiscipline.disciplineId, groupDiscipline.teacherId);
    this.groupService.detachTeacherWithDiscipline(dto).subscribe({
      next: (res: any) => {
        for (let i = 0; i < this.groupDisciplines.length; i++) {
          if (this.groupDisciplines[i].disciplineId == this.selectedDisciplineId)
            this.groupDisciplines.splice(i, 1);
        }
      },
      error: (res: any) => {
        alert(res.data);
      }
    })
  }

  showAddModal() {
    this.showAddPopup = true;
    this.selectedDisciplineId = 0
    this.selectedTeacherId = 0;
  }

  // showEditModal() {

  // }

  closeAddModal() {
    this.showAddPopup = false;
    this.selectedDisciplineId = 0
    this.selectedTeacherId = 0;
  }

  // closeEditModal() {

  // }


}
