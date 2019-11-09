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
@Table(name = "eveeventfile")
@XmlRootElement

public class EveEventFileEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ideveventfile")
    private Integer ideveventfile;
    @Size(max = 45)
    @Column(name = "txuser")
    private String txuser;
    @Size(max = 100)
    @Column(name = "txhost")
    private String txhost;
    @Column(name = "txdate")
    @Temporal(TemporalType.DATE)
    private Date txdate;
    @JoinColumn(name = "idevefile", referencedColumnName = "idfile")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveFileEntity idevefile;
    @JoinColumn(name = "idevent", referencedColumnName = "idevent")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveEventEntity idevent;

    public EveEventFileEntity() {
    }

    public EveEventFileEntity(Integer ideveventfile) {
        this.ideveventfile = ideveventfile;
    }

    public Integer getIdeveventfile() {
        return ideveventfile;
    }

    public void setIdeveventfile(Integer ideveventfile) {
        this.ideveventfile = ideveventfile;
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

    public EveFileEntity getIdevefile() {
        return idevefile;
    }

    public void setIdevefile(EveFileEntity idevefile) {
        this.idevefile = idevefile;
    }

    public EveEventEntity getIdevent() {
        return idevent;
    }

    public void setIdevent(EveEventEntity idevent) {
        this.idevent = idevent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideveventfile != null ? ideveventfile.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EveEventFileEntity)) {
            return false;
        }
        EveEventFileEntity other = (EveEventFileEntity) object;
        if ((this.ideveventfile == null && other.ideveventfile != null) || (this.ideveventfile != null && !this.ideveventfile.equals(other.ideveventfile))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EveEventFileEntity[ ideveventfile=" + ideveventfile + " ]";
    }
}
*/
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
