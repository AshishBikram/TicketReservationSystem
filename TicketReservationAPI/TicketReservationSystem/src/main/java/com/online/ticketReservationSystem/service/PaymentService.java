package com.online.ticketReservationSystem.service;

import com.online.ticketReservationSystem.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Repository
public class PaymentService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean savePayment(int reservationId) {
        String paymentInsert = "INSERT INTO PAYMENT(RESERVATION_ID,UPDATE_DATE) VALUES(?, NOW())";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(paymentInsert, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, reservationId);
            return ps;
        }, keyHolder);
        return true;
    }

    public List<Integer> getAllPayments() {
        String userSQLQuery = "SELECT * FROM PAYMENT";
        List<Integer> reservationIds = jdbcTemplate.query(userSQLQuery, new String[] { },
                (ResultSet rs, int rowNum) -> rs.getInt("RESERVATION_ID"));
        return reservationIds;
    }
}
