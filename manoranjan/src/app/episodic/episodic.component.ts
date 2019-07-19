import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators, FormControl} from '@angular/forms';

import {ActivatedRoute,Router} from '@angular/router';
@Component({
  selector: 'app-episodic',
  templateUrl: './episodic.component.html',
  styleUrls: ['./episodic.component.css']
})
export class EpisodicComponent implements OnInit {
  firstFormGroup: FormGroup
  secondFormGroup: FormGroup
  thirdFormGroup: FormGroup
   
 ngOnInit() {
  this.firstFormGroup = this._formBuilder.group({
episodeLanguage:['', Validators.compose([Validators.required,Validators.maxLength(20)])],
episodePoster: ['', Validators.compose([Validators.required,Validators.maxLength(20)])],
episodeType: ["" , Validators.compose([Validators.required])],
screenName: ['', Validators.compose([Validators.required,Validators.maxLength(20)])],
  });
   this.secondFormGroup = this._formBuilder.group({

     crewName: ['', Validators.compose([Validators.required,Validators.maxLength(20)])],
    crewRole: ['', Validators.compose([Validators.required,Validators.maxLength(20)])],
    screenName: ['', Validators.compose([Validators.required,Validators.maxLength(20)])],
    realName: ['', Validators.compose([Validators.required,Validators.maxLength(20)])]   
   });
   this.thirdFormGroup = this._formBuilder.group({
    episodeNo: ['', Validators.compose([Validators.required,Validators.maxLength(20)])],
    episodeUrl: ['', Validators.compose([Validators.required,Validators.maxLength(20)])],
    posterDescription:['', Validators.compose([Validators.required,Validators.maxLength(100)])],
    posterUrl:['', Validators.compose([Validators.required,Validators.maxLength(100)])],
    
    releaseDate:["" , Validators.compose([Validators.required])]
   });
  
     }
     // ng oninit closed
     validation_messages = {
      'episodePoster': [
        { type: 'required', message: 'crewName is required' },
        { type: 'maxlength', message: 'crewName cannot be more than 25 characters long' },
        { type: 'pattern', message: 'Your username must contain only numbers and letters' },
        { type: 'validUsername', message: 'Your username has already been taken' }
      ],

      'crewName': [
        { type: 'required', message: 'crewName is required' },
        { type: 'maxlength', message: 'crewName cannot be more than 25 characters long' },
        { type: 'pattern', message: 'Your username must contain only numbers and letters' },
        { type: 'validUsername', message: 'Your username has already been taken' }
      ],
      'crewRole': [
        { type: 'required', message: 'crewRole is required' },
        { type: 'maxlength', message: 'Username cannot be more than 25 characters long' },
        { type: 'pattern', message: 'Your username must contain only numbers and letters' },
        { type: 'validUsername', message: 'Your username has already been taken' }
      ],
      'screenName': [
        { type: 'required', message: 'screenName is required' },
        { type: 'maxlength', message: 'screenName cannot be more than 25 characters long' },
        { type: 'pattern', message: 'Your screenName must contain only numbers and letters' },
        { type: 'validUsername', message: 'Your username has already been taken' }
      ],
      'realName': [
        { type: 'required', message: 'Username is required' },
        { type: 'maxlength', message: 'Username cannot be more than 25 characters long' },
        { type: 'pattern', message: 'Your username must contain only numbers and letters' },
        { type: 'validUsername', message: 'Your username has already been taken' }
      ],
      
    
  
      }
  
  constructor(private _formBuilder: FormBuilder, private router: Router) { }

}
