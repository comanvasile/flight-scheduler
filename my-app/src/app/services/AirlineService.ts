import { Injectable } from '@angular/core';

import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Airport } from '../models/Airport';
import { Airline } from '../models/Airline';

@Injectable({
    providedIn: 'root'
  })
export class AirlineService{
    private airportURL: string = 'http://localhost:8080/airline';

    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    constructor(private http:HttpClient){

    }
    public getAirlines(): Observable<Array<Airline>> {
        //const headers= new HttpHeaders({ 'Content-Type': 'application/json' , 'Authorization': JSON.stringify(localStorage.getItem('currentUser'))});
         return this.http.get<Array<Airline>>(this.airportURL+"/list", { headers: this.headers });
       }
}