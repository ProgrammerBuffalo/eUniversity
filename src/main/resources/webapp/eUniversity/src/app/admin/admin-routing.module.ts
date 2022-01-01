import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../guards/auth.guard';
import { Role } from '../core/models/role';

const routes: Routes = [
  { path: '', component: undefined, canActivate: [AuthGuard], data: { roles: [Role.Admin] } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
