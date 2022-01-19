import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateToSemestr'
})
export class DateToSemestrPipe implements PipeTransform {

  transform(value: Date, ...args: unknown[]): number {
    console.log(value);
    return 1;
  }

}
