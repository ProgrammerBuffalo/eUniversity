import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DateToSemestrPipe } from './date-to-semestr.pipe';


@NgModule({
  declarations: [DateToSemestrPipe],
  imports: [
    CommonModule
  ],
  exports: [
    DateToSemestrPipe
  ]
})
export class PipeModule { }
