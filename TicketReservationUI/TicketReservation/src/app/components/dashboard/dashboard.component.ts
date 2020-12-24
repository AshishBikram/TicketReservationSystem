import { Component, OnInit } from '@angular/core';
import { ReservationService } from 'src/app/shared/service/reservation.service';
import { StorageService } from 'src/app/shared/service/storage.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(
    private reservationService: ReservationService,
    private storageService: StorageService,
    private route: Router) { }

  token_string;
  token;
  chartData;
  ngOnInit() {
    var token = this.storageService.getAPIToken();
    if (!token) {
      this.route.navigate(['/login'])
    }
    this.getChartData();
  }

  getChartData() {
    this.reservationService.getChartData().subscribe(
      response => {
        this.chartData = response;
      },
      (error) => {
        console.log(error);
      })
  }

}
