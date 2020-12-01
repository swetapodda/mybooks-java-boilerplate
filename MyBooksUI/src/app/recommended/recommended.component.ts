import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';
import { AuthenticationService } from '../authentication.service';
import { RouterService } from '../router.service';
import { RecommendedBook } from '../RecommendedBook';

@Component({
  selector: 'app-recommended',
  templateUrl: './recommended.component.html',
  styleUrls: ['./recommended.component.css']
})
export class RecommendedComponent implements OnInit {

  recommandedErrMessage: string;
  recommanded: Array<RecommendedBook>;

  constructor(private bookService: BookService, public authenticationService: AuthenticationService,
    public routerService: RouterService) {

    if (authenticationService.getBearerToken() == null) {
      routerService.routeToLogin();
    }
    this.recommanded = [];
    this.fetchAllRecommandedFromServer();
  }

  fetchAllRecommandedFromServer() {
    this.bookService.fetchAllRecommandedFromServer();

    this.bookService.getRecommanded().subscribe(
      result3 => {
        this.recommanded = result3;
      },
      exception => {
        this.recommandedErrMessage = exception.message;
      }
    );
  }

  routeToDashboard() {
    this.routerService.routeToDashboard();
  }

  routeToFavouriteView() {
    this.routerService.routeToFavouriteView();
  }

  logout() {
    this.routerService.routeToIndex();
  }

  ngOnInit() {
    if (this.authenticationService.getBearerToken() == null) {
      this.routerService.routeToLogin();
    }
  }

}
