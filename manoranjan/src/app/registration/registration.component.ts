import { Component, OnInit } from '@angular/core';
import {User} from '../user';
import { UserService } from '../user.service';
import {ActivatedRoute,Router} from '@angular/router';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  user: User=new User();
  constructor(private router:Router, private route:ActivatedRoute,private userService:UserService) { }

  ngOnInit() {
  }
  submit(email,name,age,gender,mobileno,password,genre){
    this.user.emailId = email;
    this.user.name = name;
    this.user.age = age;
    this.user.gender= gender;
    this.user.mobileNo = mobileno;
    this.user.password = password;
    this.user.genre =genre;
    this.userService.saveUser(this.user).
    subscribe(
        data => {
        alert("valid")
          console.log("POST Request is successful ", data);},
          
        error => {
          alert("Invalid")
          console.log("Error", error);}
          
  );
  }
}
