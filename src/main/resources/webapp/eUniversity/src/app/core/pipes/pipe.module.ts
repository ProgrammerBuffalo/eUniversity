import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DateToSemestrPipe } from './date-to-semestr.pipe';
import { WeekPipe } from './week.pipe';
import { IdToNamePipe } from './id-to-name.pipe';


@NgModule({
  declarations: [
    DateToSemestrPipe,
    WeekPipe,
    IdToNamePipe
  ],
  imports: [
    CommonModule
  ],
  exports: [
    DateToSemestrPipe,
    WeekPipe,
    IdToNamePipe
  ]
})
export class PipeModule { }
