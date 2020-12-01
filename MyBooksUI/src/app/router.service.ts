import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class RouterService {

  constructor(public router: Router, public authenticationService: AuthenticationService) { }

  routeToRegisteration() {
      console.log('Route to register');
      this.router.navigate(['register']);
  }

  routeToLogin() {
    console.log('Route to login');
      this.router.navigate(['login']);
  }

  routeToDashboard() {
      this.router.navigate(['dashboard']);
  }

  routeToEditNoteView(noteId) {
    this.router.navigate(['dashboard', {
      outlets: {
        noteEditOutlet: ['note', noteId, 'edit']
      }
    }]);
  }

  routeToNoteView() {
    this.router.navigate(['dashboard/view/noteview']);
  }

  routeToListView() {
    this.router.navigate(['dashboard/view/listview']);
  }

  routeToIndex() {
    this.authenticationService.removeBearerToken();
    this.router.navigate(['login']);
  }

  routeToFavouriteView() {
    this.router.navigate(['favouriteView']);
  }

  routeToRecommendedView() {
    this.router.navigate(['recommendedView']);
  }
}
