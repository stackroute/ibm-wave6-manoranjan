import { Component, OnInit } from '@angular/core';
import { User } from '../user';

@Component({
  selector: 'app-producerprofile',
  templateUrl: './producerprofile.component.html',
  styleUrls: ['./producerprofile.component.css']
})
export class ProducerprofileComponent implements OnInit {

  users:User;
  user=new User();
  emailId;
  constructor() { }

  ngOnInit() {
    this.user.emailId=sessionStorage.getItem('email');
      console.log(this.user.emailId)
  }

}
