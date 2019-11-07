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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "eveleaseplace")
@XmlRootElement
public class EveLeasePlace implements Serializable {

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
    private List<EveNotification> evenotificationList;
    @JoinColumn(name = "idaddress", referencedColumnName = "idaddress")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveAddress idaddress;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveUser iduser;

    public EveLeasePlace() {
    }

    public EveLeasePlace(Integer idleaseplace) {
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
    public List<EveNotification> getEvenotificationList() {
        return evenotificationList;
    }

    public void setEvenotificationList(List<EveNotification> evenotificationList) {
        this.evenotificationList = evenotificationList;
    }

    public EveAddress getIdaddress() {
        return idaddress;
    }

    public void setIdaddress(EveAddress idaddress) {
        this.idaddress = idaddress;
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
        hash += (idleaseplace != null ? idleaseplace.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EveLeasePlace)) {
            return false;
        }
        EveLeasePlace other = (EveLeasePlace) object;
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
