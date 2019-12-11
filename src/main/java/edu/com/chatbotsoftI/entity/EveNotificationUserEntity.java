package edu.com.chatbotsoftI.entity;

import javax.persistence.*;

@Entity
@Table(name = "eve_notification_user", schema = "dbbot", catalog = "")
public class EveNotificationUserEntity {
    private int idNotificationUser;
    private EveNotificationEntity eveNotificationByIdNotification;
    private EveUserEntity eveUserByIdUser;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification_user", nullable = false)
    public int getIdNotificationUser() {
        return idNotificationUser;
    }

    public void setIdNotificationUser(int idNotificationUser) {
        this.idNotificationUser = idNotificationUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EveNotificationUserEntity that = (EveNotificationUserEntity) o;

        if (idNotificationUser != that.idNotificationUser) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idNotificationUser;
    }

    @ManyToOne
    @JoinColumn(name = "id_notification", referencedColumnName = "id_notification", nullable = false)
    public EveNotificationEntity getEveNotificationByIdNotification() {
        return eveNotificationByIdNotification;
    }

    public void setEveNotificationByIdNotification(EveNotificationEntity eveNotificationByIdNotification) {
        this.eveNotificationByIdNotification = eveNotificationByIdNotification;
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    public EveUserEntity getEveUserByIdUser() {
        return eveUserByIdUser;
    }

    public void setEveUserByIdUser(EveUserEntity eveUserByIdUser) {
        this.eveUserByIdUser = eveUserByIdUser;
    }
}
