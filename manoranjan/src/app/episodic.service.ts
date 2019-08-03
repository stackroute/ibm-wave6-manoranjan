import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Episodic } from './episodic';

@Injectable({
  providedIn: 'root'
})
export class EpisodicService {

  constructor(private http: HttpClient) { }

  getShowsByLanguage(language:string){
    // return this.http.get('http://13.235.52.81:8083/episodic-media-service/series/tv/'+language,{
      return this.http.get('http://localhost:8083/episodic-media-service/series/tv/'+language,{
        headers:new HttpHeaders({
        'Access-Control-Allow-Origin' : '*'
      })
    });
  }

  getEpisodic(series:string){
    // return this.http.get('http://13.235.52.81:8083/episodic-media-service/series/category/'+series,{
      return this.http.get('http://localhost:8083/episodic-media-service/series/category/'+series,{
        headers:new HttpHeaders({
        'Access-Control-Allow-Origin' : '*'
      })
    });
  }
  saveSerial(serial:Episodic){
    // return this.http.post<Episodic>("http://13.235.52.81:8083/episodic-media-service/serial",serial);
    return this.http.post<Episodic>("http://localhost:8083/episodic-media-service/serial",serial);
  }

  getWishlist(titles:Array<string>){
    // return this.http.patch('http://13.235.52.81:8083/episodic-media-service/wish',titles);
    return this.http.patch('http://localhost:8083/episodic-media-service/wish',titles);
  }
}
