import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Page404Component } from './page404/page404.component';
import { Page500Component } from './page500/page500.component';
import { PaginationComponent } from './pagination/pagination.component';
import { SearchInputComponent } from './search-input/search-input.component';


@NgModule({
  declarations: [
    PaginationComponent,
    Page404Component,
    Page500Component,
    SearchInputComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports: [
    PaginationComponent,
    SearchInputComponent
  ],
  providers: [],
})
export class SharedModule { }
