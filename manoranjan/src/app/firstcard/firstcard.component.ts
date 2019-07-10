import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-firstcard',
  templateUrl: './firstcard.component.html',
  styleUrls: ['./firstcard.component.css']
})
export class FirstcardComponent implements OnInit {

  items:Array<any> =[];
  constructor() {
    this.items=[
      {name:'../../assets/mov1.webp'},
      {name:'../../assets/mov2.webp'},
      {name:'../../assets/mov3.webp'},
      {name:'../../assets/mov4.webp'},
      {name:'../../assets/mov5.webp'},
      {name:'../../assets/mov1.webp'},
      {name:'../../assets/mov2.webp'},
      {name:'../../assets/ddlj.jpeg'},
      {name:'../../assets/dch.jpeg'},
      {name:'../../assets/kahani.jpeg'},
      {name:'../../assets/lagan.jpeg'},
      {name:'../../assets/three idiots.jpeg'},
      {name:'../../assets/dch.jpeg'},
      {name:'../../assets/kahani.jpeg'},
      {name:'../../assets/ddlj.jpeg'},
    ];
   }

  ngOnInit() {
  }

}
