/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.chatbotsoftI.domain;

import javax.persistence.*;
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
@Table(name = "evetypeevent")
@XmlRootElement
public class EveTypeEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtypeevent")
    private Integer idtypeevent;
    @Size(max = 45)
    @Column(name = "typeevent")
    private String typeevent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtypeevent", fetch = FetchType.LAZY)
    private List<EveEvent> eveeventList;

    public EveTypeEvent() {
    }

    public EveTypeEvent(Integer idtypeevent) {
        this.idtypeevent = idtypeevent;
    }

    public Integer getIdtypeevent() {
        return idtypeevent;
    }

    public void setIdtypeevent(Integer idtypeevent) {
        this.idtypeevent = idtypeevent;
    }

    public String getTypeevent() {
        return typeevent;
    }

    public void setTypeevent(String typeevent) {
        this.typeevent = typeevent;
    }

    @XmlTransient
    public List<EveEvent> getEveeventList() {
        return eveeventList;
    }

    public void setEveeventList(List<EveEvent> eveeventList) {
        this.eveeventList = eveeventList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtypeevent != null ? idtypeevent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EveTypeEvent)) {
            return false;
        }
        EveTypeEvent other = (EveTypeEvent) object;
        if ((this.idtypeevent == null && other.idtypeevent != null) || (this.idtypeevent != null && !this.idtypeevent.equals(other.idtypeevent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.com.chatbotsoftI.domain.Evetypeevent[ idtypeevent=" + idtypeevent + " ]";
    }
    
}
