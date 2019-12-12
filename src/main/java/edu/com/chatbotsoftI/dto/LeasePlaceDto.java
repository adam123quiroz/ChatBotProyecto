package edu.com.chatbotsoftI.dto;

import edu.com.chatbotsoftI.entity.EveLeasePlaceEntity;

import java.math.BigDecimal;
import java.util.Date;

public class LeasePlaceDto {

    private Integer idleaseplace;
    private String nameplace;
    private Date date;
    private BigDecimal price;
    private Integer status;
    private String address;

    public LeasePlaceDto(){

    }

    public LeasePlaceDto(EveLeasePlaceEntity leaseplace){
        this.idleaseplace = leaseplace.getIdLeasePlace();
        this.nameplace = leaseplace.getNamePlace();
        this.date = leaseplace.getDate();
        this.price = leaseplace.getPrice();

          this.address = leaseplace.getEveAddressByIdAddress().getAddress();
     //   this.address = leaseplace.getIdaddress().getAddress();
    }

    public Integer getIdleaseplace() {
        return idleaseplace;
    }

    public void setIdleaseplace(Integer idleaseplace) {
        this.idleaseplace = idleaseplace;
    }

    public String getNameplace() {
        return nameplace;
    }

    public void setNameplace(String nameplace) {
        this.nameplace = nameplace;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return "LeasePlaceDto{" +
                "idleaseplace=" + idleaseplace +
                ", nameplace='" + nameplace + '\'' +
                ", date='" + date +
                ", price='" + price +
                ", status='" + status +
                ", address='" + address + '\''+
        '}';
    }
}
