package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "eveeventfile", schema = "dbbot", catalog = "")
public class EveEventFileEntity {
    private Integer ideveventfile;
    private String txuser;
    private String txhost;
    private Date txdate;
    private EveEventEntity eveeventByIdevent;
    private EveFileEntity evefileByIdevefile;

    @Id
    @Column(name = "ideveventfile", nullable = false)
    public Integer getIdeveventfile() {
        return ideveventfile;
    }

    public void setIdeveventfile(Integer ideveventfile) {
        this.ideveventfile = ideveventfile;
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

        EveEventFileEntity that = (EveEventFileEntity) o;

        if (ideveventfile != null ? !ideveventfile.equals(that.ideveventfile) : that.ideveventfile != null)
            return false;
        if (txuser != null ? !txuser.equals(that.txuser) : that.txuser != null) return false;
        if (txhost != null ? !txhost.equals(that.txhost) : that.txhost != null) return false;
        if (txdate != null ? !txdate.equals(that.txdate) : that.txdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ideveventfile != null ? ideveventfile.hashCode() : 0;
        result = 31 * result + (txuser != null ? txuser.hashCode() : 0);
        result = 31 * result + (txhost != null ? txhost.hashCode() : 0);
        result = 31 * result + (txdate != null ? txdate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idevent", referencedColumnName = "idevent", nullable = false)
    public EveEventEntity getEveeventByIdevent() {
        return eveeventByIdevent;
    }

    public void setEveeventByIdevent(EveEventEntity eveeventByIdevent) {
        this.eveeventByIdevent = eveeventByIdevent;
    }

    @ManyToOne
    @JoinColumn(name = "idevefile", referencedColumnName = "idfile", nullable = false)
    public EveFileEntity getEvefileByIdevefile() {
        return evefileByIdevefile;
    }

    public void setEvefileByIdevefile(EveFileEntity evefileByIdevefile) {
        this.evefileByIdevefile = evefileByIdevefile;
    }
}
