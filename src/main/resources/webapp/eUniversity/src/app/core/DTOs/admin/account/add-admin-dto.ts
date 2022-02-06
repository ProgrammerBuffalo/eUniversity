export class AddAdminDTO {
  fullName: string;
  password: string
  login: string;
  age: number;

  constructor(fullName: string, login: string, password: string, age: number) {
    this.login = login;
    this.password = password;
    this.fullName = fullName;
    this.age = age;
  }
}
