package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "eve_person", schema = "dbbot", catalog = "")
public class EvePersonEntity {
    private int idPerson;
    private String ci;
    private String name;
    private String lastName;
    private String botUserId;
    private List<EveChatEntity> eveChatsByIdPerson;
    private List<EvePaymentEntity> evePaymentsByIdPerson;
    private List<EveUserEntity> eveUsersByIdPerson;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person", nullable = false)
    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    @Basic
    @Column(name = "ci", nullable = true, length = 45)
    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 45)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "bot_user_id", nullable = true, length = 100)
    public String getBotUserId() {
        return botUserId;
    }

    public void setBotUserId(String botUserId) {
        this.botUserId = botUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EvePersonEntity that = (EvePersonEntity) o;

        if (idPerson != that.idPerson) return false;
        if (ci != null ? !ci.equals(that.ci) : that.ci != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (botUserId != null ? !botUserId.equals(that.botUserId) : that.botUserId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPerson;
        result = 31 * result + (ci != null ? ci.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (botUserId != null ? botUserId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "evePersonByIdPerson")
    public List<EveChatEntity> getEveChatsByIdPerson() {
        return eveChatsByIdPerson;
    }

    public void setEveChatsByIdPerson(List<EveChatEntity> eveChatsByIdPerson) {
        this.eveChatsByIdPerson = eveChatsByIdPerson;
    }

    @OneToMany(mappedBy = "evePersonByIdPerson")
    public List<EvePaymentEntity> getEvePaymentsByIdPerson() {
        return evePaymentsByIdPerson;
    }

    public void setEvePaymentsByIdPerson(List<EvePaymentEntity> evePaymentsByIdPerson) {
        this.evePaymentsByIdPerson = evePaymentsByIdPerson;
    }

    @OneToMany(mappedBy = "evePersonByIdPerson")
    public List<EveUserEntity> getEveUsersByIdPerson() {
        return eveUsersByIdPerson;
    }

    public void setEveUsersByIdPerson(List<EveUserEntity> eveUsersByIdPerson) {
        this.eveUsersByIdPerson = eveUsersByIdPerson;
    }
}
