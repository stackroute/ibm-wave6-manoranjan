import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  userService: any;
  route: any;
  headers = new HttpHeaders({'Access-Control-Allow-Origin' : '*'})
  constructor(private http:HttpClient) { }
  
  saveUser(user:User){
   return this.http.post<User>("http://localhost:8050/api/v1//user",user);
  }
  getById(emailId):any{
    return this.http.get("http://localhost:8050/api/v1/users/"+emailId,{
      headers:new HttpHeaders({
        'Access-Control-Allow-Origin' : '*'
      })
    });
   }
}
