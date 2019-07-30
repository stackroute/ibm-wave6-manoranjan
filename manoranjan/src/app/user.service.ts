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
   return this.http.post<User>("http://localhost:8011/userservice/user",user);
  }
  getById(emailId):any{
    return this.http.get("http://localhost:8011/userservice/users/"+emailId,{
      headers:new HttpHeaders({
        'Access-Control-Allow-Origin' : '*'
      })
    });
   }
  getAllUsers():any{
    return this.http.get("http://localhost:8011/userservice/users");
  }
  updateUser(emailId,user:User):any{
    return this.http.put("http://localhost:8011/userservice/user/"+emailId,user,{
      headers:new HttpHeaders({
        'Access-Control-Allow-Origin' : '*'
      })
    });
}
}
