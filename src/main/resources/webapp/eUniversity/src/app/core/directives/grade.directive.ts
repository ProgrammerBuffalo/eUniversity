import { Directive, ElementRef, Input, OnInit } from '@angular/core';
import { GrandeColor } from '../util/grade-color'

@Directive({
  selector: '[appGrade]'
})
export class GradeDirective implements OnInit {

  @Input('appGrade') grade: number;
  @Input() maxValue: number;

  constructor(private el: ElementRef) {
    this.grade = 0;
    this.maxValue = 0;
  }

  ngOnInit(): void {
    let temp: number = this.maxValue / 6;

    if (temp * 1 >= this.grade)
      this.el.nativeElement.style.color = GrandeColor.VeryBad;
    else if (temp * 2 >= this.grade)
      this.el.nativeElement.style.color = GrandeColor.Bad;
    else if (temp * 3 >= this.grade)
      this.el.nativeElement.style.color = GrandeColor.Noramal;
    else if (temp * 4 >= this.grade)
      this.el.nativeElement.style.color = GrandeColor.Good;
    else if (temp * 5 >= this.grade)
      this.el.nativeElement.style.color = GrandeColor.VeryGood;
    else if (temp * 6 >= this.grade)
      this.el.nativeElement.style.color = GrandeColor.Excellent;
  }
}
