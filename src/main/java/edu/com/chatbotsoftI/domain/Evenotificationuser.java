/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.chatbotsoftI.domain;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "evenotificationuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evenotificationuser.findAll", query = "SELECT e FROM Evenotificationuser e")
    , @NamedQuery(name = "Evenotificationuser.findByIdnotificationuser", query = "SELECT e FROM Evenotificationuser e WHERE e.idnotificationuser = :idnotificationuser")})
public class Evenotificationuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnotificationuser")
    private Integer idnotificationuser;
    @JoinColumn(name = "idnotification", referencedColumnName = "idnotification")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Evenotification idnotification;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Eveuser iduser;

    public Evenotificationuser() {
    }

    public Evenotificationuser(Integer idnotificationuser) {
        this.idnotificationuser = idnotificationuser;
    }

    public Integer getIdnotificationuser() {
        return idnotificationuser;
    }

    public void setIdnotificationuser(Integer idnotificationuser) {
        this.idnotificationuser = idnotificationuser;
    }

    public Evenotification getIdnotification() {
        return idnotification;
    }

    public void setIdnotification(Evenotification idnotification) {
        this.idnotification = idnotification;
    }

    public Eveuser getIduser() {
        return iduser;
    }

    public void setIduser(Eveuser iduser) {
        this.iduser = iduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnotificationuser != null ? idnotificationuser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evenotificationuser)) {
            return false;
        }
        Evenotificationuser other = (Evenotificationuser) object;
        if ((this.idnotificationuser == null && other.idnotificationuser != null) || (this.idnotificationuser != null && !this.idnotificationuser.equals(other.idnotificationuser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.com.chatbotsoftI.domain.Evenotificationuser[ idnotificationuser=" + idnotificationuser + " ]";
    }
    
}
