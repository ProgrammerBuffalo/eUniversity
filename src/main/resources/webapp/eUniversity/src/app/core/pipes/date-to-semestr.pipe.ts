import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateToSemestr'
})
export class DateToSemestrPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
