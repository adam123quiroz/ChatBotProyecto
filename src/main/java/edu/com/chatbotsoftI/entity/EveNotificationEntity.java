package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "evenotification", schema = "dbbot", catalog = "")
public class EveNotificationEntity {
    private Integer idnotification;
    private String msnotification;
    private EveLeasePlaceEntity eveleaseplaceByIdleaseplace;
    private List<EveNotificationUserEntity> evenotificationusersByIdnotification;

    @Id
    @Column(name = "idnotification", nullable = false)
    public Integer getIdnotification() {
        return idnotification;
    }

    public void setIdnotification(Integer idnotification) {
        this.idnotification = idnotification;
    }

    @Basic
    @Column(name = "msnotification", nullable = true, length = 45)
    public String getMsnotification() {
        return msnotification;
    }

    public void setMsnotification(String msnotification) {
        this.msnotification = msnotification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EveNotificationEntity that = (EveNotificationEntity) o;

        if (idnotification != null ? !idnotification.equals(that.idnotification) : that.idnotification != null)
            return false;
        if (msnotification != null ? !msnotification.equals(that.msnotification) : that.msnotification != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idnotification != null ? idnotification.hashCode() : 0;
        result = 31 * result + (msnotification != null ? msnotification.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idleaseplace", referencedColumnName = "idleaseplace", nullable = false)
    public EveLeasePlaceEntity getEveleaseplaceByIdleaseplace() {
        return eveleaseplaceByIdleaseplace;
    }

    public void setEveleaseplaceByIdleaseplace(EveLeasePlaceEntity eveleaseplaceByIdleaseplace) {
        this.eveleaseplaceByIdleaseplace = eveleaseplaceByIdleaseplace;
    }

    @OneToMany(mappedBy = "evenotificationByIdnotification")
    public List<EveNotificationUserEntity> getEvenotificationusersByIdnotification() {
        return evenotificationusersByIdnotification;
    }

    public void setEvenotificationusersByIdnotification(List<EveNotificationUserEntity> evenotificationusersByIdnotification) {
        this.evenotificationusersByIdnotification = evenotificationusersByIdnotification;
    }
}
