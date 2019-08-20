import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AdminComponent } from './components/admin/admin.component';
import { AddFlightComponent } from './components/add-flight/add-flight.component';
import { AddSupervisorComponent } from './components/add-supervisor/add-supervisor.component';
import { SupervisorComponent } from './components/supervisor/supervisor.component';
import { AuthGuard } from './auth/auth';
import { UserComponent } from './components/user/user.component';

const routes: Routes = [{
  path:'',
  component:LoginComponent
},
{
  path:'user/login',
  component:LoginComponent

},
{
  path:'user/admin',
  component:AdminComponent,
  canActivate: [AuthGuard]
},
{
  path:'user/admin/add-flight',
  component:AddFlightComponent,
  canActivate: [AuthGuard]
},
{
  path: 'user/admin/edit-component/:id',
  component: AddFlightComponent,
  canActivate: [AuthGuard]
  
},
{
  path:'user/admin/add-supervisor',
  component:AddSupervisorComponent,
  canActivate: [AuthGuard]
},{
  path:'user/supervisor',
  component:SupervisorComponent,
  canActivate: [AuthGuard]
},
{
  path:'user',
  component:UserComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
