/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.chatbotsoftI.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "eveleaseplace")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eveleaseplace.findAll", query = "SELECT e FROM Eveleaseplace e")
    , @NamedQuery(name = "Eveleaseplace.findByIdleaseplace", query = "SELECT e FROM Eveleaseplace e WHERE e.idleaseplace = :idleaseplace")
    , @NamedQuery(name = "Eveleaseplace.findByNameplace", query = "SELECT e FROM Eveleaseplace e WHERE e.nameplace = :nameplace")
    , @NamedQuery(name = "Eveleaseplace.findByDate", query = "SELECT e FROM Eveleaseplace e WHERE e.date = :date")
    , @NamedQuery(name = "Eveleaseplace.findByPrice", query = "SELECT e FROM Eveleaseplace e WHERE e.price = :price")
    , @NamedQuery(name = "Eveleaseplace.findByStatus", query = "SELECT e FROM Eveleaseplace e WHERE e.status = :status")
    , @NamedQuery(name = "Eveleaseplace.findByTxuser", query = "SELECT e FROM Eveleaseplace e WHERE e.txuser = :txuser")
    , @NamedQuery(name = "Eveleaseplace.findByTxhost", query = "SELECT e FROM Eveleaseplace e WHERE e.txhost = :txhost")
    , @NamedQuery(name = "Eveleaseplace.findByTxdate", query = "SELECT e FROM Eveleaseplace e WHERE e.txdate = :txdate")})
public class Eveleaseplace implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idleaseplace")
    private Integer idleaseplace;
    @Size(max = 45)
    @Column(name = "nameplace")
    private String nameplace;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private BigDecimal price;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idleaseplace", fetch = FetchType.LAZY)
    private List<Evenotification> evenotificationList;
    @JoinColumn(name = "idaddress", referencedColumnName = "idaddress")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Eveaddress idaddress;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Eveuser iduser;

    public Eveleaseplace() {
    }

    public Eveleaseplace(Integer idleaseplace) {
        this.idleaseplace = idleaseplace;
    }

    public Integer getIdleaseplace() {
        return idleaseplace;
    }

    public void setIdleaseplace(Integer idleaseplace) {
        this.idleaseplace = idleaseplace;
    }

    public String getNameplace() {
        return nameplace;
    }

    public void setNameplace(String nameplace) {
        this.nameplace = nameplace;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
    public List<Evenotification> getEvenotificationList() {
        return evenotificationList;
    }

    public void setEvenotificationList(List<Evenotification> evenotificationList) {
        this.evenotificationList = evenotificationList;
    }

    public Eveaddress getIdaddress() {
        return idaddress;
    }

    public void setIdaddress(Eveaddress idaddress) {
        this.idaddress = idaddress;
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
        hash += (idleaseplace != null ? idleaseplace.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eveleaseplace)) {
            return false;
        }
        Eveleaseplace other = (Eveleaseplace) object;
        if ((this.idleaseplace == null && other.idleaseplace != null) || (this.idleaseplace != null && !this.idleaseplace.equals(other.idleaseplace))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.com.chatbotsoftI.domain.Eveleaseplace[ idleaseplace=" + idleaseplace + " ]";
    }
    
}
