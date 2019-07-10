import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-popularclips',
  templateUrl: './popularclips.component.html',
  styleUrls: ['./popularclips.component.css']
})
export class PopularclipsComponent implements OnInit {


  items:Array<any> =[];
  constructor() {
    this.items=[
      {name:'./assets/j1.jpg'},
      {name:'./assets/12.jpeg'},
      {name:'./assets/13.jpeg'},
      {name:'./assets/a1.jpeg'},
      {name:'./assets/a2.jpeg'},
      {name:'./assets/a3.jpeg'},
      {name:'./assets/a4.jpeg'},
      {name:'./assets/a5.jpeg'},
    ];
   }

  ngOnInit() {
  }

}
