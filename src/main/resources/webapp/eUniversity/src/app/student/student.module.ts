import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StudentRoutingModule } from './student-routing.module';
import { TempComponent } from './temp/temp.component';
import { DirectiveModule } from '../core/directives/directive.module';


@NgModule({
  declarations: [
    TempComponent
  ],
  imports: [
    CommonModule,
    StudentRoutingModule,
    DirectiveModule
  ]
})
export class StudentModule { }
