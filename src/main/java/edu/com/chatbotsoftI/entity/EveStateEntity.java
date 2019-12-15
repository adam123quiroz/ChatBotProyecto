package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "eve_state", schema = "dbbot", catalog = "")
public class EveStateEntity {
    private int idState;
    private String state;
    private List<EveCityEntity> eveCitiesByIdState;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_state", nullable = false)
    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
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

        if (idState != that.idState) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idState;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "eveStateByIdState")
    public List<EveCityEntity> getEveCitiesByIdState() {
        return eveCitiesByIdState;
    }

    public void setEveCitiesByIdState(List<EveCityEntity> eveCitiesByIdState) {
        this.eveCitiesByIdState = eveCitiesByIdState;
    }
}
