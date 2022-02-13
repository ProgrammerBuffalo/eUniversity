export class EduMaterial {
  id: number;
  account: string;
  fileId: string;
  fileName: string;
  educationalProcess: number;
  description: string;
  order: number;

  constructor(id: number, account: string, fileId: string, fileName: string, eduProccess: number, description: string, order: number) {
    this.id = id;
    this.account = account
    this.fileId = fileId
    this.fileName = fileName
    this.educationalProcess = eduProccess
    this.description = description
    this.order = order
  }
}
