import { Component, OnInit } from '@angular/core';
import { MediaService } from '../media.service';
import { List } from 'lodash';
import { Router } from '@angular/router';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {

  media: any;
  constructor(private mediaService: MediaService, private router: Router) {

  }

  ngOnInit() {

    var m = [["yeh hai mohabbatein", "TV Episodes"], ["kahaani", "Movie"]];
    this.mediaService.getList(m).subscribe(data => {
      this.media = data;
      console.log(data);
    })
  }
  sendDetails(serial) {
    let x = JSON.stringify(serial)
    sessionStorage.setItem("serial", x);
    console.log(serial.episodicTitle)
    this.router.navigateByUrl("/image");
  }
}
