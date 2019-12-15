package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "eve_lease_place", schema = "dbbot", catalog = "")
public class EveLeasePlaceEntity {
    private int idLeasePlace;
    private String namePlace;
    private Date date;
    private BigDecimal price;
    private Integer status;
    private String txUser;
    private String txHost;
    private Date txDate;
    private EveUserEntity eveUserByIdUser;
    private EveAddressEntity eveAddressByIdAddress;
    private List<EveNotificationEntity> eveNotificationsByIdLeasePlace;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lease_place", nullable = false)
    public int getIdLeasePlace() {
        return idLeasePlace;
    }

    public void setIdLeasePlace(int idLeasePlace) {
        this.idLeasePlace = idLeasePlace;
    }

    @Basic
    @Column(name = "name_place", nullable = true, length = 45)
    public String getNamePlace() {
        return namePlace;
    }

    public void setNamePlace(String namePlace) {
        this.namePlace = namePlace;
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
    @Column(name = "tx_user", nullable = true, length = 45)
    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    @Basic
    @Column(name = "tx_host", nullable = true, length = 100)
    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    @Basic
    @Column(name = "tx_date", nullable = true)
    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EveLeasePlaceEntity that = (EveLeasePlaceEntity) o;

        if (idLeasePlace != that.idLeasePlace) return false;
        if (namePlace != null ? !namePlace.equals(that.namePlace) : that.namePlace != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txHost != null ? !txHost.equals(that.txHost) : that.txHost != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idLeasePlace;
        result = 31 * result + (namePlace != null ? namePlace.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txHost != null ? txHost.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    public EveUserEntity getEveUserByIdUser() {
        return eveUserByIdUser;
    }

    public void setEveUserByIdUser(EveUserEntity eveUserByIdUser) {
        this.eveUserByIdUser = eveUserByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "id_address", referencedColumnName = "id_address", nullable = false)
    public EveAddressEntity getEveAddressByIdAddress() {
        return eveAddressByIdAddress;
    }

    public void setEveAddressByIdAddress(EveAddressEntity eveAddressByIdAddress) {
        this.eveAddressByIdAddress = eveAddressByIdAddress;
    }

    @OneToMany(mappedBy = "eveLeasePlaceByIdLeasePlace")
    public List<EveNotificationEntity> getEveNotificationsByIdLeasePlace() {
        return eveNotificationsByIdLeasePlace;
    }

    public void setEveNotificationsByIdLeasePlace(List<EveNotificationEntity> eveNotificationsByIdLeasePlace) {
        this.eveNotificationsByIdLeasePlace = eveNotificationsByIdLeasePlace;
    }
}
