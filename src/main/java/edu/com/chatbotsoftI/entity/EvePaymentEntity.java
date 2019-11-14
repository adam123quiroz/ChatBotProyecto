package edu.com.chatbotsoftI.entity;
/*
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "evepayment")
@XmlRootElement

public class EvePaymentEntity implements Serializable { private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpayment")
    private Integer idpayment;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private BigDecimal total;
    @Size(max = 45)
    @Column(name = "txuser")
    private String txuser;
    @Size(max = 45)
    @Column(name = "txhost")
    private String txhost;
    @Column(name = "txdate")
    @Temporal(TemporalType.DATE)
    private Date txdate;
    @JoinColumn(name = "idpaymentmethod", referencedColumnName = "idpaymentmethod")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EvePaymentMethodEntity idpaymentmethod;
    @JoinColumn(name = "idbooking", referencedColumnName = "idbooking")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveBookingEntity idbooking;

    public EvePaymentEntity() {
    }

    public EvePaymentEntity(Integer idpayment) {
        this.idpayment = idpayment;
    }

    public Integer getIdpayment() {
        return idpayment;
    }

    public void setIdpayment(Integer idpayment) {
        this.idpayment = idpayment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getTxuser() {
        return txuser;
    }

    public void setTxuser(String txuser) {
        this.txuser = txuser;
    }

    public String getTxhost() {
        return txhost;
    }

    public void setTxhost(String txhost) {
        this.txhost = txhost;
    }

    public Date getTxdate() {
        return txdate;
    }

    public void setTxdate(Date txdate) {
        this.txdate = txdate;
    }

    public EvePaymentMethodEntity getIdpaymentmethod() {
        return idpaymentmethod;
    }

    public void setIdpaymentmethod(EvePaymentMethodEntity idpaymentmethod) {
        this.idpaymentmethod = idpaymentmethod;
    }

    public EveBookingEntity getIdbooking() {
        return idbooking;
    }

    public void setIdbooking(EveBookingEntity idbooking) {
        this.idbooking = idbooking;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpayment != null ? idpayment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        //
        if (!(object instanceof EvePaymentEntity)) {
            return false;
        }
        EvePaymentEntity other = (EvePaymentEntity) object;
        if ((this.idpayment == null && other.idpayment != null) || (this.idpayment != null && !this.idpayment.equals(other.idpayment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EvePaymentEntity[ idpayment=" + idpayment + " ]";
    }
}
*/
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
