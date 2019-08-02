import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StandaloneService } from '../standalone.service';
import { EpisodicService } from '../episodic.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {

  id;
  media: any;
  constructor(private standaloneService: StandaloneService,private episodicService:EpisodicService, 
    private router: Router,private userService:UserService) {

  }

  ngOnInit() {

    this.id=sessionStorage.getItem('email');
    if(this.id!==null){
      this.userService.getEpisodicWishlist(this.id).subscribe((data:any)=>{
        this.episodicService.getWishlist(data).subscribe(list=>{
          console.log("episodic"+list);
        })
      });
      this.userService.getStandaloneWishlist(this.id).subscribe((data:any)=>{
        this.standaloneService.getWishlist(data).subscribe(list=>{
          console.log("standalone - "+list);
        })
      })
    }
  }
  sendDetails(serial) {
    let x = JSON.stringify(serial)
    sessionStorage.setItem("serial", x);
    console.log(serial.episodicTitle)
    this.router.navigateByUrl("/image");
  }
}
