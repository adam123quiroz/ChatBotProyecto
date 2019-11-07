package edu.com.chatbotsoftI.entity;

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
