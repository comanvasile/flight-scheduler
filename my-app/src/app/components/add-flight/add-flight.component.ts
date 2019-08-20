import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
//import { DatePipe } from '@angular/common';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { Flight } from 'src/app/models/Flight';
import { Airport } from 'src/app/models/Airport';
import { Airline } from 'src/app/models/Airline';
import { AirportService } from 'src/app/services/AirportService';
import { AirlineService } from 'src/app/services/AirlineService';
import { FlightService } from 'src/app/services/FlightService';
import { DialogComponent } from '../dialog/dialog.component';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-add-flight',
  templateUrl: './add-flight.component.html',
  styleUrls: ['./add-flight.component.scss']
})
export class AddFlightComponent implements OnInit {

  addForm: FormGroup;
   title: string;
   buttonTitle: string;
   editMode: boolean;
   errorMessage: string;
   airportList: Array<Airport>=new Array<Airport>();
   airportList1: Array<Airport>=new Array<Airport>();
   airportList2: Array<Airport>=new Array<Airport>();
  flight: Flight;
   airlineList: Array<Airline>=new Array<Airline>();
  flightId: number;
   username:string;
   constructor(private http: HttpClient, private flightService:FlightService,public dialog:MatDialog,private fb:FormBuilder,private router:Router,private activatedRoute: ActivatedRoute,private airportService: AirportService,private airlineService:AirlineService) { }

   ngOnInit() {
    
     this.username=localStorage.getItem('currentUser');
     this.activatedRoute.params.subscribe(params => {
       const id = params.id;
       if (id) {
       
        this.flightService.getFlightById(id).subscribe(f =>
          this.createForm(f));
        this.title = "Edit Flight";
        this.editMode = true;
        this.buttonTitle = "Edit";
        this.flightId = id;
        
       } else {
         this.createForm();
         this.title = "Add Flight";
         this.editMode = false;
         this.buttonTitle = "Add";
       }
     });
   }
   createForm(flight?: Flight) {
    
     this.addForm = this.fb.group({
          sourceControl: [flight ? flight.source : '',[ Validators.required]],
       departureControl:[flight ? flight.departure:'',[Validators.required]],
       destinationControl: [flight ? flight.destination : '',[ Validators.required]],
       arrivalControl:[flight ? flight.arrival:'',[Validators.required]],
       airlineControl: [flight ? flight.airline: '',[ Validators.required]],
       haltStationControl: [flight ? flight.haltStation : 'none'],
        haltDurationControl: [flight ? flight.haltDuration : '00:00']});
        this.airportService.getAirports().subscribe(r=>this.airportList=r.filter(a=>a.name!="none"));
        this.airlineService.getAirlines().subscribe(r=>
          this.airlineList=r
          );
 
    
   }
   changeState(){
   
    
      this.airportList1=this.airportList.filter(a=>a.name!==this.addForm.value.sourceControl)
    
    
   }
   changeState2(){
    
 
        this.airportList2=this.airportList1.filter(a=>a.name!==this.addForm.value.destinationControl)
      
 }

   cancel(){
     this.router.navigateByUrl('user/admin');
   }
   updateOrAddFlight(){
    if (this.editMode) {
      
      this.flight.id = this.flightId;
      this.flightService.updateFlight(this.flight).subscribe(flight => {
        
        this.router.navigateByUrl('user/admin')

        const dialogConfig=new MatDialogConfig();
        dialogConfig.data={
          message:'Flight updated successfully!'
          
        }
        const dialogRef = this.dialog.open(DialogComponent,
        dialogConfig);
      },
        error => {
         
          const dialogConfig=new MatDialogConfig();
          dialogConfig.data={
            message:'This flight cannot be updated! '
            
          }
          const dialogRef = this.dialog.open(DialogComponent,
          dialogConfig);
         

          this.errorMessage = error.message;
        });

    }
    else{
  //    this.addForm.value.haltStationControl="none";
  //    this.addForm.value.haltDurationControl="00:00";
  console.log("sunt aici");
      this.flight=new Flight(this.addForm.value.sourceControl,this.addForm.value.departureControl,this.addForm.value.destinationControl,this.addForm.value.arrivalControl,this.addForm.value.haltStationControl,this.addForm.value.haltDurationControl,this.addForm.value.airlineControl);
      this.flightService.addFlight(this.flight).subscribe(
    
       r=>{
       //  console.log(loginUrl+"/save" + this.flight)
       this.router.navigateByUrl('user/admin');
         const dialogConfig=new MatDialogConfig();
       dialogConfig.data={
          message:'Flight has been added successfully'
          
        }
       const dialogRef = this.dialog.open(DialogComponent,
        dialogConfig);
        


       },
       e=>{
        const dialogConfig=new MatDialogConfig();
      dialogConfig.data={
         message:'Flight can\'t be added'
          
        }
        const dialogRef = this.dialog.open(DialogComponent,
        dialogConfig);
        

     }
      
    );
   }
  }
}
