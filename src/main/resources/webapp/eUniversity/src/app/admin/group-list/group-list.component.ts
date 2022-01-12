import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Group } from 'src/app/core/models/admin/group';
import { BaseResponse } from 'src/app/core/models/base/base-response';
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
    this.groupService.getAllGroups().subscribe((res: BaseResponse<Group[]>) => {
      this.groups = res.data;
    });
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
    if (this.addForm.valid)
      this.groupService.addGroup(this.addForm.value).subscribe({
        next: (res) => {
          let group: Group = new Group(res.data, this.addName?.value, new Date());
          this.groups.unshift(group);
        },
        error: (res) => {
          alert('group with this name already exsists');
        }
      })
  }

  updateGroup() {
    if (this.editForm.valid)
      this.groupService.updateGroup(this.selectedGroup.id, this.editName?.value).subscribe({
        next: (res) => {
          this.selectedGroup.name = this.editName?.value;
        },
        error: (res) => {
          alert('group with this name already exsists');
        }
      });
  }

  removeGroup(id: number) {
    this.groupService.removeGroup(id).subscribe({
      next: (res) => {
        for (let i = 0; i < this.groups.length; i++) {
          if (this.groups[i].id == id)
            this.groups.splice(i, 1);
        }
      },
      error: (res) => {
        alert('can`t remove group it has dependencies');
      }
    })
  }

}
