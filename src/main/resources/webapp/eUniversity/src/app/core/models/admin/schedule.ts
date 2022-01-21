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
  scheduleId: number;
  teacherId: number;
  teacherName: string;
  weekNum: number;
  from: Date;
  to: Date;
  type: string;

  constructor(scheduleId: number, teacherId: number, teacherName: string, weekNum: number, from: Date, to: Date, type: string) {
    this.scheduleId = scheduleId;
    this.teacherId = teacherId;
    this.teacherName = teacherName;
    this.weekNum = weekNum;
    this.from = from;
    this.to = to;
    this.type = type;
  }
}
