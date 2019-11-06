/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.chatbotsoftI.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "evebuyticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evebuyticket.findAll", query = "SELECT e FROM Evebuyticket e")
    , @NamedQuery(name = "Evebuyticket.findByIdbuy", query = "SELECT e FROM Evebuyticket e WHERE e.idbuy = :idbuy")
    , @NamedQuery(name = "Evebuyticket.findByQuantity", query = "SELECT e FROM Evebuyticket e WHERE e.quantity = :quantity")
    , @NamedQuery(name = "Evebuyticket.findByTotal", query = "SELECT e FROM Evebuyticket e WHERE e.total = :total")
    , @NamedQuery(name = "Evebuyticket.findByTxuser", query = "SELECT e FROM Evebuyticket e WHERE e.txuser = :txuser")
    , @NamedQuery(name = "Evebuyticket.findByTxhost", query = "SELECT e FROM Evebuyticket e WHERE e.txhost = :txhost")
    , @NamedQuery(name = "Evebuyticket.findByTxdate", query = "SELECT e FROM Evebuyticket e WHERE e.txdate = :txdate")})
public class Evebuyticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbuy")
    private Integer idbuy;
    @Column(name = "quantity")
    private Integer quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private BigDecimal total;
    @Size(max = 45)
    @Column(name = "txuser")
    private String txuser;
    @Size(max = 100)
    @Column(name = "txhost")
    private String txhost;
    @Column(name = "txdate")
    @Temporal(TemporalType.DATE)
    private Date txdate;
    @JoinColumn(name = "idevent", referencedColumnName = "idevent")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Eveevent idevent;
    @JoinColumn(name = "idpaymentmethod", referencedColumnName = "idpaymentmethod")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Evepaymentmethod idpaymentmethod;
    @JoinColumn(name = "idticket", referencedColumnName = "idticket")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Eveticket idticket;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Eveuser iduser;

    public Evebuyticket() {
    }

    public Evebuyticket(Integer idbuy) {
        this.idbuy = idbuy;
    }

    public Integer getIdbuy() {
        return idbuy;
    }

    public void setIdbuy(Integer idbuy) {
        this.idbuy = idbuy;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public Eveevent getIdevent() {
        return idevent;
    }

    public void setIdevent(Eveevent idevent) {
        this.idevent = idevent;
    }

    public Evepaymentmethod getIdpaymentmethod() {
        return idpaymentmethod;
    }

    public void setIdpaymentmethod(Evepaymentmethod idpaymentmethod) {
        this.idpaymentmethod = idpaymentmethod;
    }

    public Eveticket getIdticket() {
        return idticket;
    }

    public void setIdticket(Eveticket idticket) {
        this.idticket = idticket;
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
        hash += (idbuy != null ? idbuy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evebuyticket)) {
            return false;
        }
        Evebuyticket other = (Evebuyticket) object;
        if ((this.idbuy == null && other.idbuy != null) || (this.idbuy != null && !this.idbuy.equals(other.idbuy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.com.chatbotsoftI.domain.Evebuyticket[ idbuy=" + idbuy + " ]";
    }
    
}
