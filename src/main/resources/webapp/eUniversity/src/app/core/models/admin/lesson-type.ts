export class LessonType {
  id: number;
  name: string;

  constructor(id: number, name: string) {
    this.id = id;
    this.name = name;
  }
}

export let types: LessonType[] = [{ id: 1, name: 'typ1' }, { id: 2, name: 'typ2' }, { id: 3, name: 'typ3' }, { id: 4, name: 'typ4' }]
