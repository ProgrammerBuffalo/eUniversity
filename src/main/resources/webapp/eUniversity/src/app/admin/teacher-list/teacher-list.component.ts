import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UpdateTeacherDTO } from 'src/app/core/DTOs/admin/account/update-teacher-dto';
import { PaginationDTO } from 'src/app/core/DTOs/pagination-dto';
import { Teacher } from 'src/app/core/models/admin/account/teacher';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { PaginatedList } from 'src/app/core/models/paginated-list';
import { AccountService } from 'src/app/services/accounts.service';

@Component({
  selector: 'app-teacher-list',
  templateUrl: './teacher-list.component.html',
  styleUrls: ['../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class TeacherListComponent implements OnInit {

  isAddFormVisible: boolean;
  isEditFormVisible: boolean;

  teachers: PaginatedList<Teacher>;
  selectedTeacher!: Teacher;


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

    this.teachers = new PaginatedList([], 0);
    this.pageCount = 0;
    this.paginationDTO = new PaginationDTO(0, 12, '');
  }

  ngOnInit(): void {
    this.getTeachers();
  }

  showAddForm() {
    this.isAddFormVisible = true;
    this.addForm.reset();
  }

  showEditForm(teacher: Teacher) {
    this.isEditFormVisible = true;
    this.selectedTeacher = teacher;

    this.editForm.get('login')!.setValue(teacher.login);
    this.editForm.get('fullName')!.setValue(teacher.fullName);
    this.editForm.get('age')!.setValue(teacher.age);
  }

  closeEditForm() {
    this.isEditFormVisible = false;
  }

  closeAddForm() {
    this.isAddFormVisible = false;
  }

  addTeacher() {
    if (this.addForm.valid)
      this.accountService.registerTeacher(this.addForm.value).subscribe({
        next: (data: BaseResponse<Teacher>) => {
          this.getTeachers();

          this.isAddFormVisible = false;
        },
        error: (data) => {
          alert('can`t add teacher');
        }
      });
  }

  updateTeacher() {
    if (this.editForm.valid) {
      let dto: UpdateTeacherDTO = new UpdateTeacherDTO(
        this.selectedTeacher.accountId, this.editLogin?.value, this.editFullName?.value, this.editAge?.value);

      this.accountService.updateTeacher(dto).subscribe({
        next: (data) => {
          this.selectedTeacher.login = this.editLogin?.value;
          this.selectedTeacher.fullName = this.editFullName?.value;
          this.selectedTeacher.age = this.editAge?.value;

          this.isEditFormVisible = false;
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
        this.getTeachers();
      },
      error: (data) => {
        alert('cant remove this teacher');
      }
    })
  }

  onPageChanged(pageIndex: number) {
    this.paginationDTO.pageIndex = pageIndex;
    this.getTeachers();
  }

  searchTeachers(searchText: string) {
    this.paginationDTO.search = searchText;
    this.getTeachers();
  }

  getTeachers() {
    this.accountService.getTeachers(this.paginationDTO).subscribe((res: BaseResponse<PaginatedList<Teacher>>) => {
      this.teachers = res.data;
    });
  }

}
