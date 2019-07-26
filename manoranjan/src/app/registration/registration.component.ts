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

  user:User=new User();
  completeDetails = []
  genre =[]
  email;

  firstFormGroup: FormGroup
  secondFormGroup: FormGroup

  constructor(private route:ActivatedRoute,private router:Router,private _formBuilder: FormBuilder,private _userService:UserService,private userService: UserService) {}
    
  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
       name: ['', Validators.compose([Validators.required,Validators.maxLength(20)])],
          emailId:[ '',Validators.compose([Validators.required,Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$')])],
      password:['',Validators.compose([ Validators.minLength(5), Validators.required,Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$')])]
     
    });
    this.secondFormGroup = this._formBuilder.group({
      age: ["", Validators.compose([Validators.required])],
      gender: ["" , Validators.compose([Validators.required])],
      mobileNo:["", Validators.compose([ Validators.maxLength(10),Validators.required,
          Validators.minLength(10),Validators.pattern("^[0-9]+")])]
    });
    
  }

  onSubmitViewerDetails(value) {
    console.log(value)
    this.completeDetails.push(value)
  }
  onSubmitMoreDetails(value) {
    console.log(value)
    this.completeDetails.push(value)
    console.log(this.completeDetails[1])

  }
  onSubmInterest(interest) {
    console.log(interest)
    this.completeDetails.push(interest) 
    this.genre.push(interest);
    console.log(this.genre);
  }
  submitDetails() {
      var m = {
        'name': this.completeDetails[0].name,
        'emailId': this.completeDetails[0].emailId,
        'password': this.completeDetails[0].password,
        'age': this.completeDetails[1].age,
        'gender': this.completeDetails[1].gender,
        'mobileNo': this.completeDetails[1].mobileNo,
        'genre': this.genre,
       
      }
      
      this.userService.saveUser(m).subscribe(data => {
        alert("valid")
        console.log("POST Request is successful ", data);},
        error => {
          alert("Invalid")
          console.log("Error", error);}
          );
          
          this.router.navigateByUrl('/package/'+this.completeDetails[0].emailId); 
    }
     
  validation_messages = {
    'name': [
      { type: 'required', message: 'Username is required' },
      { type: 'maxlength', message: 'Username cannot be more than 25 characters long' },
      { type: 'pattern', message: 'Your username must contain only numbers and letters' },
      { type: 'validUsername', message: 'Your username has already been taken' }
    ],
    'emailId': [
      { type: 'required', message: 'Email is required' },
      { type: 'pattern', message: 'Enter a valid email' }
    ],
    
    'password': [
      { type: 'required', message: 'Password is required' },
      { type: 'minlength', message: 'Password must be at least 5 characters long' },
      { type: 'pattern', message: 'Your password must contain at least one uppercase, one lowercase, and one number' }
    ],
    'mobileNo': [
      { type: 'required', message: 'mobileNo is required' },
      { type: 'minlength', message: 'mobileNo must be at 10 characters long' },
      { type: 'pattern', message: 'Your mobileNo must contain one number' }
    ]

  }

 }
        
