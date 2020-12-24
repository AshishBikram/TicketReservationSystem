import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpOptionsService } from './http-options.service';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  token_string;
  token;
  constructor(private http: HttpClient, private httpOptionsService: HttpOptionsService) { }

  getTicketDetails(reservationId) {
    return this.http.get("http://localhost:8080/reservation/" + reservationId, this.httpOptionsService.getHttpOptions())
  }

  pay(id) {
    return this.http.post("http://localhost:8080/pay/" + id, id, this.httpOptionsService.getHttpOptions())
  }
}
