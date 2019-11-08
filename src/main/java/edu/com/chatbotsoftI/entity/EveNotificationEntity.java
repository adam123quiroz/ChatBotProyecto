package edu.com.chatbotsoftI.entity;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "evenotification")
@XmlRootElement

public class EveNotificationEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnotification")
    private Integer idnotification;
    @Size(max = 45)
    @Column(name = "msnotification")
    private String msnotification;
    @JoinColumn(name = "idleaseplace", referencedColumnName = "idleaseplace")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveLeasePlaceEntity idleaseplace;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idnotification", fetch = FetchType.LAZY)
    private List<EveNotificationUserEntity> evenotificationuserList;

    public EveNotificationEntity() {
    }

    public EveNotificationEntity(Integer idnotification) {
        this.idnotification = idnotification;
    }

    public Integer getIdnotification() {
        return idnotification;
    }

    public void setIdnotification(Integer idnotification) {
        this.idnotification = idnotification;
    }

    public String getMsnotification() {
        return msnotification;
    }

    public void setMsnotification(String msnotification) {
        this.msnotification = msnotification;
    }

    public EveLeasePlaceEntity getIdleaseplace() {
        return idleaseplace;
    }

    public void setIdleaseplace(EveLeasePlaceEntity idleaseplace) {
        this.idleaseplace = idleaseplace;
    }

    @XmlTransient
    public List<EveNotificationUserEntity> getEvenotificationuserList() {
        return evenotificationuserList;
    }

    public void setEvenotificationuserList(List<EveNotificationUserEntity> evenotificationuserList) {
        this.evenotificationuserList = evenotificationuserList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnotification != null ? idnotification.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EveNotificationUserEntity)) {
            return false;
        }
        EveNotificationEntity other = (EveNotificationEntity) object;
        if ((this.idnotification == null && other.idnotification != null) || (this.idnotification != null && !this.idnotification.equals(other.idnotification))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EveNotificationEntity[ idnotification=" + idnotification + " ]";
    }

}
