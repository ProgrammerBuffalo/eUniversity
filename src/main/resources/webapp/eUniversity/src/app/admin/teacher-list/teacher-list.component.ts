import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UpdateTeacherDTO } from 'src/app/core/DTOs/admin/update-teacher-dto';
import { Teacher } from 'src/app/core/models/admin/teacher';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { AccountService } from 'src/app/services/accounts.service';

@Component({
  selector: 'app-teacher-list',
  templateUrl: './teacher-list.component.html',
  styleUrls: ['../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class TeacherListComponent implements OnInit {

  showAddPopup: boolean;
  showEditPopup: boolean;

  selectedTeacher!: Teacher;
  teachers: Teacher[];

  addForm: FormGroup;
  editForm: FormGroup;

  get addLogin() { return this.addForm.get('login'); }

  get addPassword() { return this.addForm.get('password'); }

  get addFullName() { return this.addForm.get('fullName'); }

  get addAge() { return this.addForm.get('age'); }

  get editLogin() { return this.editForm.get('login'); }

  get editFullName() { return this.editForm.get('fullName'); }

  get editAge() { return this.editForm.get('age'); }

  constructor(
    private accountService: AccountService
  ) {
    this.showAddPopup = false;
    this.showEditPopup = false;

    this.addForm = new FormGroup({
      login: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      fullName: new FormControl('', Validators.required),
      age: new FormControl('', [Validators.required, Validators.min(16), Validators.max(100)])
    });

    this.editForm = new FormGroup({
      login: new FormControl('', Validators.required),
      fullName: new FormControl('', Validators.required),
      age: new FormControl('', [Validators.required, Validators.min(16), Validators.max(100)])
    });

    this.teachers = [];
  }

  ngOnInit(): void {
    this.accountService.getTeachers().subscribe((data: BaseResponse<Teacher[]>) => {
      this.teachers = data.data;
    });
  }

  showAddModal() {
    this.showAddPopup = true;

    this.editForm.get('login')!.setValue('');
    this.editForm.get('password')!.setValue('');
    this.editForm.get('fullName')!.setValue('');
    this.editForm.get('age')!.setValue('');
  }

  showEditModal(teacher: Teacher) {
    this.showEditPopup = true;
    this.selectedTeacher = teacher;

    this.editForm.get('login')!.setValue(teacher.login);
    this.editForm.get('fullName')!.setValue(teacher.fullName);
    this.editForm.get('age')!.setValue(teacher.age);
  }

  closeEditModal() {
    this.showEditPopup = false;
  }

  closeAddModal() {
    this.showAddPopup = false;
  }

  addTeacher() {
    if (this.addForm.valid)
      this.accountService.registerTeacher(this.addForm.value).subscribe({
        next: (data: BaseResponse<Teacher>) => {
          this.teachers.unshift(data.data);

          this.showAddPopup = false;
        },
        error: (data) => {
          alert('can`t add student');
        }
      });
  }

  updateTeacher() {
    if (this.editForm.valid) {
      let dto: UpdateTeacherDTO = new UpdateTeacherDTO(
        this.selectedTeacher.id, this.editLogin?.value, this.editFullName?.value, this.editAge?.value);

      this.accountService.updateStudent(dto).subscribe({
        next: (data) => {
          this.selectedTeacher.login = this.editLogin?.value;
          this.selectedTeacher.fullName = this.editFullName?.value;
          this.selectedTeacher.age = this.editAge?.value;

          this.showEditPopup = false;
        },
        error: (data) => {
          alert('this login alredy exsists');
        }
      });
    }
  }

  removeTeacher(id: string) {
    this.accountService.deleteTeacher(id).subscribe({
      next: (data) => {
        for (let i = 0; i < this.teachers.length; i++) {
          if (this.teachers[i].id == id)
            this.teachers.splice(i, 1);
        }
      },
      error: (data) => {
        alert('cant remove this student');
      }
    })
  }

}
