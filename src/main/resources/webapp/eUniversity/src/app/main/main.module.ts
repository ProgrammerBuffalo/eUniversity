import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MainRoutingModule } from './main-routing.module';
import { AsideMenuComponent } from './aside-menu/aside-menu.component';


@NgModule({
  declarations: [
    AsideMenuComponent
  ],
  imports: [
    CommonModule,
    MainRoutingModule
  ]
})
export class MainModule { }
