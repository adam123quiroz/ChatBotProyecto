/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.chatbotsoftI.domain;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "eveticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eveticket.findAll", query = "SELECT e FROM Eveticket e")
    , @NamedQuery(name = "Eveticket.findByIdticket", query = "SELECT e FROM Eveticket e WHERE e.idticket = :idticket")
    , @NamedQuery(name = "Eveticket.findByNumberticket", query = "SELECT e FROM Eveticket e WHERE e.numberticket = :numberticket")
    , @NamedQuery(name = "Eveticket.findByTxuser", query = "SELECT e FROM Eveticket e WHERE e.txuser = :txuser")
    , @NamedQuery(name = "Eveticket.findByTxhost", query = "SELECT e FROM Eveticket e WHERE e.txhost = :txhost")
    , @NamedQuery(name = "Eveticket.findByTxdate", query = "SELECT e FROM Eveticket e WHERE e.txdate = :txdate")})
public class Eveticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idticket")
    private Integer idticket;
    @Size(max = 100)
    @Column(name = "numberticket")
    private String numberticket;
    @Size(max = 45)
    @Column(name = "txuser")
    private String txuser;
    @Size(max = 100)
    @Column(name = "txhost")
    private String txhost;
    @Column(name = "txdate")
    @Temporal(TemporalType.DATE)
    private Date txdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idticket", fetch = FetchType.LAZY)
    private List<Evebuyticket> evebuyticketList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idticket", fetch = FetchType.LAZY)
    private List<Evebooking> evebookingList;

    public Eveticket() {
    }

    public Eveticket(Integer idticket) {
        this.idticket = idticket;
    }

    public Integer getIdticket() {
        return idticket;
    }

    public void setIdticket(Integer idticket) {
        this.idticket = idticket;
    }

    public String getNumberticket() {
        return numberticket;
    }

    public void setNumberticket(String numberticket) {
        this.numberticket = numberticket;
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

    @XmlTransient
    public List<Evebuyticket> getEvebuyticketList() {
        return evebuyticketList;
    }

    public void setEvebuyticketList(List<Evebuyticket> evebuyticketList) {
        this.evebuyticketList = evebuyticketList;
    }

    @XmlTransient
    public List<Evebooking> getEvebookingList() {
        return evebookingList;
    }

    public void setEvebookingList(List<Evebooking> evebookingList) {
        this.evebookingList = evebookingList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idticket != null ? idticket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eveticket)) {
            return false;
        }
        Eveticket other = (Eveticket) object;
        if ((this.idticket == null && other.idticket != null) || (this.idticket != null && !this.idticket.equals(other.idticket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.com.chatbotsoftI.domain.Eveticket[ idticket=" + idticket + " ]";
    }
    
}