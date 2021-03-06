import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PrepareApi } from './prepare-api';
import { Observable } from 'rxjs';
import { LoginDTO } from '../core/DTOs/login-dto';
import { User } from '../core/models/user';
import { BaseResponse } from '../core/models/base/base-response';
import { Auth } from '../core/models/auth/auth';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly controllerName: string = 'authentication';
  private readonly jwtKey: string = 'jwtToken';
  private readonly rtKey: string = 'rt';
  private readonly userKey: string = 'user';

  constructor(
    private http: HttpClient
  ) { }

  login(dto: LoginDTO): Observable<BaseResponse<Auth>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'authorize');
    return this.http.post<BaseResponse<Auth>>(url, dto);
  }

  logout() {
    sessionStorage.removeItem(this.userKey);
    sessionStorage.removeItem(this.jwtKey);
  }

  refreshTokens(rt: string): Observable<BaseResponse<Auth>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'refresh-tokens');
    //let headers = { Rt: rt };
    return this.http.post<BaseResponse<Auth>>(url, rt);
  }

  saveUser(data: Auth) {
    let user = new User(data.id, data.fullName, data.role);

    localStorage.setItem('rt', data.refreshToken);
    sessionStorage.setItem('jwtToken', data.jwtToken);
    localStorage.setItem('user', JSON.stringify(user));
  }

  getRefrehToken(): string {
    return localStorage.getItem(this.rtKey)!;
  }

  getJwtToken(): string {
    return sessionStorage.getItem(this.jwtKey)!;
  }

  getAccountId() {
    let user: User = JSON.parse(localStorage.getItem(this.userKey)!);
    return user.id;
  }

}
