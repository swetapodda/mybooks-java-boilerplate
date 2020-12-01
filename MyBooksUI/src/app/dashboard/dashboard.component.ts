import { Component, OnInit } from '@angular/core';
import { Book } from '../Book';
import { User } from '../User';
import { BookService } from '../book.service';
import { AuthenticationService } from '../authentication.service';
import { MatDialog } from '@angular/material';
import { RouterService } from '../router.service';
import { BookViewComponent } from '../book-view/book-view.component';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

    searchText: string;
    bookErrMessage: string;
    books: Array<Book>;
    favourites: Array<Book>;
    book: Book;
    user: User;

    constructor(private bookService: BookService, public authenticationService: AuthenticationService,
        public dialog: MatDialog, public routerService: RouterService) {
        this.books = [];
        this.favourites = [];
        this.fetchAllBooksFromServer();
    }

    fetchAllBooksFromServer() {
        this.bookService.fetchAllBooksFromServer();

        this.bookService.getArticles().subscribe(
            result1 => {
                this.books = result1;
            },
            exception => {
                this.bookErrMessage = exception.message;
            }
        );
    }

    openFavouriteBooks() {
        this.routerService.routeToFavouriteView();
    }

    openRecommandedBooks() {
        this.routerService.routeToRecommendedView();
    }

    addToFavourate(selectedBook: Book) {
        this.bookErrMessage = '';
        this.user = new User();
        this.user = this.authenticationService.getUser();
        selectedBook.username = this.user.username;

        this.bookService.addToFavourate(selectedBook).subscribe(
            addedBook => {
                console.log(selectedBook);
            },
            error => {
                if (409 === error.status) {
                    this.bookErrMessage = "Already Added to Favourites List."
                } else {
                    this.bookErrMessage = error.message;
                }
            }
        );
    }

    openBook(selectedBook: Book) {
        const editDialog = this.dialog.open(BookViewComponent, {
            data: selectedBook
        });
    }

    ngOnInit() {
        if (this.authenticationService.getBearerToken() == null || '' === this.authenticationService.getBearerToken()) {
            this.routerService.routeToLogin();
        }
    }

    logout() {
        this.user = null;
        this.routerService.routeToIndex();
    }
}
