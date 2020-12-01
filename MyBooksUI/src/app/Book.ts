export class Book {
  id: Number;
  //username
  username: string;
  //cover_edition_key
  bookID: string;
  //cover_id
  bookURL: string;
  //title
  bookTitle: string;

  constructor() {
    this.username = '';
    this.bookID = '';
    this.bookURL = '';
    this.bookTitle = '';
  }
}