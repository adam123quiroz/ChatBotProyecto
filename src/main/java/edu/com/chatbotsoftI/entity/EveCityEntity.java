package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "evecity", schema = "dbbot", catalog = "")
public class EveCityEntity {
    private Integer idcity;
    private String city;
    private List<EveAddressEntity> eveaddressesByIdcity;
    private EveStateEntity evestateByIdstate;

    @Id
    @Column(name = "idcity", nullable = false)
    public Integer getIdcity() {
        return idcity;
    }

    public void setIdcity(Integer idcity) {
        this.idcity = idcity;
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

        if (idcity != null ? !idcity.equals(that.idcity) : that.idcity != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcity != null ? idcity.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "evecityByIdcity")
    public List<EveAddressEntity> getEveaddressesByIdcity() {
        return eveaddressesByIdcity;
    }

    public void setEveaddressesByIdcity(List<EveAddressEntity> eveaddressesByIdcity) {
        this.eveaddressesByIdcity = eveaddressesByIdcity;
    }

    @ManyToOne
    @JoinColumn(name = "idstate", referencedColumnName = "idstate", nullable = false)
    public EveStateEntity getEvestateByIdstate() {
        return evestateByIdstate;
    }

    public void setEvestateByIdstate(EveStateEntity evestateByIdstate) {
        this.evestateByIdstate = evestateByIdstate;
    }
}
