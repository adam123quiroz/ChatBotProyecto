package edu.com.chatbotsoftI.entity;

import javax.persistence.*;

@Entity
@Table(name = "evenotificationuser", schema = "dbbot", catalog = "")
public class EveNotificationUserEntity {
    private Integer idnotificationuser;
    private EveNotificationEntity evenotificationByIdnotification;
    private EveUserEntity eveuserByIduser;

    @Id
    @Column(name = "idnotificationuser", nullable = false)
    public Integer getIdnotificationuser() {
        return idnotificationuser;
    }

    public void setIdnotificationuser(Integer idnotificationuser) {
        this.idnotificationuser = idnotificationuser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EveNotificationUserEntity that = (EveNotificationUserEntity) o;

        if (idnotificationuser != null ? !idnotificationuser.equals(that.idnotificationuser) : that.idnotificationuser != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idnotificationuser != null ? idnotificationuser.hashCode() : 0;
    }

    @ManyToOne
    @JoinColumn(name = "idnotification", referencedColumnName = "idnotification", nullable = false)
    public EveNotificationEntity getEvenotificationByIdnotification() {
        return evenotificationByIdnotification;
    }

    public void setEvenotificationByIdnotification(EveNotificationEntity evenotificationByIdnotification) {
        this.evenotificationByIdnotification = evenotificationByIdnotification;
    }

    @ManyToOne
    @JoinColumn(name = "iduser", referencedColumnName = "iduser", nullable = false)
    public EveUserEntity getEveuserByIduser() {
        return eveuserByIduser;
    }

    public void setEveuserByIduser(EveUserEntity eveuserByIduser) {
        this.eveuserByIduser = eveuserByIduser;
    }
}
