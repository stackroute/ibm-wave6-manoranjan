import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule, MatCheckboxModule, MatToolbarModule, MatIconModule, MatListModule} from '@angular/material';
import {MatMenuModule} from '@angular/material/menu';
import {MatSidenavModule} from '@angular/material/sidenav';
import { MainNavComponent } from './main-nav/main-nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { CarousalComponent } from './carousal/carousal.component';
import { FirstcardComponent } from './firstcard/firstcard.component';
import {Ng2CarouselamosModule} from 'ng2-carouselamos';
import { SecondcardComponent } from './secondcard/secondcard.component';
import { ThirdcardComponent } from './thirdcard/thirdcard.component';
import { FooterComponent } from './footer/footer.component';
import { AboutComponent } from './about/about.component';
import { TermsOfUseComponent } from './terms-of-use/terms-of-use.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { FaqComponent } from './faq/faq.component';
import { DocumentaryComponent } from './documentary/documentary.component';
import { LandingComponent } from './landing/landing.component';
import { NavLandComponent } from './nav-land/nav-land.component';
import { PopularclipsComponent } from './popularclips/popularclips.component';
import { InformationComponent } from './information/information.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { NavNormComponent } from './nav-norm/nav-norm.component';
import { NormcarousalComponent } from './normcarousal/normcarousal.component';
import { MoviesComponent } from './movies/movies.component';
import { MoviedescriptionComponent } from './moviedescription/moviedescription.component';
import { TvComponent } from './tv/tv.component';
import { WebComponent } from './web/web.component';
import { ImageComponent } from './image/image.component';
import { LikeCardComponent } from './like-card/like-card.component';
import { EpisoderecommendationComponent } from './episoderecommendation/episoderecommendation.component';
import { HindiserialsComponent } from './hindiserials/hindiserials.component';
import { MarathiserialsComponent } from './marathiserials/marathiserials.component';
import { TeluguserialsComponent } from './teluguserials/teluguserials.component';
import { TamilserialsComponent } from './tamilserials/tamilserials.component';
import { BengaliserialsComponent } from './bengaliserials/bengaliserials.component';
import { EnglishserialsComponent } from './englishserials/englishserials.component';
import { GujarathiserialsComponent } from './gujarathiserials/gujarathiserials.component';
import { ActionmoviesComponent } from './actionmovies/actionmovies.component';
import { HorrormoviesComponent } from './horrormovies/horrormovies.component';
import { RomanticmoviesComponent } from './romanticmovies/romanticmovies.component';
import { ThrillermoviesComponent } from './thrillermovies/thrillermovies.component';
import { SuspensemoviesComponent } from './suspensemovies/suspensemovies.component';
import { ComedymoviesComponent } from './comedymovies/comedymovies.component';
import { MysterymoviesComponent } from './mysterymovies/mysterymovies.component';
import { LinknormviewComponent } from './linknormview/linknormview.component';
import { LinkpriviewComponent } from './linkpriview/linkpriview.component';
import { LinkpriviewloginComponent } from './linkpriviewlogin/linkpriviewlogin.component';
import { WishlistComponent } from './wishlist/wishlist.component';
import { LinkDocNorComponent } from './link-doc-nor/link-doc-nor.component';
import { LinkDocPriComponent } from './link-doc-pri/link-doc-pri.component';
import { LinkTvNorComponent } from './link-tv-nor/link-tv-nor.component';
import { LinkTvPriComponent } from './link-tv-pri/link-tv-pri.component';
import { LinkMovieNorComponent } from './link-movie-nor/link-movie-nor.component';
import { LinkMoviePriComponent } from './link-movie-pri/link-movie-pri.component';
import { LinkWebNorComponent } from './link-web-nor/link-web-nor.component';
import { LinkWebPriComponent } from './link-web-pri/link-web-pri.component';


@NgModule({
  declarations: [
    AppComponent,
    MainNavComponent,
    CarousalComponent,
    FirstcardComponent,
    SecondcardComponent,
    ThirdcardComponent,
    FooterComponent,
    AboutComponent,
    TermsOfUseComponent,
    FeedbackComponent,
    FaqComponent,
    DocumentaryComponent,
    LandingComponent,
    NavLandComponent,
    PopularclipsComponent,
    InformationComponent,
    LoginComponent,
    RegistrationComponent,
    NavNormComponent,
    NormcarousalComponent,
    MoviesComponent,
    MoviedescriptionComponent,
    TvComponent,
    WebComponent,
    ImageComponent,
    EpisoderecommendationComponent,
    LikeCardComponent,
    HindiserialsComponent,
    MarathiserialsComponent,
    TeluguserialsComponent,
    TamilserialsComponent,
    BengaliserialsComponent,
    EnglishserialsComponent,
    GujarathiserialsComponent,
    ActionmoviesComponent,
    HorrormoviesComponent,
    RomanticmoviesComponent,
    ThrillermoviesComponent,
    SuspensemoviesComponent,
    ComedymoviesComponent,
    MysterymoviesComponent,
    LinknormviewComponent,
    LinkpriviewComponent,
    LinkpriviewloginComponent,
    WishlistComponent,
    LinkDocNorComponent,
    LinkDocPriComponent,
    LinkTvNorComponent,
    LinkTvPriComponent,
    LinkMovieNorComponent,
    LinkMoviePriComponent,
    LinkWebNorComponent,
    LinkWebPriComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    NoopAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    MatMenuModule,
    MatSidenavModule,
    LayoutModule,
    MatToolbarModule,
    MatIconModule,
    MatListModule,
    Ng2CarouselamosModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  exports: [MatButtonModule, MatCheckboxModule],
})
export class AppModule { }
