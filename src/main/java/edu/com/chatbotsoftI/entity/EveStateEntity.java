package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "evestate", schema = "dbbot", catalog = "")
public class EveStateEntity {
    private Integer idstate;
    private String state;
    private List<EveCityEntity> evecitiesByIdstate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idstate", nullable = false)
    public Integer getIdstate() {
        return idstate;
    }

    public void setIdstate(Integer idstate) {
        this.idstate = idstate;
    }

    @Basic
    @Column(name = "state", nullable = true, length = 45)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EveStateEntity that = (EveStateEntity) o;

        if (idstate != null ? !idstate.equals(that.idstate) : that.idstate != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idstate != null ? idstate.hashCode() : 0;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "evestateByIdstate")
    public List<EveCityEntity> getEvecitiesByIdstate() {
        return evecitiesByIdstate;
    }

    public void setEvecitiesByIdstate(List<EveCityEntity> evecitiesByIdstate) {
        this.evecitiesByIdstate = evecitiesByIdstate;
    }
}
