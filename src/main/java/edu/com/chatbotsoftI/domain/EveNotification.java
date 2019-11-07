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
@Table(name = "evenotification")
@XmlRootElement
public class EveNotification implements Serializable {

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
    private EveLeasePlace idleaseplace;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idnotification", fetch = FetchType.LAZY)
    private List<EveNotificationUser> evenotificationuserList;

    public EveNotification() {
    }

    public EveNotification(Integer idnotification) {
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

    public EveLeasePlace getIdleaseplace() {
        return idleaseplace;
    }

    public void setIdleaseplace(EveLeasePlace idleaseplace) {
        this.idleaseplace = idleaseplace;
    }

    @XmlTransient
    public List<EveNotificationUser> getEvenotificationuserList() {
        return evenotificationuserList;
    }

    public void setEvenotificationuserList(List<EveNotificationUser> evenotificationuserList) {
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
        if (!(object instanceof EveNotification)) {
            return false;
        }
        EveNotification other = (EveNotification) object;
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
