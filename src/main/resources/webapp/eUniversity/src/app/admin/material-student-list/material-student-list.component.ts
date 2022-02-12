import { Component, OnInit } from '@angular/core';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { DDL } from 'src/app/core/models/ddl';
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
  }

  ngOnInit(): void {
    this.groupService.getGroupsDDL().subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.groupsDDL = res.data;
    })
  }

  groupChanged() {
    this.groupService.getGroupDisciplinesDDL(this.groupId).subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.disciplineId = 0;
      this.disciplinesDDL = res.data;
    })
  }

  changeMaterial() {

  }

  addMaterial() {
    if (this.groupId != 0 && this.disciplineId != 0) {

    }
  }

  removeMaterial() {

  }
}
