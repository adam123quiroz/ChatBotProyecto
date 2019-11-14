package edu.com.chatbotsoftI.entity;
/*
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "evenotificationuser")
@XmlRootElement

public class EveNotificationUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnotificationuser")
    private Integer idnotificationuser;
    @JoinColumn(name = "idnotification", referencedColumnName = "idnotification")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveNotificationEntity idnotification;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveUserEntity iduser;

    public EveNotificationUserEntity() {
    }

    public EveNotificationUserEntity(Integer idnotificationuser) {
        this.idnotificationuser = idnotificationuser;
    }

    public Integer getIdnotificationuser() {
        return idnotificationuser;
    }

    public void setIdnotificationuser(Integer idnotificationuser) {
        this.idnotificationuser = idnotificationuser;
    }

    public EveNotificationEntity getIdnotification() {
        return idnotification;
    }

    public void setIdnotification(EveNotificationEntity idnotification) {
        this.idnotification = idnotification;
    }

    public EveUserEntity getIduser() {
        return iduser;
    }

    public void setIduser(EveUserEntity iduser) {
        this.iduser = iduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnotificationuser != null ? idnotificationuser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EveNotificationUserEntity)) {
            return false;
        }
        EveNotificationUserEntity other = (EveNotificationUserEntity) object;
        if ((this.idnotificationuser == null && other.idnotificationuser != null) || (this.idnotificationuser != null && !this.idnotificationuser.equals(other.idnotificationuser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EveNotificationUserEntity[ idnotificationuser=" + idnotificationuser + " ]";
    }

}
*/

import javax.persistence.*;

@Entity
@Table(name = "evenotificationuser", schema = "dbbot", catalog = "")
public class EveNotificationUserEntity {
    private Integer idnotificationuser;
    private EveNotificationEntity evenotificationByIdnotification;
    private EveUserEntity eveuserByIduser;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
