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
      {name:'../../assets/j1.jpg'},
      {name:'../../assets/dch.jpeg'},
      {name:'../../assets/kahani.jpeg'},
      {name:'../../assets/lagan.jpeg'},
      {name:'../../assets/three idiots.jpeg'},
      {name:'../../assets/dch.jpeg'},
      {name:'../../assets/kahani.jpeg'},
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
