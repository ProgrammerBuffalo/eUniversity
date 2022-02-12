import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { GradeDirective } from './grade.directive';


@NgModule({
  declarations: [
    GradeDirective
  ],
  imports: [
    BrowserModule,
  ],
  exports: [
    GradeDirective
  ]
})
export class DirectiveModule { }
