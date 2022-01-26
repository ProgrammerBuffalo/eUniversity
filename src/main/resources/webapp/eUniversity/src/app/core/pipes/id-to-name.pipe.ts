import { Input, OnInit, Pipe, PipeTransform } from '@angular/core';
import { DDL } from '../models/ddl';

@Pipe({
  name: 'idToName'
})
export class IdToNamePipe implements PipeTransform {

  constructor() {
  }

  transform(value: number, array: DDL<number>[]): unknown {
    //console.log(value);
    //console.log(array);
    for (let i = 0; i < array.length; i++) {
      if (array[i].id = value) {
        return array[i].name;
        break;
      }
    }
    return '';
  }

}
