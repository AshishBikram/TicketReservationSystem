import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpOptionsService } from './http-options.service';
@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  token_string;
  token;
  constructor(private http: HttpClient, private httpOptionsService: HttpOptionsService) { }

  reserveTicket(data) {
    return this.http.post("http://localhost:8080/reservation/", data, this.httpOptionsService.getHttpOptions())
  }

  getAllReservation() {
    return this.http.get("http://localhost:8080/reservation/", this.httpOptionsService.getHttpOptions())
  }

  getChartData() {
    return this.http.get("http://localhost:8080/reservation/chart-data", this.httpOptionsService.getHttpOptions())
  }
}
