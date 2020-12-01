import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FavouriteComponent } from './favourite.component';
import { MatCardModule, MatFormFieldModule, MatInputModule, MatDialogModule, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FilterPipe } from '../filter.pipe';
import { AuthenticationService } from '../authentication.service';
import { RouterService } from '../router.service';
import { BookService } from '../book.service';

describe('FavoriteComponent', () => {
  let component: FavouriteComponent;
  let fixture: ComponentFixture<FavouriteComponent>;
  let filterPipe: FilterPipe

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ MatCardModule, MatFormFieldModule, MatInputModule, FormsModule, ReactiveFormsModule,
        BrowserAnimationsModule, HttpClientTestingModule, RouterTestingModule,
        MatDialogModule],
      declarations: [ FavouriteComponent, FilterPipe ],
      providers: [ FilterPipe,  AuthenticationService, RouterService, BookService,
        { provide: MatDialogRef, useValue: {} }, { provide: MAT_DIALOG_DATA, useValue: [] }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    filterPipe = TestBed.get(FilterPipe);
    fixture = TestBed.createComponent(FavouriteComponent);
    component = fixture.debugElement.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
