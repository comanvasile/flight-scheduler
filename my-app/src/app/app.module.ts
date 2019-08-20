import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MaterialModule } from './material/material.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {MatDatepickerModule} from '@angular/material/datepicker'
import { MatNativeDateModule } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { LoginService } from './services/LoginService';
import { AdminComponent } from './components/admin/admin.component';
import { AddFlightComponent } from './components/add-flight/add-flight.component';
import { HeaderComponent } from './components/header/header.component';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';
import { DialogComponent } from './components/dialog/dialog.component';
import { MessageComponent } from './components/message/message.component';
import { FlightService } from './services/FlightService';
import { DeleteDialogComponent } from './components/delete-dialog/delete-dialog.component';
import { AddSupervisorComponent } from './components/add-supervisor/add-supervisor.component';
import { SupervisorComponent } from './components/supervisor/supervisor.component';
import { AddDelayComponent } from './components/add-delay/add-delay.component';
import { UserComponent } from './components/user/user.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminComponent,
    AddFlightComponent,
    HeaderComponent,
    DialogComponent,
    MessageComponent,
    DeleteDialogComponent,
    AddSupervisorComponent,
    SupervisorComponent,
    AddDelayComponent,
    UserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    
    MatDatepickerModule,
    MatNativeDateModule,
    NgxMaterialTimepickerModule,
    HttpClientModule
    
  ],
  entryComponents:[DialogComponent,DeleteDialogComponent,AddDelayComponent],
  providers: [LoginService,FlightService],
  bootstrap: [AppComponent]
})
export class AppModule { }
