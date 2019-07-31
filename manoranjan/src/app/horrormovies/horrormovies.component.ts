import { Component, OnInit } from '@angular/core';
import { Media } from '../media';
import { MediaService } from '../media.service';

@Component({
  selector: 'app-horrormovies',
  templateUrl: './horrormovies.component.html',
  styleUrls: ['./horrormovies.component.css']
})
export class HorrormoviesComponent implements OnInit {

  movies;
  movies1 = new Array<Media>()
  constructor(private mediaService: MediaService) { }

  ngOnInit() {
    this.mediaService.getMovieByGenre("horror").subscribe(data => {
      this.movies = data
      this.movies1 = this.movies
      console.log(this.movies1)
    })
  }


}
