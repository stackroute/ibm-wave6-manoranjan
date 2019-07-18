import { Component, OnInit } from '@angular/core';
import {User} from '../user';
import {FormBuilder, FormGroup, Validators, FormControl} from '@angular/forms';
import { UserService } from '../user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Media } from '../media';
import { MediaService } from '../media.service';
@Component({
  selector: 'app-standalone-stepper',
  templateUrl: './standalone-stepper.component.html',
  styleUrls: ['./standalone-stepper.component.css']
})
export class StandaloneStepperComponent implements OnInit {
  [x: string]: any;
  
  user:User=new User();
  media:Media=new Media();
  mediaDetails=[];
  genres=[];
  email = new FormControl('', [Validators.required, Validators.email]);
  
  hide:true;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;

  title;
  category;
  synopsis;

  constructor(private _formBuilder: FormBuilder,private _userService:UserService,private router:Router,private mediaService:MediaService,private activatedRoute:ActivatedRoute) {}
  
  onSubmit(){
    this._userService.saveUser(this.user)
    .subscribe(
      data => console.log('success',data)
      
    )
  }

  ngOnInit() {

    this.activatedRoute.paramMap.subscribe(params=>{
      this.title=params.get('title');
      this.category=params.get('category');
      this.synopsis=params.get('synopsis');
      console.log("title- "+this.title+" category- "+this.category+" synopsis- "+this.synopsis);
    });

    this.firstFormGroup = this._formBuilder.group({
      // firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      // secondCtrl: ['', Validators.required]
    });
    this.thirdFormGroup = this._formBuilder.group({
      // thirdCtrl: ['', Validators.required]
    });
  }

  getErrorMessage() {
    return this.email.hasError('required') ? 'You must enter a value' :
        this.email.hasError('email') ? 'Not a valid email' : '';
  }

  onFirstSubmit(value){
    console.log(value);
    this.mediaDetails.push(value);
  }

  onSecondSubmit(value){
    console.log(value);
    this.mediaDetails.push(value);
  }

  onThirdSubmit(value){
    console.log(value);
    this.mediaDetails.push(value);
  }
  onGenreSubmit(value){
console.log(value);
this.genres.push(value);
  }
 }
        


