export class Schedule {
  disciplineId: number;
  disciplineName: string;
  scheduleDiscipline: ScheduleDiscipline[];

  constructor(disciplineId: number, disciplineName: string, scheduleDiscipline: ScheduleDiscipline[]) {
    this.disciplineId = disciplineId;
    this.disciplineName = disciplineName;
    this.scheduleDiscipline = scheduleDiscipline;
  }
}

export class ScheduleDiscipline {
  teacherName: string;
  weekId: number;
  timeFrom: Date;
  timeTo: Date;

  constructor(teacherName: string, weekId: number, timeFrom: Date, timeTo: Date) {
    this.teacherName = teacherName;
    this.weekId = weekId;
    this.timeFrom = timeFrom;
    this.timeTo = timeTo;
  }
}
