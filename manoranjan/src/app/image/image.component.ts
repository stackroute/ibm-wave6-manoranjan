import { Component, OnInit } from '@angular/core';
import { Episodic } from '../episodic';
import { Router } from '@angular/router';

@Component({
  selector: 'app-image',
  templateUrl: './image.component.html',
  styleUrls: ['./image.component.css']
})
export class ImageComponent implements OnInit {

  serial;
  serial1=new Episodic()
  episodeList;
  constructor(private router:Router) { }
  title;
  ngOnInit() {
    this.serial=JSON.parse(sessionStorage.getItem("serial"));
    // this.title=JSON.parse(sessionStorage.getItem('serial')).episodicTitle;
    // console.log("pppppppppppppp"+this.title);
    this.serial1=this.serial
    this.episodeList=this.serial.episodeList
    this.title=this.serial.episodicTitle
    // console.log(this.serial1.episodicTitle)
  }

  playVideo(){
    console.log();
    this.router.navigateByUrl('/play/'+this.serial.episodicTitle+'/'+this.serial1.episodeList[0].episodeUrl);
  }
}
