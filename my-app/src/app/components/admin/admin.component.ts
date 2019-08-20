import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { Router } from '@angular/router';
import { FlightService } from 'src/app/services/FlightService';
import { Flight } from 'src/app/models/Flight';
import { DialogComponent } from '../dialog/dialog.component';
import { DeleteDialogComponent } from '../delete-dialog/delete-dialog.component';
import { LoginService } from 'src/app/services/LoginService';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

  public  flightList:Array<Flight>=new Array<Flight>();
  public option: string;
  displayedColumns: Array<string> =['id','source','departure','destination','arrival','haltStation','haltDuration','airline','action'];
  
  constructor( public dialog: MatDialog, private router: Router,private flightService: FlightService,private loginService:LoginService) {
  }
  
  ngOnInit() {
    this.getAllFlights();
  }

    getAllFlights() {
 
      this.flightService.getFlights().subscribe(r=>
        this.flightList=r)
   }

   deleteFlight(id: number) {
     this.flightService.deleteFlight(id)
       .subscribe((response) => {
         this.getAllFlights();
         const dialogConfig=new MatDialogConfig();
           dialogConfig.data={
             message:'This flight has been deleted! '
            
           }
           const dialogRef = this.dialog.open(DialogComponent,
           dialogConfig);
       })
   }

  addFlight() {
    this.router.navigateByUrl('user/admin/add-flight');
  }

   updateFlight(flight: Flight) {
     this.router.navigate(['user/admin/edit-component', flight.id]);
   }

  openDeleteDialog(flight: Flight): void {
    const dialogRef = this.dialog.open(DeleteDialogComponent, {
      data: { name: flight.id },
      width: '350px',
      height: '200px'
    });

    dialogRef.afterClosed()
      .subscribe((result) => {
         if (result) {
          this.deleteFlight(flight.id);
        }
      });
  }
  openAddSupervisor(){
    this.router.navigateByUrl("user/admin/add-supervisor");
  }
  logout() {
    this.router.navigateByUrl('');
    this.loginService.logout();
  }

}
