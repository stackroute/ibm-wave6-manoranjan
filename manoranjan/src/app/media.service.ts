import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpEvent, HttpRequest, HttpHeaders } from '@angular/common/http';
import { Media } from './media';
import { Episodic } from './episodic';

@Injectable({
  providedIn: 'root'
})
export class MediaService {

  headers=new HttpHeaders({'Access-Control-Allow-Origin':'*'})

  constructor(private http: HttpClient) {}

 pushFileToStorage(file: File): Observable<HttpEvent<{}>> {
   let formdata: FormData = new FormData();

   formdata.append('file', file);
   const req = new HttpRequest('POST', 'http://localhost:8080/stream/v1/post', formdata, {
    
     reportProgress: true,
     responseType: 'text'
     
   });

   return this.http.request(req);
 }

 saveMedia(media:Media){
   return this.http.post<Media>("http://localhost:8080/stream/v1/media",media);
 }

 getAllMedia(){
    return this.http.get('http://localhost:8080/stream/v1/medias',{
      headers:new HttpHeaders({
        'Access-Control-Allow-Origin' : '*'
      })
    });
  }

  getMediaById(id){
    return this.http.get('http://localhost:8080/stream/v1/media/'+id,{
      headers:new HttpHeaders({
        'Access-Control-Allow-Origin' : '*'
      })
    });
  }

  saveSerial(serial:Episodic){
    return this.http.post<Episodic>("http://localhost:8080/stream/v1/serial",serial);
  }

}
