import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators, FormControl} from '@angular/forms';

import {ActivatedRoute,Router} from '@angular/router';
import { HttpResponse, HttpEventType } from '@angular/common/http';
import { MediaService } from '../media.service';
import { Crew } from '../crew';
import { Cast } from '../cast';
import { Episode } from '../episode';
@Component({
  selector: 'app-episodic',
  templateUrl: './episodic.component.html',
  styleUrls: ['./episodic.component.css']
})
export class EpisodicComponent implements OnInit {
  firstFormGroup: FormGroup
  secondFormGroup: FormGroup
  thirdFormGroup: FormGroup
  sixthFormGroup:FormGroup
  seventhFormGroup:FormGroup

  episodeDetails=[]
  
  currentFileUpload:File;
  selectedVideo:FileList;

  title;
  category;
  synopsis;
  episodeName;

  crewList=[];
 crewName;
 crewRole;
 castList=[];
 screenName;
 realName;
  
  // crew:Crew=new Crew();
  // cast:Cast=new Cast();
  episode:Episode=new Episode();
  // listCrew:Array<Crew>=new Array<Crew>();
  // listCast:Array<Cast>=new Array<Cast>();
  listEpisode:Array<Episode>=new Array<Episode>();

  progress:{percentage:number}={percentage:0};

  constructor(private _formBuilder: FormBuilder, private router: Router,
    private mediaService:MediaService,private activatedRoute:ActivatedRoute) { }

 ngOnInit() {

  this.activatedRoute.paramMap.subscribe(params=>{
    this.title=params.get('title');
    this.category=params.get('category');
    this.synopsis=params.get('synopsis');
    console.log("title- "+this.title+" category- "+this.category+" synopsis- "+this.synopsis);
  });


  this.firstFormGroup = this._formBuilder.group({
episodeLanguage:new FormControl('', Validators.compose([Validators.required])),
episodePoster: new FormControl('', Validators.compose([Validators.required])),
episodeType: new FormControl("" , Validators.compose([Validators.required])),

  });
   this.secondFormGroup = this._formBuilder.group({
    EpisodeStudioName: new FormControl('', Validators.compose([Validators.required,Validators.maxLength(20)])),
     crewName: new FormControl('', Validators.compose([Validators.maxLength(20)])),
    crewRole: new FormControl('', Validators.compose([])),
    screenName: new FormControl('', Validators.compose([Validators.maxLength(20)])),
    realName: new FormControl('', Validators.compose([Validators.maxLength(20)]))   
   });
   this.thirdFormGroup = this._formBuilder.group({
    episodeNo: new FormControl('', Validators.compose([Validators.required,Validators.maxLength(20)])),
    episodeUrl: new FormControl('', Validators.compose([Validators.required])),
    posterDescription:new FormControl('', Validators.compose([Validators.required,Validators.maxLength(100)])),
    posterUrl:new FormControl('', Validators.compose([Validators.required])),
    
    releaseDate:new FormControl("" , Validators.compose([Validators.required]))
   });

   this.sixthFormGroup = this._formBuilder.group({
    crewName:new FormControl(),
    crewRole:new FormControl(),
  });
  this.seventhFormGroup = this._formBuilder.group({
    screenName:new FormControl(),
    realName:new FormControl(),
  });
  
     }
     // ng oninit closed
     validation_messages = {
      'crewName': [
        { type: 'required', message: 'crewName is required' },
        { type: 'maxlength', message: 'crewName cannot be more than 25 characters long' },
        { type: 'pattern', message: 'Your username must contain only numbers and letters' },
        { type: 'validUsername', message: 'Your username has already been taken' }
      ],
      'screenName': [
        { type: 'required', message: 'screenName is required' },
        { type: 'maxlength', message: 'screenName cannot be more than 25 characters long' },
        { type: 'pattern', message: 'Your screenName must contain only numbers and letters' },
        { type: 'validUsername', message: 'Your username has already been taken' }
      ],
      'episodeStudioName': [
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

      addCrew(name,role){
        this.crewList.push(
          {crewName:name,crewRole:role}
        )
        console.log(this.crewList);
      }
      deleteCrew(name,role){
        console.log(name,role);
        for(var i=0;i<this.crewList.length;i++)
        {
          if(this.crewList[i]["crewName"]==name)
          {
            this.crewList.splice(i,1);
          }
        }
      }
      addCast(screen,real){
        this.castList.push(
          {screenName:screen,realName:real}
        )
        console.log(this.castList);
      }
      deleteCast(screenName,realName){
        console.log(screenName,realName);
        for(var i=0;i<this.castList.length;i++)
        {
          if(this.castList[i]["screenName"]==screenName)
          {
            this.castList.splice(i,1);
          }
        }
      }

      onFirstSubmit(value){
        // console.log(this.firstFormGroup.controls.language.value)
        console.log("first");
        console.log(value);
        this.episodeDetails.push(value);
      }
  
      onSecondSubmit(value){
        // console.log(this.firstFormGroup.controls.language.value)
        console.log("second");
        console.log(value);
        this.episodeDetails.push(value);
      }

      onThirdSubmit(value){
        console.log("third");
        console.log(value);
        this.episodeDetails.push(value);
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
            this.episodeName=this.currentFileUpload.name;
          }
        })
        this.selectedVideo = undefined
      }
      
      saveMedia(){
        // this.crew.crewName=this.episodeDetails[1].crewName;
        // this.crew.crewRole=this.episodeDetails[1].crewRole;
        // this.listCrew.push(this.crew);
    
        // this.cast.screenName=this.episodeDetails[1].screenName;
        // this.cast.realName=this.episodeDetails[1].realName;
        // this.listCast.push(this.cast);

        this.episode.episodeNo=this.episodeDetails[2].episodeNo;
        this.episode.episodeDescription=this.episodeDetails[2].posterDescription;
        this.episode.episodePosterUrl=this.episodeDetails[2].posterUrl;
        this.episode.episodeReleaseDate=this.episodeDetails[2].releaseDate;
        this.episode.episodeUrl=this.episodeName;

        this.listEpisode.push(this.episode);
    
        var video={
          'episodicTitle':this.title,
          'episodicCategory':this.category,
          'episodicSynopsis':this.synopsis,
          'episodicLanguage':this.episodeDetails[0].episodeLanguage,
          'episodicPosterUrl':this.episodeDetails[0].episodePoster,
          'episodicStudioName':this.episodeDetails[1].EpisodeStudioName,
          'episodicCrew':this.crewList,
          'episodicCast':this.castList,
          'episodeList':this.listEpisode,
          'episodicType':this.episodeDetails[0].episodeType
        };
    
        this.mediaService.saveSerial(video).subscribe(com=>{
          console.log("saved");
          console.log(com)
        },
        error=>{
          console.log(error)
        });
      }

}
