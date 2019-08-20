import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Login } from 'src/app/models/Login';
import { LoginService } from 'src/app/services/LoginService';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  hide=true;
  errorMessage='';
  constructor(private fb:FormBuilder,private router:Router,private loginService: LoginService) { }
  user: Login;
  ngOnInit() {
    this.createForm();
  }
  createForm(){
    this.loginForm = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }
  login(){
    const login = new Login(this.loginForm.value.email, this.loginForm.value.password);
    this.loginService.submitLogin(login).subscribe(
     ( response) => {
       this.user=response;
       if(this.user!=null)
      {
         localStorage.setItem('currentUser',login.username);
       if(this.user.userRole==='ADMINISTRATOR')
      this.router.navigateByUrl('user/admin');
      else
    this.router.navigateByUrl('user/supervisor');
    }
  else{
     this.errorMessage = "Incorrect email or password!"
}
},
     ( error )=> { this.errorMessage = "Incorrect email or password!"},
    );
  }

}
