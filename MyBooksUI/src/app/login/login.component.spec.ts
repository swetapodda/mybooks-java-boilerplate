import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login.component';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';

import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AuthenticationService } from '../authentication.service';
import { RouterService } from '../router.service';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authenticationService: AuthenticationService;
  let httpTestingController: HttpTestingController;
  let routerService: RouterService;
  let router: RouterTestingModule;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ MatCardModule, MatFormFieldModule, MatInputModule, FormsModule, ReactiveFormsModule,
        HttpClientTestingModule, RouterTestingModule, BrowserAnimationsModule ],
      declarations: [ LoginComponent ],
      providers: [ AuthenticationService, RouterService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    authenticationService = TestBed.get(AuthenticationService);
    httpTestingController = TestBed.get(HttpTestingController);
    routerService = TestBed.get(RouterService);
    router = TestBed.get(RouterTestingModule);

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
