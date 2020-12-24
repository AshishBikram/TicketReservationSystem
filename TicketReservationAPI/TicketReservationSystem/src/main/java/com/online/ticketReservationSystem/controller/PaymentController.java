package com.online.ticketReservationSystem.controller;

import com.google.gson.Gson;
import com.online.ticketReservationSystem.model.Reservation;
import com.online.ticketReservationSystem.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pay")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{reservation_id}")
    public String pay(@PathVariable("reservation_id") int reservation_id) {
        paymentService.savePayment(reservation_id);
        return "{\"success\":true}";
    }
}
