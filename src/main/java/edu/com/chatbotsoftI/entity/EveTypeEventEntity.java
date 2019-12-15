package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "eve_type_event", schema = "dbbot", catalog = "")
public class EveTypeEventEntity {
    private int idTypeEvent;
    private String typeEvent;
    private List<EveEventEntity> eveEventsByIdTypeEvent;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_event", nullable = false)
    public int getIdTypeEvent() {
        return idTypeEvent;
    }

    public void setIdTypeEvent(int idTypeEvent) {
        this.idTypeEvent = idTypeEvent;
    }

    @Basic
    @Column(name = "type_event", nullable = true, length = 45)
    public String getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(String typeEvent) {
        this.typeEvent = typeEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EveTypeEventEntity that = (EveTypeEventEntity) o;

        if (idTypeEvent != that.idTypeEvent) return false;
        if (typeEvent != null ? !typeEvent.equals(that.typeEvent) : that.typeEvent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTypeEvent;
        result = 31 * result + (typeEvent != null ? typeEvent.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "eveTypeEventByIdTypeEvent")
    public List<EveEventEntity> getEveEventsByIdTypeEvent() {
        return eveEventsByIdTypeEvent;
    }

    public void setEveEventsByIdTypeEvent(List<EveEventEntity> eveEventsByIdTypeEvent) {
        this.eveEventsByIdTypeEvent = eveEventsByIdTypeEvent;
    }
}
