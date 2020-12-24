package com.online.ticketReservationSystem.model;

import java.util.Date;

public class Ticket {
    private int ID;
    private String START_DESTINATION;
    private String END_DESTINATION;
    private int PRICE;
    private Date UPDATE_DATE;
    private Date DEPARTURE_DATE;
    private String DEPARTURE_TIME;

    public Ticket(){ }

    public Ticket(int ID, String START_DESTINATION, String END_DESTINATION, int PRICE, Date UPDATE_DATE, Date DEPARTURE_DATE, String DEPARTURE_TIME) {
        this.ID = ID;
        this.START_DESTINATION = START_DESTINATION;
        this.END_DESTINATION = END_DESTINATION;
        this.PRICE = PRICE;
        this.UPDATE_DATE = UPDATE_DATE;
        this.DEPARTURE_DATE = DEPARTURE_DATE;
        this.DEPARTURE_TIME = DEPARTURE_TIME;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setSTART_DESTINATION(String START_DESTINATION) {
        this.START_DESTINATION = START_DESTINATION;
    }

    public void setEND_DESTINATION(String END_DESTINATION) {
        this.END_DESTINATION = END_DESTINATION;
    }

    public void setPRICE(int PRICE) {
        this.PRICE = PRICE;
    }

    public void setUPDATE_DATE(Date UPDATE_DATE) {
        this.UPDATE_DATE = UPDATE_DATE;
    }

    public void setDEPARTURE_DATE(Date DEPARTURE_DATE) {
        this.DEPARTURE_DATE = DEPARTURE_DATE;
    }

    public void setDEPARTURE_TIME(String DEPARTURE_TIME) {
        this.DEPARTURE_TIME = DEPARTURE_TIME;
    }

    public int getID() {
        return ID;
    }

    public String getSTART_DESTINATION() {
        return START_DESTINATION;
    }

    public String getEND_DESTINATION() {
        return END_DESTINATION;
    }

    public int getPRICE() {
        return PRICE;
    }

    public Date getUPDATE_DATE() {
        return UPDATE_DATE;
    }

    public Date getDEPARTURE_DATE() {
        return DEPARTURE_DATE;
    }

    public String getDEPARTURE_TIME() {
        return DEPARTURE_TIME;
    }


}
