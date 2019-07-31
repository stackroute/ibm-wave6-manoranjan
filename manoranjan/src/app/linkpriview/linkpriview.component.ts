import { Component, OnInit } from '@angular/core';
import { MediaService } from '../media.service';
import { Media } from '../media';
import { Episodic } from '../episodic';

@Component({
  selector: 'app-linkpriview',
  templateUrl: './linkpriview.component.html',
  styleUrls: ['./linkpriview.component.css']
})
export class LinkpriviewComponent implements OnInit {

  heading1 = "Movies";
  heading2 = "Tv Episodes";
  heading3 = "Web series";
  heading4 = "Documentries";

  allMedia;
  allMedia1 = new Array<Media>()
  movie = new Array<Media>()

  docs = new Array<Media>();

  weblist;
  weblist2 = new Array<Episodic>();
  web = new Array<Episodic>();

  tvlist;
  tvlist2 = new Array<Episodic>();
  tv = new Array<Episodic>();
  constructor(private mediaService: MediaService) { }

  ngOnInit() {
    this.mediaService.getEpisodic("Web Series").subscribe(data => {
      this.weblist = data
      this.weblist2 = this.weblist
      this.weblist2.forEach(element => {
        if (element.episodicType === "premium") {
          this.web.push(element)
          console.log(element)
        }
      });
      console.log("list" + this.weblist2)
      console.log(this.web)
    });

    this.mediaService.getEpisodic("TV Episodes").subscribe(data => {
      this.tvlist = data
      this.tvlist2 = this.tvlist
      this.tvlist2.forEach(element => {
        if (element.episodicType === "premium") {
          this.tv.push(element)
          console.log(element)
        }
      });
      console.log("list" + this.tvlist2)
      console.log(this.tv)
    });

    this.mediaService.getAllMedia().subscribe(data => {
      this.allMedia = data;
      this.allMedia1 = this.allMedia
      console.log(this.allMedia)
      this.allMedia1.forEach(element => {
        if (element.mediaCategory === "Movie") {
          if (element.mediaType === "premium") {
            this.movie.push(element)
          }
        }
        if (element.mediaCategory == "Documentary") {
          if (element.mediaType == "premium") {
            this.docs.push(element)
          }
        }
      });
    })
  }
}
