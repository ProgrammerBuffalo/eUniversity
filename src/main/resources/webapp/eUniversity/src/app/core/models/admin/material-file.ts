export class MaterialFile {
  id: number;
  userFullName: string;
  fileId: string;
  fileName: string;
  educationalProcess: number;
  description: string;
  order: number;

  constructor(id: number, account: string, fileId: string, fileName: string, eduProccess: number, description: string, order: number) {
    this.id = id;
    this.userFullName = account
    this.fileId = fileId
    this.fileName = fileName
    this.educationalProcess = eduProccess
    this.description = description
    this.order = order
  }
}
