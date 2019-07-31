import { Component, OnInit } from '@angular/core';
import { Media } from '../media';
import { MediaService } from '../media.service';

@Component({
  selector: 'app-sciencefictionmovies',
  templateUrl: './sciencefictionmovies.component.html',
  styleUrls: ['./sciencefictionmovies.component.css']
})
export class SciencefictionmoviesComponent implements OnInit {

  movies;
  movies1 = new Array<Media>()
  constructor(private mediaService: MediaService) { }

  ngOnInit() {
    this.mediaService.getMovieByGenre("science").subscribe(data => {
      this.movies = data
      this.movies1 = this.movies
      console.log(this.movies1)
    })
  }


}
