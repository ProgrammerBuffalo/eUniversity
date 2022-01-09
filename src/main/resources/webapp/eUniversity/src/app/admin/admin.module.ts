import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


import { AdminRoutingModule } from './admin-routing.module';
import { AsideMenuComponent } from './aside-menu/aside-menu.component';
import { RouterModule } from '@angular/router';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { AdminListComponent } from './admin-list/admin-list.component';
import { TeacherListComponent } from './teacher-list/teacher-list.component';
import { StudentListComponent } from './student-list/student-list.component';
import { DisciplineListComponent } from './discipline-list/discipline-list.component';
import { TeacherDisciplineListComponent } from './teacher-discipline-list/teacher-discipline-list.component';


@NgModule({
  declarations: [
    AdminListComponent,
    TeacherListComponent,
    StudentListComponent,
    AsideMenuComponent,
    AdminPanelComponent,
    DisciplineListComponent,
    TeacherDisciplineListComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    RouterModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class AdminModule { }
