package edu.com.chatbotsoftI.domain;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "booking")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b")
        , @NamedQuery(name = "Booking.findByIdbooking", query = "SELECT b FROM Booking b WHERE b.idbooking = :idbooking")
        , @NamedQuery(name = "Booking.findByQuantity", query = "SELECT b FROM Booking b WHERE b.quantity = :quantity")
        , @NamedQuery(name = "Booking.findByDate", query = "SELECT b FROM Booking b WHERE b.date = :date")
        , @NamedQuery(name = "Booking.findByStatus", query = "SELECT b FROM Booking b WHERE b.status = :status")})
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbooking")
    private Integer idbooking;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "idevent", referencedColumnName = "idevent")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Event idevent;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User iduser;
    @JoinColumn(name = "idticket", referencedColumnName = "idticket")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ticket idticket;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbooking", fetch = FetchType.LAZY)
    private List<Payment> paymentList;

    public Booking() {
    }

    public Booking(Integer idbooking) {
        this.idbooking = idbooking;
    }

    public Integer getIdbooking() {
        return idbooking;
    }

    public void setIdbooking(Integer idbooking) {
        this.idbooking = idbooking;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Event getIdevent() {
        return idevent;
    }

    public void setIdevent(Event idevent) {
        this.idevent = idevent;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }

    public Ticket getIdticket() {
        return idticket;
    }

    public void setIdticket(Ticket idticket) {
        this.idticket = idticket;
    }

    @XmlTransient
    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbooking != null ? idbooking.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.idbooking == null && other.idbooking != null) || (this.idbooking != null && !this.idbooking.equals(other.idbooking))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Booking[ idbooking=" + idbooking + " ]";
    }

}

