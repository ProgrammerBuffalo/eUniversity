export class UpdateAdminDTO {
  id: number;
  fullName: string;
  login: string;
  age: number;

  constructor(id: number, fullName: string, login: string, age: number) {
    this.id = id;
    this.fullName = fullName;
    this.login = login;
    this.age = age;
  }
}
