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
    MoviesComponent
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
