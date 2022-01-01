import { Role } from "../models/role";

export class RegDTO {
  login: string
  password: string
  fullName: string
  role: Role

  constructor(login: string, password: string, fullName: string, role: Role) {
    this.login = login;
    this.password = password;
    this.fullName = fullName;
    this.role = role;
  }
}
