package edu.com.chatbotsoftI.entity;

/*
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
*/

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "eveaddress", schema = "dbbot", catalog = "")
public class EveAddressEntity {
    private Integer idaddress;
    private String address;
    private EveCityEntity evecityByIdcity;
    private List<EveEventEntity> eveeventsByIdaddress;
    private List<EveLeasePlaceEntity> eveleaseplacesByIdaddress;

    @Id
    @Column(name = "idaddress", nullable = false)
    public Integer getIdaddress() {
        return idaddress;
    }

    public void setIdaddress(Integer idaddress) {
        this.idaddress = idaddress;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 45)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EveAddressEntity that = (EveAddressEntity) o;

        if (idaddress != null ? !idaddress.equals(that.idaddress) : that.idaddress != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idaddress != null ? idaddress.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idcity", referencedColumnName = "idcity", nullable = false)
    public EveCityEntity getEvecityByIdcity() {
        return evecityByIdcity;
    }

    public void setEvecityByIdcity(EveCityEntity evecityByIdcity) {
        this.evecityByIdcity = evecityByIdcity;
    }

    @OneToMany(mappedBy = "eveaddressByIdaddress")
    public List<EveEventEntity> getEveeventsByIdaddress() {
        return eveeventsByIdaddress;
    }

    public void setEveeventsByIdaddress(List<EveEventEntity> eveeventsByIdaddress) {
        this.eveeventsByIdaddress = eveeventsByIdaddress;
    }

    @OneToMany(mappedBy = "eveaddressByIdaddress")
    public List<EveLeasePlaceEntity> getEveleaseplacesByIdaddress() {
        return eveleaseplacesByIdaddress;
    }

    public void setEveleaseplacesByIdaddress(List<EveLeasePlaceEntity> eveleaseplacesByIdaddress) {
        this.eveleaseplacesByIdaddress = eveleaseplacesByIdaddress;
    }
}
