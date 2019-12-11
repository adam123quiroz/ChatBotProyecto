package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "eve_event", schema = "dbbot", catalog = "")
public class EveEventEntity {
    private int idEvent;
    private String nameEvent;
    private BigDecimal price;
    private Date date;
    private Time startTime;
    private Integer status;
    private String txUser;
    private String txHost;
    private Date txDate;
    private List<EveBuyTicketEntity> eveBuyTicketsByIdEvent;
    private EveUserEntity eveUserByIdUser;
    private EveTypeEventEntity eveTypeEventByIdTypeEvent;
    private EveCategoryEntity eveCategoryByIdCategory;
    private EveAddressEntity eveAddressByIdAddress;
    private List<EveEventFileEntity> eveEventFilesByIdEvent;
    private List<EvePaymentEntity> evePaymentsByIdEvent;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event", nullable = false)
    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    @Basic
    @Column(name = "name_event", nullable = true, length = 100)
    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 5)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "start_time", nullable = true)
    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "tx_user", nullable = true, length = 45)
    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    @Basic
    @Column(name = "tx_host", nullable = true, length = 100)
    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    @Basic
    @Column(name = "tx_date", nullable = true)
    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EveEventEntity that = (EveEventEntity) o;

        if (idEvent != that.idEvent) return false;
        if (nameEvent != null ? !nameEvent.equals(that.nameEvent) : that.nameEvent != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txHost != null ? !txHost.equals(that.txHost) : that.txHost != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEvent;
        result = 31 * result + (nameEvent != null ? nameEvent.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txHost != null ? txHost.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "eveEventByIdEvent")
    public List<EveBuyTicketEntity> getEveBuyTicketsByIdEvent() {
        return eveBuyTicketsByIdEvent;
    }

    public void setEveBuyTicketsByIdEvent(List<EveBuyTicketEntity> eveBuyTicketsByIdEvent) {
        this.eveBuyTicketsByIdEvent = eveBuyTicketsByIdEvent;
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    public EveUserEntity getEveUserByIdUser() {
        return eveUserByIdUser;
    }

    public void setEveUserByIdUser(EveUserEntity eveUserByIdUser) {
        this.eveUserByIdUser = eveUserByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "id_type_event", referencedColumnName = "id_type_event", nullable = false)
    public EveTypeEventEntity getEveTypeEventByIdTypeEvent() {
        return eveTypeEventByIdTypeEvent;
    }

    public void setEveTypeEventByIdTypeEvent(EveTypeEventEntity eveTypeEventByIdTypeEvent) {
        this.eveTypeEventByIdTypeEvent = eveTypeEventByIdTypeEvent;
    }

    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id_category", nullable = false)
    public EveCategoryEntity getEveCategoryByIdCategory() {
        return eveCategoryByIdCategory;
    }

    public void setEveCategoryByIdCategory(EveCategoryEntity eveCategoryByIdCategory) {
        this.eveCategoryByIdCategory = eveCategoryByIdCategory;
    }

    @ManyToOne
    @JoinColumn(name = "id_address", referencedColumnName = "id_address")
    public EveAddressEntity getEveAddressByIdAddress() {
        return eveAddressByIdAddress;
    }

    public void setEveAddressByIdAddress(EveAddressEntity eveAddressByIdAddress) {
        this.eveAddressByIdAddress = eveAddressByIdAddress;
    }

    @OneToMany(mappedBy = "eveEventByIdEvent")
    public List<EveEventFileEntity> getEveEventFilesByIdEvent() {
        return eveEventFilesByIdEvent;
    }

    public void setEveEventFilesByIdEvent(List<EveEventFileEntity> eveEventFilesByIdEvent) {
        this.eveEventFilesByIdEvent = eveEventFilesByIdEvent;
    }

    @OneToMany(mappedBy = "eveEventByIdEvent")
    public List<EvePaymentEntity> getEvePaymentsByIdEvent() {
        return evePaymentsByIdEvent;
    }

    public void setEvePaymentsByIdEvent(List<EvePaymentEntity> evePaymentsByIdEvent) {
        this.evePaymentsByIdEvent = evePaymentsByIdEvent;
    }
}
