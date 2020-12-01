import { Component, OnInit } from '@angular/core';
import { User } from '../User';
import { MatDialog } from '@angular/material/dialog';
import { Book } from '../Book';
import { BookService } from '../book.service';
import { BookViewComponent } from '../book-view/book-view.component';
import { AuthenticationService } from '../authentication.service';
import { RouterService } from '../router.service';

@Component({
  selector: 'app-open-favourite-view',
  templateUrl: './favourite.component.html',
  styleUrls: ['./favourite.component.css']
})
export class FavouriteComponent implements OnInit {

  favouritesErrMessage: string;
  favourites: Array<Book>;

  user: User;

  constructor(private bookService: BookService, public authenticationService: AuthenticationService,
    public routerService: RouterService, public dialog: MatDialog) {
    this.favourites = [];
    this.user = new User();
    this.user = this.authenticationService.getUser();
  }

  fetchAllFavouritesFromServer() {
    this.bookService.fetchAllFavouritesFromServer(this.user.username);
    this.bookService.getFavourites().subscribe(
      result2 => {
        this.favourites = result2;
      },
      exception => {
        this.favouritesErrMessage = exception.message;
      }
    );
  }

  deleteFromFavArticle(selectedBook: Book) {
    this.bookService.deleteFromFavArticle(this.user.username, selectedBook.bookID);
    this.bookService.getFavourites().subscribe(
      result2 => {
        this.favourites = result2;
      },
      exception => {
        this.favouritesErrMessage = exception.message;
      }
    );
  }

  openArticle(selectedbook: Book) {
    const editDialog = this.dialog.open(BookViewComponent, {
      data: selectedbook
    });
  }

  routeToDashboard() {
    this.routerService.routeToDashboard();
  }

  routeToRecommendedView() {
    this.routerService.routeToRecommendedView();
  }

  logout() {
    this.user = new User();
    this.authenticationService.setUser(new User());

    this.routerService.routeToIndex();
  }

  ngOnInit() {
    if (this.authenticationService.getBearerToken() == null) {
      this.routerService.routeToLogin();
    } else {
      this.fetchAllFavouritesFromServer();
    }
  }
}