import { UserRole } from './UserRole';

export class Login{
  
    username:string;
    password:string;
    userRole:string;
    constructor(username:string,password:string){
           
            this.username=username;
            this.password=password;
    }
}