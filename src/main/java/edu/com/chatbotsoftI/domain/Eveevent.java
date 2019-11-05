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
@Table(name = "eveevent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eveevent.findAll", query = "SELECT e FROM Eveevent e")
    , @NamedQuery(name = "Eveevent.findByIdevent", query = "SELECT e FROM Eveevent e WHERE e.idevent = :idevent")
    , @NamedQuery(name = "Eveevent.findByNameevent", query = "SELECT e FROM Eveevent e WHERE e.nameevent = :nameevent")
    , @NamedQuery(name = "Eveevent.findByPrice", query = "SELECT e FROM Eveevent e WHERE e.price = :price")
    , @NamedQuery(name = "Eveevent.findByDate", query = "SELECT e FROM Eveevent e WHERE e.date = :date")
    , @NamedQuery(name = "Eveevent.findByStarttime", query = "SELECT e FROM Eveevent e WHERE e.starttime = :starttime")
    , @NamedQuery(name = "Eveevent.findByStatus", query = "SELECT e FROM Eveevent e WHERE e.status = :status")
    , @NamedQuery(name = "Eveevent.findByTxuser", query = "SELECT e FROM Eveevent e WHERE e.txuser = :txuser")
    , @NamedQuery(name = "Eveevent.findByTxhost", query = "SELECT e FROM Eveevent e WHERE e.txhost = :txhost")
    , @NamedQuery(name = "Eveevent.findByTxdate", query = "SELECT e FROM Eveevent e WHERE e.txdate = :txdate")})
public class Eveevent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idevent")
    private Integer idevent;
    @Size(max = 100)
    @Column(name = "nameevent")
    private String nameevent;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "starttime")
    @Temporal(TemporalType.TIME)
    private Date starttime;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idevent", fetch = FetchType.LAZY)
    private List<Eveeventfile> eveeventfileList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idevent", fetch = FetchType.LAZY)
    private List<Evebuyticket> evebuyticketList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idevent", fetch = FetchType.LAZY)
    private List<Evebooking> evebookingList;
    @JoinColumn(name = "idcategory", referencedColumnName = "idcategory")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Evecategory idcategory;
    @JoinColumn(name = "idaddress", referencedColumnName = "idaddress")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Eveaddress idaddress;
    @JoinColumn(name = "idtypeevent", referencedColumnName = "idtypeevent")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Evetypeevent idtypeevent;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Eveuser iduser;

    public Eveevent() {
    }

    public Eveevent(Integer idevent) {
        this.idevent = idevent;
    }

    public Integer getIdevent() {
        return idevent;
    }

    public void setIdevent(Integer idevent) {
        this.idevent = idevent;
    }

    public String getNameevent() {
        return nameevent;
    }

    public void setNameevent(String nameevent) {
        this.nameevent = nameevent;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
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
    public List<Eveeventfile> getEveeventfileList() {
        return eveeventfileList;
    }

    public void setEveeventfileList(List<Eveeventfile> eveeventfileList) {
        this.eveeventfileList = eveeventfileList;
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

    public Evecategory getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(Evecategory idcategory) {
        this.idcategory = idcategory;
    }

    public Eveaddress getIdaddress() {
        return idaddress;
    }

    public void setIdaddress(Eveaddress idaddress) {
        this.idaddress = idaddress;
    }

    public Evetypeevent getIdtypeevent() {
        return idtypeevent;
    }

    public void setIdtypeevent(Evetypeevent idtypeevent) {
        this.idtypeevent = idtypeevent;
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
        hash += (idevent != null ? idevent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eveevent)) {
            return false;
        }
        Eveevent other = (Eveevent) object;
        if ((this.idevent == null && other.idevent != null) || (this.idevent != null && !this.idevent.equals(other.idevent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.com.chatbotsoftI.domain.Eveevent[ idevent=" + idevent + " ]";
    }
    
}
