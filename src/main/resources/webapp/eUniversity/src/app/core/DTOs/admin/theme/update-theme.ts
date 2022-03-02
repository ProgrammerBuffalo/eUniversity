export class UpdateThemeDTO {
  id: number;
  title: string;
  order: number;
  description: string;

  constructor(id: number, title: string, order: number, description: string) {
    this.id = id;
    this.title = title;
    this.order = order;
    this.description = description;
  }
}
