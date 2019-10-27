package edu.com.chatbotsoftI.domain;


import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "buyticket")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Buyticket.findAll", query = "SELECT b FROM Buyticket b")
        , @NamedQuery(name = "Buyticket.findByIdbuy", query = "SELECT b FROM Buyticket b WHERE b.idbuy = :idbuy")
        , @NamedQuery(name = "Buyticket.findByQuantity", query = "SELECT b FROM Buyticket b WHERE b.quantity = :quantity")
        , @NamedQuery(name = "Buyticket.findByTotal", query = "SELECT b FROM Buyticket b WHERE b.total = :total")})
public class Buyticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbuy")
    private Integer idbuy;
    @Column(name = "quantity")
    private Integer quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private BigDecimal total;
    @JoinColumn(name = "idevent", referencedColumnName = "idevent")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Event idevent;
    @JoinColumn(name = "idpaymentmethod", referencedColumnName = "idpaymentmethod")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paymentmethod idpaymentmethod;
    @JoinColumn(name = "idticket", referencedColumnName = "idticket")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ticket idticket;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User iduser;

    public Buyticket() {
    }

    public Buyticket(Integer idbuy) {
        this.idbuy = idbuy;
    }

    public Integer getIdbuy() {
        return idbuy;
    }

    public void setIdbuy(Integer idbuy) {
        this.idbuy = idbuy;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Event getIdevent() {
        return idevent;
    }

    public void setIdevent(Event idevent) {
        this.idevent = idevent;
    }

    public Paymentmethod getIdpaymentmethod() {
        return idpaymentmethod;
    }

    public void setIdpaymentmethod(Paymentmethod idpaymentmethod) {
        this.idpaymentmethod = idpaymentmethod;
    }

    public Ticket getIdticket() {
        return idticket;
    }

    public void setIdticket(Ticket idticket) {
        this.idticket = idticket;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbuy != null ? idbuy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Buyticket)) {
            return false;
        }
        Buyticket other = (Buyticket) object;
        if ((this.idbuy == null && other.idbuy != null) || (this.idbuy != null && !this.idbuy.equals(other.idbuy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Buyticket[ idbuy=" + idbuy + " ]";
    }

}

