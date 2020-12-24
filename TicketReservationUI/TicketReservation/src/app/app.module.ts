import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import {
  MatFormFieldModule,
  MatInputModule,
  MatCardModule,
  MatToolbarModule, MatIconModule, MatSidenavModule, MatListModule, MatButtonModule, MatSnackBarModule
} from '@angular/material';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ReservationComponent } from './components/reservation/reservation.component';
import { PaymentComponent } from './components/payment/payment.component';
import { TicketsComponent } from './components/tickets/tickets.component';
import { HeaderComponent } from './components/header/header.component';
import { ReservationListComponent } from './components/reservation-list/reservation-list.component';
import { LineGraphComponent } from './components/line-graph/line-graph.component';
import { LogoutComponent } from './components/logout/logout.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    ReservationComponent,
    PaymentComponent,
    TicketsComponent,
    HeaderComponent,
    ReservationListComponent,
    LineGraphComponent,
    LogoutComponent
  ],
  imports: [
    BrowserModule,

    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatToolbarModule, MatIconModule, MatSidenavModule, MatListModule, MatButtonModule, MatSnackBarModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
