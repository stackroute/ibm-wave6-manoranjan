import { Component, OnInit } from '@angular/core';
import { Media } from '../media';
import { StandaloneService } from '../standalone.service';

@Component({
  selector: 'app-biopicmovies',
  templateUrl: './biopicmovies.component.html',
  styleUrls: ['./biopicmovies.component.css']
})
export class BiopicmoviesComponent implements OnInit {

  movies;
  movies1 = new Array<Media>()
  constructor(private mediaService: StandaloneService) { }

  ngOnInit() {
    this.mediaService.getMovieByGenre("biopic").subscribe(data => {
      this.movies = data
      this.movies1 = this.movies
      console.log(this.movies1)
    })
  }

}
