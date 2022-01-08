export class DDL<T>{
  id: T;
  name: string

  constructor(id: T, name: string) {
    this.id = id;
    this.name = name;
  }
}
