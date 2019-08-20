import { Injectable } from '@angular/core';

import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Airport } from '../models/Airport';

@Injectable({
    providedIn: 'root'
  })
export class AirportService{
    private airportURL: string = 'http://localhost:8080/airport';

    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    constructor(private http:HttpClient){

    }
    public getAirports(): Observable<Array<Airport>> {
        //const headers= new HttpHeaders({ 'Content-Type': 'application/json' , 'Authorization': JSON.stringify(localStorage.getItem('currentUser'))});
         return this.http.get<Array<Airport>>(this.airportURL+"/list", { headers: this.headers });
       }
}