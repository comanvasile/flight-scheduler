import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';

import { Router } from '@angular/router';
import { LoginService } from '../services/LoginService';
//import { LoginService } from '../services/login.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
 
  constructor(private loginService: LoginService, private router: Router) {  
  }
  
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    let url:string=state.url;
    return this.checkLogin(url);
  }

  checkLogin(url: string): boolean {
    if(localStorage.getItem('currentUser')){
      return true;
    }

    // Store the attempted URL for redirecting
    this.loginService.redirectUrl = url;
    // Navigate to the login page with extras
    this.router.navigateByUrl('');
    return false;
  }
}
