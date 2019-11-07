package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "evebuyticket", schema = "dbbot", catalog = "")
public class EveBuyTicketEntity {
    private Integer idbuy;
    private Integer quantity;
    private BigDecimal total;
    private String txuser;
    private String txhost;
    private Date txdate;
    private EveUserEntity eveuserByIduser;
    private EveEventEntity eveeventByIdevent;
    private EveTicketEntity eveticketByIdticket;
    private EvePaymentMethodEntity evepaymentmethodByIdpaymentmethod;

    @Id
    @Column(name = "idbuy", nullable = false)
    public Integer getIdbuy() {
        return idbuy;
    }

    public void setIdbuy(Integer idbuy) {
        this.idbuy = idbuy;
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
    @Column(name = "txuser", nullable = true, length = 45)
    public String getTxuser() {
        return txuser;
    }

    public void setTxuser(String txuser) {
        this.txuser = txuser;
    }

    @Basic
    @Column(name = "txhost", nullable = true, length = 100)
    public String getTxhost() {
        return txhost;
    }

    public void setTxhost(String txhost) {
        this.txhost = txhost;
    }

    @Basic
    @Column(name = "txdate", nullable = true)
    public Date getTxdate() {
        return txdate;
    }

    public void setTxdate(Date txdate) {
        this.txdate = txdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EveBuyTicketEntity that = (EveBuyTicketEntity) o;

        if (idbuy != null ? !idbuy.equals(that.idbuy) : that.idbuy != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (txuser != null ? !txuser.equals(that.txuser) : that.txuser != null) return false;
        if (txhost != null ? !txhost.equals(that.txhost) : that.txhost != null) return false;
        if (txdate != null ? !txdate.equals(that.txdate) : that.txdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idbuy != null ? idbuy.hashCode() : 0;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (txuser != null ? txuser.hashCode() : 0);
        result = 31 * result + (txhost != null ? txhost.hashCode() : 0);
        result = 31 * result + (txdate != null ? txdate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "iduser", referencedColumnName = "iduser", nullable = false)
    public EveUserEntity getEveuserByIduser() {
        return eveuserByIduser;
    }

    public void setEveuserByIduser(EveUserEntity eveuserByIduser) {
        this.eveuserByIduser = eveuserByIduser;
    }

    @ManyToOne
    @JoinColumn(name = "idevent", referencedColumnName = "idevent", nullable = false)
    public EveEventEntity getEveeventByIdevent() {
        return eveeventByIdevent;
    }

    public void setEveeventByIdevent(EveEventEntity eveeventByIdevent) {
        this.eveeventByIdevent = eveeventByIdevent;
    }

    @ManyToOne
    @JoinColumn(name = "idticket", referencedColumnName = "idticket", nullable = false)
    public EveTicketEntity getEveticketByIdticket() {
        return eveticketByIdticket;
    }

    public void setEveticketByIdticket(EveTicketEntity eveticketByIdticket) {
        this.eveticketByIdticket = eveticketByIdticket;
    }

    @ManyToOne
    @JoinColumn(name = "idpaymentmethod", referencedColumnName = "idpaymentmethod", nullable = false)
    public EvePaymentMethodEntity getEvepaymentmethodByIdpaymentmethod() {
        return evepaymentmethodByIdpaymentmethod;
    }

    public void setEvepaymentmethodByIdpaymentmethod(EvePaymentMethodEntity evepaymentmethodByIdpaymentmethod) {
        this.evepaymentmethodByIdpaymentmethod = evepaymentmethodByIdpaymentmethod;
    }
}
