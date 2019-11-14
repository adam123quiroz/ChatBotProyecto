package edu.com.chatbotsoftI.entity;
/*
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "evepersonchat")
@XmlRootElement

public class EvePersonChatEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idevuserchat")
    private Integer idevuserchat;
    @Size(max = 45)
    @Column(name = "userbotchatid")
    private String userbotchatid;
    @Size(max = 45)
    @Column(name = "txuser")
    private String txuser;
    @Size(max = 100)
    @Column(name = "txhost")
    private String txhost;
    @Column(name = "txdate")
    @Temporal(TemporalType.DATE)
    private Date txdate;
    @JoinColumn(name = "idperson", referencedColumnName = "idperson")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EvePersonEntity idperson;

    public EvePersonChatEntity() {
    }

    public EvePersonChatEntity(Integer idevuserchat) {
        this.idevuserchat = idevuserchat;
    }

    public Integer getIdevuserchat() {
        return idevuserchat;
    }

    public void setIdevuserchat(Integer idevuserchat) {
        this.idevuserchat = idevuserchat;
    }

    public String getUserbotchatid() {
        return userbotchatid;
    }

    public void setUserbotchatid(String userbotchatid) {
        this.userbotchatid = userbotchatid;
    }

    public String getTxuser() {
        return txuser;
    }

    public void setTxuser(String txuser) {
        this.txuser = txuser;
    }

    public String getTxhost() {
        return txhost;
    }

    public void setTxhost(String txhost) {
        this.txhost = txhost;
    }

    public Date getTxdate() {
        return txdate;
    }

    public void setTxdate(Date txdate) {
        this.txdate = txdate;
    }

    public EvePersonEntity getIdperson() {
        return idperson;
    }

    public void setIdperson(EvePersonEntity idperson) {
        this.idperson = idperson;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevuserchat != null ? idevuserchat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvePersonChatEntity)) {
            return false;
        }
        EvePersonChatEntity other = (EvePersonChatEntity) object;
        if ((this.idevuserchat == null && other.idevuserchat != null) || (this.idevuserchat != null && !this.idevuserchat.equals(other.idevuserchat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EvePersonChatEntity[ idevuserchat=" + idevuserchat + " ]";
    }

}
 */
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "evepersonchat", schema = "dbbot", catalog = "")
public class EvePersonChatEntity {
    private Integer idevuserchat;
    private String userbotchatid;
    private String txuser;
    private String txhost;
    private Date txdate;
    private EvePersonEntity evepersonByIdperson;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idevuserchat", nullable = false)
    public Integer getIdevuserchat() {
        return idevuserchat;
    }

    public void setIdevuserchat(Integer idevuserchat) {
        this.idevuserchat = idevuserchat;
    }

    @Basic
    @Column(name = "userbotchatid", nullable = true, length = 45)
    public String getUserbotchatid() {
        return userbotchatid;
    }

    public void setUserbotchatid(String userbotchatid) {
        this.userbotchatid = userbotchatid;
    }

    @Basic
    @Column(name = "txuser", nullable = true, length = 45)
    public String getTxuser() {
        return txuser;
    }

    public void setTxuser(String txuser) {
        this.txuser = txuser;
    }

    @Basic
    @Column(name = "txhost", nullable = true, length = 100)
    public String getTxhost() {
        return txhost;
    }

    public void setTxhost(String txhost) {
        this.txhost = txhost;
    }

    @Basic
    @Column(name = "txdate", nullable = true)
    public Date getTxdate() {
        return txdate;
    }

    public void setTxdate(Date txdate) {
        this.txdate = txdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EvePersonChatEntity that = (EvePersonChatEntity) o;

        if (idevuserchat != null ? !idevuserchat.equals(that.idevuserchat) : that.idevuserchat != null) return false;
        if (userbotchatid != null ? !userbotchatid.equals(that.userbotchatid) : that.userbotchatid != null)
            return false;
        if (txuser != null ? !txuser.equals(that.txuser) : that.txuser != null) return false;
        if (txhost != null ? !txhost.equals(that.txhost) : that.txhost != null) return false;
        if (txdate != null ? !txdate.equals(that.txdate) : that.txdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idevuserchat != null ? idevuserchat.hashCode() : 0;
        result = 31 * result + (userbotchatid != null ? userbotchatid.hashCode() : 0);
        result = 31 * result + (txuser != null ? txuser.hashCode() : 0);
        result = 31 * result + (txhost != null ? txhost.hashCode() : 0);
        result = 31 * result + (txdate != null ? txdate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idperson", referencedColumnName = "idperson", nullable = false)
    public EvePersonEntity getEvepersonByIdperson() {
        return evepersonByIdperson;
    }

    public void setEvepersonByIdperson(EvePersonEntity evepersonByIdperson) {
        this.evepersonByIdperson = evepersonByIdperson;
    }
}
