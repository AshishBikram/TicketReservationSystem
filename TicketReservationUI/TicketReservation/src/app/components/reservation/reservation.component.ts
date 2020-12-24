import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NotifyService } from 'src/app/shared/service/notify.service';
import { ReservationService } from 'src/app/shared/service/reservation.service';
import { TicketService } from 'src/app/shared/service/ticket.service';
import { StorageService } from 'src/app/shared/service/storage.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {
  ticketResponse;
  total_quantity = 1;
  submitInProgress = false;
  name: string;
  totalPrice: number;
  ticketId;
  ticketDetails;
  constructor(private activateRoute: ActivatedRoute,
    private ticketService: TicketService,
    private reservationService: ReservationService,
    private notifyService: NotifyService,
    private storageService: StorageService,
    private route: Router) { }
  ngOnInit() {
    var token = this.storageService.getAPIToken();
    if (!token) {
      this.route.navigate(['/login'])
    }
    this.activateRoute.params.subscribe(params => {
      this.ticketId = params.id
    })
    this.getTicketDetails(this.ticketId);
  }

  getTicketDetails(ticketId) {
    this.ticketService.getTicketDetail(ticketId).subscribe(
      response => {
        this.ticketDetails = response
      },
      (err) => {
        console.log(err)
      },
      () => {
        this.calculateTotal();
      }
    )
  }
  calculateTotal() {
    this.totalPrice = this.total_quantity * this.ticketDetails.PRICE;
  }

  submit() {
    this.submitInProgress = true;
    if (!this.name) {
      this.submitInProgress = false;
      return
    }
    var data = {
      "TICKET_ID": this.ticketDetails.ID,
      "NAME": this.name,
      "TOTAL_TICKET": this.total_quantity,
      "TOTAL_PRICE": this.totalPrice
    }
    console.log(data)
    this.reservationService.reserveTicket(data).subscribe(response => {
      this.ticketResponse = response;
      this.notifyService.show("Ticket Reserved Successfully")

    },
      (err) => {
        this.notifyService.show(err.error.message);
      },
      () => {
        this.submitInProgress = false;
        this.route.navigate(["/payment/" + this.ticketResponse.ID])
      }
    )
    this.route.navigate(["/payment/1"])
  }

}
