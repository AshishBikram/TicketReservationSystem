import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { StorageService } from '../../shared/service/storage.service'
@Injectable({
  providedIn: 'root'
})
export class HttpOptionsService {
  token_string;
  token;
  constructor(private storageService: StorageService) { }

  getHttpOptions() {
    this.token_string = this.storageService.getAPIToken();
    this.token = this.token_string ? JSON.parse(this.token_string) : null;
    return {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + this.token.access_token.toString()
      })
    };
  }
}
