export class AddDisciplineDTO {
  name: string;
  shortName: string;

  constructor(name: string, shortName: string) {
    this.name = name;
    this.shortName = shortName;
  }
}
