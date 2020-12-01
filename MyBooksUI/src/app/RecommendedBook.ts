export class RecommendedBook {
  id: Number;
  //username
  username: string;
  //cover_edition_key
  bookID: string;
  //cover_id
  bookURL: string;
  //cover_id
  bookTitle: string;
  recommendationCount: number;

  constructor() {
    this.username = '';
    this.bookID = '';
    this.bookURL = '';
    this.bookTitle = '';
    this.recommendationCount = 0;
  }
}