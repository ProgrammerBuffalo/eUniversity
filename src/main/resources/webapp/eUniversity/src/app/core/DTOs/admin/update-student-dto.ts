export class UpdateStudentDTO {
  accountId: string;
  fullName: string;
  login: string;
  age: number;

  constructor(accountId: string, fullName: string, login: string, age: number) {
    this.accountId = accountId;
    this.fullName = fullName;
    this.login = login;
    this.age = age;
  }
}
