import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-play',
  templateUrl: './play.component.html',
  styleUrls: ['./play.component.css']
})
export class PlayComponent implements OnInit {

  url;
  title
  constructor(private activatedRoute:ActivatedRoute,private sanitizer:DomSanitizer) {
    // this.videoUrl=false
   }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params=>{
      this.title=params.get('title')
      this.url=params.get('url')
      console.log(this.url);
    });

    this.url=this.sanitizer.bypassSecurityTrustResourceUrl("rtmp://localhost:1935/vod/mp4:"+this.url)
  }
}
