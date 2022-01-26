export class Journal {
  id: number
  discipline: string;
  teacher: string;
  educationalProcess: number;
  date: Date;
  present: boolean;
  progress: number;
  feedback: string;

  constructor(id: number, discipline: string, teacher: string, educationalProcess: number, date: Date, present: boolean, progress: number, feedback: string) {
    this.id = id;
    this.discipline = discipline;
    this.teacher = teacher;
    this.educationalProcess = educationalProcess;
    this.date = date;
    this.present = present;
    this.progress = progress;
    this.feedback = feedback;
  }
}
