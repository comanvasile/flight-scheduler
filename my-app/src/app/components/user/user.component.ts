import { Component, OnInit } from '@angular/core';
import { Airline } from 'src/app/models/Airline';
import { Airport } from 'src/app/models/Airport';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AirportService } from 'src/app/services/AirportService';
import { AirlineService } from 'src/app/services/AirlineService';
import { Flight } from 'src/app/models/Flight';
import { FlightService } from 'src/app/services/FlightService';
import { FlightUser } from 'src/app/models/FlightUser';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  addForm: FormGroup;
  airportList: Array<Airport>=new Array<Airport>();
  airlineList: Array<Airline>=new Array<Airline>();
  flightList: Array<FlightUser>=new Array<FlightUser>();
 flightId: number;
  username:string;
  displayedColumns: Array<string> =['id','destination','arrival','duration'];

  constructor(private flightService: FlightService,public fb:FormBuilder,public router:Router,private airportService:AirportService,private airlineService:AirlineService) { }

  ngOnInit() {
    this.createForm()
  }
  createForm() {
    this.addForm = this.fb.group({
      sourceControl: [ '',[ Validators.required]],
   airlineControl: [ '',[ Validators.required]] });
   this.airportService.getAirports().subscribe(r=>this.airportList=r.filter(a=>a.name!="none"));
   this.airlineService.getAirlines().subscribe(r=>
     this.airlineList=r
     );
  
  }

  cancel(){
    this.router.navigateByUrl('');
  }
  search(){
      this.flightService.getFlightUser().subscribe(r=>this.flightList=r.filter(a=>a.airline==this.addForm.value.airlineControl && a.source==this.addForm.value.sourceControl))
  }

}
