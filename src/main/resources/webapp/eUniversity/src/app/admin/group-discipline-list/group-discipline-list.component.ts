import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { GroupDisciplineDTO } from 'src/app/core/DTOs/admin/group/group-discipline-dto';
import { GroupDiscipline } from 'src/app/core/models/admin/group-discipline';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { DDL } from 'src/app/core/models/ddl';
import { refreshSelectPicker } from 'src/app/core/util/select-picker';
import { DisciplineService } from 'src/app/services/discipline.service';
import { GroupService } from 'src/app/services/group.service';
import { TeacherService } from 'src/app/services/teacher.service';

@Component({
  selector: 'app-group-discipline-list',
  templateUrl: './group-discipline-list.component.html',
  styleUrls: ['../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class GroupDisciplineListComponent implements OnInit {

  groupDisciplines: GroupDiscipline[];

  addForm: FormGroup;

  groupsDDL: DDL<number>[];
  teachersDDL: DDL<number>[];
  disciplinesDDL: DDL<number>[];

  selectedGroupId;
  selectedTeacherId;
  selectedDisciplineId;

  isAddFormVisible: boolean;

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

    this.isAddFormVisible = false;

    this.addForm = new FormGroup({
      teacher: new FormControl(''),
      discipline: new FormControl('')
    })
  }

  ngOnInit(): void {
    this.groupService.getGroupsDDL().subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.groupsDDL = res.data;
    });

    this.disciplineService.getDisciplinesDLL().subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.disciplinesDDL = res.data;
    });

    refreshSelectPicker(100);
  }

  groupChange() {
    this.groupService.getGroupDisciplines(this.selectedGroupId).subscribe((res: BaseResponse<GroupDiscipline[]>) => {
      this.groupDisciplines = res.data;
    });
  }

  diciplineChanged() {
    this.selectedTeacherId = 0;
    this.teacherService.getDisciplineTeachersDDL(this.selectedDisciplineId).subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.teachersDDL = res.data;
      refreshSelectPicker();
    });
  }

  attachGroupDiscipline() {
    if (this.selectedGroupId > 0) {
      let dto = new GroupDisciplineDTO(this.selectedGroupId, this.selectedDisciplineId, this.selectedTeacherId);
      this.groupService.attachGroupDiscipline(dto).subscribe({
        next: (res: BaseResponse<GroupDiscipline>) => {
          this.groupDisciplines.push(res.data);
        },
        error: (res: any) => {
          alert(res.data);
        }
      });
    }
  }

  detachTeacherWithDiscipline(groupDiscipline: GroupDiscipline) {
    let dto = new GroupDisciplineDTO(this.selectedGroupId, groupDiscipline.disciplineId, groupDiscipline.teacherId);
    this.groupService.detachGroupDiscipline(dto).subscribe({
      next: (res: any) => {
        for (let i = 0; i < this.groupDisciplines.length; i++) {
          if (this.groupDisciplines[i].disciplineId == groupDiscipline.disciplineId
            && this.groupDisciplines[i].teacherId == groupDiscipline.teacherId) {
            this.groupDisciplines.splice(i, 1);
            break;
          }
        }
      },
      error: (res: any) => {
        alert('can`t remove teacher with discipline');
      }
    })
  }

  showAddForm() {
    this.isAddFormVisible = true;
    this.selectedDisciplineId = 0
    this.selectedTeacherId = 0;
  }

  closeAddForm() {
    this.isAddFormVisible = false;
    this.selectedDisciplineId = 0
    this.selectedTeacherId = 0;
  }

}
