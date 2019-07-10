import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-thirdcard',
  templateUrl: './thirdcard.component.html',
  styleUrls: ['./thirdcard.component.css']
})
export class ThirdcardComponent implements OnInit {

  items:Array<any> =[];
  constructor() {
    this.items=[
      {name:'../../assets/yghd.jpg'},
      {name:'../../assets/prdp.jpg'},
      {name:'../../assets/hddcs.jpg'},
      {name:'../../assets/vivah.jpg'},
      {name:'../../assets/mohabbatein.jpg'},
      {name:'../../assets/luka-chupi.jpg'},
      {name:'../../assets/rab-ne-bnad- jodi.jpg'},
      {name:'../../assets/ddlj.jpg'},
      {name:'../../assets/chennai-express.jpg'},
    ];
   }

  ngOnInit() {
  }

}
