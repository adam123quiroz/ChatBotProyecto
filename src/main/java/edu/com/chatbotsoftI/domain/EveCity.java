/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.chatbotsoftI.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "evecity")
@XmlRootElement
public class EveCity implements Serializable {

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
    private EveState idstate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcity", fetch = FetchType.LAZY)
    private List<EveAddress> eveaddressList;

    public EveCity() {
    }

    public EveCity(Integer idcity) {
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

    public EveState getIdstate() {
        return idstate;
    }

    public void setIdstate(EveState idstate) {
        this.idstate = idstate;
    }

    @XmlTransient
    public List<EveAddress> getEveaddressList() {
        return eveaddressList;
    }

    public void setEveaddressList(List<EveAddress> eveaddressList) {
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
        if (!(object instanceof EveCity)) {
            return false;
        }
        EveCity other = (EveCity) object;
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
