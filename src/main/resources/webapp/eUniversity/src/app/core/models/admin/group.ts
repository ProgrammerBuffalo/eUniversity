import { DateToSemestrPipe } from "../../pipes/date-to-semestr.pipe";

export class Group {
  id: number;
  name: string;
  date: Date;

  constructor(id: number, name: string, creationData: Date) {
    this.id = id;
    this.name = name;
    this.date = creationData;
  }
}
