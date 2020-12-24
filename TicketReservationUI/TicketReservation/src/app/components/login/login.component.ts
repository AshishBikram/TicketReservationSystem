import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms'
import { LoginService } from '../../shared/service/login.service'
import { StorageService } from '../../shared/service/storage.service'
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginInProgress = false;
  formSubmitted = false;
  token;
  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  })
  constructor(private loginService: LoginService, private storageService: StorageService) { }

  ngOnInit() {
  }

  submit() {
    this.loginInProgress = true;
    this.formSubmitted = true;
    if (this.loginForm.invalid) {
      this.loginInProgress = false;
      return;
    }
    this.loginService.login(this.loginForm.value).subscribe((response: any) => {
      console.log(this.getTokenObject(response))
      this.storageService.setAPIToken(JSON.stringify(this.getTokenObject(response)));
    },
      (err) => {
        console.log(err)
      },
      () => {
        window.location.href = '/dashboard';
        this.loginInProgress = false;
      }
    )
    this.loginInProgress = false;
    this.formSubmitted = false;

  }
  getTokenObject(responseObject) {
    return {
      expiresAt: responseObject.expires_in,
      token_type: responseObject.token_type,
      access_token: responseObject.access_token,
      refresh_token: responseObject.refresh_token
    };
  }

  getExpiryDate(tokenExpiresIn) {
    const dateTime = new Date();
    dateTime.setSeconds(dateTime.getSeconds() + tokenExpiresIn);
    return dateTime;
  }
}
