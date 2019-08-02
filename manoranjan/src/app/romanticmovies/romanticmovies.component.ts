import { Component, OnInit } from '@angular/core';
import { Media } from '../media';
import { MediaService } from '../media.service';
import { StandaloneService } from '../standalone.service';

@Component({
  selector: 'app-romanticmovies',
  templateUrl: './romanticmovies.component.html',
  styleUrls: ['./romanticmovies.component.css']
})
export class RomanticmoviesComponent implements OnInit {

  movies;
  movies1 = new Array<Media>()
  constructor(private mediaService: StandaloneService) { }

  ngOnInit() {
    this.mediaService.getMovieByGenre("romantic").subscribe(data => {
      this.movies = data
      this.movies1 = this.movies
      console.log(this.movies1)
    })
  }


}
