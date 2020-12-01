import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule, MatExpansionModule, MatCardModule, MatDialogModule } from '@angular/material';

import { MatInputModule } from '@angular/material/input';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RouterModule } from '@angular/router';
import { SignupComponent } from './signup/signup.component';
import { RouterService } from './router.service';
import { AuthenticationService } from './authentication.service';
import { DashboardComponent } from './dashboard/dashboard.component';
import { RecommendedComponent } from './recommended/recommended.component';
import { HttpClientModule } from '@angular/common/http';
import { BookViewComponent } from './book-view/book-view.component';
import { FilterPipe } from './filter.pipe';
import { FavouriteComponent } from './favourite/favourite.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    DashboardComponent,
    RecommendedComponent,
    BookViewComponent,
    FilterPipe,
    FavouriteComponent,
    RecommendedComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MatInputModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatExpansionModule,
    MatCardModule,
    HttpClientModule,
    RouterModule,
    MatDialogModule
  ],
  providers: [
      RouterService,
      AuthenticationService
    ],
  bootstrap: [AppComponent],
  entryComponents: [BookViewComponent]
})
export class AppModule { }
