import { Role } from "./auth/role";

export class User {
  id: string;
  fullName: string;
  role: Role

  constructor(id: string, fullName: string,  role: Role) {
    this.id = id;
    this.fullName = fullName;
    this.role = role;
  }
}

