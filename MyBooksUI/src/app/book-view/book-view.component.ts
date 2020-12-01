import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Book } from '../Book';
import { AuthenticationService } from '../authentication.service';
import { RouterService } from '../router.service';

@Component({
  selector: 'app-book-view',
  templateUrl: './book-view.component.html',
  styleUrls: ['./book-view.component.css']
})
export class BookViewComponent implements OnInit {

  book: Book;

  constructor(private matDialogRef: MatDialogRef<BookViewComponent>,
    @Inject(MAT_DIALOG_DATA) private data: Book, private authenticationService: AuthenticationService, private routerService: RouterService) {
    this.book = this.data;
  }

  ngOnInit() {
    if (this.authenticationService.getBearerToken() == null) {
      this.routerService.routeToLogin();
    }
  }
}
