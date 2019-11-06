/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.chatbotsoftI.domain;

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

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "eveeventfile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eveeventfile.findAll", query = "SELECT e FROM Eveeventfile e")
    , @NamedQuery(name = "Eveeventfile.findByIdeveventfile", query = "SELECT e FROM Eveeventfile e WHERE e.ideveventfile = :ideveventfile")
    , @NamedQuery(name = "Eveeventfile.findByTxuser", query = "SELECT e FROM Eveeventfile e WHERE e.txuser = :txuser")
    , @NamedQuery(name = "Eveeventfile.findByTxhost", query = "SELECT e FROM Eveeventfile e WHERE e.txhost = :txhost")
    , @NamedQuery(name = "Eveeventfile.findByTxdate", query = "SELECT e FROM Eveeventfile e WHERE e.txdate = :txdate")})
public class Eveeventfile implements Serializable {

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
    private Evefile idevefile;
    @JoinColumn(name = "idevent", referencedColumnName = "idevent")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Eveevent idevent;

    public Eveeventfile() {
    }

    public Eveeventfile(Integer ideveventfile) {
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

    public Evefile getIdevefile() {
        return idevefile;
    }

    public void setIdevefile(Evefile idevefile) {
        this.idevefile = idevefile;
    }

    public Eveevent getIdevent() {
        return idevent;
    }

    public void setIdevent(Eveevent idevent) {
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
        if (!(object instanceof Eveeventfile)) {
            return false;
        }
        Eveeventfile other = (Eveeventfile) object;
        if ((this.ideveventfile == null && other.ideveventfile != null) || (this.ideveventfile != null && !this.ideveventfile.equals(other.ideveventfile))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.com.chatbotsoftI.domain.Eveeventfile[ ideveventfile=" + ideveventfile + " ]";
    }
    
}
