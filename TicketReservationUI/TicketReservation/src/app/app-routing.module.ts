import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component'
import { ReservationComponent } from './components/reservation/reservation.component';
import { TicketsComponent } from './components/tickets/tickets.component'
import { PaymentComponent } from './components/payment/payment.component';
import { ReservationListComponent } from './components/reservation-list/reservation-list.component';
import { LogoutComponent } from './components/logout/logout.component';
const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'reservation/:id', component: ReservationComponent },
  { path: 'payment/:id', component: PaymentComponent },
  { path: 'ticket', component: TicketsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'reservation-list', component: ReservationListComponent },
  { path: 'logout', component: LogoutComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
