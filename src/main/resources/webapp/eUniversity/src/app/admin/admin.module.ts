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
import { GroupListComponent } from './group-list/group-list.component';
import { GroupStudentListComponent } from './group-student-list/group-student-list.component';
import { GroupDisciplineListComponent } from './group-discipline-list/group-discipline-list.component';
import { PipeModule } from '../core/pipes/pipe.module';
import { ScheduleListComponent } from './schedule-list/schedule-list.component';
import { HomeComponent } from './home/home.component';
import { ScheduleExamListComponent } from './schedule-exam-list/schedule-exam-list.component';
import { JournalListComponent } from './journal-list/journal-list.component';
import { SharedModule } from '../shared/shared.module';
import { MaterialEduListComponent } from './material-edu-list/material-edu-list.component';
import { MaterialStudentListComponent } from './material-student-list/material-student-list.component';
import { ThemeListComponent } from './theme-list/theme-list.component';


@NgModule({
  declarations: [
    AdminListComponent,
    TeacherListComponent,
    StudentListComponent,
    AsideMenuComponent,
    AdminPanelComponent,
    DisciplineListComponent,
    TeacherDisciplineListComponent,
    GroupListComponent,
    GroupStudentListComponent,
    GroupDisciplineListComponent,
    ScheduleListComponent,
    HomeComponent,
    ScheduleExamListComponent,
    JournalListComponent,
    MaterialEduListComponent,
    MaterialStudentListComponent,
    ThemeListComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    RouterModule,
    ReactiveFormsModule,
    FormsModule,
    PipeModule,
    SharedModule
  ]
})
export class AdminModule { }
