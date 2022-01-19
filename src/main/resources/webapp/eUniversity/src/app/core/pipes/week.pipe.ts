import { Pipe, PipeTransform } from '@angular/core';
import { weeks } from '../util/weeks';

@Pipe({
  name: 'week'
})
export class WeekPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    for (let i = 0; i < weeks.length; i++) {
      if(weeks[i].id == i)
        return  weeks[i].name;
    }
    return '';
  }

}
