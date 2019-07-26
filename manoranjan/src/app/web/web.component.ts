import { Component, OnInit } from '@angular/core';
import { MediaService } from '../media.service';
import { Episodic } from '../episodic';

@Component({
  selector: 'app-web',
  templateUrl: './web.component.html',
  styleUrls: ['./web.component.css']
})
export class WebComponent implements OnInit {

  series;
  series1=new Array<Episodic>()

  constructor(private mediaService:MediaService) { }

  ngOnInit() {
    this.mediaService.getEpisodic("Web Series").subscribe(data=>{
      this.series=data;
      this.series1=this.series
      console.log(this.series1)
    })
  }

}
