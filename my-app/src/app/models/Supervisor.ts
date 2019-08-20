export class Supervisor{
    name:string;
    email:string;
    phone:string;
    username:string;
    password:string;
    airport:string;
    constructor(n:string,e:string,p:string,u:string,pa:string,a:string){
        this.name=n;
        this.email=e;
        this.phone=p;
        this.username=u;
        this.password=pa;
        this.airport=a;
    }
}