import { Component, OnInit } from '@angular/core';
import {ActivatedRoute,Router} from '@angular/router';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  constructor(private route:ActivatedRoute,private router:Router) { }

  ngOnInit() {
  }
  // onSelect(){
  //   // this.router.navigate(['about'],{relativeTo:this.route});
  //   this.router.navigateByUrl("/about1");
  // }

}
