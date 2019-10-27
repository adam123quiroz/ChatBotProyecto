package edu.com.chatbotsoftI.domain;


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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "payment")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p")
        , @NamedQuery(name = "Payment.findByIdpayment", query = "SELECT p FROM Payment p WHERE p.idpayment = :idpayment")
        , @NamedQuery(name = "Payment.findByDate", query = "SELECT p FROM Payment p WHERE p.date = :date")
        , @NamedQuery(name = "Payment.findByTotal", query = "SELECT p FROM Payment p WHERE p.total = :total")})
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;
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
    @JoinColumn(name = "idpaymentmethod", referencedColumnName = "idpaymentmethod")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paymentmethod idpaymentmethod;
    @JoinColumn(name = "idbooking", referencedColumnName = "idbooking")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Booking idbooking;

    public Payment() {
    }

    public Payment(Integer idpayment) {
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

    public Paymentmethod getIdpaymentmethod() {
        return idpaymentmethod;
    }

    public void setIdpaymentmethod(Paymentmethod idpaymentmethod) {
        this.idpaymentmethod = idpaymentmethod;
    }

    public Booking getIdbooking() {
        return idbooking;
    }

    public void setIdbooking(Booking idbooking) {
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
        if (!(object instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) object;
        if ((this.idpayment == null && other.idpayment != null) || (this.idpayment != null && !this.idpayment.equals(other.idpayment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Payment[ idpayment=" + idpayment + " ]";
    }

}
