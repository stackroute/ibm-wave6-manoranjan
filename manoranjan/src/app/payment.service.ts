import { Injectable } from '@angular/core';
import { Userpayment } from './userpayment';
import{HttpClient, HttpHeaders} from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  headers = new HttpHeaders({'Access-Control-Allow-Origin' : '*'})
  constructor(private http:HttpClient) { }
  save(userpayment:Userpayment){
    console.log("userpayment - "+userpayment.mydate)
    return this.http.post("http://localhost:8070/api/v1/user",userpayment,{responseType: 'text'});
    
  }
}
