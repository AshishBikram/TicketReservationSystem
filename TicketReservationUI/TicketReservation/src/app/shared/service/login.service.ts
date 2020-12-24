import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization': 'Basic cGl4ZWx0cmljZTpwaXhlbHRyaWNlLXNlY3JldC1rZXk='
    })
  };
  constructor(private http: HttpClient) { }
  login(loginData): Observable<any> {
    loginData.grant_type = environment.grant_type;
    console.log(loginData)
    let body = new URLSearchParams();
    body.set('username', 'admin@pixeltrice.com');
    body.set('password', 'password');
    body.set('grant_type', environment.grant_type);
    var url = "http://localhost:8080/oauth/token"
    return this.http.post(url, body.toString(), this.httpOptions);
  }

  get(token): Observable<any> {
    var httpOptions2 = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + token.toString()
      })
    };
    return this.http.get("http://localhost:8080/home", httpOptions2)
  }
}
