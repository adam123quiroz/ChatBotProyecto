package edu.com.chatbotsoftI.entity;


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


@Entity
@Table(name = "eveaddress")
@XmlRootElement

public class EveAddressEntity implements Serializable {
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
    private EveCityEntity idcity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idaddress", fetch = FetchType.LAZY)
    private List<EveEventEntity> eveeventList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idaddress", fetch = FetchType.LAZY)
    private List<EveLeasePlaceEntity> eveleaseplaceList;

    public EveAddressEntity() {
    }

    public EveAddressEntity(Integer idaddress) {
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

    public EveCityEntity getIdcity() {
        return idcity;
    }

    public void setIdcity(EveCityEntity idcity) {
        this.idcity = idcity;
    }

    @XmlTransient
    public List<EveEventEntity> getEveeventList() {
        return eveeventList;
    }

    public void setEveeventList(List<EveEventEntity> eveeventList) {
        this.eveeventList = eveeventList;
    }

    @XmlTransient
    public List<EveLeasePlaceEntity> getEveleaseplaceList() {
        return eveleaseplaceList;
    }

    public void setEveleaseplaceList(List<EveLeasePlaceEntity> eveleaseplaceList) {
        this.eveleaseplaceList = eveleaseplaceList;
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
        if (!(object instanceof EveAddressEntity)) {
            return false;
        }
        EveAddressEntity other = (EveAddressEntity) object;
        if ((this.idaddress == null && other.idaddress != null) || (this.idaddress != null && !this.idaddress.equals(other.idaddress))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EveAddressEntity[ idaddress=" + idaddress + " ]";
    }
}
