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
@Table(name = "evepaymentmethod")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evepaymentmethod.findAll", query = "SELECT e FROM Evepaymentmethod e")
    , @NamedQuery(name = "Evepaymentmethod.findByIdpaymentmethod", query = "SELECT e FROM Evepaymentmethod e WHERE e.idpaymentmethod = :idpaymentmethod")
    , @NamedQuery(name = "Evepaymentmethod.findByPaymentmethod", query = "SELECT e FROM Evepaymentmethod e WHERE e.paymentmethod = :paymentmethod")})
public class Evepaymentmethod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpaymentmethod")
    private Integer idpaymentmethod;
    @Size(max = 45)
    @Column(name = "paymentmethod")
    private String paymentmethod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpaymentmethod", fetch = FetchType.LAZY)
    private List<Evepayment> evepaymentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpaymentmethod", fetch = FetchType.LAZY)
    private List<Evebuyticket> evebuyticketList;

    public Evepaymentmethod() {
    }

    public Evepaymentmethod(Integer idpaymentmethod) {
        this.idpaymentmethod = idpaymentmethod;
    }

    public Integer getIdpaymentmethod() {
        return idpaymentmethod;
    }

    public void setIdpaymentmethod(Integer idpaymentmethod) {
        this.idpaymentmethod = idpaymentmethod;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    @XmlTransient
    public List<Evepayment> getEvepaymentList() {
        return evepaymentList;
    }

    public void setEvepaymentList(List<Evepayment> evepaymentList) {
        this.evepaymentList = evepaymentList;
    }

    @XmlTransient
    public List<Evebuyticket> getEvebuyticketList() {
        return evebuyticketList;
    }

    public void setEvebuyticketList(List<Evebuyticket> evebuyticketList) {
        this.evebuyticketList = evebuyticketList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpaymentmethod != null ? idpaymentmethod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evepaymentmethod)) {
            return false;
        }
        Evepaymentmethod other = (Evepaymentmethod) object;
        if ((this.idpaymentmethod == null && other.idpaymentmethod != null) || (this.idpaymentmethod != null && !this.idpaymentmethod.equals(other.idpaymentmethod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.com.chatbotsoftI.domain.Evepaymentmethod[ idpaymentmethod=" + idpaymentmethod + " ]";
    }
    
}
