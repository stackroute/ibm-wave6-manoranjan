import { Component, OnInit } from '@angular/core';
import { Episodic } from '../episodic';
import { MediaService } from '../media.service';

@Component({
  selector: 'app-marathiserials',
  templateUrl: './marathiserials.component.html',
  styleUrls: ['./marathiserials.component.css']
})
export class MarathiserialsComponent implements OnInit {

  serials;
  serials1 = new Array<Episodic>()
  constructor(private mediaService: MediaService) { }

  ngOnInit() {
    this.mediaService.getShowsByLanguage("Marathi").subscribe(data => {
      this.serials = data
      this.serials1 = this.serials
      console.log(this.serials1)
    })
  }

}
