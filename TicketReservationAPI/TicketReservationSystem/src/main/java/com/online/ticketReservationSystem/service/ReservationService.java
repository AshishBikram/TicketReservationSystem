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
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Repository
public class ReservationService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PaymentService paymentService;

    public List<Reservation> getAllReservation() {
        List reservationIds = paymentService.getAllPayments();
        String userSQLQuery = "SELECT * FROM RESERVATION";
        List<Reservation> list = jdbcTemplate.query(userSQLQuery, new String[] { },
                (ResultSet rs, int rowNum) -> {
                    Reservation reservationObject = new Reservation(
                            rs.getInt("ID"),
                            rs.getInt("TICKET_ID"),
                            rs.getInt("TOTAL_TICKET"),
                            rs.getString("NAME"),
                            rs.getInt("TOTAL_PRICE"),
                            rs.getDate("UPDATE_DATE"),
                            rs.getInt("USER_ID")
                    );
                    if(reservationIds.contains(rs.getInt("ID"))){
                        reservationObject.setPaid(true);
                    }else{
                        reservationObject.setPaid(false);
                    }
                    return reservationObject;
                });
        return list;
    }

    public Reservation getReservation(long id) {
        String userSQLQuery = "SELECT * FROM RESERVATION WHERE ID = ?";
        return jdbcTemplate.queryForObject(userSQLQuery, new Object[]{id}, (rs, rowNum) ->
                new Reservation(
                        rs.getInt("ID"),
                        rs.getInt("TICKET_ID"),
                        rs.getInt("TOTAL_TICKET"),
                        rs.getString("NAME"),
                        rs.getInt("TOTAL_PRICE"),
                        rs.getDate("UPDATE_DATE"),
                        rs.getInt("USER_ID")
                ));
    }

    public List<Reservation> getMyReservations(int userId) {
        String userSQLQuery = "SELECT * FROM RESERVATION WHERE USER_ID = ?";
        List<Reservation> list = jdbcTemplate.query(userSQLQuery, new Object[] {userId},
                (ResultSet rs, int rowNum) -> new Reservation(
                        rs.getInt("ID"),
                        rs.getInt("TICKET_ID"),
                        rs.getInt("TOTAL_TICKET"),
                        rs.getString("NAME"),
                        rs.getInt("TOTAL_PRICE"),
                        rs.getDate("UPDATE_DATE"),
                        rs.getInt("USER_ID")
                ));
        return list;
    }

    public Reservation saveReservation(Reservation reservation) {
        String reservationInsert = "INSERT INTO RESERVATION(TICKET_ID,TOTAL_TICKET, NAME, TOTAL_PRICE, UPDATE_DATE, USER_ID) VALUES(?, ?, ?, ?, NOW(), ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(reservationInsert, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, reservation.getTICKET_ID());
            ps.setInt(2, reservation.getTOTAL_TICKET());
            ps.setString(3, reservation.getNAME());
            ps.setInt(4, reservation.getTOTAL_PRICE());
            ps.setInt(5, reservation.getUSER_ID());
            return ps;
        }, keyHolder);
        reservation.setID((int)keyHolder.getKey());
        return reservation;
    }

    public Map getChartData(){
        List reservationIds = paymentService.getAllPayments();
        String sevenDaysData = "SELECT * FROM RESERVATION WHERE UPDATE_DATE > (current_date - 7);";
        List<Reservation> chartDatas = jdbcTemplate.query(sevenDaysData, new String[] { },
                (ResultSet rs, int rowNum) -> {
                    return new Reservation(
                            rs.getInt("ID"),
                            rs.getInt("TICKET_ID"),
                            rs.getInt("TOTAL_TICKET"),
                            rs.getString("NAME"),
                            rs.getInt("TOTAL_PRICE"),
                            rs.getDate("UPDATE_DATE"),
                            rs.getInt("USER_ID"));
                });
        Map dayVSTotalTicket = new HashMap();
        Map dayVsPaidPrice = new HashMap();
        for (Reservation reservation : chartDatas) {
            int totalTickets = (int)dayVSTotalTicket.getOrDefault(reservation.getUPDATE_DATE(), 0);
            totalTickets += reservation.getTOTAL_TICKET();
            dayVSTotalTicket.put(reservation.getUPDATE_DATE(), totalTickets);
            if(reservationIds.contains(reservation.getTICKET_ID())){
                int totalPrice = (int)dayVsPaidPrice.getOrDefault(reservation.getUPDATE_DATE(), 0);
                totalPrice += reservation.getTOTAL_PRICE();
                dayVsPaidPrice.put(reservation.getUPDATE_DATE(), totalPrice);
            }
        }
        Map finalData = new HashMap();
        finalData.put("day_vs_total_ticket", getFinalMapData(dayVSTotalTicket));
        finalData.put("day_vs_total_price", getFinalMapData(dayVsPaidPrice));
        return finalData;
    }

    public List getFinalMapData(Map data){
        List<Map> finalData = new ArrayList();
        data.forEach((key, value) -> {
            Map row = new HashMap();
            row.put("value", value);
            row.put("date", key);
            finalData.add(row);
        });
        finalData.sort(Comparator.comparing(o -> String.valueOf(o.get("date"))));
        return finalData;
    }
}
