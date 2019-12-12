package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "eve_buy_ticket", schema = "dbbot", catalog = "")
public class EveBuyTicketEntity {
    private int idBuy;
    private Integer quantity;
    private BigDecimal total;
    private String txUser;
    private String txHost;
    private Date txDate;
    private EveUserEntity eveUserByIdUser;
    private EveEventEntity eveEventByIdEvent;
    private EveTicketEntity eveTicketByIdTicket;
    private EvePaymentMethodEntity evePaymentMethodByIdPaymentMethod;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_buy", nullable = false)
    public int getIdBuy() {
        return idBuy;
    }

    public void setIdBuy(int idBuy) {
        this.idBuy = idBuy;
    }

    @Basic
    @Column(name = "quantity", nullable = true)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

        EveBuyTicketEntity that = (EveBuyTicketEntity) o;

        if (idBuy != that.idBuy) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txHost != null ? !txHost.equals(that.txHost) : that.txHost != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBuy;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txHost != null ? txHost.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        return result;
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
    @JoinColumn(name = "id_event", referencedColumnName = "id_event", nullable = false)
    public EveEventEntity getEveEventByIdEvent() {
        return eveEventByIdEvent;
    }

    public void setEveEventByIdEvent(EveEventEntity eveEventByIdEvent) {
        this.eveEventByIdEvent = eveEventByIdEvent;
    }

    @ManyToOne
    @JoinColumn(name = "id_ticket", referencedColumnName = "id_ticket", nullable = false)
    public EveTicketEntity getEveTicketByIdTicket() {
        return eveTicketByIdTicket;
    }

    public void setEveTicketByIdTicket(EveTicketEntity eveTicketByIdTicket) {
        this.eveTicketByIdTicket = eveTicketByIdTicket;
    }

    @ManyToOne
    @JoinColumn(name = "id_payment_method", referencedColumnName = "id_payment_method", nullable = false)
    public EvePaymentMethodEntity getEvePaymentMethodByIdPaymentMethod() {
        return evePaymentMethodByIdPaymentMethod;
    }

    public void setEvePaymentMethodByIdPaymentMethod(EvePaymentMethodEntity evePaymentMethodByIdPaymentMethod) {
        this.evePaymentMethodByIdPaymentMethod = evePaymentMethodByIdPaymentMethod;
    }
}
