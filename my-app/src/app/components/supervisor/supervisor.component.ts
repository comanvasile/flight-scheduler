import { Component, OnInit } from '@angular/core';
import { Flight } from 'src/app/models/Flight';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { Router } from '@angular/router';
import { FlightService } from 'src/app/services/FlightService';
import { LoginService } from 'src/app/services/LoginService';
import { DialogComponent } from '../dialog/dialog.component';
import { DeleteDialogComponent } from '../delete-dialog/delete-dialog.component';
import { Supervisor } from 'src/app/models/Supervisor';
import { AddDelayComponent } from '../add-delay/add-delay.component';

@Component({
  selector: 'app-supervisor',
  templateUrl: './supervisor.component.html',
  styleUrls: ['./supervisor.component.scss']
})
export class SupervisorComponent implements OnInit {

  public  flightList:Array<Flight>=new Array<Flight>();
  public option: string;
  public username: string=localStorage.getItem('currentUser');
  displayedColumns: Array<string> =['id','source','departure','destination','arrival','haltStation','haltDuration','airline','delay','action'];
  public supervisor: Supervisor;
  constructor( public dialog: MatDialog, private router: Router,private flightService: FlightService,private loginService:LoginService) {
  }
  
  ngOnInit() {
   
    
    this.getAllFlights();
  }

    getAllFlights() {
      this.loginService.getSupervisorByUsername(this.username).subscribe(r=>{this.supervisor=r; this.getFlights();
      
      });
   }
   getFlights(){
    this.flightService.getFlightsByUsername(this.supervisor.airport).subscribe(r=>
      this.flightList=r)
   }

  //  deleteFlight(id: number) {
  //    this.flightService.deleteFlight(id)
  //      .subscribe((response) => {
  //        this.getAllFlights();
  //        const dialogConfig=new MatDialogConfig();
  //          dialogConfig.data={
  //            message:'This flight has been deleted! '
            
  //          }
  //          const dialogRef = this.dialog.open(DialogComponent,
  //          dialogConfig);
  //      })
  //  }

  // addFlight() {
  //   this.router.navigateByUrl('user/admin/add-flight');
  // }

  //  updateFlight(flight: Flight) {
  //    this.router.navigate(['user/admin/edit-component', flight.id]);
  //  }

  // openDeleteDialog(flight: Flight): void {
  //   const dialogRef = this.dialog.open(DeleteDialogComponent, {
  //     data: { name: flight.id },
  //     width: '350px',
  //     height: '200px'
  //   });

  //   dialogRef.afterClosed()
  //     .subscribe((result) => {
  //        if (result) {
  //         this.deleteFlight(flight.id);
  //       }
  //     });
  // }
  // openAddSupervisor(){
  //   this.router.navigateByUrl("user/admin/add-supervisor");
  // }
  addDelay(flight:Flight){
    const dialogRef = this.dialog.open(AddDelayComponent, {
      //data: { name: flight.id },
      width: '350px',
      height: '200px'
    });

    dialogRef.afterClosed()
      .subscribe((result) => {
         if (result) {
           console.log(result);
           flight.delay=result;
          this.updateFlight(flight);
        }
      });

  }
  updateFlight(flight:Flight){
      this.flightService.updateFlight2(flight).subscribe(r=>{
        const dialogConfig=new MatDialogConfig();
                 dialogConfig.data={
                   message:'This delay has been added! '
                  
                 }
                 const dialogRef = this.dialog.open(DialogComponent,
                 dialogConfig);
             },
             error=>{
              const dialogConfig=new MatDialogConfig();
              dialogConfig.data={
                message:'This delay cannot be added! '
               
              }
              const dialogRef = this.dialog.open(DialogComponent,
              dialogConfig);
             });
      
  }
  logout() {
    this.router.navigateByUrl('');
    this.loginService.logout();
  }

}
