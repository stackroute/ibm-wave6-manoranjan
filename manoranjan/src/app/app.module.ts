import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule} from '@angular/common/http';
import {FormsModule,ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatCardModule} from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatRadioModule} from '@angular/material/radio';
import {MatButtonModule, MatCheckboxModule, MatToolbarModule, MatIconModule, MatListModule,MatFormFieldModule, MatInputModule} from '@angular/material';
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
import {MatStepperModule} from '@angular/material/stepper';

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
    LikeCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    MatMenuModule,
    MatSidenavModule,
    LayoutModule,
    MatToolbarModule,
    MatIconModule,
    MatListModule,
    Ng2CarouselamosModule,
    MatStepperModule,
    MatCardModule,
    MatGridListModule,
    MatRadioModule,
    MatFormFieldModule, 
    MatInputModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  exports: [MatButtonModule, MatCheckboxModule],
})
export class AppModule { }
