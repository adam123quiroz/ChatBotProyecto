package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "evepayment", schema = "dbbot", catalog = "")
public class EvePaymentEntity {
    private Integer idpayment;
    private Timestamp date;
    private BigDecimal total;
    private String txuser;
    private String txhost;
    private Date txdate;
    private EvePaymentMethodEntity evepaymentmethodByIdpaymentmethod;
    private EveBookingEntity evebookingByIdbooking;

    @Id
    @Column(name = "idpayment", nullable = false)
    public Integer getIdpayment() {
        return idpayment;
    }

    public void setIdpayment(Integer idpayment) {
        this.idpayment = idpayment;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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
    @Column(name = "txhost", nullable = true, length = 45)
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

        EvePaymentEntity that = (EvePaymentEntity) o;

        if (idpayment != null ? !idpayment.equals(that.idpayment) : that.idpayment != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (txuser != null ? !txuser.equals(that.txuser) : that.txuser != null) return false;
        if (txhost != null ? !txhost.equals(that.txhost) : that.txhost != null) return false;
        if (txdate != null ? !txdate.equals(that.txdate) : that.txdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idpayment != null ? idpayment.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (txuser != null ? txuser.hashCode() : 0);
        result = 31 * result + (txhost != null ? txhost.hashCode() : 0);
        result = 31 * result + (txdate != null ? txdate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idpaymentmethod", referencedColumnName = "idpaymentmethod", nullable = false)
    public EvePaymentMethodEntity getEvepaymentmethodByIdpaymentmethod() {
        return evepaymentmethodByIdpaymentmethod;
    }

    public void setEvepaymentmethodByIdpaymentmethod(EvePaymentMethodEntity evepaymentmethodByIdpaymentmethod) {
        this.evepaymentmethodByIdpaymentmethod = evepaymentmethodByIdpaymentmethod;
    }

    @ManyToOne
    @JoinColumn(name = "idbooking", referencedColumnName = "idbooking", nullable = false)
    public EveBookingEntity getEvebookingByIdbooking() {
        return evebookingByIdbooking;
    }

    public void setEvebookingByIdbooking(EveBookingEntity evebookingByIdbooking) {
        this.evebookingByIdbooking = evebookingByIdbooking;
    }
}
