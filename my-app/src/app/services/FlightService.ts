import { Injectable } from '@angular/core';

import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Flight } from '../models/Flight';
import { FlightUser } from '../models/FlightUser';

@Injectable({
    providedIn: 'root'
  })
export class FlightService{
    private loginUrl="http://localhost:8080/flight";
    redirectUrl:string;
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    constructor(private http:HttpClient){

    }
     addFlight (programare: Flight):Observable<any> {
         return this.http.post<any>(this.loginUrl+'/save',programare,{observe:'response'});
      }
      // addDelay(delay:string,id:number):Observable<any>{
      //   return this.http.put<any>(this.loginUrl+"/delay/"+id,delay,{observe:'response'})
      // }
    //  public addFlight(flight: Flight, callback?: any): Observable<any> {
    //    console.log(this.loginUrl+"/save" + '  ' + flight);
    //     return this.http.post(this.loginUrl+"/save",flight);
          
    //   }
     public getFlights(): Observable<Array<Flight>> {
        //const headers= new HttpHeaders({ 'Content-Type': 'application/json' , 'Authorization': JSON.stringify(localStorage.getItem('currentUser'))});
         return this.http.get<Array<Flight>>(this.loginUrl+'/list', { headers: this.headers });
       }
       public getFlightUser():Observable<Array<FlightUser>>{
         return this.http.get<Array<FlightUser>>(this.loginUrl+'/search',{headers:this.headers});
       }
       public getFlightsByUsername(username:string): Observable<Array<Flight>> {
        //const headers= new HttpHeaders({ 'Content-Type': 'application/json' , 'Authorization': JSON.stringify(localStorage.getItem('currentUser'))});
         return this.http.get<Array<Flight>>(this.loginUrl+'/list/'+username, { headers: this.headers });
       }
         public deleteFlight(id: number): Observable<Flight> {
    return this.http.delete<Flight>(this.loginUrl + "/" + id, { headers: this.headers });
  }
  public getFlightById(id: number): Observable<Flight> {
    return this.http.get<Flight>(this.loginUrl + "/" + id, { headers: this.headers });
  }
  public updateFlight(flight: Flight) {
    let body = JSON.stringify({ id: flight.id, source: flight.source, departure: flight.departure, destination: flight.destination,arrival:flight.arrival,haltStation:flight.haltStation,haltDuration:flight.haltDuration,airline:flight.airline });
    return this.http.put<Flight>(this.loginUrl + "/" + flight.id, body, { headers: this.headers });
  }
  public updateFlight2(flight: Flight) {
    let body = JSON.stringify({ id: flight.id, source: flight.source, departure: flight.departure, destination: flight.destination,arrival:flight.arrival,haltStation:flight.haltStation,haltDuration:flight.haltDuration,airline:flight.airline,delay:flight.delay });
    return this.http.put<Flight>(this.loginUrl + "/delay/" + flight.id, body, { headers: this.headers });
  }
}
