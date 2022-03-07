export class Theme {
  id: number;
  name: string;
  order: number;
  fileName: string;

  constructor(id: number, name: string, order: number, fileName: string) {
    this.id = id;
    this.name = name;
    this.order = order;
    this.fileName = fileName;
  }
}
