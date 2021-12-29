import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PrepareApi } from './prepare-api';
import { Observable } from 'rxjs';
import { LoginDTO } from '../core/DTOs/login-dto';
import { RegDTO } from '../core/DTOs/reg-dto';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly controllerName: string = 'authentication';

  constructor(
    private http: HttpClient
  ) { }

  login(dto: LoginDTO): Observable<String> {
    let url: string = PrepareApi.prepare(this.controllerName, 'authorize');
    let params = {};
    return this.http.get<string>(url);
  }

  register(dto: RegDTO) {
    let url: string = PrepareApi.prepare(this.controllerName, 'reg');
    let params = {};
    return this.http.get<string>(url);
  }
}
