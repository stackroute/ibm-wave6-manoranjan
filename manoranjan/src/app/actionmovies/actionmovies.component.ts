import { Component, OnInit } from '@angular/core';
import { Media } from '../media';
import { StandaloneService } from '../standalone.service';

@Component({
  selector: 'app-actionmovies',
  templateUrl: './actionmovies.component.html',
  styleUrls: ['./actionmovies.component.css']
})
export class ActionmoviesComponent implements OnInit {

  movies;
  movies1 = new Array<Media>()
  constructor(private mediaService: StandaloneService) { }

  ngOnInit() {
    this.mediaService.getMovieByGenre("action").subscribe(data => {
      this.movies = data
      this.movies1 = this.movies
      console.log(this.movies1)
    })
  }

}
