import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { Page404Component } from './page404/page404.component';
import { Page500Component } from './page500/page500.component';
import { PaginationComponent } from './pagination/pagination.component';


@NgModule({
  declarations: [
    PaginationComponent,
    Page404Component,
    Page500Component
  ],
  imports: [
    CommonModule
  ],
  exports: [
    PaginationComponent
  ],
  providers: [],
})
export class SharedModule { }
