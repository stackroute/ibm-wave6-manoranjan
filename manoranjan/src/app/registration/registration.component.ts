import { Component, OnInit } from '@angular/core';
import {User} from '../user';
import {FormBuilder, FormGroup, Validators, FormControl} from '@angular/forms';
import { UserService } from '../user.service';
import {ActivatedRoute,Router} from '@angular/router';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  [x: string]: any;
  user:User=new User();
  email = new FormControl('', [Validators.required, Validators.email]);
  onSubmit(){
    this._userService.saveUser(this.user)
    .subscribe(
      data => console.log('success',data)
      
    )
  }

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
    this.thirdFormGroup = this._formBuilder.group({
      thirdCtrl: ['', Validators.required]
    });
  }
  // submit(email,name,age,gender,mobileno,password,genre){
  //   this.user.emailId = email;
  //   this.user.name = name;
  //   this.user.age = age;
  //   this.user.gender= gender;
  //   this.user.mobileNo = mobileno;
  //   this.user.password = password;
  //   this.user.genre =genre;
  //   this.userService.saveUser(this.user).
 
  getErrorMessage() {
    return this.email.hasError('required') ? 'You must enter a value' :
        this.email.hasError('email') ? 'Not a valid email' : '';
  }
  hide:true;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;
  constructor(private _formBuilder: FormBuilder,private _userService:UserService) {}
 }
        
