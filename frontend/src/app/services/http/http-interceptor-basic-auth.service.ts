import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginService } from '../login/login.service';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorBasicAuthService implements HttpInterceptor {

  constructor(private loginService: LoginService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let basicAuthHeader = this.loginService.getAuthenticatedToken();
    let username = this.loginService.getAuthenticatedUser();

    if (username && basicAuthHeader) {
      req = req.clone({
        setHeaders: {
          Authorization: 'Bearer ' + basicAuthHeader
        }
      })
    }
    
    return next.handle(req);
  }
}
