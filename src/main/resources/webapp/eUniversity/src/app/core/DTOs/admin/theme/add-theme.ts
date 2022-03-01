export class AddThemeDTO {
  title: string;
  order: number;
  description: string;

  constructor(title: string, order: number, description: string) {
    this.title = title;
    this.order = order;
    this.description = description;
  }
}
