import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../guards/auth.guard';
import { Role } from '../core/models/role';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { TeacherListComponent } from './teacher-list/teacher-list.component';
import { AdminListComponent } from './admin-list/admin-list.component';
import { StudentListComponent } from './student-list/student-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'Panel', pathMatch: 'full' },

  {
    path: 'Panel',
    component: AdminPanelComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.Admin] },
    children: [
      { path: '', component: StudentListComponent },

      { path: 'student-list', component: StudentListComponent },

      { path: 'teacher-list', component: TeacherListComponent },

      { path: 'admin-list', component: AdminListComponent }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
