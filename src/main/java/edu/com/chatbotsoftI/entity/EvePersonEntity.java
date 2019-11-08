package edu.com.chatbotsoftI.entity;

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
