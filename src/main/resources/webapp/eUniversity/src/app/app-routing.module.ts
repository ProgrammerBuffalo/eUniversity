import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Page404Component } from './shared/page404/page404.component';

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)
  },
  {
    path: 'Auth',
    loadChildren: () => import('./main/main.module').then(m => m.MainModule)
  },
  {
    path: 'Admin',
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)
  },
  {
    path: 'Student',
    loadChildren: () => import('./student/student.module').then(m => m.StudentModule)
  },
  { path: 'page-404', component: Page404Component },
  { path: 'page-505', component: Page404Component },
  { path: '**', component: Page404Component }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
