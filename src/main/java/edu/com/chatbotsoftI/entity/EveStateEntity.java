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
@Table(name = "evestate")
@XmlRootElement

public class EveStateEntity implements Serializable  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idstate")
    private Integer idstate;
    @Size(max = 45)
    @Column(name = "state")
    private String state;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idstate", fetch = FetchType.LAZY)
    private List<EveCityEntity> evecityList;

    public EveStateEntity() {
    }

    public EveStateEntity(Integer idstate) {
        this.idstate = idstate;
    }

    public Integer getIdstate() {
        return idstate;
    }

    public void setIdstate(Integer idstate) {
        this.idstate = idstate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @XmlTransient
    public List<EveCityEntity> getEvecityList() {
        return evecityList;
    }

    public void setEvecityList(List<EveCityEntity> evecityList) {
        this.evecityList = evecityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstate != null ? idstate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EveStateEntity)) {
            return false;
        }
        EveStateEntity other = (EveStateEntity) object;
        if ((this.idstate == null && other.idstate != null) || (this.idstate != null && !this.idstate.equals(other.idstate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EveStateEntity[ idstate=" + idstate + " ]";
    }

}
