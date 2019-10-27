package edu.com.chatbotsoftI.domain;


import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "event")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e")
        , @NamedQuery(name = "Event.findByIdevent", query = "SELECT e FROM Event e WHERE e.idevent = :idevent")
        , @NamedQuery(name = "Event.findByNameevent", query = "SELECT e FROM Event e WHERE e.nameevent = :nameevent")
        , @NamedQuery(name = "Event.findByPrice", query = "SELECT e FROM Event e WHERE e.price = :price")
        , @NamedQuery(name = "Event.findByDate", query = "SELECT e FROM Event e WHERE e.date = :date")
        , @NamedQuery(name = "Event.findByStarttime", query = "SELECT e FROM Event e WHERE e.starttime = :starttime")
        , @NamedQuery(name = "Event.findByStatus", query = "SELECT e FROM Event e WHERE e.status = :status")})
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idevent")
    private Integer idevent;
    @Size(max = 45)
    @Column(name = "nameevent")
    private String nameevent;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "starttime")
    @Temporal(TemporalType.TIME)
    private Date starttime;
    @Column(name = "status")
    private Integer status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idevent", fetch = FetchType.LAZY)
    private List<Booking> bookingList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idevent", fetch = FetchType.LAZY)
    private List<Buyticket> buyticketList;
    @JoinColumn(name = "idcategory", referencedColumnName = "idcategory")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Category idcategory;
    @JoinColumn(name = "idaddress", referencedColumnName = "idaddress")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Address idaddress;
    @JoinColumn(name = "idtypeevent", referencedColumnName = "idtypeevent")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Typeevent idtypeevent;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User iduser;

    public Event() {
    }

    public Event(Integer idevent) {
        this.idevent = idevent;
    }

    public Integer getIdevent() {
        return idevent;
    }

    public void setIdevent(Integer idevent) {
        this.idevent = idevent;
    }

    public String getNameevent() {
        return nameevent;
    }

    public void setNameevent(String nameevent) {
        this.nameevent = nameevent;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Category getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(Category idcategory) {
        this.idcategory = idcategory;
    }

    public Address getIdaddress() {
        return idaddress;
    }

    public void setIdaddress(Address idaddress) {
        this.idaddress = idaddress;
    }

    public Typeevent getIdtypeevent() {
        return idtypeevent;
    }

    public void setIdtypeevent(Typeevent idtypeevent) {
        this.idtypeevent = idtypeevent;
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
        hash += (idevent != null ? idevent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.idevent == null && other.idevent != null) || (this.idevent != null && !this.idevent.equals(other.idevent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Event[ idevent=" + idevent + " ]";
    }

}

