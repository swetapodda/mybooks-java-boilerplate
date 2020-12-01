import { async, TestBed } from '@angular/core/testing';

import { BookService } from './book.service';
import { HttpClientModule } from '@angular/common/http';

describe('BookService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [ HttpClientModule]
  }));

  beforeEach(async(() => {
    TestBed.configureTestingModule({
    })
    .compileComponents();
  }));

  it('should be created', () => {
    const service: BookService = TestBed.get(BookService);
    expect(service).toBeTruthy();
  });
});
