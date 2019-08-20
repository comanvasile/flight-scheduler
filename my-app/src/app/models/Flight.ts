import { Timestamp } from 'rxjs';
import { Time } from '@angular/common';

export class Flight{
    id:number;
    source:string;
    departure:string;
    destination:string;
    arrival:string;
    haltStation:string;
    haltDuration:string;
    airline:string;
    delay:string;
    constructor(s:string,d:string,de:string,a:string,h:string,hd:string,ar:string){

        this.source=s;
        this.departure=d;
        this.destination=de;
        this.arrival=a;
        this.haltStation=h;
        this.haltDuration=hd;
        this.airline=ar;
    }

}