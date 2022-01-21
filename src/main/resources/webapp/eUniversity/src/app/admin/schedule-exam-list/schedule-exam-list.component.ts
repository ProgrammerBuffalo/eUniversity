import { Component, OnInit } from '@angular/core';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { DDL } from 'src/app/core/models/ddl';
import { GroupService } from 'src/app/services/group.service';
import { refreshSelectPicker } from 'src/app/core/util/select-picker';
import { TeacherService } from 'src/app/services/teacher.service';

@Component({
  selector: 'app-schedule-exam-list',
  templateUrl: './schedule-exam-list.component.html',
  styleUrls: ['../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class ScheduleExamListComponent implements OnInit {

  groupId: number;

  groupsDDL: DDL<number>[];
  disciplinesDDL: DDL<number>[];
  teachersDDL: DDL<number>[];

  showAddModal: boolean;

  constructor(
    private groupService: GroupService,
    private teacherService: TeacherService
  ) {
    this.groupsDDL = [];
    this.disciplinesDDL = [];
    this.teachersDDL = [];

    this.groupId = 0;

    this.showAddModal = false;
  }

  ngOnInit(): void {
    this.groupService.getGroupsDDL().subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.groupsDDL = res.data;
      refreshSelectPicker();
    });
  }

  groupChanged() {

  }

  showAddPopup() {
    this.showAddModal = true;
  }

  closeAddPopup() {
    this.showAddModal = false;
  }

}
