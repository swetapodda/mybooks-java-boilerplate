import { Component, OnInit } from '@angular/core';
import { RouterService } from '../router.service';
import { FormControl, Validators, FormBuilder } from '@angular/forms';
import { AuthenticationService } from '../authentication.service';

@Component({
    selector: 'app-signup',
    templateUrl: './signup.component.html',
    styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

    submitMessage: String;
    firstName = new FormControl('', [Validators.required]);
    lastName = new FormControl('', [Validators.required]);
    username = new FormControl('', [Validators.required, Validators.minLength(5)]);
    password = new FormControl('', [Validators.required, Validators.minLength(8)]);

    authenticationService: AuthenticationService;

    constructor(authenticationService: AuthenticationService,
        public routerService: RouterService) {
        this.authenticationService = authenticationService;
    }

    ngOnInit() {
    }

    switchView() {
        this.routerService.routeToLogin();
    }

    registerUser() {
        this.submitMessage = '';
        if (this.firstName.invalid || this.lastName.invalid
            || this.username.invalid || this.password.invalid) {
            if (this.firstName.hasError('required')) {
                this.submitMessage = 'Firstname is mandatory ';
            }
            if (this.lastName.hasError('required')) {
                this.submitMessage = this.submitMessage + 'LastName is mandatory ';
            }
            if (this.username.hasError('required') || this.password.hasError('required')) {
                this.submitMessage = this.submitMessage + 'Username and password are mandatory ';
            }
            if (this.username.hasError('minlength')) {
                this.submitMessage = this.submitMessage + 'Username must have min 5 characters ';
            }
            if (this.password.hasError('minlength')) {
                this.submitMessage = this.submitMessage + 'Password must have min 8 characters ';
            }
        } else {
            this.authenticationService.setUser({
                'username': this.username.value, 'firstName': this.firstName.value,
                'lastName': this.lastName.value, 'password': this.password.value
            });

            this.authenticationService.registerUser({
                'username': this.username.value, 'firstName': this.firstName.value,
                'lastName': this.lastName.value, 'password': this.password.value
            }).subscribe(
                res => {
                    if (res === true) {
                        this.routerService.routeToDashboard();
                    } else {
                        this.submitMessage = 'Unable to register as user already exists.';
                    }
                },
                err => {
                    this.submitMessage = 'Unable to register as user already exists.';
                }
            );
        }
    }
}
