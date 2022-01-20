export class GroupDiscipline {
  disciplineId: number;
  teacherId: number;
  disciplineName: string;
  teacherName: string;

  constructor(disciplineId: number, teacherId: number, disciplineName: string, teacherName: string) {
    this.disciplineId = disciplineId;
    this.teacherId = teacherId;
    this.disciplineName = disciplineName;
    this.teacherName = teacherName;
  }
}
