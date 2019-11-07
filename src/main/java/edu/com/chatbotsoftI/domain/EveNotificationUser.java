/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.chatbotsoftI.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "evenotificationuser")
@XmlRootElement
public class EveNotificationUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnotificationuser")
    private Integer idnotificationuser;
    @JoinColumn(name = "idnotification", referencedColumnName = "idnotification")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveNotification idnotification;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveUser iduser;

    public EveNotificationUser() {
    }

    public EveNotificationUser(Integer idnotificationuser) {
        this.idnotificationuser = idnotificationuser;
    }

    public Integer getIdnotificationuser() {
        return idnotificationuser;
    }

    public void setIdnotificationuser(Integer idnotificationuser) {
        this.idnotificationuser = idnotificationuser;
    }

    public EveNotification getIdnotification() {
        return idnotification;
    }

    public void setIdnotification(EveNotification idnotification) {
        this.idnotification = idnotification;
    }

    public EveUser getIduser() {
        return iduser;
    }

    public void setIduser(EveUser iduser) {
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
        if (!(object instanceof EveNotificationUser)) {
            return false;
        }
        EveNotificationUser other = (EveNotificationUser) object;
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