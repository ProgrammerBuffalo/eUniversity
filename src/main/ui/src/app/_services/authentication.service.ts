import { Injectable } from '@angular/core';

import {environment} from "../../environments/environment";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs";

@Injectable({providedIn: 'root'})
export class AuthenticationService {
  constructor(
    private route: Router,
    private http: HttpClient
  ) {
  }

  login(login: string, password: string) {
    return this.http.post<any>(`${environment.apiUrl}/authentication/authorize`, {login, password})
      .pipe(map(jwt => {
        return jwt;
      }));
  }
}
