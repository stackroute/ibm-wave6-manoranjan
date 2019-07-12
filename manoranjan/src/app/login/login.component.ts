import { Component, OnInit } from '@angular/core';
import { Userauthen } from '../userauthen';
import { UserauthenService } from '../userauthen.service';
import {ActivatedRoute,Router} from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
user:Userauthen=new Userauthen();
  constructor(private router:Router, private route:ActivatedRoute,private userService:UserauthenService) { }

  ngOnInit() {
  }
  submit(email,pass){
    this.user.emailId = email;
    this.user.password = pass;
    this.userService.login(this.user).
    subscribe(
        data => {
        this.router.navigate(["/linkprilog"],{relativeTo:this.route});
          console.log("POST Request is successful ", data);},
          
        error => {
          alert("Login Unsuccessful, tryagain")
          this.router.navigate(["/login"],{relativeTo:this.route});
          console.log("Error", error);}
          
  );
  }
}
