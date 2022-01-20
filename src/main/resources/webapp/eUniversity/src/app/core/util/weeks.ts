export class WeekDay {
  id: number;
  name: string;

  constructor(id: number, name: string) {
    this.id = id;
    this.name = name;
  }
}

export var weeks: WeekDay[] = [
  { id: 1, name: 'Monday' },
  { id: 1, name: 'Tuesday ' },
  { id: 1, name: 'Wednesday ' },
  { id: 1, name: 'Thursday ' },
  { id: 1, name: 'Friday ' },
  { id: 1, name: 'Saturday ' },
  { id: 1, name: 'Sunday ' }
];

