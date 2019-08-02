import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { EpisodicService } from '../episodic.service';
import { StandaloneService } from '../standalone.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  id;
  constructor(private userService:UserService,private episodicService:EpisodicService,
    private standaloneService:StandaloneService) { }

  ngOnInit() {
    this.id=sessionStorage.getItem('email');
    if(this.id!==null){
      this.userService.getEpisodicHistory(this.id).subscribe((data:any)=>{
        this.episodicService.getWishlist(data).subscribe(list=>{
          console.log("episodic"+list);
        })
      });
      this.userService.getStandaloneHistory(this.id).subscribe((data:any)=>{
        this.standaloneService.getWishlist(data).subscribe(list=>{
          console.log("standalone - "+list);
        })
      })
    }
  }
}
