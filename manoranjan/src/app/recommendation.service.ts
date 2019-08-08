import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest, HttpHeaders } from '@angular/common/http';
import { Movie } from './movie';
import { Documentary } from './documentary';
import { Tvepisodes } from './tvepisodes';
import { Webseries } from './webseries';
import { Viewer } from './viewer';

@Injectable({
  providedIn: 'root'
})
export class RecommendationService {

  headers = new HttpHeaders({ 'Access-Control-Allow-Origin': '*' })

  constructor(private http: HttpClient) { }
  getRecInterestDocumentary(email):any{
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/recommendedInterestDocumentary/"+email);
    return this.http.get("http://localhost:8083/recommendation-service/recommendedInterestDocumentary/"+email,{
        headers:new HttpHeaders({
          'Access-Control-Allow-Origin' : '*'
        })
    });
  }
  getRecInterestMovie(email):any{
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/recommendedInterestMovie/"+email);
    return this.http.get("http://localhost:8083/recommendation-service/recommendedInterestMovie/"+email,{
      headers:new HttpHeaders({
        'Access-Control-Allow-Origin' : '*'
      })
    });
  }


  getRecLangDoc(email):any{
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/recLangDocumentary/"+email);
    return this.http.get("http://localhost:8083/recommendation-service/recLangDocumentary/"+email);
  }
  getRecLangMovie(email):any{
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/recLangMovie/"+email);
    return this.http.get("http://localhost:8083/recommendation-service/recLangMovie/"+email);
  }
  getRecLangTvEpisodes(email):any{
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/recLangTvEpisodes/"+email);
    return this.http.get("http://localhost:8083/recommendation-service/recLangTvEpisodes/"+email);
  }
  getRecLangWebSeries(email):any{
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/recLangWebSeries/"+email);
    return this.http.get("http://localhost:8083/recommendation-service/recLangWebSeries/"+email);

  }
}
