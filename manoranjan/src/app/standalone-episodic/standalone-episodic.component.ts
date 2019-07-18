import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-standalone-episodic',
  templateUrl: './standalone-episodic.component.html',
  styleUrls: ['./standalone-episodic.component.css']
})
export class StandaloneEpisodicComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }

  sendDetails(title,category,synopsis){
    this.router.navigateByUrl('/standalonestepper/'+title+'/'+category+'/'+synopsis);
  }
}
