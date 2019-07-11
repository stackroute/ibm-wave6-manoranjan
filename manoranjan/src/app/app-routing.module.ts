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
import { MoviedescriptionComponent } from './moviedescription/moviedescription.component';
import { TvComponent } from './tv/tv.component';
import { WebComponent } from './web/web.component';
import { ImageComponent } from './image/image.component';
import { LinknormviewComponent } from './linknormview/linknormview.component';
import { LinkpriviewComponent } from './linkpriview/linkpriview.component';
import { LinkpriviewloginComponent } from './linkpriviewlogin/linkpriviewlogin.component';

const routes: Routes = [
  {path :'terms',component:TermsOfUseComponent},
  {path :'feedback',component:FeedbackComponent},
  {path :'faq',component:FaqComponent},
  {path: 'document',component:DocumentaryComponent}, 
  {path: 'land',component:LandingComponent},
  {path: 'navland',component:NavLandComponent},
  {path: 'login',component:LoginComponent},
  {path: 'register',component:RegistrationComponent},
  {path: 'navnorm',component:NavNormComponent},
  {path: 'movie',component:MoviesComponent},
  {path: 'moviedes',component:MoviedescriptionComponent},
  {path: 'tv',component:TvComponent},
  {path: 'web',component:WebComponent},
  {path: 'image',component:ImageComponent},
  {path: 'linknorm',component:LinknormviewComponent},
  {path: 'linkpri',component:LinkpriviewComponent},
  {path: 'linkprilog',component:LinkpriviewloginComponent},
  {path: 'nav',component:MainNavComponent},
  {path :'about1',component:AboutComponent},
  {path: '**', redirectTo: '/navland'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
