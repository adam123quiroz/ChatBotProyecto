package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "evefile", schema = "dbbot", catalog = "")
public class EveFileEntity {
    private Integer idfile;
    private String filename;
    private String mimetype;
    private String path;
    private String storetype;
    private String txuser;
    private String txhost;
    private Date txdate;
    private List<EveEventFileEntity> eveeventfilesByIdfile;

    @Id
    @Column(name = "idfile", nullable = false)
    public Integer getIdfile() {
        return idfile;
    }

    public void setIdfile(Integer idfile) {
        this.idfile = idfile;
    }

    @Basic
    @Column(name = "filename", nullable = true, length = 250)
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Basic
    @Column(name = "mimetype", nullable = true, length = 200)
    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    @Basic
    @Column(name = "path", nullable = true, length = 100)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "storetype", nullable = true, length = 45)
    public String getStoretype() {
        return storetype;
    }

    public void setStoretype(String storetype) {
        this.storetype = storetype;
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

        EveFileEntity that = (EveFileEntity) o;

        if (idfile != null ? !idfile.equals(that.idfile) : that.idfile != null) return false;
        if (filename != null ? !filename.equals(that.filename) : that.filename != null) return false;
        if (mimetype != null ? !mimetype.equals(that.mimetype) : that.mimetype != null) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;
        if (storetype != null ? !storetype.equals(that.storetype) : that.storetype != null) return false;
        if (txuser != null ? !txuser.equals(that.txuser) : that.txuser != null) return false;
        if (txhost != null ? !txhost.equals(that.txhost) : that.txhost != null) return false;
        if (txdate != null ? !txdate.equals(that.txdate) : that.txdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idfile != null ? idfile.hashCode() : 0;
        result = 31 * result + (filename != null ? filename.hashCode() : 0);
        result = 31 * result + (mimetype != null ? mimetype.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (storetype != null ? storetype.hashCode() : 0);
        result = 31 * result + (txuser != null ? txuser.hashCode() : 0);
        result = 31 * result + (txhost != null ? txhost.hashCode() : 0);
        result = 31 * result + (txdate != null ? txdate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "evefileByIdevefile")
    public List<EveEventFileEntity> getEveeventfilesByIdfile() {
        return eveeventfilesByIdfile;
    }

    public void setEveeventfilesByIdfile(List<EveEventFileEntity> eveeventfilesByIdfile) {
        this.eveeventfilesByIdfile = eveeventfilesByIdfile;
    }
}
