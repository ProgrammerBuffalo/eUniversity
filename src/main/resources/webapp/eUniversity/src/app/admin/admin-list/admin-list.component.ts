import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UpdateAdminDTO } from 'src/app/core/DTOs/admin/update-admin-dto';
import { Admin } from 'src/app/core/models/admin/admin';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { AccountService } from 'src/app/services/accounts.service';

@Component({
  selector: 'app-admin-list',
  templateUrl: './admin-list.component.html',
  styleUrls: ['../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class AdminListComponent implements OnInit {

  showAddPopup: boolean;
  showEditPopup: boolean;

  selectedAdmin!: Admin;
  admins: Admin[];

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

    this.admins = [];
  }

  ngOnInit(): void {
    this.accountService.getAdmins().subscribe((data: BaseResponse<Admin[]>) => {
      this.admins = data.data;
    });
  }

  showAddModal() {
    this.showAddPopup = true;

    this.editForm.get('login')!.setValue('');
    this.editForm.get('password')!.setValue('');
    this.editForm.get('fullName')!.setValue('');
    this.editForm.get('age')!.setValue('');
  }

  showEditModal(admin: Admin) {
    this.showEditPopup = true;
    this.selectedAdmin = admin;

    this.editForm.get('login')!.setValue(admin.login);
    this.editForm.get('fullName')!.setValue(admin.fullName);
    this.editForm.get('age')!.setValue(admin.age);
  }

  closeEditModal() {
    this.showEditPopup = false;
  }

  closeAddModal() {
    this.showAddPopup = false;
  }

  addAdmin() {
    if (this.addForm.valid)
      this.accountService.registerAdmin(this.addForm.value).subscribe({
        next: (data: BaseResponse<Admin>) => {
          this.admins.unshift(data.data);
          this.showAddPopup = false;
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

          this.showEditPopup = false;
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
        for (let i = 0; i < this.admins.length; i++) {
          if (this.admins[i].accountId == id)
            this.admins.splice(i, 1);
        }
      },
      error: (data) => {
        alert('cant remove this admin');
      }
    })
  }
}
