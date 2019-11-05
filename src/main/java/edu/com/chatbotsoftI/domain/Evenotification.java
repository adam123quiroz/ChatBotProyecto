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
@Table(name = "evenotification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evenotification.findAll", query = "SELECT e FROM Evenotification e")
    , @NamedQuery(name = "Evenotification.findByIdnotification", query = "SELECT e FROM Evenotification e WHERE e.idnotification = :idnotification")
    , @NamedQuery(name = "Evenotification.findByMsnotification", query = "SELECT e FROM Evenotification e WHERE e.msnotification = :msnotification")})
public class Evenotification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnotification")
    private Integer idnotification;
    @Size(max = 45)
    @Column(name = "msnotification")
    private String msnotification;
    @JoinColumn(name = "idleaseplace", referencedColumnName = "idleaseplace")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Eveleaseplace idleaseplace;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idnotification", fetch = FetchType.LAZY)
    private List<Evenotificationuser> evenotificationuserList;

    public Evenotification() {
    }

    public Evenotification(Integer idnotification) {
        this.idnotification = idnotification;
    }

    public Integer getIdnotification() {
        return idnotification;
    }

    public void setIdnotification(Integer idnotification) {
        this.idnotification = idnotification;
    }

    public String getMsnotification() {
        return msnotification;
    }

    public void setMsnotification(String msnotification) {
        this.msnotification = msnotification;
    }

    public Eveleaseplace getIdleaseplace() {
        return idleaseplace;
    }

    public void setIdleaseplace(Eveleaseplace idleaseplace) {
        this.idleaseplace = idleaseplace;
    }

    @XmlTransient
    public List<Evenotificationuser> getEvenotificationuserList() {
        return evenotificationuserList;
    }

    public void setEvenotificationuserList(List<Evenotificationuser> evenotificationuserList) {
        this.evenotificationuserList = evenotificationuserList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnotification != null ? idnotification.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evenotification)) {
            return false;
        }
        Evenotification other = (Evenotification) object;
        if ((this.idnotification == null && other.idnotification != null) || (this.idnotification != null && !this.idnotification.equals(other.idnotification))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.com.chatbotsoftI.domain.Evenotification[ idnotification=" + idnotification + " ]";
    }
    
}