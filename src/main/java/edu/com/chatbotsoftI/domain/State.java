package edu.com.chatbotsoftI.domain;

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

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "state")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "State.findAll", query = "SELECT s FROM State s")
        , @NamedQuery(name = "State.findByIdstate", query = "SELECT s FROM State s WHERE s.idstate = :idstate")
        , @NamedQuery(name = "State.findByState", query = "SELECT s FROM State s WHERE s.state = :state")})
public class State implements Serializable {

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
    private List<City> cityList;

    public State() {
    }

    public State(Integer idstate) {
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
    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
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
        if (!(object instanceof State)) {
            return false;
        }
        State other = (State) object;
        if ((this.idstate == null && other.idstate != null) || (this.idstate != null && !this.idstate.equals(other.idstate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.State[ idstate=" + idstate + " ]";
    }

}

