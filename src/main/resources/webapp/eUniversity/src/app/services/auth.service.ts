import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PrepareApi } from './prepare-api';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly controllerName: string = 'Auth';

  constructor(
    private http: HttpClient
  ) { }

  login() {
      this.http.get(PrepareApi.prepare(this.controllerName, ''));
  }

  register() {

  }
}
