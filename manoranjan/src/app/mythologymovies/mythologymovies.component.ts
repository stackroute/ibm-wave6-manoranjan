import { Component, OnInit } from '@angular/core';
import { Media } from '../media';
import { MediaService } from '../media.service';

@Component({
  selector: 'app-mythologymovies',
  templateUrl: './mythologymovies.component.html',
  styleUrls: ['./mythologymovies.component.css']
})
export class MythologymoviesComponent implements OnInit {

  movies;
  movies1=new Array<Media>()
  constructor(private mediaService:MediaService) { }

  ngOnInit() {
    this.mediaService.getMovieByGenre("mythology").subscribe(data=>{
      this.movies=data
      this.movies1=this.movies
      console.log(this.movies1)
    })
  }


}
