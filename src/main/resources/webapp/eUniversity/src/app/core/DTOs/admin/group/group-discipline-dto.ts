export class GroupDisciplineDTO {
  groupId: number;
  disciplineId: number;
  teacherId: number;

  constructor(groupId: number, disciplineId: number, teacherId: number) {
    this.groupId = groupId;
    this.disciplineId = disciplineId;
    this.teacherId = teacherId;
  }
}
