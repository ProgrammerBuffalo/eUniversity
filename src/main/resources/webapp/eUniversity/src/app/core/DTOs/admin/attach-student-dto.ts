export class AttachStudentDTO {
  groupId: number;
  studentId: number;

  constructor(groupId: number, studentId: number) {
    this.groupId = groupId;
    this.studentId = studentId;
  }
}
