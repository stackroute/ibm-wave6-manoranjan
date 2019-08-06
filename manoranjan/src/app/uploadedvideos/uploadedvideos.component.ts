import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-uploadedvideos',
  templateUrl: './uploadedvideos.component.html',
  styleUrls: ['./uploadedvideos.component.css']
})
export class UploadedvideosComponent implements OnInit {


  items: Array<any> = [];

  constructor(private userService:UserService) {
    this.items = [
      { name: '../../assets/caro/5.jpg' },
      { name: '../../assets/mov3.webp' },
      { name: '../../assets/caro/2.jpg' },
      { name: '../../assets/caro/1.jpg' },
      { name: '../../assets/mov5.webp' },
      { name: '../../assets/mov1.webp' },
      { name: '../../assets/caro/4.jpg' },
      { name: '../../assets/caro/3.jpg' },
      { name: '../../assets/dch.jpeg' },
      { name: '../../assets/kahani.jpeg' },
      { name: '../../assets/mov4.webp' },
      { name: '../../assets/three idiots.jpeg' },
      { name: '../../assets/m1.jpeg' },
      { name: '../../assets/lagan.jpeg' },
      { name: '../../assets/ddlj.jpeg' },
    ];
  }
  ngOnInit() {
    // this.userService.getMovieByGenre("action").subscribe(data => {
    //   this.movies = data
    //   this.movies1 = this.movies
    //   console.log(this.movies1)
    // })
  }

}
