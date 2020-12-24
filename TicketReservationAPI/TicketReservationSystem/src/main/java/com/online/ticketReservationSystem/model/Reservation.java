package com.online.ticketReservationSystem.model;

import java.util.Date;

public class Reservation {
    private int ID;
    private int TICKET_ID;
    private int TOTAL_TICKET;
    private String NAME;
    private int TOTAL_PRICE;
    private Date UPDATE_DATE;
    private int USER_ID;
    private boolean paid;

    public Reservation(int ID, int TICKET_ID, int TOTAL_TICKET, String NAME, int TOTAL_PRICE, Date UPDATE_DATE, int USER_ID) {
        this.ID = ID;
        this.TICKET_ID = TICKET_ID;
        this.TOTAL_TICKET = TOTAL_TICKET;
        this.NAME = NAME;
        this.TOTAL_PRICE = TOTAL_PRICE;
        this.UPDATE_DATE = UPDATE_DATE;
        this.USER_ID = USER_ID;
    }

    public boolean getPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTICKET_ID(int TICKET_ID) {
        this.TICKET_ID = TICKET_ID;
    }

    public int getTOTAL_TICKET() {
        return TOTAL_TICKET;
    }

    public void setTOTAL_TICKET(int TOTAL_TICKET) {
        this.TOTAL_TICKET = TOTAL_TICKET;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public void setTOTAL_PRICE(int TOTAL_PRICE) {
        this.TOTAL_PRICE = TOTAL_PRICE;
    }

    public void setUPDATE_DATE(Date UPDATE_DATE) {
        this.UPDATE_DATE = UPDATE_DATE;
    }

    public int getID() {
        return ID;
    }

    public int getTICKET_ID() {
        return TICKET_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public int getTOTAL_PRICE() {
        return TOTAL_PRICE;
    }

    public Date getUPDATE_DATE() {
        return UPDATE_DATE;
    }
}
