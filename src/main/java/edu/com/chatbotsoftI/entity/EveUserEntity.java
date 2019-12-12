package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "eve_user", schema = "dbbot", catalog = "")
public class EveUserEntity {
    private int idUser;
    private Date birthday;
    private String email;
    private String nameUser;
    private String password;
    private Integer status;
    private String txUser;
    private String txHost;
    private Date txDate;
    private List<EveBuyTicketEntity> eveBuyTicketsByIdUser;
    private List<EveEventEntity> eveEventsByIdUser;
    private List<EveLeasePlaceEntity> eveLeasePlacesByIdUser;
    private List<EveNotificationUserEntity> eveNotificationUsersByIdUser;
    private List<EvePaymentEntity> evePaymentsByIdUser;
    private EvePersonEntity evePersonByIdPerson;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
    @Column(name = "name_user", nullable = false, length = 45)
    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
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
    @Column(name = "tx_user", nullable = true, length = 45)
    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    @Basic
    @Column(name = "tx_host", nullable = true, length = 45)
    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    @Basic
    @Column(name = "tx_date", nullable = true)
    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EveUserEntity that = (EveUserEntity) o;

        if (idUser != that.idUser) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (nameUser != null ? !nameUser.equals(that.nameUser) : that.nameUser != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txHost != null ? !txHost.equals(that.txHost) : that.txHost != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (nameUser != null ? nameUser.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txHost != null ? txHost.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "eveUserByIdUser")
    public List<EveBuyTicketEntity> getEveBuyTicketsByIdUser() {
        return eveBuyTicketsByIdUser;
    }

    public void setEveBuyTicketsByIdUser(List<EveBuyTicketEntity> eveBuyTicketsByIdUser) {
        this.eveBuyTicketsByIdUser = eveBuyTicketsByIdUser;
    }

    @OneToMany(mappedBy = "eveUserByIdUser")
    public List<EveEventEntity> getEveEventsByIdUser() {
        return eveEventsByIdUser;
    }

    public void setEveEventsByIdUser(List<EveEventEntity> eveEventsByIdUser) {
        this.eveEventsByIdUser = eveEventsByIdUser;
    }

    @OneToMany(mappedBy = "eveUserByIdUser")
    public List<EveLeasePlaceEntity> getEveLeasePlacesByIdUser() {
        return eveLeasePlacesByIdUser;
    }

    public void setEveLeasePlacesByIdUser(List<EveLeasePlaceEntity> eveLeasePlacesByIdUser) {
        this.eveLeasePlacesByIdUser = eveLeasePlacesByIdUser;
    }

    @OneToMany(mappedBy = "eveUserByIdUser")
    public List<EveNotificationUserEntity> getEveNotificationUsersByIdUser() {
        return eveNotificationUsersByIdUser;
    }

    public void setEveNotificationUsersByIdUser(List<EveNotificationUserEntity> eveNotificationUsersByIdUser) {
        this.eveNotificationUsersByIdUser = eveNotificationUsersByIdUser;
    }

    @OneToMany(mappedBy = "eveUserByIdUser")
    public List<EvePaymentEntity> getEvePaymentsByIdUser() {
        return evePaymentsByIdUser;
    }

    public void setEvePaymentsByIdUser(List<EvePaymentEntity> evePaymentsByIdUser) {
        this.evePaymentsByIdUser = evePaymentsByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id_person", nullable = false)
    public EvePersonEntity getEvePersonByIdPerson() {
        return evePersonByIdPerson;
    }

    public void setEvePersonByIdPerson(EvePersonEntity evePersonByIdPerson) {
        this.evePersonByIdPerson = evePersonByIdPerson;
    }
}
