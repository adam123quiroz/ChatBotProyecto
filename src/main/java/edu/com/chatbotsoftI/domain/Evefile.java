/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.chatbotsoftI.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "evefile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evefile.findAll", query = "SELECT e FROM Evefile e")
    , @NamedQuery(name = "Evefile.findByIdfile", query = "SELECT e FROM Evefile e WHERE e.idfile = :idfile")
    , @NamedQuery(name = "Evefile.findByFilename", query = "SELECT e FROM Evefile e WHERE e.filename = :filename")
    , @NamedQuery(name = "Evefile.findByMimetype", query = "SELECT e FROM Evefile e WHERE e.mimetype = :mimetype")
    , @NamedQuery(name = "Evefile.findByPath", query = "SELECT e FROM Evefile e WHERE e.path = :path")
    , @NamedQuery(name = "Evefile.findByStoretype", query = "SELECT e FROM Evefile e WHERE e.storetype = :storetype")
    , @NamedQuery(name = "Evefile.findByTxuser", query = "SELECT e FROM Evefile e WHERE e.txuser = :txuser")
    , @NamedQuery(name = "Evefile.findByTxhost", query = "SELECT e FROM Evefile e WHERE e.txhost = :txhost")
    , @NamedQuery(name = "Evefile.findByTxdate", query = "SELECT e FROM Evefile e WHERE e.txdate = :txdate")})
public class Evefile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfile")
    private Integer idfile;
    @Size(max = 250)
    @Column(name = "filename")
    private String filename;
    @Size(max = 200)
    @Column(name = "mimetype")
    private String mimetype;
    @Size(max = 100)
    @Column(name = "path")
    private String path;
    @Size(max = 45)
    @Column(name = "storetype")
    private String storetype;
    @Size(max = 45)
    @Column(name = "txuser")
    private String txuser;
    @Size(max = 100)
    @Column(name = "txhost")
    private String txhost;
    @Column(name = "txdate")
    @Temporal(TemporalType.DATE)
    private Date txdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idevefile", fetch = FetchType.LAZY)
    private List<Eveeventfile> eveeventfileList;

    public Evefile() {
    }

    public Evefile(Integer idfile) {
        this.idfile = idfile;
    }

    public Integer getIdfile() {
        return idfile;
    }

    public void setIdfile(Integer idfile) {
        this.idfile = idfile;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStoretype() {
        return storetype;
    }

    public void setStoretype(String storetype) {
        this.storetype = storetype;
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

    @XmlTransient
    public List<Eveeventfile> getEveeventfileList() {
        return eveeventfileList;
    }

    public void setEveeventfileList(List<Eveeventfile> eveeventfileList) {
        this.eveeventfileList = eveeventfileList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfile != null ? idfile.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evefile)) {
            return false;
        }
        Evefile other = (Evefile) object;
        if ((this.idfile == null && other.idfile != null) || (this.idfile != null && !this.idfile.equals(other.idfile))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.com.chatbotsoftI.domain.Evefile[ idfile=" + idfile + " ]";
    }
    
}
