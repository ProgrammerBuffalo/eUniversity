import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';

import { AuthService } from '../services/auth.service';
import { BaseResponse } from '../core/models/base/base-response';
import { Auth } from '../core/models/auth/auth';


@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(catchError(err => {
      if (err.status == 401 || err.status == 403) {
        console.log('401');

        this.authService.logout();

        let rt: string = this.authService.getRefrehToken();

        if (rt === undefined)
          this.router.navigate(['/Login']);

        this.authService.refreshTokens(rt).subscribe({
          next: (data: BaseResponse<Auth>) => {
            console.log(data);
            this.authService.saveUser(data.data);
            console.log('token is refreshed');
          },
          error: (data) => {
            console.log('rt token error');
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
