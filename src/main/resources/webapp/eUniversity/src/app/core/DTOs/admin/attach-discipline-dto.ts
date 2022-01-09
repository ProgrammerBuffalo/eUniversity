export class AttachDisciplineDTO {
  teacherId: number;
  disciplineId: number;

  constructor(teacherId: number, disciplineId: number) {
    this.teacherId = teacherId;
    this.disciplineId = disciplineId;
  }
}
