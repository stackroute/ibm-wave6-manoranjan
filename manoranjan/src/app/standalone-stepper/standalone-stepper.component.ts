import { Component, OnInit } from '@angular/core';
import {User} from '../user';
import {FormBuilder, FormGroup, Validators, FormControl} from '@angular/forms';
import { UserService } from '../user.service';
@Component({
  selector: 'app-standalone-stepper',
  templateUrl: './standalone-stepper.component.html',
  styleUrls: ['./standalone-stepper.component.css']
})
export class StandaloneStepperComponent implements OnInit {
  myGroup: FormGroup;
  [x: string]: any;
  user:User=new User();
  task: string;
  task1:string;
  tasks = [];
  tasks1=[];
  
  email = new FormControl('', [Validators.required, Validators.email]);
  onSubmit(){
    this._userService.saveUser(this.user)
    .subscribe(
      data => console.log('success',data)
      
    )
  }
  
  onClick(){
    this.tasks.push({name: this.task});
    this.tasks.push({role: this.task1});
console.log('tasks'+this.tasks);
console.log('tasks'+this.task1);
console.log('tasks'+this.task);
  this.task = '';
  this.task1 = '';
}
  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      name: ['', Validators.compose([Validators.required,Validators.maxLength(20)])],
    });
    this.thirdFormGroup = this._formBuilder.group({
      thirdCtrl: ['', Validators.required]
    });
  }

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
        


