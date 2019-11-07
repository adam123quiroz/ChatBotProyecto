package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "evetypeevent", schema = "dbbot", catalog = "")
public class EveTypeEventEntity {
    private Integer idtypeevent;
    private String typeevent;
    private List<EveEventEntity> eveeventsByIdtypeevent;

    @Id
    @Column(name = "idtypeevent", nullable = false)
    public Integer getIdtypeevent() {
        return idtypeevent;
    }

    public void setIdtypeevent(Integer idtypeevent) {
        this.idtypeevent = idtypeevent;
    }

    @Basic
    @Column(name = "typeevent", nullable = true, length = 45)
    public String getTypeevent() {
        return typeevent;
    }

    public void setTypeevent(String typeevent) {
        this.typeevent = typeevent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EveTypeEventEntity that = (EveTypeEventEntity) o;

        if (idtypeevent != null ? !idtypeevent.equals(that.idtypeevent) : that.idtypeevent != null) return false;
        if (typeevent != null ? !typeevent.equals(that.typeevent) : that.typeevent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idtypeevent != null ? idtypeevent.hashCode() : 0;
        result = 31 * result + (typeevent != null ? typeevent.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "evetypeeventByIdtypeevent")
    public List<EveEventEntity> getEveeventsByIdtypeevent() {
        return eveeventsByIdtypeevent;
    }

    public void setEveeventsByIdtypeevent(List<EveEventEntity> eveeventsByIdtypeevent) {
        this.eveeventsByIdtypeevent = eveeventsByIdtypeevent;
    }
}
