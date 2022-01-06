export class UpdateTeacherDTO {
  id: string;
  fullName: string;
  login: string;
  age: number;

  constructor(id: string, fullName: string, login: string, age: number) {
    this.id = id;
    this.fullName = fullName;
    this.login = login;
    this.age = age;
  }
}
