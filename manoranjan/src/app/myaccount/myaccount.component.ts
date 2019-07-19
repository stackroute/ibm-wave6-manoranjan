import { Component, OnInit } from '@angular/core';
import {User} from '../user';
import {FormBuilder, FormGroup, Validators, FormControl} from '@angular/forms';
import { UserService } from '../user.service';


@Component({
  selector: 'app-myaccount',
  templateUrl: './myaccount.component.html',
  styleUrls: ['./myaccount.component.css']
})
export class MyaccountComponent implements OnInit {

  users:User[];
  emailId;
  constructor(private _formBuilder: FormBuilder,private _userService:UserService,private userService: UserService) { }

  ngOnInit() {
    this.userService.getById(this.emailId).subscribe(data => {
      this.users = data;
    });
  }

}
