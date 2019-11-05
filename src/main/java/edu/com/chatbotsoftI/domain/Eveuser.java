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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "eveuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eveuser.findAll", query = "SELECT e FROM Eveuser e")
    , @NamedQuery(name = "Eveuser.findByIduser", query = "SELECT e FROM Eveuser e WHERE e.iduser = :iduser")
    , @NamedQuery(name = "Eveuser.findByBirthday", query = "SELECT e FROM Eveuser e WHERE e.birthday = :birthday")
    , @NamedQuery(name = "Eveuser.findByEmail", query = "SELECT e FROM Eveuser e WHERE e.email = :email")
    , @NamedQuery(name = "Eveuser.findByNameuser", query = "SELECT e FROM Eveuser e WHERE e.nameuser = :nameuser")
    , @NamedQuery(name = "Eveuser.findByPassword", query = "SELECT e FROM Eveuser e WHERE e.password = :password")
    , @NamedQuery(name = "Eveuser.findByStatus", query = "SELECT e FROM Eveuser e WHERE e.status = :status")
    , @NamedQuery(name = "Eveuser.findByTxuser", query = "SELECT e FROM Eveuser e WHERE e.txuser = :txuser")
    , @NamedQuery(name = "Eveuser.findByTxhost", query = "SELECT e FROM Eveuser e WHERE e.txhost = :txhost")
    , @NamedQuery(name = "Eveuser.findByTxdate", query = "SELECT e FROM Eveuser e WHERE e.txdate = :txdate")})
public class Eveuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iduser")
    private Integer iduser;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nameuser")
    private String nameuser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private Integer status;
    @Size(max = 45)
    @Column(name = "txuser")
    private String txuser;
    @Size(max = 45)
    @Column(name = "txhost")
    private String txhost;
    @Column(name = "txdate")
    @Temporal(TemporalType.DATE)
    private Date txdate;
    @JoinColumn(name = "idperson", referencedColumnName = "idperson")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Eveperson idperson;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser", fetch = FetchType.LAZY)
    private List<Evebuyticket> evebuyticketList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser", fetch = FetchType.LAZY)
    private List<Evenotificationuser> evenotificationuserList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser", fetch = FetchType.LAZY)
    private List<Evebooking> evebookingList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser", fetch = FetchType.LAZY)
    private List<Eveevent> eveeventList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser", fetch = FetchType.LAZY)
    private List<Eveleaseplace> eveleaseplaceList;

    public Eveuser() {
    }

    public Eveuser(Integer iduser) {
        this.iduser = iduser;
    }

    public Eveuser(Integer iduser, String nameuser, String password) {
        this.iduser = iduser;
        this.nameuser = nameuser;
        this.password = password;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameuser() {
        return nameuser;
    }

    public void setNameuser(String nameuser) {
        this.nameuser = nameuser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Eveperson getIdperson() {
        return idperson;
    }

    public void setIdperson(Eveperson idperson) {
        this.idperson = idperson;
    }

    @XmlTransient
    public List<Evebuyticket> getEvebuyticketList() {
        return evebuyticketList;
    }

    public void setEvebuyticketList(List<Evebuyticket> evebuyticketList) {
        this.evebuyticketList = evebuyticketList;
    }

    @XmlTransient
    public List<Evenotificationuser> getEvenotificationuserList() {
        return evenotificationuserList;
    }

    public void setEvenotificationuserList(List<Evenotificationuser> evenotificationuserList) {
        this.evenotificationuserList = evenotificationuserList;
    }

    @XmlTransient
    public List<Evebooking> getEvebookingList() {
        return evebookingList;
    }

    public void setEvebookingList(List<Evebooking> evebookingList) {
        this.evebookingList = evebookingList;
    }

    @XmlTransient
    public List<Eveevent> getEveeventList() {
        return eveeventList;
    }

    public void setEveeventList(List<Eveevent> eveeventList) {
        this.eveeventList = eveeventList;
    }

    @XmlTransient
    public List<Eveleaseplace> getEveleaseplaceList() {
        return eveleaseplaceList;
    }

    public void setEveleaseplaceList(List<Eveleaseplace> eveleaseplaceList) {
        this.eveleaseplaceList = eveleaseplaceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iduser != null ? iduser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eveuser)) {
            return false;
        }
        Eveuser other = (Eveuser) object;
        if ((this.iduser == null && other.iduser != null) || (this.iduser != null && !this.iduser.equals(other.iduser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.com.chatbotsoftI.domain.Eveuser[ iduser=" + iduser + " ]";
    }
    
}
