import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminListComponent } from './admin/admin-list/admin-list.component';
import { AdminEditPopupComponent } from './admin/admin-edit-popup/admin-edit-popup.component';
import { AdminAddPopupComponent } from './admin/admin-add-popup/admin-add-popup.component';
import { TeacherListComponent } from './teacher/teacher-list/teacher-list.component';
import { TeacherAddPopupComponent } from './teacher/teacher-add-popup/teacher-add-popup.component';
import { TeacherEditPopupComponent } from './teacher/teacher-edit-popup/teacher-edit-popup.component';
import { StudentListComponent } from './student/student-list/student-list.component';
import { StudentAddPopupComponent } from './student/student-add-popup/student-add-popup.component';
import { StudentEditPopupComponent } from './student/student-edit-popup/student-edit-popup.component';
import { AsideMenuComponent } from './aside-menu/aside-menu.component';
import { RouterModule } from '@angular/router';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';


@NgModule({
  declarations: [
    AdminListComponent,
    AdminEditPopupComponent,
    AdminAddPopupComponent,
    TeacherListComponent,
    TeacherAddPopupComponent,
    TeacherEditPopupComponent,
    StudentListComponent,
    StudentAddPopupComponent,
    StudentEditPopupComponent,
    AsideMenuComponent,
    AdminPanelComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    RouterModule
  ]
})
export class AdminModule { }
