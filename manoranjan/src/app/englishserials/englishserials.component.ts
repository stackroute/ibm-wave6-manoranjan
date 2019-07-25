import { Component, OnInit } from '@angular/core';
import { Episodic } from '../episodic';
import { MediaService } from '../media.service';

@Component({
  selector: 'app-englishserials',
  templateUrl: './englishserials.component.html',
  styleUrls: ['./englishserials.component.css']
})
export class EnglishserialsComponent implements OnInit {

  serials;
  serials1=new Array<Episodic>()
  constructor(private mediaService:MediaService) { }

  ngOnInit() {
    this.mediaService.getShowsByLanguage("English").subscribe(data=>{
      this.serials=data
      this.serials1=this.serials
      console.log(this.serials1)
    })
  }
}
