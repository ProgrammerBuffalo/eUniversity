import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../guards/auth.guard';
import { Role } from '../core/models/role';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { AdminListComponent } from './admin/admin-list/admin-list.component';
import { StudentListComponent } from './student/student-list/student-list.component';
import { AsideMenuComponent } from '../main/aside-menu/aside-menu.component';
import { TeacherListComponent } from './teacher/teacher-list/teacher-list.component';

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
