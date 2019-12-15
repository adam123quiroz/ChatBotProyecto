package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "eve_address", schema = "dbbot", catalog = "")
public class EveAddressEntity {
    private int idAddress;
    private String address;
    private EveCityEntity eveCityByIdCity;
    private List<EveEventEntity> eveEventsByIdAddress;
    private List<EveLeasePlaceEntity> eveLeasePlacesByIdAddress;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address", nullable = false)
    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
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

        if (idAddress != that.idAddress) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAddress;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_city", referencedColumnName = "id_city", nullable = false)
    public EveCityEntity getEveCityByIdCity() {
        return eveCityByIdCity;
    }

    public void setEveCityByIdCity(EveCityEntity eveCityByIdCity) {
        this.eveCityByIdCity = eveCityByIdCity;
    }

    @OneToMany(mappedBy = "eveAddressByIdAddress")
    public List<EveEventEntity> getEveEventsByIdAddress() {
        return eveEventsByIdAddress;
    }

    public void setEveEventsByIdAddress(List<EveEventEntity> eveEventsByIdAddress) {
        this.eveEventsByIdAddress = eveEventsByIdAddress;
    }

    @OneToMany(mappedBy = "eveAddressByIdAddress")
    public List<EveLeasePlaceEntity> getEveLeasePlacesByIdAddress() {
        return eveLeasePlacesByIdAddress;
    }

    public void setEveLeasePlacesByIdAddress(List<EveLeasePlaceEntity> eveLeasePlacesByIdAddress) {
        this.eveLeasePlacesByIdAddress = eveLeasePlacesByIdAddress;
    }
}
