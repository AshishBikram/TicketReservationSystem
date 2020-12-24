import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NotifyService } from 'src/app/shared/service/notify.service';
import { Router } from '@angular/router';
import { StorageService } from 'src/app/shared/service/storage.service';
import { PaymentService } from 'src/app/shared/service/payment.service';
@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  reservationId;
  ticketDetails;
  constructor(private activateRoute: ActivatedRoute,
    private notifyService: NotifyService,
    private route: Router,
    private storageService: StorageService,
    private paymentService: PaymentService) { }

  ngOnInit() {
    var token = this.storageService.getAPIToken();
    if (!token) {
      this.route.navigate(['/login'])
    }
    this.activateRoute.params.subscribe(params => {
      this.reservationId = params.id
    })
    this.getticketDetails(this.reservationId);
  }
  getticketDetails(reservationId) {
    this.paymentService.getTicketDetails(reservationId).subscribe(
      (response: any) => {
        this.ticketDetails = response;
      },
      (err) => {
        this.notifyService.show(err.error.message);
      }
    )
  }
  pay() {
    this.paymentService.pay(this.reservationId).subscribe(response => {
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

  goTo() {
    this.route.navigate(["/reservation-list"]);
  }
}
