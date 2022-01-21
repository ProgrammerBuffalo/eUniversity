import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AddScheduleDTO } from 'src/app/core/DTOs/admin/add-schedule.dto';
import { Schedule } from 'src/app/core/models/admin/schedule';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { DDL } from 'src/app/core/models/ddl';
import { refreshSelectPicker } from 'src/app/core/util/select-picker'
import { WeekDay, weeks } from 'src/app/core/util/weeks';
import { GroupService } from 'src/app/services/group.service';

@Component({
  selector: 'app-schedule-list',
  templateUrl: './schedule-list.component.html',
  styleUrls: ['../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class ScheduleListComponent implements OnInit {

  schedules: Schedule[];

  groupId: number;
  disciplineId: number;

  showAddPopup: boolean;
  addForm: FormGroup;

  groupsDDL: DDL<number>[];
  teachersDDL: DDL<number>[];
  weeksDDL: WeekDay[];

  constructor(private groupService: GroupService) {

    this.schedules = [];

    this.groupId = 0;
    this.disciplineId = 0;

    this.showAddPopup = false;
    this.weeksDDL = weeks;

    this.addForm = new FormGroup({
      teacherId: new FormControl('', Validators.required),
      weekId: new FormControl('', Validators.required,),
      timeFrom: new FormControl('', Validators.required),
      timeTo: new FormControl('', Validators.required)
    });

    this.groupsDDL = [];
    this.teachersDDL = [];
  }

  get addTeacher() { return this.addForm.get('teacherId'); }

  get addWeek() { return this.addForm.get('weekId'); }

  get addTimeFrom() { return this.addForm.get('timeFrom'); }

  get addTimeTo() { return this.addForm.get('timeTo'); }

  ngOnInit(): void {
    this.groupService.getGroupsDDL().subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.groupsDDL = res.data;
      refreshSelectPicker();
    });
  }

  closeAddForm() {
    this.showAddPopup = false;
  }

  showAddForm(disciplineId: number) {
    this.disciplineId = disciplineId;

    this.addTeacher?.setValue(0);
    this.addWeek?.setValue(0);
    this.addTimeTo?.setValue('');
    this.addTimeFrom?.setValue('');

    this.showAddPopup = true;
    this.teachersDDL = this.groupService.tempGetTeachersDDL();
    refreshSelectPicker();
  }

  groupChanged() {
    this.schedules = this.groupService.getSchedules(this.groupId);
  }

  addSchedule() {
    let dto: AddScheduleDTO = new AddScheduleDTO(this.groupId, this.disciplineId, this.addTeacher?.value, this.addWeek?.value, this.addTimeFrom?.value, this.addTimeTo?.value);
    this.groupService.tempAddSchedule(dto);
  }

  removeSchedule() {

  }

}
