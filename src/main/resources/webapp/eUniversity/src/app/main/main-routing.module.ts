import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Role } from '../core/models/auth/role';
import { AuthGuard } from '../guards/auth.guard';
import { AsideMenuComponent } from './aside-menu/aside-menu.component';

const routes: Routes = [
  { path: '', component: AsideMenuComponent, canActivate: [AuthGuard], data: { roles: [Role.Student] } },

  { path: 'AsideMenu', component: AsideMenuComponent, canActivate: [AuthGuard], data: { roles: [Role.Student] } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
