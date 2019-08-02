import { Component, OnInit } from '@angular/core';
import { Media } from '../media';
import { MediaService } from '../media.service';
import { StandaloneService } from '../standalone.service';

@Component({
  selector: 'app-mysterymovies',
  templateUrl: './mysterymovies.component.html',
  styleUrls: ['./mysterymovies.component.css']
})
export class MysterymoviesComponent implements OnInit {

  movies;
  movies1 = new Array<Media>()
  constructor(private mediaService: StandaloneService) { }

  ngOnInit() {
    this.mediaService.getMovieByGenre("mystery").subscribe(data => {
      this.movies = data
      this.movies1 = this.movies
      console.log(this.movies1)
    })
  }

}
