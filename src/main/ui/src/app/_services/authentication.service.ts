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
    const body = {"login": "teacher1", "password": "teacher1"}
    return this.http.post<any>(`${environment.apiUrl}/authentication/authorize`, body).subscribe({
      next: data => {
        return data;
      },
      error: error => {
        console.log("Error");
      }
    });
  }
}
