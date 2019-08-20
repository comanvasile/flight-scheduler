import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Supervisor } from 'src/app/models/Supervisor';
import { LoginService } from 'src/app/services/LoginService';
import { DialogComponent } from '../dialog/dialog.component';
import { Airport } from 'src/app/models/Airport';
import { AirportService } from 'src/app/services/AirportService';

@Component({
  selector: 'app-add-supervisor',
  templateUrl: './add-supervisor.component.html',
  styleUrls: ['./add-supervisor.component.scss']
})
export class AddSupervisorComponent implements OnInit {

  addUserFormGroup: FormGroup;
  airportList: Array<Airport>=new Array<Airport>();
  hide=true;
  constructor(private airportService: AirportService,private loginService: LoginService,private fb:FormBuilder,private router:Router,public dialog:MatDialog) { }

  ngOnInit() {
    this.addUserFormGroup=this.fb.group({
      nameControl:['',[Validators.required,Validators.pattern(new RegExp("^([a-zA-Z]+ [a-zA-Z]+)$"))]],
      emailControl:['',[Validators.required,Validators.email]],
      phoneControl:['',[Validators.required,Validators.pattern(new RegExp("[0-9]"))]],
     
      usernameControl:['',[Validators.required]],
      passwordControl:['',[Validators.required]],
      sourceControl:['',[Validators.required]],

    });
    this.airportService.getAirports().subscribe(r=>this.airportList=r.filter(r=>r.name!="none"));
   
  }
  cancel(){
    this.router.navigateByUrl('user/admin');
  }
  save(){

    const supervisor=new Supervisor(this.addUserFormGroup.value.nameControl,this.addUserFormGroup.value.emailControl,this.addUserFormGroup.value.phoneControl,this.addUserFormGroup.value.usernameControl,this.addUserFormGroup.value.passwordControl,this.addUserFormGroup.value.sourceControl);
    
    this.loginService.addSupervisor(supervisor).subscribe(
      r=>{  
        this.router.navigateByUrl('user/admin');

        const dialogConfig=new MatDialogConfig();
        dialogConfig.data={
          message:'Supervisor has been added successfully'
          
        }
        const dialogRef = this.dialog.open(DialogComponent,
        dialogConfig);

      },
      error=>{
        const dialogConfig=new MatDialogConfig();
              dialogConfig.data={
                message:'The supervisor can\'t be added'
                
              }
              const dialogRef = this.dialog.open(DialogComponent,
              dialogConfig);
              

      }
     );
    
      

     
     
  }
 

}
