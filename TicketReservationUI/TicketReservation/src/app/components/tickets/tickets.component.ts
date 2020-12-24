import { Component, OnInit } from '@angular/core';
import { NotifyService } from 'src/app/shared/service/notify.service';
import { TicketService } from 'src/app/shared/service/ticket.service';
import { StorageService } from 'src/app/shared/service/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.css']
})
export class TicketsComponent implements OnInit {
  ticketList;
  constructor(private ticketService: TicketService,
    private storageService: StorageService,
    private route: Router,
    private notifyService: NotifyService) { }

  ngOnInit() {
    var token = this.storageService.getAPIToken();
    if (!token) {
      this.route.navigate(['/login'])
    }
    this.getTicketList();
  }

  getTicketList() {
    this.ticketService.getTicket().subscribe(response => {
      this.ticketList = response;
    },
      (err) => {
        this.notifyService.show(err.error.message)
        console.log(err)
      },
      () => {

      }
    )
  }

}
