import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UpdateGroupDTO } from 'src/app/core/DTOs/admin/group/updae-group';
import { PaginationDTO } from 'src/app/core/DTOs/pagination-dto';
import { Group } from 'src/app/core/models/admin/group';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { PaginatedList } from 'src/app/core/models/paginated-list';
import { GroupService } from 'src/app/services/group.service';

@Component({
  selector: 'app-group-list',
  templateUrl: './group-list.component.html',
  styleUrls: ['../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class GroupListComponent implements OnInit {

  isAddFormVisible: boolean;
  isEditFormVisible: boolean;

  selectedGroup!: Group;
  groups: PaginatedList<Group>;

  paginationDTO: PaginationDTO;
  pageCount: number;

  addForm: FormGroup;
  editForm: FormGroup;

  get addName() { return this.addForm.get('name'); }

  get editName() { return this.editForm.get('name'); }

  constructor(
    private groupService: GroupService
  ) {
    this.isAddFormVisible = false;
    this.isEditFormVisible = false;

    this.addForm = new FormGroup({
      name: new FormControl('', Validators.required),
    });

    this.editForm = new FormGroup({
      name: new FormControl('', Validators.required),
    });

    this.groups = new PaginatedList([], 0);
    this.pageCount = 0;
    this.paginationDTO = new PaginationDTO(0, 12, '');
  }

  ngOnInit(): void {
    this.getGroups();
  }

  getGroups() {
    this.groupService.getAllGroups(this.paginationDTO).subscribe((res: BaseResponse<PaginatedList<Group>>) => {
      this.groups = res.data;
    });
  }

  onPageChanged(pageIndex: number) {
    this.paginationDTO.pageIndex = pageIndex;
    this.getGroups();
  }

  searchGroups(searchText: string) {
    this.paginationDTO.search = searchText;
    this.getGroups();
  }

  showAddForm() {
    this.isAddFormVisible = true;

    this.editName?.setValue('');
  }

  showEditForm(group: Group) {
    this.isEditFormVisible = true;
    this.selectedGroup = group;

    this.editName?.setValue(group.name);
  }

  closeEditForm() {
    this.isEditFormVisible = false;
  }

  closeAddForm() {
    this.isAddFormVisible = false;
  }

  addGroup() {
    if (this.addForm.valid)
      this.groupService.addGroup(this.addForm.value).subscribe({
        next: () => {
          this.getGroups();
        },
        error: () => {
          alert('group with this name already exsists');
        }
      })
  }

  updateGroup() {
    if (this.editForm.valid) {
      let dto: UpdateGroupDTO = new UpdateGroupDTO(this.selectedGroup.id, this.editName?.value);
      this.groupService.updateGroup(dto).subscribe({
        next: (res) => {
          this.selectedGroup.name = this.editName?.value;
        },
        error: (res) => {
          alert('group with this name already exsists');
        }
      });
    }
  }

  removeGroup(id: number) {
    this.groupService.removeGroup(id).subscribe({
      next: () => {
        this.getGroups();
      },
      error: () => {
        alert('can`t remove group it has dependencies');
      }
    })
  }

}
