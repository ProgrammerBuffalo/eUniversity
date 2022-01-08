import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UpdateStudentDTO } from 'src/app/core/DTOs/admin/update-student-dto';
import { Student } from 'src/app/core/models/admin/student';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { DDL } from 'src/app/core/models/ddl';
import { AccountService } from 'src/app/services/accounts.service';
import { GroupService } from 'src/app/services/group.service';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class StudentListComponent implements OnInit {
  showAddPopup: boolean;
  showEditPopup: boolean;

  selectedStudent!: Student;
  students: Student[];

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
    private accountService: AccountService,
    private groupService: GroupService
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

    this.students = [];
  }

  ngOnInit(): void {
    this.accountService.getStudents().subscribe((data: BaseResponse<Student[]>) => {
      this.students = data.data;
    });
  }

  showAddModal() {
    this.showAddPopup = true;

    this.editForm.get('login')!.setValue('');
    this.editForm.get('password')!.setValue('');
    this.editForm.get('fullName')!.setValue('');
    this.editForm.get('age')!.setValue('');
  }

  showEditModal(student: Student) {
    console.log(student);
    this.showEditPopup = true;
    this.selectedStudent = student;

    this.editForm.get('login')!.setValue(student.login);
    this.editForm.get('fullName')!.setValue(student.fullName);
    this.editForm.get('age')!.setValue(student.age);
  }

  closeEditModal() {
    this.showEditPopup = false;
  }

  closeAddModal() {
    this.showAddPopup = false;
  }

  addStudent() {
    if (this.addForm.valid)
      this.accountService.registerStudent(this.addForm.value).subscribe({
        next: (data: BaseResponse<Student>) => {
          this.students.unshift(data.data);
        },
        error: (data) => {
          alert('can`t add student');
        }
      });
  }

  updateStudent() {
    if (this.editForm.valid) {
      let dto: UpdateStudentDTO = new UpdateStudentDTO(
        this.selectedStudent.id, this.editLogin?.value, this.editFullName?.value, this.editAge?.value);

      this.accountService.updateStudent(dto).subscribe({
        next: (data) => {
          this.selectedStudent.login = this.editLogin?.value;
          this.selectedStudent.fullName = this.editFullName?.value;
          this.selectedStudent.age = this.editAge?.value;
        },
        error: (data) => {
          alert('this login alredy exsists');
        }
      });
    }
  }

  removeStudent(id: string) {
    this.accountService.deleteStudent(id).subscribe({
      next: (data) => {
        for (let i = 0; i < this.students.length; i++) {
          if (this.students[i].id == id)
            this.students.splice(i, 1);
        }
      },
      error: (data) => {
        alert('cant remove this student');
      }
    })
  }

}
