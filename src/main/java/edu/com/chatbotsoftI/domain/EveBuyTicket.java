/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.chatbotsoftI.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "evebuyticket")
@XmlRootElement
public class EveBuyTicket implements Serializable {

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
    private EveEvent idevent;
    @JoinColumn(name = "idpaymentmethod", referencedColumnName = "idpaymentmethod")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EvePaymentMethod idpaymentmethod;
    @JoinColumn(name = "idticket", referencedColumnName = "idticket")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveTicket idticket;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveUser iduser;

    public EveBuyTicket() {
    }

    public EveBuyTicket(Integer idbuy) {
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

    public EveEvent getIdevent() {
        return idevent;
    }

    public void setIdevent(EveEvent idevent) {
        this.idevent = idevent;
    }

    public EvePaymentMethod getIdpaymentmethod() {
        return idpaymentmethod;
    }

    public void setIdpaymentmethod(EvePaymentMethod idpaymentmethod) {
        this.idpaymentmethod = idpaymentmethod;
    }

    public EveTicket getIdticket() {
        return idticket;
    }

    public void setIdticket(EveTicket idticket) {
        this.idticket = idticket;
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
        hash += (idbuy != null ? idbuy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EveBuyTicket)) {
            return false;
        }
        EveBuyTicket other = (EveBuyTicket) object;
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