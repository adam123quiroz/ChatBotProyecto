package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "eve_payment", schema = "dbbot", catalog = "")
public class EvePaymentEntity {
    private int idPayment;
    private BigDecimal total;
    private Date date;
    private int status;
    private String txUser;
    private Date txDate;
    private String txHost;
    private EvePaymentMethodEntity evePaymentMethodByIdPaymentMethod;
    private EveUserEntity eveUserByIdUser;
    private EveEventEntity eveEventByIdEvent;
    private EveTicketEntity eveTicketByIdTicket;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment", nullable = false)
    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    @Basic
    @Column(name = "total", nullable = true, precision = 5)
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
    @Column(name = "tx_date", nullable = true)
    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    @Basic
    @Column(name = "tx_host", nullable = false, length = 45)
    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EvePaymentEntity that = (EvePaymentEntity) o;

        if (idPayment != that.idPayment) return false;
        if (status != that.status) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;
        if (txHost != null ? !txHost.equals(that.txHost) : that.txHost != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPayment;
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        result = 31 * result + (txHost != null ? txHost.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_payment_method", referencedColumnName = "id_payment_method", nullable = false)
    public EvePaymentMethodEntity getEvePaymentMethodByIdPaymentMethod() {
        return evePaymentMethodByIdPaymentMethod;
    }

    public void setEvePaymentMethodByIdPaymentMethod(EvePaymentMethodEntity evePaymentMethodByIdPaymentMethod) {
        this.evePaymentMethodByIdPaymentMethod = evePaymentMethodByIdPaymentMethod;
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    public EveUserEntity getEveUserByIdUser() {
        return eveUserByIdUser;
    }

    public void setEveUserByIdUser(EveUserEntity eveUserByIdUser) {
        this.eveUserByIdUser = eveUserByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "id_event", referencedColumnName = "id_event")
    public EveEventEntity getEveEventByIdEvent() {
        return eveEventByIdEvent;
    }

    public void setEveEventByIdEvent(EveEventEntity eveEventByIdEvent) {
        this.eveEventByIdEvent = eveEventByIdEvent;
    }

    @ManyToOne
    @JoinColumn(name = "id_ticket", referencedColumnName = "id_ticket")
    public EveTicketEntity getEveTicketByIdTicket() {
        return eveTicketByIdTicket;
    }

    public void setEveTicketByIdTicket(EveTicketEntity eveTicketByIdTicket) {
        this.eveTicketByIdTicket = eveTicketByIdTicket;
    }
}
