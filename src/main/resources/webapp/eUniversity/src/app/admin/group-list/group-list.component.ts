import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UpdateStudentDTO } from 'src/app/core/DTOs/admin/update-student-dto';
import { Group } from 'src/app/core/models/admin/group';
import { Student } from 'src/app/core/models/admin/student';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { AccountService } from 'src/app/services/accounts.service';
import { GroupService } from 'src/app/services/group.service';

@Component({
  selector: 'app-group-list',
  templateUrl: './group-list.component.html',
  styleUrls: ['../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class GroupListComponent implements OnInit {

  showAddPopup: boolean;
  showEditPopup: boolean;

  selectedGroup!: Group;
  groups: Group[];

  addForm: FormGroup;
  editForm: FormGroup;

  get addName() { return this.addForm.get('name'); }

  get editName() { return this.editForm.get('name'); }

  constructor(
    private groupService: GroupService
  ) {
    this.showAddPopup = false;
    this.showEditPopup = false;

    this.addForm = new FormGroup({
      name: new FormControl('', Validators.required),
    });

    this.editForm = new FormGroup({
      name: new FormControl('', Validators.required),
    });

    this.groups = [];
  }

  ngOnInit(): void {
    // this.accountService.getStudents().subscribe((data: BaseResponse<Student[]>) => {
    //   this.groups = data.data;
    // });
  }

  showAddModal() {
    this.showAddPopup = true;

    this.editForm.get('name')!.setValue('');
  }

  showEditModal(group: Group) {
    console.log(group);
    this.showEditPopup = true;
    this.selectedGroup = group;

    this.editForm.get('name')!.setValue(group.name);
  }

  closeEditModal() {
    this.showEditPopup = false;
  }

  closeAddModal() {
    this.showAddPopup = false;
  }

  addGroup() {
    //if (this.addForm.valid)
    // this.accountService.registerStudent(this.addForm.value).subscribe({
    //   next: (data: BaseResponse<Student>) => {
    //     this.groups.unshift(data.data);

    //     this.showAddPopup = false;
    //   },
    //   error: (data) => {
    //     alert('can`t add student');
    //   }
    // });
  }

  updateGroup() {
    if (this.editForm.valid) {
      // let dto: UpdateStudentDTO = new UpdateStudentDTO(
      //   this.selectedGroup.id, this.editName?.value, this.editFullName?.value, this.editAge?.value);

      // this.accountService.updateStudent(dto).subscribe({
      //   next: (data) => {
      //     this.selectedGroup.login = this.editName?.value;
      //     this.selectedGroup.fullName = this.editFullName?.value;
      //     this.selectedGroup.age = this.editAge?.value;

      //     this.showEditPopup = false;
      //   },
      //   error: (data) => {
      //     alert('this login alredy exsists');
      //   }
      // });
    }
  }

  removeGroup(id: number) {
    // this.accountService.deleteStudent(id).subscribe({
    //   next: (data) => {
    //     for (let i = 0; i < this.groups.length; i++) {
    //       if (this.groups[i].id == id)
    //         this.groups.splice(i, 1);
    //     }
    //   },
    //   error: (data) => {
    //     alert('cant remove this student');
    //   }
    // })
  }

}
