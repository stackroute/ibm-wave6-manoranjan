import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AboutComponent} from './about/about.component';
import {MainNavComponent} from './main-nav/main-nav.component';
import { TermsOfUseComponent } from './terms-of-use/terms-of-use.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { FaqComponent } from './faq/faq.component';
import { DocumentaryComponent } from './documentary/documentary.component';
import { LandingComponent } from './landing/landing.component';
import { NavLandComponent } from './nav-land/nav-land.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { NavNormComponent } from './nav-norm/nav-norm.component';
import { MoviesComponent } from './movies/movies.component';
import { WishlistComponent } from './wishlist/wishlist.component';
import { MoviedescriptionComponent } from './moviedescription/moviedescription.component';
import { TvComponent } from './tv/tv.component';
import { WebComponent } from './web/web.component';
import { ImageComponent } from './image/image.component';
import { HindiserialsComponent } from './hindiserials/hindiserials.component';
import { TamilserialsComponent } from './tamilserials/tamilserials.component';
import { MarathiserialsComponent } from './marathiserials/marathiserials.component';
import { TeluguserialsComponent } from './teluguserials/teluguserials.component';
import { BengaliserialsComponent } from './bengaliserials/bengaliserials.component';
import { EnglishserialsComponent } from './englishserials/englishserials.component';
import { GujarathiserialsComponent } from './gujarathiserials/gujarathiserials.component';
import { HorrormoviesComponent } from './horrormovies/horrormovies.component';
import { ActionmoviesComponent } from './actionmovies/actionmovies.component';
import { LinknormviewComponent } from './linknormview/linknormview.component';
import { LinkpriviewComponent } from './linkpriview/linkpriview.component';
import { LinkpriviewloginComponent } from './linkpriviewlogin/linkpriviewlogin.component';
import { LinkDocNorComponent } from './link-doc-nor/link-doc-nor.component';
import { LinkDocPriComponent } from './link-doc-pri/link-doc-pri.component';
import { LinkTvNorComponent } from './link-tv-nor/link-tv-nor.component';
import { LinkTvPriComponent } from './link-tv-pri/link-tv-pri.component';
import { LinkMovieNorComponent } from './link-movie-nor/link-movie-nor.component';
import { LinkMoviePriComponent } from './link-movie-pri/link-movie-pri.component';
import { LinkWebNorComponent } from './link-web-nor/link-web-nor.component';
import { LinkWebPriComponent } from './link-web-pri/link-web-pri.component';
import { ComedymoviesComponent } from './comedymovies/comedymovies.component';
import { SuspensemoviesComponent } from './suspensemovies/suspensemovies.component';
import { ThrillermoviesComponent } from './thrillermovies/thrillermovies.component';
import { RomanticmoviesComponent } from './romanticmovies/romanticmovies.component';
import { MysterymoviesComponent } from './mysterymovies/mysterymovies.component';


const routes: Routes = [
  {path :'terms',component:TermsOfUseComponent},
  {path :'feedback',component:FeedbackComponent},
  {path :'faq',component:FaqComponent},
  {path: 'document',component:LinkDocNorComponent}, 
  {path: 'land',component:LandingComponent},
  {path: 'navland',component:NavLandComponent},
  {path: 'login',component:LoginComponent},
  {path: 'register',component:RegistrationComponent},
  {path: 'navnorm',component:NavNormComponent},
  {path: 'movie',component:MoviesComponent},
  {path: 'wish',component:WishlistComponent},
  {path: 'moviedes',component:LinkMovieNorComponent},
  {path: 'tv',component:LinkTvNorComponent},
  {path: 'web',component:LinkWebNorComponent},
  {path: 'image',component:ImageComponent},
  {path: 'linknorm',component:LinknormviewComponent},
  {path: 'linkpri',component:LinkpriviewComponent},
  {path: 'linkprilog',component:LinkpriviewloginComponent},
  {path: 'linkdocpri',component:LinkDocPriComponent},
  {path: 'linktvpri',component:LinkTvPriComponent},
  {path: 'linkmoviepri',component:LinkMoviePriComponent},
  {path: 'linkwebpri',component:LinkWebPriComponent},
  {path: 'actionmovie',component:ActionmoviesComponent},
  {path: 'horrormovie',component:HorrormoviesComponent},
  {path: 'comedymovie',component:ComedymoviesComponent},
  {path: 'suspensemovie',component:SuspensemoviesComponent},
  {path: 'thrillermovie',component:ThrillermoviesComponent},
  {path: 'romanticmovie',component:RomanticmoviesComponent},
  {path: 'mysterymovie',component:MysterymoviesComponent},
  {path: 'hindiserial',component:HindiserialsComponent},
  {path: 'englishserial',component:EnglishserialsComponent},
  {path: 'marathiserial',component:MarathiserialsComponent},
  {path: 'tamilserial',component:TamilserialsComponent},
  {path: 'taluguserial',component:TeluguserialsComponent},
  {path: 'gujarathiserial',component:GujarathiserialsComponent},
  {path: 'bengaliserial',component:BengaliserialsComponent},
  {path: 'nav',component:MainNavComponent},
  {path :'about1',component:AboutComponent},
  {path: '**', redirectTo: '/navland'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
