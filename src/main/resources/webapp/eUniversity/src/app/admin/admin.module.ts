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
import { DateToSemestrPipe } from '../core/pipes/date-to-semestr.pipe';
import { PipeModule } from '../core/pipes/pipe.module';


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
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    RouterModule,
    ReactiveFormsModule,
    FormsModule,
    PipeModule
  ]
})
export class AdminModule { }
