import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/core/models/admin/student';
import { DDL } from 'src/app/core/models/ddl';
import { GroupService } from 'src/app/services/group.service';

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

  }

  getStudentsByGroup() {

  }

  attachStudent() {

  }

  detachStudent() {

  }
}
