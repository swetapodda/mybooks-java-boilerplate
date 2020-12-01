import { NgModule } from '@angular/core'; 
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { FavouriteComponent } from './favourite/favourite.component';
import { RecommendedComponent } from './recommended/recommended.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: SignupComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'favouriteView', component: FavouriteComponent},
  {path: 'recommendedView', component: RecommendedComponent},
  {path: '', redirectTo: 'dashboard', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
  constructor() {

  }
}
