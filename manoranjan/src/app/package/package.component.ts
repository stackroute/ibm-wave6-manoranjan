import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-package',
  templateUrl: './package.component.html',
  styleUrls: ['./package.component.css']
})
export class PackageComponent implements OnInit {

  constructor(private route:ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params=>{
      sessionStorage.setItem('email',params.get('email'))
    })
  }

}
