import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AttachScheduleDTO } from 'src/app/core/DTOs/admin/add-schedule.dto';
import { Schedule } from 'src/app/core/models/admin/schedule';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { DDL } from 'src/app/core/models/ddl';
import { refreshSelectPicker } from 'src/app/core/util/select-picker'
import { WeekDay, weeks } from 'src/app/core/util/weeks';
import { GroupService } from 'src/app/services/group.service';
import { ScheduleService } from 'src/app/services/schedule.service';

@Component({
  selector: 'app-schedule-list',
  templateUrl: './schedule-list.component.html',
  styleUrls: ['../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class ScheduleListComponent implements OnInit {

  schedules: Schedule[];

  groupId: number;
  disciplineId: number;

  isAddFormVisible: boolean;
  addForm: FormGroup;

  groupsDDL: DDL<number>[];
  teachersDDL: DDL<number>[];
  weeksDDL: WeekDay[];
  typesDDL: DDL<number>[];

  constructor(
    private groupService: GroupService,
    private scheduleService: ScheduleService
  ) {

    this.schedules = [];

    this.groupId = 0;
    this.disciplineId = 0;

    this.isAddFormVisible = false;

    this.addForm = new FormGroup({
      teacherId: new FormControl(0, Validators.required),
      weekNum: new FormControl(0, Validators.required,),
      educationalProcessId: new FormControl(0, Validators.required),
      timeFrom: new FormControl(Validators.required),
      timeTo: new FormControl(Validators.required)
    });

    this.weeksDDL = weeks;
    this.groupsDDL = [];
    this.teachersDDL = [];
    this.typesDDL = [];
  }

  get addTeacher() { return this.addForm.get('teacherId'); }

  get addWeekNum() { return this.addForm.get('weekNum'); }

  get addEducationalProcess() { return this.addForm.get('educationalProcessId'); }

  get addTimeFrom() { return this.addForm.get('timeFrom'); }

  get addTimeTo() { return this.addForm.get('timeTo'); }

  ngOnInit(): void {
    this.groupService.getGroupsDDL().subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.groupsDDL = res.data;
      refreshSelectPicker();
    });

    this.scheduleService.getLessonsDDL().subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.typesDDL = res.data;
      refreshSelectPicker();
    })
  }

  closeAddForm() {
    this.isAddFormVisible = false;

    this.addTeacher?.setValue(0);
    this.addWeekNum?.setValue(0);
    this.addEducationalProcess?.setValue(0);
    this.addTimeTo?.setValue('');
    this.addTimeFrom?.setValue('');
  }

  showAddForm(disciplineId: number) {
    this.disciplineId = disciplineId;

    this.isAddFormVisible = true;

    this.groupService.getTeachersOfGroupDisciplineDDL(this.groupId, disciplineId).subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.teachersDDL = res.data;
      refreshSelectPicker();
    });
  }

  groupChanged() {
    this.scheduleService.getScheduleLessons(this.groupId).subscribe((res: BaseResponse<Schedule[]>) => {
      this.schedules = res.data;
    });
  }

  addSchedule() {
    let dto: AttachScheduleDTO = new AttachScheduleDTO(this.groupId,
      this.disciplineId,
      this.addTeacher?.value,
      this.addWeekNum?.value,
      this.addEducationalProcess?.value,
      this.addTimeFrom?.value,
      this.addTimeTo?.value);

    console.log(dto);

    if (this.addForm.valid) {
      this.scheduleService.attachSchedule(dto).subscribe({
        next: (res: BaseResponse<Schedule>) => {
          for (let i = 0; i < this.schedules.length; i++) {
            if (this.schedules[i].disciplineId == this.disciplineId) {
              this.schedules[i].itemList.push(res.data.itemList[0]);
              break;
            }
          }
          this.closeAddForm();
        },
        error: (res: HttpErrorResponse) => {
          alert(res.error.message);
        }
      });
    }
  }

  removeSchedule(id: number) {
    this.scheduleService.detachSchedule(id).subscribe({
      next: (res: any) => {
        for (let i = 0; i < this.schedules.length; i++) {
          for (let j = 0; j < this.schedules[i].itemList.length; j++) {
            if (this.schedules[i].itemList[j].scheduleId == id) {
              this.schedules[i].itemList.splice(j, 1);
              break;
            }
          }
        }
      },
      error: (res: HttpErrorResponse) => {
        alert(res.error.message);
      }
    });
  }

}
