export class AttachScheduleDTO {
  groupId: number;
  disciplineId: number;
  teacherId: number;
  weekNum: number;
  educationalProcessId: number;
  from: Date;
  to: Date;

  constructor(groupId: number, disciplineId: number, teacherId: number, weekNum: number, educationalProcessId: number, from: Date, to: Date) {
    this.groupId = groupId;
    this.disciplineId = disciplineId;
    this.teacherId = teacherId;
    this.weekNum = weekNum;
    this.educationalProcessId = educationalProcessId;
    this.from = from;
    this.to = to;
  }
}
