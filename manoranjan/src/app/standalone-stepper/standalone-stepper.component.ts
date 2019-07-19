import { Component, OnInit } from '@angular/core';
import {User} from '../user';
import {FormBuilder, FormGroup, Validators, FormControl} from '@angular/forms';
import { UserService } from '../user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Media } from '../media';
import { MediaService } from '../media.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Crew } from '../crew';
import { Cast } from '../cast';
@Component({
  selector: 'app-standalone-stepper',
  templateUrl: './standalone-stepper.component.html',
  styleUrls: ['./standalone-stepper.component.css']
})
export class StandaloneStepperComponent implements OnInit {
  myGroup: FormGroup;
  [x: string]: any;
  user:User=new User();

  mediaDetails=[];
  
  email = new FormControl('', [Validators.required, Validators.email]);
  
  hide:true;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;

  title;
  category;
  synopsis;

  currentFileUpload:File;
  selectedVideo:FileList;
  selectedTrailer:FileList;

  mediaName;
  trailerName;

  progress:{percentage:number}={percentage:0};

  media:Media=new Media();
  crew:Crew=new Crew();
  cast:Cast=new Cast();
  listCrew:Array<Crew>=new Array<Crew>();
  listCast:Array<Cast>=new Array<Cast>();

  genres:Array<string>=new Array<string>();

  constructor(private _formBuilder: FormBuilder,private _userService:UserService,
    private router:Router,private mediaService:MediaService,private activatedRoute:ActivatedRoute) {}
  
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
      language: new FormControl(),
      date:new FormControl()

    });
    this.secondFormGroup = this._formBuilder.group({
      // name: ['', Validators.compose([Validators.required,Validators.maxLength(20)])],
      studio:new FormControl(),
      crewName:new FormControl(),
      crewRole:new FormControl(),
      screenName:new FormControl(),
      realName:new FormControl(),
    });
    this.thirdFormGroup = this._formBuilder.group({
      poster:new FormControl(),
      video:new FormControl(),
      trailer:new FormControl(),
      type:new FormControl()
    });
  }

  getErrorMessage() {
    return this.email.hasError('required') ? 'You must enter a value' :
        this.email.hasError('email') ? 'Not a valid email' : '';
  }

  onFirstSubmit(value){
    console.log(this.firstFormGroup.controls.language.value)
    console.log("first");
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
  onSubmInterest(value){
    console.log(value);
    this.genres.push(value);
  }

  selectVideo(event){
    this.selectedVideo=event.target.files;
  }

  uploadVideo(){
    this.progress.percentage = 0;
    this.currentFileUpload = this.selectedVideo.item(0)
    this.mediaService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
        this.mediaName=this.currentFileUpload.name;
      }
    })
    this.selectedVideo = undefined
  }

  selectTrailer(event){
    this.selectedTrailer=event.target.files;
  }

  uploadTrailer(){
    this.progress.percentage = 0;
    this.currentFileUpload = this.selectedTrailer.item(0)
    this.mediaService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
        this.trailerName=this.currentFileUpload.name;
      }
    })
    this.selectedTrailer = undefined
  }

  saveMedia(){
    this.crew.crewName=this.mediaDetails[1].crewName;
    this.crew.crewRole=this.mediaDetails[1].crewRole;
    this.listCrew.push(this.crew);

    this.cast.screenName=this.mediaDetails[1].screenName;
    this.cast.realName=this.mediaDetails[1].realName;
    this.listCast.push(this.cast);

    var video={
      'mediaTitle':this.title,
      'mediaCategory':this.category,
      'mediaSynopsis':this.synopsis,
      'mediaLanguage':this.mediaDetails[0].language,
      'mediaReleaseDate':this.mediaDetails[0].date,
      'mediaStudioName':this.mediaDetails[1].studio,
      'mediaCrew':this.listCrew,
      'mediaCast':this.listCast,
      'mediaPosterUrl':this.mediaDetails[2].poster,
      'mediaType':this.mediaDetails[2].type,
      'mediaUrl':this.mediaName,
      'mediaTrailerUrl':this.trailerName,
      'mediaGenre':this.genres
    };

    this.mediaService.saveMedia(video).subscribe(com=>{
      console.log("saved");
      console.log(com)
    },
    error=>{
      console.log(error)
    });
  }
 }
        


