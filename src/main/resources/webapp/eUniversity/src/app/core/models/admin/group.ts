import { DateToSemestrPipe } from "../../pipes/date-to-semestr.pipe";

export class Group {
  id: number;
  name: string;
  creationDate: Date;
  semester: number;

  constructor(id: number, name: string, creationData: Date) {
    this.id = id;
    this.name = name;
    this.creationDate = creationData;
    let pipe: DateToSemestrPipe = new DateToSemestrPipe();
    this.semester = pipe.transform(this.creationDate);
  }
}
