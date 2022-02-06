import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { from, Observable, throwError } from 'rxjs';
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
    return next.handle(request).pipe(catchError((err: any) => {

      if (err.status == 401 || err.status == 403)
        from(this.handle401Error(request, next));

      return throwError('error from interceptor');
    }));
  }

  async handle401Error(request: HttpRequest<any>, next: HttpHandler) {

    this.authService.logout();
    let rt: string = this.authService.getRefrehToken();

    if (rt == null)
      this.router.navigate(['Login']);

    this.authService.refreshTokens(rt).subscribe({
      next: (res: BaseResponse<Auth>) => {
        this.authService.saveUser(res.data);

        // request = request.clone({
        //   setHeaders:
        //     { Authorization: 'Bearer ' + res.data.jwtToken }
        // });

        console.log('next');

        return next.handle(request).toPromise();
      },
      error: () => {
        this.router.navigate(['Login']);
        return throwError('error from interceptor');
      }
    });
  }


  // intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
  //   return next.handle(request).pipe(catchError(err => {
  //     if (err.status == 401 || err.status == 403) {

  //       this.authService.logout();

  //       let rt: string = this.authService.getRefrehToken();
  //       console.log(rt);

  //       if (rt === undefined)
  //         this.router.navigate(['/Login']);

  //       this.authService.refreshTokens(rt).subscribe({
  //         next: (res: BaseResponse<Auth>) => {
  //           console.log('refresh');
  //           //this.authService.saveUser(data.data);
  //           localStorage.setItem('rt', res.data.refreshToken);
  //           sessionStorage.setItem('jwtToken', res.data.jwtToken);

  //           request = request.clone({
  //             setHeaders:
  //               { Authorization: 'Bearer ' + res.data.jwtToken }
  //           });

  //           console.log('object');

  //           return next.handle(request);
  //         },
  //         error: () => {
  //           this.router.navigate(['/Login']);
  //         }
  //       });
  //     }
  //     else if (err.status == 404) {
  //       this.router.navigate(['/404-page']);
  //     }

  //     return throwError('error from interceptor');
  //   }));
  // }
}
