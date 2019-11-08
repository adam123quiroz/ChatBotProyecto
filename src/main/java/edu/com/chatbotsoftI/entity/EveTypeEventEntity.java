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
@Table(name = "evetypeevent")
@XmlRootElement

public class EveTypeEventEntity implements Serializable{
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
    private List<EveEventEntity> eveeventList;

    public EveTypeEventEntity() {
    }

    public EveTypeEventEntity(Integer idtypeevent) {
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
    public List<EveEventEntity> getEveeventList() {
        return eveeventList;
    }

    public void setEveeventList(List<EveEventEntity> eveeventList) {
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
        if (!(object instanceof EveTypeEventEntity)) {
            return false;
        }
        EveTypeEventEntity other = (EveTypeEventEntity) object;
        if ((this.idtypeevent == null && other.idtypeevent != null) || (this.idtypeevent != null && !this.idtypeevent.equals(other.idtypeevent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EveTypeEventEntity[ idtypeevent=" + idtypeevent + " ]";
    }

}
