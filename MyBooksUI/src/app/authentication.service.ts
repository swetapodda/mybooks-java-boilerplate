import { Injectable } from '@angular/core';
import { User } from './User';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  user: User;

  validateUserURL = 'http://localhost:9300/api/v1/auth/validate';

  registerURL = 'http://localhost:9300/api/v1/auth/register';

  constructor(public httpClient: HttpClient) {
  }

  authenticateUser (data: User) {
    this.user = data;
    return this.httpClient.post(this.validateUserURL, data);
  }

  registerUser (data: User) {
    this.user = data;
    return this.httpClient.post(this.registerURL, data);
  }

  setUser(data: User) {
    this.user = data;
  }

  getUser() {
    return this.user;
  }

  setBearerToken(token: string) {
    localStorage.setItem('bearerToken', token);
  }

  getBearerToken() {
    return localStorage.getItem('bearerToken');
  }

  removeBearerToken() {
    console.log('removeBearerToken starts');
    this.getBearerToken = null;
    localStorage.removeItem('bearerToken');
  }
}
