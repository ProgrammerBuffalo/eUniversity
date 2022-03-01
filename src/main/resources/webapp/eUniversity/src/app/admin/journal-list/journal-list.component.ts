import { Component, OnInit } from '@angular/core';
import { Journal } from 'src/app/core/models/admin/journal';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { DDL } from 'src/app/core/models/ddl';
import { GroupService } from 'src/app/services/group.service';
import { ScheduleService } from 'src/app/services/schedule.service';
import { refreshSelectPicker } from 'src/app/core/util/select-picker';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-journal-list',
  templateUrl: './journal-list.component.html',
  styleUrls: ['./journal-list.component.scss', '../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class JournalListComponent implements OnInit {

  journals: Journal[];

  disciplineDDL: DDL<number>[];
  groupsDDL: DDL<number>[];
  eduProccesesDDL: DDL<number>[];

  disciplineId: number;
  groupId: number;

  editForm: FormGroup;
  isEditFormVisible: boolean;

  constructor(
    private groupService: GroupService,
    private scheduleService: ScheduleService
  ) {

    this.journals = [];

    this.disciplineDDL = [];
    this.groupsDDL = [];
    this.eduProccesesDDL = [];

    this.disciplineId = 0;
    this.groupId = 0;

    this.isEditFormVisible = false;
    this.editForm = new FormGroup({});

    this.journals = [
      { id: 1, discipline: 'dis1', teacher: 'teacher1', educationalProcess: 1, date: new Date(), feedback: 'good 1', progress: 11, present: true },
      { id: 2, discipline: 'dis2', teacher: 'teacher2', educationalProcess: 2, date: new Date(), feedback: 'good 2', progress: 16, present: true },
      { id: 3, discipline: 'dis3', teacher: 'teacher3', educationalProcess: 3, date: new Date(), feedback: 'good 3', progress: 20, present: true }
    ]
  }

  ngOnInit(): void {
    this.scheduleService.getEduProccessesDDL().subscribe((res: BaseResponse<DDL<number>[]>) => {
      console.log(res.data);
      this.eduProccesesDDL = res.data;


      this.journals = [
        { id: 1, discipline: 'dis1', teacher: 'teacher1', educationalProcess: 1, date: new Date(), feedback: 'good 1', progress: 11, present: true },
        { id: 2, discipline: 'dis2', teacher: 'teacher2', educationalProcess: 2, date: new Date(), feedback: 'good 2', progress: 16, present: true },
        { id: 3, discipline: 'dis3', teacher: 'teacher3', educationalProcess: 3, date: new Date(), feedback: 'good 3', progress: 20, present: true }
      ]
    })

    this.groupService.getGroupsDDL().subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.groupsDDL = res.data;
      refreshSelectPicker();
    });
  }

  showEditForm() {
    this.isEditFormVisible = true;
  }

  closeEditForm() {
    this.isEditFormVisible = false;
  }

  disciplineChanged() {

  }

  groupChanged() {
    this.groupService.getGroupDisciplinesDDL(this.groupId).subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.disciplineDDL = res.data;
      refreshSelectPicker();
    });
  }

}
