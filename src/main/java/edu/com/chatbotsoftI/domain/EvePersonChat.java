/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.chatbotsoftI.domain;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "evepersonchat")
@XmlRootElement
public class EvePersonChat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idevuserchat")
    private Integer idevuserchat;
    @Size(max = 45)
    @Column(name = "userbotchatid")
    private String userbotchatid;
    @Size(max = 45)
    @Column(name = "txuser")
    private String txuser;
    @Size(max = 100)
    @Column(name = "txhost")
    private String txhost;
    @Column(name = "txdate")
    @Temporal(TemporalType.DATE)
    private Date txdate;
    @JoinColumn(name = "idperson", referencedColumnName = "idperson")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EvePerson idperson;

    public EvePersonChat() {
    }

    public EvePersonChat(Integer idevuserchat) {
        this.idevuserchat = idevuserchat;
    }

    public Integer getIdevuserchat() {
        return idevuserchat;
    }

    public void setIdevuserchat(Integer idevuserchat) {
        this.idevuserchat = idevuserchat;
    }

    public String getUserbotchatid() {
        return userbotchatid;
    }

    public void setUserbotchatid(String userbotchatid) {
        this.userbotchatid = userbotchatid;
    }

    public String getTxuser() {
        return txuser;
    }

    public void setTxuser(String txuser) {
        this.txuser = txuser;
    }

    public String getTxhost() {
        return txhost;
    }

    public void setTxhost(String txhost) {
        this.txhost = txhost;
    }

    public Date getTxdate() {
        return txdate;
    }

    public void setTxdate(Date txdate) {
        this.txdate = txdate;
    }

    public EvePerson getIdperson() {
        return idperson;
    }

    public void setIdperson(EvePerson idperson) {
        this.idperson = idperson;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevuserchat != null ? idevuserchat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvePersonChat)) {
            return false;
        }
        EvePersonChat other = (EvePersonChat) object;
        if ((this.idevuserchat == null && other.idevuserchat != null) || (this.idevuserchat != null && !this.idevuserchat.equals(other.idevuserchat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.com.chatbotsoftI.domain.Evepersonchat[ idevuserchat=" + idevuserchat + " ]";
    }
    
}
