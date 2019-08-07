import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest, HttpHeaders } from '@angular/common/http';
<<<<<<< HEAD
import { Movie } from './movie';
import { Documentary } from './documentary';
=======
import { Documentary } from './documentary';
import { Movie } from './movie';
>>>>>>> 4a30af9d4bd2f9f2bbad8035bfd3f5a5a07934d0
import { Tvepisodes } from './tvepisodes';
import { Webseries } from './webseries';
import { Viewer } from './viewer';

@Injectable({
  providedIn: 'root'
})
export class RecommendationService {

  headers = new HttpHeaders({ 'Access-Control-Allow-Origin': '*' })

  constructor(private http: HttpClient) { }
<<<<<<< HEAD
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
    return this.http.get("http://localhost:8083/recommendation-service/recLangDocumentary/"+email,{
=======
  getDocumentary():any{
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/documentaries");
    return this.http.get("http://localhost:8083/recommendation-service/documentaries");
  }
  getMovie():any{
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/movies");
    return this.http.get("http://localhost:8083/recommendation-service/movies");
  }
  getTvEpisodes():any{
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/tvEpisodes");
    return this.http.get("http://localhost:8083/recommendation-service/tvEpisodes");
  }
  getWebSeries():any{
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/webSeries");
    return this.http.get("http://localhost:8083/recommendation-service/webSeries");
  }
  getLanguages():any{
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/languages");
    return this.http.get("http://localhost:8083/recommendation-service/languages");
  }
  getGenres():any{
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/genres");
    return this.http.get("http://localhost:8083/recommendation-service/genres");
  }

  getDocumentaryByTitle(title):any{
<<<<<<< HEAD
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/documentary/"+title,{
    return this.http.get("http://localhost:8083/recommendation-service/documentary/"+title,{
=======
    return this.http.get("http://13.235.52.81:8083/recommendation-service/documentary/"+title,{
    // return this.http.get("http://localhost:8083/recommendation-service/documentary/"+title,{
>>>>>>> 4a30af9d4bd2f9f2bbad8035bfd3f5a5a07934d0
>>>>>>> fb19f56cb61b1c543c184710fd52774d8c05b6a0
      headers:new HttpHeaders({
        'Access-Control-Allow-Origin' : '*'
      })
    });
  }
<<<<<<< HEAD
  getRecLangMovie(email):any{
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/recLangMovie/"+email);
    return this.http.get("http://localhost:8083/recommendation-service/recLangMovie/"+email,{
=======
  getMovieByTitle(title):any{
<<<<<<< HEAD
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/movie/"+title,{
    return this.http.get("http://localhost:8083/recommendation-service/movie/"+title,{
=======
    return this.http.get("http://13.235.52.81:8083/recommendation-service/movie/"+title,{
    // return this.http.get("http://localhost:8083/recommendation-service/movie/"+title,{
>>>>>>> 4a30af9d4bd2f9f2bbad8035bfd3f5a5a07934d0
>>>>>>> fb19f56cb61b1c543c184710fd52774d8c05b6a0
      headers:new HttpHeaders({
        'Access-Control-Allow-Origin' : '*'
      })
    });
  }
<<<<<<< HEAD
  getRecLangTvEpisodes(email):any{
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/recLangTvEpisodes/"+email);
    return this.http.get("http://localhost:8083/recommendation-service/recLangTvEpisodes/"+email,{
=======
  getTvEpisodesByTitle(title):any{
<<<<<<< HEAD
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/tvEpisodes/"+title,{
    return this.http.get("http://localhost:8083/recommendation-service/tvEpisodes/"+title,{
=======
    return this.http.get("http://13.235.52.81:8083/recommendation-service/tvEpisodes/"+title,{
    // return this.http.get("http://localhost:8083/recommendation-service/tvEpisodes/"+title,{
>>>>>>> 4a30af9d4bd2f9f2bbad8035bfd3f5a5a07934d0
>>>>>>> fb19f56cb61b1c543c184710fd52774d8c05b6a0
      headers:new HttpHeaders({
        'Access-Control-Allow-Origin' : '*'
      })
    });
  }
<<<<<<< HEAD
  getRecLangWebSeries(email):any{
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/recLangWebSeries/"+email);
    return this.http.get("http://localhost:8083/recommendation-service/recLangWebSeries/"+email,{
=======
  getWebSeriesByTitle(title):any{
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/webSeries/"+title,{
    return this.http.get("http://localhost:8083/recommendation-service/webSeries/"+title,{
      headers:new HttpHeaders({
        'Access-Control-Allow-Origin' : '*'
      })
    });
  }

  saveNewDocumentary(documentary:Documentary){
    // return this.http.post<Documentary>("http://13.235.52.81:8083/recommendation-service/standalone/documentary",documentary);
    return this.http.post<Documentary>("http://localhost:8083/recommendation-service/standalone/documentary",documentary);
  }
  saveNewMovie(movie:Movie){
    // return this.http.post<Movie>("http://13.235.52.81:8083/recommendation-service/standalone/movie",movie);
    return this.http.post<Movie>("http://localhost:8083/recommendation-service/standalone/movie",movie);
  }
  saveNewTvEpisodes(tvEpisodes:Tvepisodes){
    // return this.http.post<Tvepisodes>("http://13.235.52.81:8083/recommendation-service/episodicMedia/tvEpisode",tvEpisodes);
    return this.http.post<Tvepisodes>("http://localhost:8083/recommendation-service/episodicMedia/tvEpisode",tvEpisodes);
  }
  saveNewWebSeries(webSeries:Webseries){
    // return this.http.post<Webseries>("http://13.235.52.81:8083/recommendation-service/standalone/webSeries",webSeries);
    return this.http.post<Webseries>("http://localhost:8083/recommendation-service/standalone/webSeries",webSeries);
  }

  getViewer():any{
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/viewers");
    return this.http.get("http://localhost:8083/recommendation-service/viewers");
  }
  saveViewer(viewer:Viewer){
    // return this.http.post<Viewer>("http://13.235.52.81:8083/recommendation-service/viewer",viewer);
    return this.http.post<Viewer>("http://localhost:8083/recommendation-service/viewer",viewer);
  }
  getByEmailId(email):any{
<<<<<<< HEAD
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/viewer/"+email,{
    return this.http.get("http://localhost:8083/recommendation-service/viewer/"+email,{
=======
    return this.http.get("http://13.235.52.81:8083/recommendation-service/viewer/"+email,{
    // return this.http.get("http://localhost:8083/recommendation-service/viewer/"+email,{
>>>>>>> 4a30af9d4bd2f9f2bbad8035bfd3f5a5a07934d0
>>>>>>> fb19f56cb61b1c543c184710fd52774d8c05b6a0
      headers:new HttpHeaders({
        'Access-Control-Allow-Origin' : '*'
      })
    });
<<<<<<< HEAD
    
=======
  }
  updateDetails(viewer:Viewer):any{
    // return this.http.put("http://13.235.52.81:8083/user-service/user/"+viewer,{
      return this.http.put("http://localhost:8083/user-service/user/"+viewer,{
      headers:new HttpHeaders({
        'Access-Control-Allow-Origin' : '*'
      })
    });
  }
  deleteViewer(viewer:Viewer):any{
    // return this.http.delete("http://13.235.52.81:8083/user-service/user/"+viewer,{
      return this.http.delete("http://localhost:8083/user-service/user/"+viewer,{
      headers:new HttpHeaders({
        'Access-Control-Allow-Origin' : '*'
      })
    });
  }

  getRecInterestDocumentary(email):any{
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/recommendedInterestDocumentary/"+email);
    return this.http.get("http://localhost:8083/recommendation-service/recommendedInterestDocumentary/"+email);
  }
  getRecInterestMovie(email):any{
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/recommendedInterestMovie/"+email);
    return this.http.get("http://localhost:8083/recommendation-service/recommendedInterestMovie/"+email);
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
<<<<<<< HEAD
    // return this.http.get("http://13.235.52.81:8083/recommendation-service/recLangWebSeries/"+email);
    return this.http.get("http://localhost:8083/recommendation-service/recLangWebSeries/"+email);
=======
    return this.http.get("http://13.235.52.81:8083/recommendation-service/recLangWebSeries/"+email);
    // return this.http.get("http://localhost:8083/recommendation-service/recLangWebSeries/"+email);
>>>>>>> 4a30af9d4bd2f9f2bbad8035bfd3f5a5a07934d0
>>>>>>> fb19f56cb61b1c543c184710fd52774d8c05b6a0
  }
}
