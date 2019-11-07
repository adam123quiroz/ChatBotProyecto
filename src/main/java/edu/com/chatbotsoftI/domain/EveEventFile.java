/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.chatbotsoftI.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "eveeventfile")
@XmlRootElement
public class EveEventFile implements Serializable {

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
    private EveFile idevefile;
    @JoinColumn(name = "idevent", referencedColumnName = "idevent")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveEvent idevent;

    public EveEventFile() {
    }

    public EveEventFile(Integer ideveventfile) {
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

    public EveFile getIdevefile() {
        return idevefile;
    }

    public void setIdevefile(EveFile idevefile) {
        this.idevefile = idevefile;
    }

    public EveEvent getIdevent() {
        return idevent;
    }

    public void setIdevent(EveEvent idevent) {
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
        if (!(object instanceof EveEventFile)) {
            return false;
        }
        EveEventFile other = (EveEventFile) object;
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
