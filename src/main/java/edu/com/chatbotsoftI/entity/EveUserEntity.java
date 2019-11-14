package edu.com.chatbotsoftI.entity;
/*
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

@Entity
@Table(name = "eveuser")
@XmlRootElement

public class EveUserEntity implements Serializable {
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
    private EvePersonEntity idperson;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser", fetch = FetchType.LAZY)
    private List<EveBuyTicketEntity> evebuyticketList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser", fetch = FetchType.LAZY)
    private List<EveNotificationUserEntity> evenotificationuserList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser", fetch = FetchType.LAZY)
    private List<EveBookingEntity> evebookingList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser", fetch = FetchType.LAZY)
    private List<EveEventEntity> eveeventList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser", fetch = FetchType.LAZY)
    private List<EveLeasePlaceEntity> eveleaseplaceList;

    public EveUserEntity() {
    }

    public EveUserEntity(Integer iduser) {
        this.iduser = iduser;
    }

    public EveUserEntity(Integer iduser, String nameuser, String password) {
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

    public EvePersonEntity getIdperson() {
        return idperson;
    }

    public void setIdperson(EvePersonEntity idperson) {
        this.idperson = idperson;
    }

    @XmlTransient
    public List<EveBuyTicketEntity> getEvebuyticketList() {
        return evebuyticketList;
    }

    public void setEvebuyticketList(List<EveBuyTicketEntity> evebuyticketList) {
        this.evebuyticketList = evebuyticketList;
    }

    @XmlTransient
    public List<EveNotificationUserEntity> getEvenotificationuserList() {
        return evenotificationuserList;
    }

    public void setEvenotificationuserList(List<EveNotificationUserEntity> evenotificationuserList) {
        this.evenotificationuserList = evenotificationuserList;
    }

    @XmlTransient
    public List<EveBookingEntity> getEvebookingList() {
        return evebookingList;
    }

    public void setEvebookingList(List<EveBookingEntity> evebookingList) {
        this.evebookingList = evebookingList;
    }

    @XmlTransient
    public List<EveEventEntity> getEveeventList() {
        return eveeventList;
    }

    public void setEveeventList(List<EveEventEntity> eveeventList) {
        this.eveeventList = eveeventList;
    }

    @XmlTransient
    public List<EveLeasePlaceEntity> getEveleaseplaceList() {
        return eveleaseplaceList;
    }

    public void setEveleaseplaceList(List<EveLeasePlaceEntity> eveleaseplaceList) {
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
        if (!(object instanceof EveUserEntity)) {
            return false;
        }
        EveUserEntity other = (EveUserEntity) object;
        if ((this.iduser == null && other.iduser != null) || (this.iduser != null && !this.iduser.equals(other.iduser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EveUserEntity[ iduser=" + iduser + " ]";
    }

}
*/
import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "eveuser", schema = "dbbot", catalog = "")
public class EveUserEntity {
    private Integer iduser;
    private Date birthday;
    private String email;
    private String nameuser;
    private String password;
    private Integer status;
    private String txuser;
    private String txhost;
    private Date txdate;
    private List<EveBookingEntity> evebookingsByIduser;
    private List<EveBuyTicketEntity> evebuyticketsByIduser;
    private List<EveEventEntity> eveeventsByIduser;
    private List<EveLeasePlaceEntity> eveleaseplacesByIduser;
    private List<EveNotificationUserEntity> evenotificationusersByIduser;
    private EvePersonEntity evepersonByIdperson;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser", nullable = false)
    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    @Basic
    @Column(name = "birthday", nullable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "nameuser", nullable = false, length = 45)
    public String getNameuser() {
        return nameuser;
    }

    public void setNameuser(String nameuser) {
        this.nameuser = nameuser;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
    @Column(name = "txhost", nullable = true, length = 45)
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

        EveUserEntity that = (EveUserEntity) o;

        if (iduser != null ? !iduser.equals(that.iduser) : that.iduser != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (nameuser != null ? !nameuser.equals(that.nameuser) : that.nameuser != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (txuser != null ? !txuser.equals(that.txuser) : that.txuser != null) return false;
        if (txhost != null ? !txhost.equals(that.txhost) : that.txhost != null) return false;
        if (txdate != null ? !txdate.equals(that.txdate) : that.txdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iduser != null ? iduser.hashCode() : 0;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (nameuser != null ? nameuser.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (txuser != null ? txuser.hashCode() : 0);
        result = 31 * result + (txhost != null ? txhost.hashCode() : 0);
        result = 31 * result + (txdate != null ? txdate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "eveuserByIduser")
    public List<EveBookingEntity> getEvebookingsByIduser() {
        return evebookingsByIduser;
    }

    public void setEvebookingsByIduser(List<EveBookingEntity> evebookingsByIduser) {
        this.evebookingsByIduser = evebookingsByIduser;
    }

    @OneToMany(mappedBy = "eveuserByIduser")
    public List<EveBuyTicketEntity> getEvebuyticketsByIduser() {
        return evebuyticketsByIduser;
    }

    public void setEvebuyticketsByIduser(List<EveBuyTicketEntity> evebuyticketsByIduser) {
        this.evebuyticketsByIduser = evebuyticketsByIduser;
    }

    @OneToMany(mappedBy = "eveuserByIduser")
    public List<EveEventEntity> getEveeventsByIduser() {
        return eveeventsByIduser;
    }

    public void setEveeventsByIduser(List<EveEventEntity> eveeventsByIduser) {
        this.eveeventsByIduser = eveeventsByIduser;
    }

    @OneToMany(mappedBy = "eveuserByIduser")
    public List<EveLeasePlaceEntity> getEveleaseplacesByIduser() {
        return eveleaseplacesByIduser;
    }

    public void setEveleaseplacesByIduser(List<EveLeasePlaceEntity> eveleaseplacesByIduser) {
        this.eveleaseplacesByIduser = eveleaseplacesByIduser;
    }

    @OneToMany(mappedBy = "eveuserByIduser")
    public List<EveNotificationUserEntity> getEvenotificationusersByIduser() {
        return evenotificationusersByIduser;
    }

    public void setEvenotificationusersByIduser(List<EveNotificationUserEntity> evenotificationusersByIduser) {
        this.evenotificationusersByIduser = evenotificationusersByIduser;
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
