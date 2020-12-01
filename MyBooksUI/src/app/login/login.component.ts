import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RouterService } from '../router.service';
import { AuthenticationService } from '../authentication.service';
import { User } from '../User';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    submitMessage: string;
    username = new FormControl();
    password = new FormControl();
    loginFormGroup: FormGroup;
    user: User;

    constructor(private routerService: RouterService, private authenticationService: AuthenticationService) {
        this.loginFormGroup = new FormGroup({

        });
        this.username.setValidators(Validators.required);
        this.password.setValidators(Validators.required);
        this.loginFormGroup.addControl('username', this.username);
        this.loginFormGroup.addControl('password', this.password);
    }

    ngOnInit() {
    }

    loginSubmit() {
      console.log('inside submit login... start');
      console.log(this.username.invalid + '' + this.password.invalid);
      if (this.username.invalid || this.password.invalid) {
        if (this.username.hasError('required') || this.password.hasError('required')) {
          this.submitMessage = 'Username and password are mandatory';
        } else if (this.username.hasError('minlength')) {
          this.submitMessage = 'Username must have min 5 characters';
        } else {
          this.submitMessage = 'Password must have min 8 characters';
        }
      } else {
        this.user = new User();
        this.user.username = this.username.value;
        this.user.password = this.password.value;
        console.log('Before authenticate in login');
        this.authenticationService.authenticateUser(this.user).subscribe(
          res => {
            console.log('before res in login');
            console.log(res);
            console.log('after res in login');
            if (null != res) {
              console.log('inside submit login... printing token');
              console.log(res);
              console.log(res['token']);
              console.log('inside submit login... after printing token');
              this.authenticationService.setBearerToken(res['token']);
              this.routerService.routeToDashboard();
            } else {
              this.submitMessage = 'Unable to authenticate user';
            }
          },
          err => {
            if (null != err && null != err.error && null != err.error.text) {
              this.authenticationService.setBearerToken(err.error.text);
              this.routerService.routeToDashboard();
            } else {
              this.submitMessage = 'Unable to authenticate user';
            }
          }
        );
      }
    }

    switchView() {
        this.routerService.routeToRegisteration();
    }
}
