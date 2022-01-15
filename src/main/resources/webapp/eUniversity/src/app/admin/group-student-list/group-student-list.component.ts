declare var $: any;
import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/core/models/admin/student';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { DDL } from 'src/app/core/models/ddl';
import { GroupService } from 'src/app/services/group.service';
import 'bootstrap-select';


@Component({
  selector: 'app-group-student-list',
  templateUrl: './group-student-list.component.html',
  styleUrls: ['../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class GroupStudentListComponent implements OnInit {

  groupsDDL: DDL<number>[];
  studetnsDDL: DDL<number>[];

  selectedGroup: number;
  selectedStudent: number;

  students: Student[];
  data = ['data1', 'data2', 'data3'];

  constructor(
    private groupService: GroupService
  ) {
    this.groupsDDL = [];
    this.studetnsDDL = [];

    this.selectedGroup = 0;
    this.selectedStudent = 0;

    this.students = [];
  }

  ngOnInit(): void {
    this.groupService.getAllGroupsDDL().subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.groupsDDL = res.data;
    });

    this.groupService.getAllStudentsWithoutGroup().subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.studetnsDDL = res.data;
    });
    setTimeout(() => {
      this.data.push('data4');
    }, 1000);
    setTimeout(() => {
      $('.selectpicker2').selectpicker('refresh');
    }, 1000);
  }

  getStudentsByGroup() {
    this.groupService.getStudentsByGroup(this.selectedGroup).subscribe((res: BaseResponse<Student[]>) => {
      this.students = res.data;
    })
  }

  attachStudent() {

  }

  detachStudent() {

  }
}
