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
@Table(name = "eveaddress")
@XmlRootElement
public class EveAddress implements Serializable {

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
    private EveCity idcity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idaddress", fetch = FetchType.LAZY)
    private List<EveEvent> eveeventList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idaddress", fetch = FetchType.LAZY)
    private List<EveLeasePlace> eveleaseplaceList;

    public EveAddress() {
    }

    public EveAddress(Integer idaddress) {
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

    public EveCity getIdcity() {
        return idcity;
    }

    public void setIdcity(EveCity idcity) {
        this.idcity = idcity;
    }

    @XmlTransient
    public List<EveEvent> getEveeventList() {
        return eveeventList;
    }

    public void setEveeventList(List<EveEvent> eveeventList) {
        this.eveeventList = eveeventList;
    }

    @XmlTransient
    public List<EveLeasePlace> getEveleaseplaceList() {
        return eveleaseplaceList;
    }

    public void setEveleaseplaceList(List<EveLeasePlace> eveleaseplaceList) {
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
        if (!(object instanceof EveAddress)) {
            return false;
        }
        EveAddress other = (EveAddress) object;
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
