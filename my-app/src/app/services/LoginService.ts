import { Injectable } from '@angular/core';

import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Login } from '../models/Login';
import { Supervisor } from '../models/Supervisor';


@Injectable({
    providedIn: 'root'
  })
export class LoginService{
    private loginUrl="http://localhost:8080/user";
    redirectUrl:string;
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    constructor(private http:HttpClient){

    }
    // submitLogin (login: Login):Observable<any> {
    //     return this.http.post<Login>(this.loginUrl,login,{observe:'response'});
    //  }
     logout() {
        localStorage.removeItem('currentUser');
    }
    addSupervisor (supervisor: Supervisor):Observable<any> {
      return this.http.post<any>(this.loginUrl+"/save",supervisor,{observe:'response'});
   }
    public submitLogin(login: Login, callback?: any): Observable<any> {
        return this.http.post(this.loginUrl+'/login',login);
          
      }
      public getSupervisorByUsername(username: string): Observable<Supervisor> {
        return this.http.get<Supervisor>(this.loginUrl + "/find/" + username, { headers: this.headers });
      }
      
}