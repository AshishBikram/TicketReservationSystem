import { Component, OnInit } from '@angular/core';
import { NotifyService } from 'src/app/shared/service/notify.service';
import { ReservationService } from 'src/app/shared/service/reservation.service';
import { Router } from '@angular/router';
import { TicketService } from 'src/app/shared/service/ticket.service';
import { PaymentService } from 'src/app/shared/service/payment.service'
import { StorageService } from 'src/app/shared/service/storage.service';

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent implements OnInit {
  reserveTicketList;
  reserveTicketFormatList = [];
  ticketDetail;
  constructor(private reservationService: ReservationService,
    private notifyService: NotifyService,
    private route: Router,
    private ticketService: TicketService,
    private storageService: StorageService,
    private paymentService: PaymentService) { }

  ngOnInit() {
    var token = this.storageService.getAPIToken();
    if (!token) {
      this.route.navigate(['/login'])
    }
    this.getReservedList()
  }
  getReservedList() {
    this.reservationService.getAllReservation().subscribe(response => {
      this.reserveTicketList = response;
    },
      err => {
        this.notifyService.show("Error while loading data");
      },
      () => {
        this.formatTicketData(this.reserveTicketList);
      }
    )
  }
  formatTicketData(reserveTicketList) {
    for (let reserveTicket of reserveTicketList) {
      this.ticketService.getTicketDetail(reserveTicket.TICKET_ID).subscribe(res => {
        reserveTicket.ticket = res;
        this.reserveTicketFormatList.push(reserveTicket);
      })
    }
    console.log(this.reserveTicketFormatList)

  }

  pay(reservationId) {
    this.paymentService.pay(reservationId).subscribe(response => {
      this.notifyService.show("Payment Successfully Done");
    },
      (err) => {
        this.notifyService.show("Error on Payment")
      },
      () => {
        this.route.navigate(["/reservation-list"]);

      }
    )
  }

}
