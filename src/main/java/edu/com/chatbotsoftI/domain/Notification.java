package edu.com.chatbotsoftI.domain;


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

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "notification")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n")
        , @NamedQuery(name = "Notification.findByIdnotification", query = "SELECT n FROM Notification n WHERE n.idnotification = :idnotification")
        , @NamedQuery(name = "Notification.findByMsnotification", query = "SELECT n FROM Notification n WHERE n.msnotification = :msnotification")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnotification")
    private Integer idnotification;
    @Size(max = 45)
    @Column(name = "msnotification")
    private String msnotification;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idnotification", fetch = FetchType.LAZY)
    private List<Notificationuser> notificationuserList;
    @JoinColumn(name = "idleaseplace", referencedColumnName = "idleaseplace")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Leaseplace idleaseplace;

    public Notification() {
    }

    public Notification(Integer idnotification) {
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

    @XmlTransient
    public List<Notificationuser> getNotificationuserList() {
        return notificationuserList;
    }

    public void setNotificationuserList(List<Notificationuser> notificationuserList) {
        this.notificationuserList = notificationuserList;
    }

    public Leaseplace getIdleaseplace() {
        return idleaseplace;
    }

    public void setIdleaseplace(Leaseplace idleaseplace) {
        this.idleaseplace = idleaseplace;
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
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.idnotification == null && other.idnotification != null) || (this.idnotification != null && !this.idnotification.equals(other.idnotification))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Notification[ idnotification=" + idnotification + " ]";
    }

}

