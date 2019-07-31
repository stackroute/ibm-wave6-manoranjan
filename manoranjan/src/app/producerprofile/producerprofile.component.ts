import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-producerprofile',
  templateUrl: './producerprofile.component.html',
  styleUrls: ['./producerprofile.component.css']
})
export class ProducerprofileComponent implements OnInit {

  users: User;
  user = new User();
  emailId;
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.user.emailId = sessionStorage.getItem('email');
    console.log(this.user.emailId);
    this.userService.getById(this.user.emailId).subscribe(data => {
      this.users = data;
      console.log("POST Request is successful ", data);
    },
      error => {
        console.log("Error", error);
      }
    );
  }

}
