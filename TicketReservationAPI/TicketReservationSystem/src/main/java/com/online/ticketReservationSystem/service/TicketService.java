package com.online.ticketReservationSystem.service;

import java.sql.ResultSet;
import java.util.List;

import com.online.ticketReservationSystem.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TicketService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Ticket> getTickets() {
        String userSQLQuery = "SELECT * FROM TICKET ORDER BY DEPARTURE_DATE, DEPARTURE_TIME ASC";
        List<Ticket> list = jdbcTemplate.query(userSQLQuery, new String[] { },
                (ResultSet rs, int rowNum) -> {
                    Ticket ticket = new Ticket();
                    ticket.setID(rs.getInt("ID"));
                    ticket.setPRICE(rs.getInt("PRICE"));
                    ticket.setSTART_DESTINATION(rs.getString("START_DESTINATION"));
                    ticket.setEND_DESTINATION(rs.getString("END_DESTINATION"));
                    ticket.setDEPARTURE_DATE(rs.getDate("DEPARTURE_DATE"));
                    ticket.setDEPARTURE_TIME(rs.getString("DEPARTURE_TIME"));
                    return ticket;
                });

        return list;
    }

    public Ticket getTicket(long id) {
        String userSQLQuery = "SELECT * FROM TICKET WHERE ID = ?";
        return jdbcTemplate.queryForObject(userSQLQuery, new Object[]{id}, (rs, rowNum) ->
                new Ticket(
                        rs.getInt("ID"),
                        rs.getString("START_DESTINATION"),
                        rs.getString("END_DESTINATION"),
                        rs.getInt("PRICE"),
                        rs.getDate("UPDATE_DATE"),
                        rs.getDate("DEPARTURE_DATE"),
                        rs.getString("DEPARTURE_TIME")
                ));
    }
}
