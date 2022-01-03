import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AsideMenuComponent } from './aside-menu/aside-menu.component';
import { RouterModule } from '@angular/router';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { AdminListComponent } from './admin-list/admin-list.component';
import { TeacherListComponent } from './teacher-list/teacher-list.component';
import { StudentListComponent } from './student-list/student-list.component';


@NgModule({
  declarations: [
    AdminListComponent,
    TeacherListComponent,
    StudentListComponent,
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
