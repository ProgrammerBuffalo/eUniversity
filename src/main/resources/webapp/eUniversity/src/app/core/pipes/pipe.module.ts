import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DateToSemestrPipe } from './date-to-semestr.pipe';
import { WeekPipe } from './week.pipe';


@NgModule({
  declarations: [
    DateToSemestrPipe,
    WeekPipe
  ],
  imports: [
    CommonModule
  ],
  exports: [
    DateToSemestrPipe,
    WeekPipe
  ]
})
export class PipeModule { }
