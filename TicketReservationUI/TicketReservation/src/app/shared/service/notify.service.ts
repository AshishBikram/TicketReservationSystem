import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material';

@Injectable({
  providedIn: 'root'
})
export class NotifyService {
  constructor(private notification: MatSnackBar) { }

  show(message: string) {
    this.notification.open(message, 'Ok', {
      duration: 5000
    });
  }
}
