import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardComponent } from './dashboard.component';
import { MatCardModule, MatFormFieldModule, MatInputModule, MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FilterPipe } from '../filter.pipe';
import { AuthenticationService } from '../authentication.service';
import { RouterService } from '../router.service';
import { BookService } from '../book.service';

describe('DashboardComponent', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;
  let filterPipe: FilterPipe;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ MatCardModule, MatFormFieldModule, MatInputModule, FormsModule, ReactiveFormsModule,
        BrowserAnimationsModule, HttpClientTestingModule, RouterTestingModule,
        MatDialogModule],
      declarations: [ DashboardComponent, FilterPipe ],
      providers: [ FilterPipe,  AuthenticationService, RouterService, BookService,
        { provide: MatDialogRef, useValue: {} }, { provide: MAT_DIALOG_DATA, useValue: [] }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    filterPipe = TestBed.get(FilterPipe);
    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.debugElement.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
