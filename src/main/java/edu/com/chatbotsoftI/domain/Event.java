package edu.com.chatbotsoftI.domain;

import java.sql.Time;
import java.util.Date;

public class Event {
    private long id;
    private String nameEvent;
    private double priceEvent;
    private Date date;
    private Time time;
    private Category category;
    private TypeEvent typeEvent;

    public Event() {
    }

    public Event(long id, String nameEvent, double priceEvent, Date date, Time time, Category category, TypeEvent typeEvent) {
        this.id = id;
        this.nameEvent = nameEvent;
        this.priceEvent = priceEvent;
        this.date = date;
        this.time = time;
        this.category = category;
        this.typeEvent = typeEvent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public double getPriceEvent() {
        return priceEvent;
    }

    public void setPriceEvent(double priceEvent) {
        this.priceEvent = priceEvent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public TypeEvent getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(TypeEvent typeEvent) {
        this.typeEvent = typeEvent;
    }
}
