package edu.com.chatbotsoftI.entity;
/*
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

@Entity
@Table(name = "eveleaseplace")
@XmlRootElement

public class EveLeasePlaceEntity implements Serializable{
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
    private List<EveNotificationEntity> evenotificationList;
    @JoinColumn(name = "idaddress", referencedColumnName = "idaddress")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveAddressEntity idaddress;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveUserEntity iduser;

    public EveLeasePlaceEntity() {
    }

    public EveLeasePlaceEntity(Integer idleaseplace) {
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
    public List<EveNotificationEntity> getEvenotificationList() {
        return evenotificationList;
    }

    public void setEvenotificationList(List<EveNotificationEntity> evenotificationList) {
        this.evenotificationList = evenotificationList;
    }

    public EveAddressEntity getIdaddress() {
        return idaddress;
    }

    public void setIdaddress(EveAddressEntity idaddress) {
        this.idaddress = idaddress;
    }

    public EveUserEntity getIduser() {
        return iduser;
    }

    public void setIduser(EveUserEntity iduser) {
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
        if (!(object instanceof EveLeasePlaceEntity)) {
            return false;
        }
        EveLeasePlaceEntity other = (EveLeasePlaceEntity) object;
        if ((this.idleaseplace == null && other.idleaseplace != null) || (this.idleaseplace != null && !this.idleaseplace.equals(other.idleaseplace))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EveLeasePlaceEntity[ idleaseplace=" + idleaseplace + " ]";
    }

}
*/

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "eveleaseplace", schema = "dbbot", catalog = "")
public class EveLeasePlaceEntity {
    private Integer idleaseplace;
    private String nameplace;
    private Date date;
    private BigDecimal price;
    private Integer status;
    private String txuser;
    private String txhost;
    private Date txdate;
    private EveUserEntity eveuserByIduser;
    private EveAddressEntity eveaddressByIdaddress;
    private List<EveNotificationEntity> evenotificationsByIdleaseplace;

    @Id
    @Column(name = "idleaseplace", nullable = false)
    public Integer getIdleaseplace() {
        return idleaseplace;
    }

    public void setIdleaseplace(Integer idleaseplace) {
        this.idleaseplace = idleaseplace;
    }

    @Basic
    @Column(name = "nameplace", nullable = true, length = 45)
    public String getNameplace() {
        return nameplace;
    }

    public void setNameplace(String nameplace) {
        this.nameplace = nameplace;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 5)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "txuser", nullable = true, length = 45)
    public String getTxuser() {
        return txuser;
    }

    public void setTxuser(String txuser) {
        this.txuser = txuser;
    }

    @Basic
    @Column(name = "txhost", nullable = true, length = 100)
    public String getTxhost() {
        return txhost;
    }

    public void setTxhost(String txhost) {
        this.txhost = txhost;
    }

    @Basic
    @Column(name = "txdate", nullable = true)
    public Date getTxdate() {
        return txdate;
    }

    public void setTxdate(Date txdate) {
        this.txdate = txdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EveLeasePlaceEntity that = (EveLeasePlaceEntity) o;

        if (idleaseplace != null ? !idleaseplace.equals(that.idleaseplace) : that.idleaseplace != null) return false;
        if (nameplace != null ? !nameplace.equals(that.nameplace) : that.nameplace != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (txuser != null ? !txuser.equals(that.txuser) : that.txuser != null) return false;
        if (txhost != null ? !txhost.equals(that.txhost) : that.txhost != null) return false;
        if (txdate != null ? !txdate.equals(that.txdate) : that.txdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idleaseplace != null ? idleaseplace.hashCode() : 0;
        result = 31 * result + (nameplace != null ? nameplace.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (txuser != null ? txuser.hashCode() : 0);
        result = 31 * result + (txhost != null ? txhost.hashCode() : 0);
        result = 31 * result + (txdate != null ? txdate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "iduser", referencedColumnName = "iduser", nullable = false)
    public EveUserEntity getEveuserByIduser() {
        return eveuserByIduser;
    }

    public void setEveuserByIduser(EveUserEntity eveuserByIduser) {
        this.eveuserByIduser = eveuserByIduser;
    }

    @ManyToOne
    @JoinColumn(name = "idaddress", referencedColumnName = "idaddress", nullable = false)
    public EveAddressEntity getEveaddressByIdaddress() {
        return eveaddressByIdaddress;
    }

    public void setEveaddressByIdaddress(EveAddressEntity eveaddressByIdaddress) {
        this.eveaddressByIdaddress = eveaddressByIdaddress;
    }

    @OneToMany(mappedBy = "eveleaseplaceByIdleaseplace")
    public List<EveNotificationEntity> getEvenotificationsByIdleaseplace() {
        return evenotificationsByIdleaseplace;
    }

    public void setEvenotificationsByIdleaseplace(List<EveNotificationEntity> evenotificationsByIdleaseplace) {
        this.evenotificationsByIdleaseplace = evenotificationsByIdleaseplace;
    }
}
