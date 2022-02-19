import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AddThemeDTO } from 'src/app/core/DTOs/admin/theme/add-theme';
import { UpdateThemeDTO } from 'src/app/core/DTOs/admin/theme/update-theme';
import { Theme } from 'src/app/core/models/admin/theme';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { DDL } from 'src/app/core/models/ddl';
import { refreshSelectPicker } from 'src/app/core/util/select-picker';
import { GroupService } from 'src/app/services/group.service';
import { ThemeService } from 'src/app/services/theme.service';

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

  themes: Theme[];

  addForm: FormGroup;
  editForm: FormGroup;

  constructor(
    private groupService: GroupService,
    private themeService: ThemeService
  ) {

    this.isAddFormVisible = false;
    this.isEditFormVisible = false;

    this.groupsDDL = [];
    this.disciplinesDDL = [];

    this.groupId = 0;
    this.disciplineId = 0;
    this.selectedThemeId = 0;

    this.addForm = new FormGroup({
      title: new FormControl('', Validators.required),
      order: new FormControl(Validators.required),
      description: new FormControl('')
    });

    this.editForm = new FormGroup({
      title: new FormControl('', Validators.required),
      order: new FormControl(Validators.required),
      description: new FormControl('')
    });

    this.themes = [];
  }

  get AddTitle() { return this.addForm.get('title'); }

  get AddOrder() { return this.addForm.get('order'); }

  get AddDescription() { return this.addForm.get('description'); }

  get EditTitle() { return this.editForm.get('title'); }

  get EditOrder() { return this.editForm.get('order'); }

  get EditDescription() { return this.editForm.get('description'); }

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
    this.EditTitle?.setValue(theme.title);
    this.EditOrder?.setValue(theme.order);
    this.EditDescription?.setValue(theme.description);
  }

  closeEditForm() {
    this.isEditFormVisible = false;
  }

  addTheme() {
    let dto = new AddThemeDTO(this.AddTitle?.value, this.AddOrder?.value, this.AddDescription?.value);
    this.themeService.addTheme(dto).subscribe({
      next: (res: any) => {

      },
      error: (res: any) => {

      }
    })
  }

  updateTheme() {
    let dto = new UpdateThemeDTO(this.selectedThemeId, this.EditTitle?.value, this.EditOrder?.value, this.EditDescription?.value);
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
