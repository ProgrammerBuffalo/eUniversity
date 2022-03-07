export class AddThemeDTO {
  name: string;
  order: number;
  fileName: string;

  constructor(name: string, order: number) {
    this.name = name;
    this.order = order;
    this.fileName = '';
  }
}
