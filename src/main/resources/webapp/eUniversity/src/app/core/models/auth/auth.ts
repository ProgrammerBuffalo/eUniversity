import { Role } from "./role";

export class Auth {
  id: string;

  fullName: string;

  role: Role;

  jwtToken: string;

  refreshToken: string;

  constructor(id: string, fullName: string, role: Role, jwtToken: string, refreshToken: string) {
    this.id = id;
    this.fullName = fullName;
    this.role = role;
    this.jwtToken = jwtToken;
    this.refreshToken = refreshToken;
  }
}
