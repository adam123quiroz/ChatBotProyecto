package edu.com.chatbotsoftI.dto;
import edu.com.chatbotsoftI.entity.EveEventEntity;
import java.math.BigDecimal;
import java.util.Date;

public class EventDto {
    private Integer idevent;
    private String nameevent;
    private BigDecimal price;
    private Date date;
    private Date starttime;
    private Integer status;
    private String category;
    private String address;

    public EventDto() {
    }

    public EventDto(EveEventEntity event) {
        this.idevent = event.getIdevent();
        this.nameevent = event.getNameevent();
        this.price = event.getPrice();
        this.date = event.getDate();
        this.starttime = event.getStarttime();
        this.status = event.getStatus();

         this.category = event.getEvecategoryByIdcategory().getCategory();
       // this.category = event.getIdcategory().getCategory();
       // this.address = event.getIdaddress().getAddress();
        this.address = event.getEveaddressByIdaddress().getAddress();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
                ", status=" + status +
                ", category='" + category + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
