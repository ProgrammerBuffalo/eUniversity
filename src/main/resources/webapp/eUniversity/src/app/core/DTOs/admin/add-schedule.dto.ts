export class AddScheduleDTO {
  groupId: number;
  disciplineId: number;
  teacherId: number;
  weekId: number;
  timeFrom: Date;
  timeTo: Date;

  constructor(groupId: number, disciplineId: number, teacherId: number, weekId: number, timeFrom: Date, timeTo: Date) {
    this.groupId = groupId;
    this.disciplineId = disciplineId;
    this.teacherId = teacherId;
    this.weekId = weekId;
    this.timeFrom = timeFrom;
    this.timeTo = timeTo;
  }
}
