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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "evebooking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evebooking.findAll", query = "SELECT e FROM Evebooking e")
    , @NamedQuery(name = "Evebooking.findByIdbooking", query = "SELECT e FROM Evebooking e WHERE e.idbooking = :idbooking")
    , @NamedQuery(name = "Evebooking.findByQuantity", query = "SELECT e FROM Evebooking e WHERE e.quantity = :quantity")
    , @NamedQuery(name = "Evebooking.findByDate", query = "SELECT e FROM Evebooking e WHERE e.date = :date")
    , @NamedQuery(name = "Evebooking.findByStatus", query = "SELECT e FROM Evebooking e WHERE e.status = :status")
    , @NamedQuery(name = "Evebooking.findByTxuser", query = "SELECT e FROM Evebooking e WHERE e.txuser = :txuser")
    , @NamedQuery(name = "Evebooking.findByTxhost", query = "SELECT e FROM Evebooking e WHERE e.txhost = :txhost")
    , @NamedQuery(name = "Evebooking.findByTxdate", query = "SELECT e FROM Evebooking e WHERE e.txdate = :txdate")})
public class Evebooking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbooking")
    private Integer idbooking;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "status")
    private Integer status;
    @Size(max = 45)
    @Column(name = "txuser")
    private String txuser;
    @Size(max = 100)
    @Column(name = "txhost")
    private String txhost;
    @Column(name = "txdate")
    @Temporal(TemporalType.DATE)
    private Date txdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbooking", fetch = FetchType.LAZY)
    private List<Evepayment> evepaymentList;
    @JoinColumn(name = "idticket", referencedColumnName = "idticket")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Eveticket idticket;
    @JoinColumn(name = "idevent", referencedColumnName = "idevent")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Eveevent idevent;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Eveuser iduser;

    public Evebooking() {
    }

    public Evebooking(Integer idbooking) {
        this.idbooking = idbooking;
    }

    public Integer getIdbooking() {
        return idbooking;
    }

    public void setIdbooking(Integer idbooking) {
        this.idbooking = idbooking;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
    public List<Evepayment> getEvepaymentList() {
        return evepaymentList;
    }

    public void setEvepaymentList(List<Evepayment> evepaymentList) {
        this.evepaymentList = evepaymentList;
    }

    public Eveticket getIdticket() {
        return idticket;
    }

    public void setIdticket(Eveticket idticket) {
        this.idticket = idticket;
    }

    public Eveevent getIdevent() {
        return idevent;
    }

    public void setIdevent(Eveevent idevent) {
        this.idevent = idevent;
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
        hash += (idbooking != null ? idbooking.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evebooking)) {
            return false;
        }
        Evebooking other = (Evebooking) object;
        if ((this.idbooking == null && other.idbooking != null) || (this.idbooking != null && !this.idbooking.equals(other.idbooking))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.com.chatbotsoftI.domain.Evebooking[ idbooking=" + idbooking + " ]";
    }
    
}