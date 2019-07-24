import { Component, OnInit } from '@angular/core';
import { MediaService } from '../media.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Media } from '../media';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {

  media:any;
  title;
  media1=new Media()
  date=new Date()

  constructor(private mediaService:MediaService,private activatedRoute:ActivatedRoute,private router:Router) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params=>{
      this.title=params.get('title')
      this.getDetail(params.get('title'))
      console.log(params.get('title'));
    });

  }

  getDetail(id){
    this.mediaService.getMediaById(id).subscribe(data=>{
      this.media=data
      this.media1=this.media
      this.date=this.media1.mediaReleaseDate
      console.log(this.media1)
    })
  }

  playVideo(){
    console.log();
    this.router.navigateByUrl('/play/'+this.media1.mediaTitle+'/'+this.media1.mediaUrl);
  }
}
