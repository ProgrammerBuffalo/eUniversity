import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UpdateStudentDTO } from 'src/app/core/DTOs/admin/account/update-student-dto';
import { PaginationDTO } from 'src/app/core/DTOs/pagination-dto';
import { Student } from 'src/app/core/models/admin/account/student';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { PaginatedList } from 'src/app/core/models/paginated-list';
import { AccountService } from 'src/app/services/accounts.service';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class StudentListComponent implements OnInit {
  isAddFormVisible: boolean;
  isEditFormVisible: boolean;

  selectedStudent!: Student;
  students: PaginatedList<Student>;

  paginationDTO: PaginationDTO;
  pageCount: number;

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
    this.isAddFormVisible = false;
    this.isEditFormVisible = false;

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

    this.students = new PaginatedList([], 0);
    this.pageCount = 0;
    this.paginationDTO = new PaginationDTO(0, 12, '');
  }

  ngOnInit(): void {
    this.getStudents();
  }

  showAddForm() {
    this.isAddFormVisible = true;
    this.addForm.reset();
  }

  showEditForm(student: Student) {
    this.isEditFormVisible = true;
    this.selectedStudent = student;

    this.editForm.get('login')!.setValue(student.login);
    this.editForm.get('fullName')!.setValue(student.fullName);
    this.editForm.get('age')!.setValue(student.age);
  }

  closeEditForm() {
    this.isEditFormVisible = false;
  }

  closeAddForm() {
    this.isAddFormVisible = false;
  }

  addStudent() {
    if (this.addForm.valid)
      this.accountService.registerStudent(this.addForm.value).subscribe({
        next: (data: BaseResponse<Student>) => {
          //this.students.unshift(data.data);
          this.getStudents();

          this.isAddFormVisible = false;
        },
        error: (data) => {
          alert('can`t add student');
        }
      });
  }

  updateStudent() {
    if (this.editForm.valid) {
      let dto: UpdateStudentDTO = new UpdateStudentDTO(
        this.selectedStudent.accountId, this.editLogin?.value, this.editFullName?.value, this.editAge?.value);

      this.accountService.updateStudent(dto).subscribe({
        next: (data) => {
          this.selectedStudent.login = this.editLogin?.value;
          this.selectedStudent.fullName = this.editFullName?.value;
          this.selectedStudent.age = this.editAge?.value;

          this.isEditFormVisible = false;
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
        this.getStudents();

        // for (let i = 0; i < this.students.length; i++) {
        //   if (this.students[i].accountId == id) {
        //     this.students.splice(i, 1);
        //     break;
        //   }
        // }
      },
      error: (data) => {
        alert('cant remove this student');
      }
    })
  }

  searchStudents(searchText: string) {
    this.paginationDTO.search = searchText;
    this.getStudents();
  }

  onPageChanged(pageIndex: number) {
    this.paginationDTO.pageIndex = pageIndex;
    this.getStudents();
  }

  getStudents() {
    this.accountService.getStudents(this.paginationDTO).subscribe((res: BaseResponse<PaginatedList<Student>>) => {
      this.students = res.data;
    });
  }

}
