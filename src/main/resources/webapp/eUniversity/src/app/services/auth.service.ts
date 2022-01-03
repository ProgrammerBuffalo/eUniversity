import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PrepareApi } from './prepare-api';
import { Observable } from 'rxjs';
import { LoginDTO } from '../core/DTOs/login-dto';
import { User } from '../core/models/user';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly controllerName: string = 'authentication';

  constructor(
    private http: HttpClient
  ) { }

  login(dto: LoginDTO): Observable<User> {
    let url: string = PrepareApi.prepare(this.controllerName, 'authorize');
    let params = {};
    return this.http.post<User>(url, dto);
  }

  logout() {
    console.log('logout');
    //localStorage.removeItem('user');
  }

  refreshTokens(rt: string) {
    let url: string = PrepareApi.prepare(this.controllerName, 'refresh-tokens');
    let params = { rt: rt };
    return this.http.post(url, params);
  }

}
