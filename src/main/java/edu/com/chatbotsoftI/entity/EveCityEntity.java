package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "eve_city", schema = "dbbot", catalog = "")
public class EveCityEntity {
    private int idCity;
    private String city;
    private List<EveAddressEntity> eveAddressesByIdCity;
    private EveStateEntity eveStateByIdState;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_city", nullable = false)
    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    @Basic
    @Column(name = "city", nullable = true, length = 45)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EveCityEntity that = (EveCityEntity) o;

        if (idCity != that.idCity) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCity;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "eveCityByIdCity")
    public List<EveAddressEntity> getEveAddressesByIdCity() {
        return eveAddressesByIdCity;
    }

    public void setEveAddressesByIdCity(List<EveAddressEntity> eveAddressesByIdCity) {
        this.eveAddressesByIdCity = eveAddressesByIdCity;
    }

    @ManyToOne
    @JoinColumn(name = "id_state", referencedColumnName = "id_state", nullable = false)
    public EveStateEntity getEveStateByIdState() {
        return eveStateByIdState;
    }

    public void setEveStateByIdState(EveStateEntity eveStateByIdState) {
        this.eveStateByIdState = eveStateByIdState;
    }
}
