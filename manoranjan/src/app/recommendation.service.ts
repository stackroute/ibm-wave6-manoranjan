import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RecommendationService {

  headers = new HttpHeaders({ 'Access-Control-Allow-Origin': '*' })

  constructor(private http: HttpClient) { }
  
}
