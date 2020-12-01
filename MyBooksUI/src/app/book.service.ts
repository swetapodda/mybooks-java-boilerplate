import { Injectable } from '@angular/core';
import { Book } from './Book';
import { RecommendedBook } from "./RecommendedBook";
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { AuthenticationService } from './authentication.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  books: Array<Book>;
  favourites: Array<Book>;
  recommanded: Array<RecommendedBook>;
  articlesSubject: BehaviorSubject<Array<Book>>;
  favouritesSubject: BehaviorSubject<Array<Book>>;
  recommandedSubject: BehaviorSubject<Array<RecommendedBook>>;
  book: Book;

  constructor(public httpClient: HttpClient, public authService: AuthenticationService) {
    this.books = [];
    this.favourites = [];
    this.recommanded = [];
    this.articlesSubject = new BehaviorSubject<Array<Book>>([]);
    this.favouritesSubject = new BehaviorSubject<Array<Book>>([]);
    this.recommandedSubject = new BehaviorSubject<Array<RecommendedBook>>([]);
  }

  fetchAllBooksFromServer() {
    return this.httpClient.get<Array<Book>>(
        'https://openlibrary.org/subjects/space_flight.json?limit=20&offset=1',
    ).subscribe(res => {
        res["works"].forEach(element => {
            if('' !== element.title) {
                this.book = new Book();
                this.book.bookID = element.cover_edition_key;
                this.book.bookURL = 'https://covers.openlibrary.org/w/id/'+ (element.cover_id) + '.jpg';
                this.book.bookTitle = element.title;
                this.books.push(this.book);
            }
        });
        this.articlesSubject.next(this.books);
    }, err => {
        this.articlesSubject.next(err);
    });
  }

  fetchAllFavouritesFromServer(username) {
    return this.httpClient.get<Array<Book>>(
        'http://localhost:9400/api/v1/favourate/' + username //,
    ).subscribe(res1 => {
        this.favourites = res1;
        this.favouritesSubject.next(this.favourites);
    }, err => {
        this.favouritesSubject.next(err);
        this.favouritesSubject.next(this.favourites);
    });
  }

  fetchAllRecommandedFromServer() {
    return this.httpClient.get<Array<RecommendedBook>>(
        'http://localhost:9500/api/v1/recommendation',
    ).subscribe(res2 => {
        this.recommanded = res2;
        this.recommandedSubject.next(this.recommanded);
    }, err => {
        this.recommandedSubject.next(err);
        this.recommandedSubject.next(this.recommanded);
    });
  }

  getArticles(): BehaviorSubject<Array<Book>> {
    return this.articlesSubject;
  }

  getFavourites(): BehaviorSubject<Array<Book>> {
    return this.favouritesSubject;
  }

  getRecommanded(): BehaviorSubject<Array<RecommendedBook>> {
    return this.recommandedSubject;
  }

  addToFavourate(newBook: Book): Observable<Book> {
    return this.httpClient.post<Book>('http://localhost:9400/api/v1/favourate', newBook
    ).pipe(
      tap(data => {
        this.favourites.push(data);
        this.favouritesSubject.next(this.favourites);
      })
    );
  }

  deleteFromFavArticle(username: string, gipherID: string) {
    return this.httpClient.delete<Array<Book>>(
        'http://localhost:9400/api/v1/favourate/' + username + '/' + gipherID
    ).subscribe(res1 => {
        this.favourites = res1;
        this.favouritesSubject.next(this.favourites);
    }, err => {
        this.favouritesSubject.next(err);
        this.favouritesSubject.next(this.favourites);
    });
  }


  openRecommandedBook(title: string): Observable<Book> {
    return this.httpClient.get<Book>('http://localhost:9500/api/v1/recommendation/' + title
    ).pipe(
      tap(count => {
        console.log("Total fav count found - ", count);
      })
    );
  }
}
