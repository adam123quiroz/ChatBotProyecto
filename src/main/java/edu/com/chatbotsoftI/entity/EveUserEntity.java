package edu.com.chatbotsoftI.entity;

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