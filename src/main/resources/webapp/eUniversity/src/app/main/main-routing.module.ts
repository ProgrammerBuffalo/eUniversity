import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AsideMenuComponent } from './aside-menu/aside-menu.component';

const routes: Routes = [
  { path: '', component: AsideMenuComponent },
  { path: 'AsideMenu', component: AsideMenuComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
