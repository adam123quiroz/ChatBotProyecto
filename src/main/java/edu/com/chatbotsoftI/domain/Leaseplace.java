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
@Table(name = "leaseplace")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Leaseplace.findAll", query = "SELECT l FROM Leaseplace l")
        , @NamedQuery(name = "Leaseplace.findByIdleaseplace", query = "SELECT l FROM Leaseplace l WHERE l.idleaseplace = :idleaseplace")
        , @NamedQuery(name = "Leaseplace.findByNameplace", query = "SELECT l FROM Leaseplace l WHERE l.nameplace = :nameplace")
        , @NamedQuery(name = "Leaseplace.findByDate", query = "SELECT l FROM Leaseplace l WHERE l.date = :date")
        , @NamedQuery(name = "Leaseplace.findByPrice", query = "SELECT l FROM Leaseplace l WHERE l.price = :price")
        , @NamedQuery(name = "Leaseplace.findByStatus", query = "SELECT l FROM Leaseplace l WHERE l.status = :status")})
public class Leaseplace implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idleaseplace")
    private Integer idleaseplace;
    @Size(max = 45)
    @Column(name = "nameplace")
    private String nameplace;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "status")
    private Integer status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idleaseplace", fetch = FetchType.LAZY)
    private List<Notification> notificationList;
    @JoinColumn(name = "idaddress", referencedColumnName = "idaddress")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Address idaddress;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User iduser;

    public Leaseplace() {
    }

    public Leaseplace(Integer idleaseplace) {
        this.idleaseplace = idleaseplace;
    }

    public Integer getIdleaseplace() {
        return idleaseplace;
    }

    public void setIdleaseplace(Integer idleaseplace) {
        this.idleaseplace = idleaseplace;
    }

    public String getNameplace() {
        return nameplace;
    }

    public void setNameplace(String nameplace) {
        this.nameplace = nameplace;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @XmlTransient
    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    public Address getIdaddress() {
        return idaddress;
    }

    public void setIdaddress(Address idaddress) {
        this.idaddress = idaddress;
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
        hash += (idleaseplace != null ? idleaseplace.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Leaseplace)) {
            return false;
        }
        Leaseplace other = (Leaseplace) object;
        if ((this.idleaseplace == null && other.idleaseplace != null) || (this.idleaseplace != null && !this.idleaseplace.equals(other.idleaseplace))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Leaseplace[ idleaseplace=" + idleaseplace + " ]";
    }

}

