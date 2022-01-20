import { getTranslationDeclStmts } from "@angular/compiler/src/render3/view/template";

export class Student {
  accountId: string;
  userId: number;
  fullName: string;
  login: string;
  age: number;
  group: string;

  constructor(accountId: string, userId: number, fullName: string, login: string, age: number, group: string) {
    this.accountId = accountId;
    this.userId = userId;
    this.fullName = fullName;
    this.login = login;
    this.age = age;
    this.group = group;
  }
}
