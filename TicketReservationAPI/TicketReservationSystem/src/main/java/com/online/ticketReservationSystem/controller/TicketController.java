package com.online.ticketReservationSystem.controller;
import com.online.ticketReservationSystem.model.Ticket;
import com.online.ticketReservationSystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;


import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/")
    public String getAllTickets() {
        List<Ticket> tickets = ticketService.getTickets();
        Gson gson = new Gson();
        return gson.toJson(tickets);
    }

    @GetMapping("/{id}")
    public String getTicket(@PathVariable("id") long id) {
        Ticket ticket = ticketService.getTicket(id);
        Gson gson = new Gson();
        return gson.toJson(ticket);
    }
}
