package com.online.ticketReservationSystem.controller;

import com.google.gson.Gson;
import com.online.ticketReservationSystem.model.Reservation;
import com.online.ticketReservationSystem.model.Ticket;
import com.online.ticketReservationSystem.service.ReservationService;
import com.online.ticketReservationSystem.service.TicketService;
import com.online.ticketReservationSystem.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private TicketService ticketService;

    @GetMapping("/")
    public String getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservation();
        Gson gson = new Gson();
        return gson.toJson(reservations);
    }

    @GetMapping("/{id}")
    public String getReservation(@PathVariable("id") long id) {
        Reservation reservation = reservationService.getReservation(id);
        Ticket ticket = ticketService.getTicket(reservation.getTICKET_ID());
        Gson gson = new Gson();
        Map result = new HashMap<>();
        result.put("reservation", reservation);
        result.put("ticket", ticket);
        return gson.toJson(result);
    }

    @GetMapping("/me")
    public String getMyReservations() {
        int userId = usersService.getUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        List<Reservation> reservations = reservationService.getMyReservations(userId);
        Gson gson = new Gson();
        return gson.toJson(reservations);
    }

    @PostMapping("/")
    public String doReservation(@RequestBody Reservation reservation) {
        int userId = usersService.getUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        reservation.setUSER_ID(userId);
        Reservation reservations = reservationService.saveReservation(reservation);
        Gson gson = new Gson();
        return gson.toJson(reservations);
    }

    @GetMapping("/chart-data")
    public String getData() {
        Map chartData = reservationService.getChartData();
        Gson gson = new Gson();
        return gson.toJson(chartData);
    }
}
