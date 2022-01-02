import { Role } from "./role";

export class User {
  id: number;
  fullName: string;
  token: string;
  role: Role

  constructor(id: number, fullName: string, token: string, role: Role) {
    this.id = id;
    this.fullName = fullName;
    this.token = token;
    this.role = role;
  }
}
