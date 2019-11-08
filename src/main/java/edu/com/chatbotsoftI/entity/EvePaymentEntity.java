package edu.com.chatbotsoftI.entity;

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
        // TODO: Warning - this method won't work in the case the id fields are not set
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
