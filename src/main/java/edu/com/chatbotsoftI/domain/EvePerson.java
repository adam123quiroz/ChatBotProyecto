/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.chatbotsoftI.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "eveperson")
@XmlRootElement
public class EvePerson implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "bot_user_id")
    private String botUserId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idperson", fetch = FetchType.LAZY)
    private List<EveUser> eveuserList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idperson", fetch = FetchType.LAZY)
    private List<EvePersonChat> evepersonchatList;

    public EvePerson() {
    }



    public EvePerson(Integer idperson) {
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

    @XmlTransient
    public List<EveUser> getEveuserList() {
        return eveuserList;
    }

    public String getBotUserId() {
        return botUserId;
    }

    public void setBotUserId(String botUserId) {
        this.botUserId = botUserId;
    }

    public void setEveuserList(List<EveUser> eveuserList) {
        this.eveuserList = eveuserList;
    }

    @XmlTransient
    public List<EvePersonChat> getEvepersonchatList() {
        return evepersonchatList;
    }

    public void setEvepersonchatList(List<EvePersonChat> evepersonchatList) {
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
        if (!(object instanceof EvePerson)) {
            return false;
        }
        EvePerson other = (EvePerson) object;
        if ((this.idperson == null && other.idperson != null) || (this.idperson != null && !this.idperson.equals(other.idperson))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.com.chatbotsoftI.domain.Eveperson[ idperson=" + idperson + " ]";
    }
    
}
