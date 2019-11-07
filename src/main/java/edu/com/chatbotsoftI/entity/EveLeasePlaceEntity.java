package edu.com.chatbotsoftI.entity;

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
