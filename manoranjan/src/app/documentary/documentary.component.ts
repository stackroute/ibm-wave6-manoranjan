import { Component, OnInit } from '@angular/core';
import { Media } from '../media';
import { StandaloneService } from '../standalone.service';

@Component({
  selector: 'app-documentary',
  templateUrl: './documentary.component.html',
  styleUrls: ['./documentary.component.css']
})
export class DocumentaryComponent implements OnInit {

  documentaries;
  documentaries1 = new Array<Media>()
  constructor(private mediaService: StandaloneService) { }

  ngOnInit() {
    this.mediaService.getStandalone("Documentary").subscribe(data => {
      this.documentaries = data
      this.documentaries1 = this.documentaries
      console.log(this.documentaries1)
    })
  }

}
