package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "eve_notification", schema = "dbbot", catalog = "")
public class EveNotificationEntity {
    private int idNotification;
    private String msNotification;
    private EveLeasePlaceEntity eveLeasePlaceByIdLeasePlace;
    private List<EveNotificationUserEntity> eveNotificationUsersByIdNotification;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification", nullable = false)
    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

    @Basic
    @Column(name = "ms_notification", nullable = true, length = 45)
    public String getMsNotification() {
        return msNotification;
    }

    public void setMsNotification(String msNotification) {
        this.msNotification = msNotification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EveNotificationEntity that = (EveNotificationEntity) o;

        if (idNotification != that.idNotification) return false;
        if (msNotification != null ? !msNotification.equals(that.msNotification) : that.msNotification != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idNotification;
        result = 31 * result + (msNotification != null ? msNotification.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_lease_place", referencedColumnName = "id_lease_place", nullable = false)
    public EveLeasePlaceEntity getEveLeasePlaceByIdLeasePlace() {
        return eveLeasePlaceByIdLeasePlace;
    }

    public void setEveLeasePlaceByIdLeasePlace(EveLeasePlaceEntity eveLeasePlaceByIdLeasePlace) {
        this.eveLeasePlaceByIdLeasePlace = eveLeasePlaceByIdLeasePlace;
    }

    @OneToMany(mappedBy = "eveNotificationByIdNotification")
    public List<EveNotificationUserEntity> getEveNotificationUsersByIdNotification() {
        return eveNotificationUsersByIdNotification;
    }

    public void setEveNotificationUsersByIdNotification(List<EveNotificationUserEntity> eveNotificationUsersByIdNotification) {
        this.eveNotificationUsersByIdNotification = eveNotificationUsersByIdNotification;
    }
}
