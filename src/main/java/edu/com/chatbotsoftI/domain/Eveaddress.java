/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "eveaddress")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eveaddress.findAll", query = "SELECT e FROM Eveaddress e")
    , @NamedQuery(name = "Eveaddress.findByIdaddress", query = "SELECT e FROM Eveaddress e WHERE e.idaddress = :idaddress")
    , @NamedQuery(name = "Eveaddress.findByAddress", query = "SELECT e FROM Eveaddress e WHERE e.address = :address")})
public class Eveaddress implements Serializable {

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
    private Evecity idcity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idaddress", fetch = FetchType.LAZY)
    private List<Eveevent> eveeventList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idaddress", fetch = FetchType.LAZY)
    private List<Eveleaseplace> eveleaseplaceList;

    public Eveaddress() {
    }

    public Eveaddress(Integer idaddress) {
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

    public Evecity getIdcity() {
        return idcity;
    }

    public void setIdcity(Evecity idcity) {
        this.idcity = idcity;
    }

    @XmlTransient
    public List<Eveevent> getEveeventList() {
        return eveeventList;
    }

    public void setEveeventList(List<Eveevent> eveeventList) {
        this.eveeventList = eveeventList;
    }

    @XmlTransient
    public List<Eveleaseplace> getEveleaseplaceList() {
        return eveleaseplaceList;
    }

    public void setEveleaseplaceList(List<Eveleaseplace> eveleaseplaceList) {
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
        if (!(object instanceof Eveaddress)) {
            return false;
        }
        Eveaddress other = (Eveaddress) object;
        if ((this.idaddress == null && other.idaddress != null) || (this.idaddress != null && !this.idaddress.equals(other.idaddress))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.com.chatbotsoftI.domain.Eveaddress[ idaddress=" + idaddress + " ]";
    }
    
}
