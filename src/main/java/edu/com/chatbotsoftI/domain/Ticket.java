package edu.com.chatbotsoftI.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "ticket")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t")
        , @NamedQuery(name = "Ticket.findByIdticket", query = "SELECT t FROM Ticket t WHERE t.idticket = :idticket")
        , @NamedQuery(name = "Ticket.findByNumberticket", query = "SELECT t FROM Ticket t WHERE t.numberticket = :numberticket")})
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idticket")
    private Integer idticket;
    @Size(max = 45)
    @Column(name = "numberticket")
    private String numberticket;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idticket", fetch = FetchType.LAZY)
    private List<Booking> bookingList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idticket", fetch = FetchType.LAZY)
    private List<Buyticket> buyticketList;

    public Ticket() {
    }

    public Ticket(Integer idticket) {
        this.idticket = idticket;
    }

    public Integer getIdticket() {
        return idticket;
    }

    public void setIdticket(Integer idticket) {
        this.idticket = idticket;
    }

    public String getNumberticket() {
        return numberticket;
    }

    public void setNumberticket(String numberticket) {
        this.numberticket = numberticket;
    }

    @XmlTransient
    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @XmlTransient
    public List<Buyticket> getBuyticketList() {
        return buyticketList;
    }

    public void setBuyticketList(List<Buyticket> buyticketList) {
        this.buyticketList = buyticketList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idticket != null ? idticket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.idticket == null && other.idticket != null) || (this.idticket != null && !this.idticket.equals(other.idticket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Ticket[ idticket=" + idticket + " ]";
    }

}

