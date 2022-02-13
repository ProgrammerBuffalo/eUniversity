import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../core/guards/auth.guard';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { TeacherListComponent } from './teacher-list/teacher-list.component';
import { AdminListComponent } from './admin-list/admin-list.component';
import { StudentListComponent } from './student-list/student-list.component';
import { Role } from '../core/models/auth/role';
import { DisciplineListComponent } from './discipline-list/discipline-list.component';
import { TeacherDisciplineListComponent } from './teacher-discipline-list/teacher-discipline-list.component';
import { GroupListComponent } from './group-list/group-list.component';
import { GroupStudentListComponent } from './group-student-list/group-student-list.component';
import { GroupDisciplineListComponent } from './group-discipline-list/group-discipline-list.component';
import { ScheduleListComponent } from './schedule-list/schedule-list.component';
import { HomeComponent } from './home/home.component';
import { ScheduleExamListComponent } from './schedule-exam-list/schedule-exam-list.component';
import { JournalListComponent } from './journal-list/journal-list.component';
import { MaterialEduListComponent } from './material-edu-list/material-edu-list.component';
import { MaterialStudentListComponent } from './material-student-list/material-student-list.component';

const routes: Routes = [
  {
    path: '',
    component: AdminPanelComponent,
    // canActivate: [AuthGuard],
    // data: { roles: [Role.Admin] },
    children: [
      { path: '', component: HomeComponent },

      { path: 'home', component: HomeComponent },

      { path: 'student-list', component: StudentListComponent },

      { path: 'teacher-list', component: TeacherListComponent },

      { path: 'admin-list', component: AdminListComponent },

      { path: 'discipline-list', component: DisciplineListComponent },

      { path: 'teacher-discipline-list', component: TeacherDisciplineListComponent },

      { path: 'group-list', component: GroupListComponent },

      { path: 'group-student-list', component: GroupStudentListComponent },

      { path: 'group-discipline-list', component: GroupDisciplineListComponent },

      { path: 'schedule-schedule-list', component: ScheduleListComponent },

      { path: 'schedule-exam-list', component: ScheduleExamListComponent },

      { path: 'journal-list', component: JournalListComponent },

      { path: 'material-education-list', component: MaterialEduListComponent },

      { path: 'material-student-list', component: MaterialStudentListComponent }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
