import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'semestr'
})
export class DateToSemestrPipe implements PipeTransform {

  transform(value: Date, ...args: unknown[]): number {
    let smt: number = 1;
    let temp: Date = new Date(value);
    let now: Date = new Date();

    while (true) {
      temp.setMonth(temp.getMonth() + 6);
      if (temp > now)
        break;
      else
        smt++;
    }
    return smt;
  }

}
