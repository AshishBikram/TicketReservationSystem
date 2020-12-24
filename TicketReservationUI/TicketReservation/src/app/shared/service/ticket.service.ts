import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpOptionsService } from './http-options.service';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class TicketService {
  httpOptions = this.httpOptionsService.getHttpOptions();
  constructor(private http: HttpClient, private httpOptionsService: HttpOptionsService) { }

  getTicket(): Observable<any> {
    return this.http.get("http://localhost:8080/ticket/", this.httpOptions)
  }

  getTicketDetail(ticketId): Observable<any> {
    return this.http.get("http://localhost:8080/ticket/" + ticketId, this.httpOptions)
  }
}
