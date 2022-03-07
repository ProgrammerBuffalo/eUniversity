import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { AddThemeDTO } from 'src/app/core/DTOs/admin/theme/add-theme';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { DDL } from 'src/app/core/models/ddl';
import { GroupService } from 'src/app/services/group.service';
import { Theme } from 'src/app/core/models/admin/theme';
import { ThemeService } from 'src/app/services/theme.service';
import { UpdateThemeDTO } from 'src/app/core/DTOs/admin/theme/update-theme';
import { refreshSelectPicker } from 'src/app/core/util/select-picker';

@Component({
  selector: 'app-theme-list',
  templateUrl: './theme-list.component.html',
  styleUrls: ['../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class ThemeListComponent implements OnInit {

  groupsDDL: DDL<number>[];
  disciplinesDDL: DDL<number>[];

  groupId: number;
  disciplineId: number;
  selectedThemeId: number;

  isAddFormVisible: boolean;
  isEditFormVisible: boolean;
  isUploadFileFormVisible: boolean;

  themes: Theme[];

  addForm: FormGroup;
  editForm: FormGroup;
  uploadFileForm: FormGroup;

  constructor(
    private groupService: GroupService,
    private themeService: ThemeService
  ) {

    this.isAddFormVisible = false;
    this.isEditFormVisible = false;
    this.isUploadFileFormVisible = false;

    this.groupsDDL = [];
    this.disciplinesDDL = [];

    this.groupId = 0;
    this.disciplineId = 0;
    this.selectedThemeId = 0;

    this.addForm = new FormGroup({
      name: new FormControl('', Validators.required),
      order: new FormControl(Validators.required)
    });

    this.editForm = new FormGroup({
      name: new FormControl('', Validators.required),
      order: new FormControl(Validators.required)
    });

    this.uploadFileForm = new FormGroup({
      groups: new FormControl(0, Validators.required),
      fileName: new FormControl('', Validators.required),
    });

    this.themes = [];
  }

  get AddName() { return this.addForm.get('name'); }

  get AddOrder() { return this.addForm.get('order'); }

  get EditName() { return this.editForm.get('name'); }

  get EditOrder() { return this.editForm.get('order'); }

  ngOnInit(): void {
    this.groupService.getGroupsDDL().subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.groupsDDL = res.data;
      refreshSelectPicker();
    })
  }

  groupChange() {
    this.groupService.getGroupDisciplinesDDL(this.groupId).subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.disciplinesDDL = res.data;
      this.disciplineId = 0;
      refreshSelectPicker();
    })
  }

  disciplineChange() {
    this.themeService.getThemes(this.groupId, this.disciplineId).subscribe((res: BaseResponse<Theme[]>) => {
      this.themes = res.data;
    })
  }

  showAddForm() {
    this.addForm.reset();
    this.isAddFormVisible = true;
  }

  closeAddForm() {
    this.isAddFormVisible = false;
  }

  showEditForm(theme: Theme) {
    this.isEditFormVisible = true;
    this.selectedThemeId = theme.id;
    this.EditName?.setValue(theme.name);
    this.EditOrder?.setValue(theme.order);
  }

  closeEditForm() {
    this.isEditFormVisible = false;
  }

  showUploadFileForm() {
    this.uploadFileForm.reset();
    this.isUploadFileFormVisible = true;
  }

  closeUploadFileForm() {
    this.isUploadFileFormVisible = false;
  }

  handleClick() {
    document.getElementById('upload-file')!.click();
  }

  addAttachment(fileInput: any) {
    const fileName = this.getFiles()![0].name;
    (document.getElementById('file-text')! as HTMLInputElement).value = fileName;
  }

  getFiles() {
    return (document.getElementById('upload-file')! as HTMLInputElement).files;
  }

  uploadFile() {

  }

  addTheme() {
    let dto = new AddThemeDTO(this.AddName?.value, this.AddOrder?.value);
    this.themeService.addTheme(dto).subscribe({
      next: (res: any) => {

      },
      error: (res: any) => {

      }
    })
  }

  updateTheme() {
    let dto = new UpdateThemeDTO(this.selectedThemeId, this.EditName?.value, this.EditOrder?.value);
    this.themeService.updateTheme(dto).subscribe({
      next: (res: any) => {

      },
      error: (res: any) => {

      }
    })
  }

  removeTheme(id: number) {
    this.themeService.removeTheme(id).subscribe({
      next: (res: any) => {

      },
      error: (res: any) => {

      }
    })
  }

}
