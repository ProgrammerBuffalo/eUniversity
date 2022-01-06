import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';

import { AuthService } from '../../services/auth.service';
import { BaseResponse } from '../models/base/base-response';
import { Auth } from '../models/auth/auth';


@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(catchError(err => {
      console.log(err.status);
      if (err.status == 401 || err.status == 403) {

        this.authService.logout();

        let rt: string = this.authService.getRefrehToken();

        if (rt === undefined)
          this.router.navigate(['/Login']);

        this.authService.refreshTokens(rt).subscribe({
          next: (data: BaseResponse<Auth>) => {
            this.authService.saveUser(data.data);
          },
          error: (data) => {
            this.router.navigate(['/Login']);
          }
        });
      }
      else if (err.status == 404) {
        this.router.navigate(['/404-page']);
      }

      return throwError('error from interceptor');
    }));
  }
}
