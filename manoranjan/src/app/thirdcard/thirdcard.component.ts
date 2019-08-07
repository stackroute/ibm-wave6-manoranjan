import { Component, OnInit } from '@angular/core';
import { RecommendationService } from '../recommendation.service';

@Component({
  selector: 'app-thirdcard',
  templateUrl: './thirdcard.component.html',
  styleUrls: ['./thirdcard.component.css']
})
export class ThirdcardComponent implements OnInit {

  id;
  items: Array<any> = [];
  constructor(private recommendation:RecommendationService) {

  }

  ngOnInit() {

    this.id=sessionStorage.getItem('email');
    console.log(this.id);
    this.recommendation.getRecInterestMovie(this.id).subscribe(data=>{
      this.items=data;
      console.log(this.items);
    })
  }

}
