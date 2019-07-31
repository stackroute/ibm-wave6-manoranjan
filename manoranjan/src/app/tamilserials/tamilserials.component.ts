import { Component, OnInit } from '@angular/core';
import { Episodic } from '../episodic';
import { MediaService } from '../media.service';

@Component({
  selector: 'app-tamilserials',
  templateUrl: './tamilserials.component.html',
  styleUrls: ['./tamilserials.component.css']
})
export class TamilserialsComponent implements OnInit {

  serials;
  serials1=new Array<Episodic>()
  constructor(private mediaService:MediaService) { }

  ngOnInit() {
    this.mediaService.getShowsByLanguage("Tamil").subscribe(data=>{
      this.serials=data
      this.serials1=this.serials
      console.log(this.serials1)
    })
  }

}
