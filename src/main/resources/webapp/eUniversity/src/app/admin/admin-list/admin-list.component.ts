import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Student } from 'src/app/core/models/admin/student';
import { AccountService } from 'src/app/services/accounts.service';

@Component({
  selector: 'app-admin-list',
  templateUrl: './admin-list.component.html',
  styleUrls: ['./admin-list.component.scss']
})
export class AdminListComponent implements OnInit {

  showAddPopup: boolean;
  showEditPopup: boolean;

  selectedStudent!: Student;
  students: Student[];

  addForm: FormGroup;
  editForm: FormGroup;

  constructor(
    private accountService: AccountService
  ) {
    this.showAddPopup = false;
    this.showEditPopup = false;

    this.addForm = new FormGroup({
      login: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      fullName: new FormControl('', Validators.required),
      age: new FormControl('', Validators.required)
    });

    this.editForm = new FormGroup({
      login: new FormControl('', Validators.required),
      fullName: new FormControl('', Validators.required),
      age: new FormControl('', Validators.required)
    });

    this.students = [];
  }

  ngOnInit(): void {
    this.accountService.getStudents().subscribe((data: Student[]) => {
      this.students = data;
    })
  }

  showAddModal() {
    this.showAddPopup = true;

    this.editForm.get('login')!.setValue('');
    this.editForm.get('password')!.setValue('');
    this.editForm.get('fullName')!.setValue('');
    this.editForm.get('age')!.setValue('');
  }

  showEditModal(student: Student) {
    this.showEditPopup = true;

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
        next: (data: number) => {

        },
        error: (data) => {
          alert(data);
        }
      });
  }

  updateStudent() {
    if (this.editForm.valid)
      this.accountService.updateStudents(this.editForm.value).subscribe({
        next: data => {

        },
        error: data => {

        }
      });
  }

  removeStudent(id: number) {
    this.accountService.deleteAdmin(1).subscribe({
      next: (data) => {

      },
      error: data => {
        alert('cant remove this student');
      }
    })
  }

}
