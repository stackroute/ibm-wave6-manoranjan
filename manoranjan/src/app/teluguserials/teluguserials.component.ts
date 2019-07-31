import { Component, OnInit } from '@angular/core';
import { MediaService } from '../media.service';
import { Episodic } from '../episodic';

@Component({
  selector: 'app-teluguserials',
  templateUrl: './teluguserials.component.html',
  styleUrls: ['./teluguserials.component.css']
})
export class TeluguserialsComponent implements OnInit {

  serials;
  serials1 = new Array<Episodic>()
  constructor(private mediaService: MediaService) { }

  ngOnInit() {
    this.mediaService.getShowsByLanguage("Telugu").subscribe(data => {
      this.serials = data
      this.serials1 = this.serials
      console.log(this.serials1)
    })
  }
}
