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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "address")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
        , @NamedQuery(name = "Address.findByIdaddress", query = "SELECT a FROM Address a WHERE a.idaddress = :idaddress")
        , @NamedQuery(name = "Address.findByAddress", query = "SELECT a FROM Address a WHERE a.address = :address")})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idaddress")
    private Integer idaddress;
    @Size(max = 45)
    @Column(name = "address")
    private String address;
    @JoinColumn(name = "idcity", referencedColumnName = "idcity")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private City idcity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idaddress", fetch = FetchType.LAZY)
    private List<Event> eventList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idaddress", fetch = FetchType.LAZY)
    private List<Leaseplace> leaseplaceList;

    public Address() {
    }

    public Address(Integer idaddress) {
        this.idaddress = idaddress;
    }

    public Integer getIdaddress() {
        return idaddress;
    }

    public void setIdaddress(Integer idaddress) {
        this.idaddress = idaddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getIdcity() {
        return idcity;
    }

    public void setIdcity(City idcity) {
        this.idcity = idcity;
    }

    @XmlTransient
    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    @XmlTransient
    public List<Leaseplace> getLeaseplaceList() {
        return leaseplaceList;
    }

    public void setLeaseplaceList(List<Leaseplace> leaseplaceList) {
        this.leaseplaceList = leaseplaceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idaddress != null ? idaddress.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.idaddress == null && other.idaddress != null) || (this.idaddress != null && !this.idaddress.equals(other.idaddress))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Address[ idaddress=" + idaddress + " ]";
    }

}