import { Component, OnInit } from '@angular/core';
import { MediaService } from '../media.service';

@Component({
  selector: 'app-popularclips',
  templateUrl: './popularclips.component.html',
  styleUrls: ['./popularclips.component.css']
})
export class PopularclipsComponent implements OnInit {


  items:Array<any> =[];
  // items:any;
  constructor(private mediaService:MediaService) {
    this.items=[
      {name:'./assets/j1.jpg'},
      {name:'./assets/caro/5.jpg'},
      {name:'./assets/13.jpeg'},
      {name:'./assets/caro/2.jpg'},
      {name:'./assets/caro/4.jpg'},
      {name:'./assets/12.jpeg'},
      {name:'./assets/caro/1.jpg'},
      {name:'./assets/a5.jpeg'},
    ];
   }

  ngOnInit() {
    // this.mediaService.getAllMedia().subscribe(data=>{
    //   this.items=data
    //   console.log(this.items)
    // })
  }
}
