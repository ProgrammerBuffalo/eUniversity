import { Directive, Input } from '@angular/core';

@Directive({
  selector: '[appGrade]'
})
export class GradeDirective {

  @Input('appGrade') grade: number;
  @Input() maxValue: number;

  constructor() {
    this.grade = 0;
    this.maxValue = 0;
  }

}
