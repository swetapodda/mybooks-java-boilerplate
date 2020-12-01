import { async, ComponentFixture, TestBed, inject } from '@angular/core/testing';

import { BookViewComponent } from './book-view.component';
import { MatCardModule, MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { HttpClient, HttpHandler, HttpClientModule } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';
import { AppRoutingModule } from '../app-routing.module';
import { RouterService } from '../router.service';
import { RouterTestingModule } from '@angular/router/testing';

describe('BookViewComponent', () => {
  let component: BookViewComponent;
  let fixture: ComponentFixture<BookViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        MatCardModule, MatDialogModule, HttpClientModule, RouterModule, RouterTestingModule
      ],
      providers: [{ provide: MatDialogRef, useValue: {} }, { provide: MAT_DIALOG_DATA, useValue: [] }],
      declarations: [ BookViewComponent ]
    })
    .compileComponents();
  }));

  it('should create the article view', () => {
    fixture = TestBed.createComponent(BookViewComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it('should render title in a mat-card-title tag', () => {
    fixture = TestBed.createComponent(BookViewComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('mat-card-subtitle').textContent).toContain('Title:');
  });
});
