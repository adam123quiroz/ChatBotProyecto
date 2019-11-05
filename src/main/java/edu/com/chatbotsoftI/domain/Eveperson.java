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
@Table(name = "eveperson")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eveperson.findAll", query = "SELECT e FROM Eveperson e")
    , @NamedQuery(name = "Eveperson.findByIdperson", query = "SELECT e FROM Eveperson e WHERE e.idperson = :idperson")
    , @NamedQuery(name = "Eveperson.findByCi", query = "SELECT e FROM Eveperson e WHERE e.ci = :ci")
    , @NamedQuery(name = "Eveperson.findByName", query = "SELECT e FROM Eveperson e WHERE e.name = :name")
    , @NamedQuery(name = "Eveperson.findByLastname", query = "SELECT e FROM Eveperson e WHERE e.lastname = :lastname")})
public class Eveperson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idperson")
    private Integer idperson;
    @Size(max = 45)
    @Column(name = "ci")
    private String ci;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "lastname")
    private String lastname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idperson", fetch = FetchType.LAZY)
    private List<Eveuser> eveuserList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idperson", fetch = FetchType.LAZY)
    private List<Evepersonchat> evepersonchatList;

    public Eveperson() {
    }

    public Eveperson(Integer idperson) {
        this.idperson = idperson;
    }

    public Integer getIdperson() {
        return idperson;
    }

    public void setIdperson(Integer idperson) {
        this.idperson = idperson;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @XmlTransient
    public List<Eveuser> getEveuserList() {
        return eveuserList;
    }

    public void setEveuserList(List<Eveuser> eveuserList) {
        this.eveuserList = eveuserList;
    }

    @XmlTransient
    public List<Evepersonchat> getEvepersonchatList() {
        return evepersonchatList;
    }

    public void setEvepersonchatList(List<Evepersonchat> evepersonchatList) {
        this.evepersonchatList = evepersonchatList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idperson != null ? idperson.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eveperson)) {
            return false;
        }
        Eveperson other = (Eveperson) object;
        if ((this.idperson == null && other.idperson != null) || (this.idperson != null && !this.idperson.equals(other.idperson))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.com.chatbotsoftI.domain.Eveperson[ idperson=" + idperson + " ]";
    }
    
}
