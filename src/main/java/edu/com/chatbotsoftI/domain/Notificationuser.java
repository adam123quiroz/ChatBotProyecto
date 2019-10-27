package edu.com.chatbotsoftI.domain;

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

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "notificationuser")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Notificationuser.findAll", query = "SELECT n FROM Notificationuser n")
        , @NamedQuery(name = "Notificationuser.findByIdnotificationuser", query = "SELECT n FROM Notificationuser n WHERE n.idnotificationuser = :idnotificationuser")})
public class Notificationuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnotificationuser")
    private Integer idnotificationuser;
    @JoinColumn(name = "idnotification", referencedColumnName = "idnotification")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Notification idnotification;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User iduser;

    public Notificationuser() {
    }

    public Notificationuser(Integer idnotificationuser) {
        this.idnotificationuser = idnotificationuser;
    }

    public Integer getIdnotificationuser() {
        return idnotificationuser;
    }

    public void setIdnotificationuser(Integer idnotificationuser) {
        this.idnotificationuser = idnotificationuser;
    }

    public Notification getIdnotification() {
        return idnotification;
    }

    public void setIdnotification(Notification idnotification) {
        this.idnotification = idnotification;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
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
        if (!(object instanceof Notificationuser)) {
            return false;
        }
        Notificationuser other = (Notificationuser) object;
        if ((this.idnotificationuser == null && other.idnotificationuser != null) || (this.idnotificationuser != null && !this.idnotificationuser.equals(other.idnotificationuser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Notificationuser[ idnotificationuser=" + idnotificationuser + " ]";
    }

}

