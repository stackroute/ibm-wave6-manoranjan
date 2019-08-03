import { Component, OnInit } from '@angular/core';
import { Media } from '../media';
import { StandaloneService } from '../standalone.service';

@Component({
  selector: 'app-comedymovies',
  templateUrl: './comedymovies.component.html',
  styleUrls: ['./comedymovies.component.css']
})
export class ComedymoviesComponent implements OnInit {

  movies;
  movies1 = new Array<Media>()
  constructor(private mediaService: StandaloneService) { }

  ngOnInit() {
    this.mediaService.getMovieByGenre("comedy").subscribe(data => {
      this.movies = data
      this.movies1 = this.movies
      console.log(this.movies1)
    })
  }


}
