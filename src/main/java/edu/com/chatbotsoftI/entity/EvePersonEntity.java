package edu.com.chatbotsoftI.entity;
/*
import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "eveperson")
@XmlRootElement

public class EvePersonEntity implements Serializable  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idperson")
    private Integer idperson;
    @Size(max = 45)
    @Column(name = "ci")
    private String ci;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 100)
    @Column(name = "bot_user_id")
    private String botUserId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idperson", fetch = FetchType.LAZY)
    private List<EveUserEntity> eveuserList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idperson", fetch = FetchType.LAZY)
    private List<EvePersonChatEntity> evepersonchatList;

    public EvePersonEntity() {
    }

    public EvePersonEntity(Integer idperson) {
        this.idperson = idperson;
    }

    public Integer getIdperson() {
        return idperson;
    }

    public void setIdperson(Integer idperson) {
        this.idperson = idperson;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBotUserId() {
        return botUserId;
    }

    public void setBotUserId(String botUserId) {
        this.botUserId = botUserId;
    }

    @XmlTransient
    public List<EveUserEntity> getEveuserList() {
        return eveuserList;
    }

    public void setEveuserList(List<EveUserEntity> eveuserList) {
        this.eveuserList = eveuserList;
    }

    @XmlTransient
    public List<EvePersonChatEntity> getEvepersonchatList() {
        return evepersonchatList;
    }

    public void setEvepersonchatList(List<EvePersonChatEntity> evepersonchatList) {
        this.evepersonchatList = evepersonchatList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idperson != null ? idperson.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvePersonEntity)) {
            return false;
        }
        EvePersonEntity other = (EvePersonEntity) object;
        if ((this.idperson == null && other.idperson != null) || (this.idperson != null && !this.idperson.equals(other.idperson))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EvePersonEntity[ idperson=" + idperson + " ]";
    }
}
 */
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "eveperson", schema = "dbbot", catalog = "")
public class EvePersonEntity {
    private Integer idperson;
    private String ci;
    private String name;
    private String lastname;
    private String botUserId;
    private List<EvePersonChatEntity> evepersonchatsByIdperson;
    private List<EveUserEntity> eveusersByIdperson;

    @Id
    @Column(name = "idperson", nullable = false)
    public Integer getIdperson() {
        return idperson;
    }

    public void setIdperson(Integer idperson) {
        this.idperson = idperson;
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
    @Column(name = "lastname", nullable = true, length = 45)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

        if (idperson != null ? !idperson.equals(that.idperson) : that.idperson != null) return false;
        if (ci != null ? !ci.equals(that.ci) : that.ci != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (botUserId != null ? !botUserId.equals(that.botUserId) : that.botUserId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idperson != null ? idperson.hashCode() : 0;
        result = 31 * result + (ci != null ? ci.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (botUserId != null ? botUserId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "evepersonByIdperson")
    public List<EvePersonChatEntity> getEvepersonchatsByIdperson() {
        return evepersonchatsByIdperson;
    }

    public void setEvepersonchatsByIdperson(List<EvePersonChatEntity> evepersonchatsByIdperson) {
        this.evepersonchatsByIdperson = evepersonchatsByIdperson;
    }

    @OneToMany(mappedBy = "evepersonByIdperson")
    public List<EveUserEntity> getEveusersByIdperson() {
        return eveusersByIdperson;
    }

    public void setEveusersByIdperson(List<EveUserEntity> eveusersByIdperson) {
        this.eveusersByIdperson = eveusersByIdperson;
    }
}
