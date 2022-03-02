import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UpdateAdminDTO } from 'src/app/core/DTOs/admin/account/update-admin-dto';
import { PaginationDTO } from 'src/app/core/DTOs/pagination-dto';
import { Admin } from 'src/app/core/models/admin/account/admin';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { PaginatedList } from 'src/app/core/models/paginated-list';
import { AccountService } from 'src/app/services/accounts.service';

@Component({
  selector: 'app-admin-list',
  templateUrl: './admin-list.component.html',
  styleUrls: ['../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class AdminListComponent implements OnInit {

  isAddFormVisible: boolean;
  isEditFormVisible: boolean;

  selectedAdmin!: Admin;
  admins: PaginatedList<Admin>;

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

    this.admins = new PaginatedList([], 0);
    this.pageCount = 0;
    this.paginationDTO = new PaginationDTO(0, 12, '');
  }

  ngOnInit(): void {
    this.getAdmins();
  }

  showAddForm() {
    this.isAddFormVisible = true;
    this.addForm.reset();
  }

  showEditForm(admin: Admin) {
    this.isEditFormVisible = true;
    this.selectedAdmin = admin;

    this.editForm.get('login')!.setValue(admin.login);
    this.editForm.get('fullName')!.setValue(admin.fullName);
    this.editForm.get('age')!.setValue(admin.age);
  }

  closeEditForm() {
    this.isEditFormVisible = false;
  }

  closeAddForm() {
    this.isAddFormVisible = false;
  }

  addAdmin() {
    if (this.addForm.valid)
      this.accountService.registerAdmin(this.addForm.value).subscribe({
        next: (data: BaseResponse<Admin>) => {
          this.getAdmins();

          this.isAddFormVisible = false;
        },
        error: (data) => {
          alert('can`t add admin');
        }
      });
  }

  updateAdmin() {
    if (this.editForm.valid) {
      let dto: UpdateAdminDTO = new UpdateAdminDTO(
        this.selectedAdmin.accountId, this.editLogin?.value, this.editFullName?.value, this.editAge?.value);

      this.accountService.updateAdmin(dto).subscribe({
        next: (data) => {
          this.selectedAdmin.login = this.editLogin?.value;
          this.selectedAdmin.fullName = this.editFullName?.value;
          this.selectedAdmin.age = this.editAge?.value;

          this.isEditFormVisible = false;
        },
        error: (data) => {
          alert('this login alredy exsists');
        }
      });
    }
  }

  removeAdmin(id: string) {
    this.accountService.deleteAdmin(id).subscribe({
      next: (data) => {
        this.getAdmins();
      },
      error: (data) => {
        alert('cant remove this admin');
      }
    })
  }

  onPageChanged(pageIndex: number) {
    this.paginationDTO.pageIndex = pageIndex;
    this.getAdmins();
  }

  searchAdmins(searchText: string) {
    this.paginationDTO.search = searchText;
    this.getAdmins();
  }

  getAdmins() {
    this.accountService.getAdmins(this.paginationDTO).subscribe((res: BaseResponse<PaginatedList<Admin>>) => {
      this.admins = res.data;
    });
  }
}
