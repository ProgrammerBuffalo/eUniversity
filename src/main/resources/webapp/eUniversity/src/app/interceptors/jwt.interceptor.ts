import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../core/models/user';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor() { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    let user: User = JSON.parse(localStorage.getItem('user')!);

    if (user) {
      request = request.clone({
        setHeaders:
          { Authorization: 'Bearer ' + user.token }
      })
    }

    return next.handle(request);
  }
}
