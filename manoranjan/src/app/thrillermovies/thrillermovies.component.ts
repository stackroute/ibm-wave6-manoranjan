import { Component, OnInit } from '@angular/core';
import { Media } from '../media';
import { MediaService } from '../media.service';

@Component({
  selector: 'app-thrillermovies',
  templateUrl: './thrillermovies.component.html',
  styleUrls: ['./thrillermovies.component.css']
})
export class ThrillermoviesComponent implements OnInit {

  movies;
  movies1=new Array<Media>()
  constructor(private mediaService:MediaService) { }

  ngOnInit() {
    this.mediaService.getMovieByGenre("thriller").subscribe(data=>{
      this.movies=data
      this.movies1=this.movies
      console.log(this.movies1)
    })
  }


}
