package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "eveaddress", schema = "dbbot", catalog = "")
public class EveAddressEntity {
    private Integer idaddress;
    private String address;
    private EveCityEntity evecityByIdcity;
    private List<EveEventEntity> eveeventsByIdaddress;
    private List<EveLeasePlaceEntity> eveleaseplacesByIdaddress;

    @Id
    @Column(name = "idaddress", nullable = false)
    public Integer getIdaddress() {
        return idaddress;
    }

    public void setIdaddress(Integer idaddress) {
        this.idaddress = idaddress;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 45)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EveAddressEntity that = (EveAddressEntity) o;

        if (idaddress != null ? !idaddress.equals(that.idaddress) : that.idaddress != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idaddress != null ? idaddress.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idcity", referencedColumnName = "idcity", nullable = false)
    public EveCityEntity getEvecityByIdcity() {
        return evecityByIdcity;
    }

    public void setEvecityByIdcity(EveCityEntity evecityByIdcity) {
        this.evecityByIdcity = evecityByIdcity;
    }

    @OneToMany(mappedBy = "eveaddressByIdaddress")
    public List<EveEventEntity> getEveeventsByIdaddress() {
        return eveeventsByIdaddress;
    }

    public void setEveeventsByIdaddress(List<EveEventEntity> eveeventsByIdaddress) {
        this.eveeventsByIdaddress = eveeventsByIdaddress;
    }

    @OneToMany(mappedBy = "eveaddressByIdaddress")
    public List<EveLeasePlaceEntity> getEveleaseplacesByIdaddress() {
        return eveleaseplacesByIdaddress;
    }

    public void setEveleaseplacesByIdaddress(List<EveLeasePlaceEntity> eveleaseplacesByIdaddress) {
        this.eveleaseplacesByIdaddress = eveleaseplacesByIdaddress;
    }
}
