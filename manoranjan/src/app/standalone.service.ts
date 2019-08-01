import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StandaloneMedia } from './standalone-media';

@Injectable({
  providedIn: 'root'
})
export class StandaloneService {

  headers = new HttpHeaders({ 'Access-Control-Allow-Origin': '*' })

  constructor(private http: HttpClient) { }

  pushFileToStorage(file: File): Observable<HttpEvent<{}>> {
    let formdata: FormData = new FormData();

  formdata.append('file', file);
  //  const req = new HttpRequest('POST', 'http://13.235.52.81:8083/media-manager-service/post', formdata, {
    const req = new HttpRequest('POST', 'http://localhost:8083/standalone-media-service/post', formdata, {
    
     reportProgress: true,
     responseType: 'text'
     
   });
   return this.http.request(req);
  }

 saveMedia(media:StandaloneMedia){
  //  return this.http.post<Media>("http://13.235.52.81:8083/media-manager-service/media",media);
   return this.http.post<StandaloneMedia>("http://localhost:8083/standalone-media-service/media",media);
 }

 getAllMedia(){
    // return this.http.get('http://13.235.52.81:8083/media-manager-service/medias',{
      return this.http.get('http://localhost:8083/standalone-media-service/medias',{
        headers:new HttpHeaders({
        'Access-Control-Allow-Origin' : '*'
   })
  });
 }
  

  getMediaById(id){
    // return this.http.get('http://13.235.52.81:8083/media-manager-service/media/'+id,{
      return this.http.get('http://localhost:8083/standalone-media-service/media/'+id,{
        headers:new HttpHeaders({
        'Access-Control-Allow-Origin' : '*'
      })
    });
  }

  getStandalone(type:string){
    // return this.http.get('http://13.235.52.81:8083/media-manager-service/media/category/'+type,{
      return this.http.get('http://localhost:8083/standalone-media-service/media/category/'+type,{
        headers:new HttpHeaders({
        'Access-Control-Allow-Origin' : '*'
      })
    });
  }

  getMovieByGenre(genre:string){
    // return this.http.get('http://13.235.52.81:8083/media-manager-service/media/movie/'+genre,{
      return this.http.get('http://localhost:8083/standalone-media-service/media/movie/'+genre,{
        headers:new HttpHeaders({
        'Access-Control-Allow-Origin' : '*'
      })
    });
  }

  // getList(mediaList:List<List<string>>){
    // return this.http.post('http://13.235.52.81:8083/media-manager-service/media/list',mediaList);
  //   return this.http.post('http://localhost:8083/media-manager-service/media/list',mediaList);

  // }
}
