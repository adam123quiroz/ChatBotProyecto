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
@Table(name = "evecity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evecity.findAll", query = "SELECT e FROM Evecity e")
    , @NamedQuery(name = "Evecity.findByIdcity", query = "SELECT e FROM Evecity e WHERE e.idcity = :idcity")
    , @NamedQuery(name = "Evecity.findByCity", query = "SELECT e FROM Evecity e WHERE e.city = :city")})
public class Evecity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcity")
    private Integer idcity;
    @Size(max = 45)
    @Column(name = "city")
    private String city;
    @JoinColumn(name = "idstate", referencedColumnName = "idstate")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Evestate idstate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcity", fetch = FetchType.LAZY)
    private List<Eveaddress> eveaddressList;

    public Evecity() {
    }

    public Evecity(Integer idcity) {
        this.idcity = idcity;
    }

    public Integer getIdcity() {
        return idcity;
    }

    public void setIdcity(Integer idcity) {
        this.idcity = idcity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Evestate getIdstate() {
        return idstate;
    }

    public void setIdstate(Evestate idstate) {
        this.idstate = idstate;
    }

    @XmlTransient
    public List<Eveaddress> getEveaddressList() {
        return eveaddressList;
    }

    public void setEveaddressList(List<Eveaddress> eveaddressList) {
        this.eveaddressList = eveaddressList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcity != null ? idcity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evecity)) {
            return false;
        }
        Evecity other = (Evecity) object;
        if ((this.idcity == null && other.idcity != null) || (this.idcity != null && !this.idcity.equals(other.idcity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.com.chatbotsoftI.domain.Evecity[ idcity=" + idcity + " ]";
    }
    
}