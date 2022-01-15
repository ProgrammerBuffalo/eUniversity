export class Student {
  accountId: string;
  userId: number;
  fullName: string;
  login: string;
  age: number;

  constructor(accountId: string, userId: number, fullName: string, login: string, age: number) {
    this.accountId = accountId;
    this.userId = userId;
    this.fullName = fullName;
    this.login = login;
    this.age = age;
  }
}
