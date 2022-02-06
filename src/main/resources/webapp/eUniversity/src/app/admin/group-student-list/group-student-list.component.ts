import { Component, OnInit } from '@angular/core';
import { AttachStudentDTO } from 'src/app/core/DTOs/admin/group/attach-student-dto';
import { Student } from 'src/app/core/models/admin/account/student';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { DDL } from 'src/app/core/models/ddl';
import { refreshSelectPicker } from 'src/app/core/util/select-picker';
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
    this.groupService.getGroupsDDL().subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.groupsDDL = res.data;
    });

    this.groupService.getAllStudentsWithoutGroup().subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.studetnsDDL = res.data;
    });

    refreshSelectPicker(100);
  }

  getStudentsByGroup() {
    this.groupService.getStudentsByGroup(this.selectedGroup).subscribe((res: BaseResponse<Student[]>) => {
      this.students = res.data;
    });
  }

  attachStudent() {
    if (this.selectedGroup != 0) {
      let dto: AttachStudentDTO = new AttachStudentDTO(this.selectedGroup, this.selectedStudent);
      this.groupService.attachStudent(dto).subscribe({
        next: (res: any) => {
          this.students.unshift(new Student('', res.data.id, res.data.fullName, '', 0, ''));
          for (let i = 0; i < this.studetnsDDL.length; i++) {
            if (this.studetnsDDL[i].id == this.selectedStudent) {
              this.studetnsDDL.splice(i, 1);
              break;
            }
          }
          refreshSelectPicker();
        },
        error: (data: any) => {
          alert(data.message);
        }
      });
    }
  }

  detachStudent(student: Student) {
    this.groupService.detachStudent(this.selectedGroup, student.userId).subscribe({
      next: (data: any) => {
        this.studetnsDDL.push(new DDL<number>(student.userId, student.fullName));
        for (let i = 0; i < this.students.length; i++) {
          if (this.students[i].userId == student.userId) {
            this.students.splice(i, 1);
            break;
          }
        }
        refreshSelectPicker();
      },
      error: (data: any) => {
        alert(data.message);
      }
    });
  }
}
