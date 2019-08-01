import { Component, OnInit } from '@angular/core';
import { MediaService } from '../media.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Media } from '../media';
import { UserService } from '../user.service';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {

  media: any;
  title;
  id: any;
  status: string = "false";
  media1 = new Media()
  date = new Date()

  constructor(private mediaService: MediaService, private activatedRoute: ActivatedRoute, private router: Router,private userService:UserService) {
  }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.title = params.get('title')
      this.getDetail(params.get('title'))
      console.log(params.get('title'));
      this.id = sessionStorage.getItem('email');
      if (sessionStorage.getItem('email') !== null) {
        this.status = "true";
      }
    });

  }

  getDetail(id) {
    this.mediaService.getMediaById(id).subscribe(data => {
      this.media = data
      this.media1 = this.media
      this.date = this.media1.mediaReleaseDate
      console.log(this.media1)
    })
  }

  playVideo() {
    this.id = sessionStorage.getItem('email')
    console.log();
    if (this.media1.mediaType === "premium") {
      if (this.id !== null) {
        this.router.navigateByUrl('/play/' + this.media1.mediaTitle + '/' + this.media1.mediaUrl);
      }
      else {
        this.router.navigateByUrl('/login');
      }
    }
    else {
      this.router.navigateByUrl('/play/' + this.media1.mediaTitle + '/' + this.media1.mediaUrl);
    }

  }

  addWishlist(title,category){
    // this.id = sessionStorage.getItem('email')
    // if(this.id!==null){
    //   this.userService.addToWishlist(this.id,title,category).subscribe(data=>{
    //     console.log(data);
    //   })
    // }
    // else{
    //   this.router.navigateByUrl('/login');
    // }
  }
}
