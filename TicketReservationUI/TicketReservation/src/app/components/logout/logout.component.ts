import { Component, OnInit } from '@angular/core';
import { StorageService } from 'src/app/shared/service/storage.service';
import { Router } from '@angular/router'
@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private storageService: StorageService, private route: Router) { }

  ngOnInit() {
    this.logout();
  }
  logout() {
    this.storageService.clear();
    var token = this.storageService.getAPIToken();
    if (!token) {
      this.route.navigate(['/login'])
    }
  }
}
