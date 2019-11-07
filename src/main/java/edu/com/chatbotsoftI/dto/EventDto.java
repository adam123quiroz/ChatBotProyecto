package edu.com.chatbotsoftI.dto;

import edu.com.chatbotsoftI.domain.Eveevent;

import java.math.BigDecimal;
import java.util.Date;

public class EventDto {
    private Integer idevent;
    private String nameevent;
    private BigDecimal price;
    private Date date;
    private Date starttime;
    private String category;
    private String address;

    public EventDto() {
    }

    public EventDto(Eveevent event) {
        this.idevent = event.getIdevent();
        this.nameevent = event.getNameevent();
        this.price = event.getPrice();
        this.date = event.getDate();
        this.starttime = event.getStarttime();
    }

    public Integer getIdevent() {
        return idevent;
    }

    public void setIdevent(Integer idevent) {
        this.idevent = idevent;
    }

    public String getNameevent() {
        return nameevent;
    }

    public void setNameevent(String nameevent) {
        this.nameevent = nameevent;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "idevent=" + idevent +
                ", nameevent='" + nameevent + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", starttime=" + starttime +
                '}';
    }
}
