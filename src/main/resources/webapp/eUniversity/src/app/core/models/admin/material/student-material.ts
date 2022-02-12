export class StudentMaterial {
  id: number;
  fileId: string;
  fileName: string;
  eduProccess: number;
  description: string;
  order: number;

  constructor(id: number, fileId: string, fileName: string, eduProccess: number, description: string, order: number) {
    this.id = id;
    this.fileId = fileId
    this.fileName = fileName
    this.eduProccess = eduProccess
    this.description = description
    this.order = order
  }
}
