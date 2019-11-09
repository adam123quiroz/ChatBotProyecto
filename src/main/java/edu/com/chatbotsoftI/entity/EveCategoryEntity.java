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
@Table(name = "evecategory")
@XmlRootElement


public class EveCategoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcategory")
    private Integer idcategory;
    @Size(max = 45)
    @Column(name = "category")
    private String category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcategory", fetch = FetchType.LAZY)
    private List<EveEventEntity> eveeventList;

    public EveCategoryEntity() {
    }

    public EveCategoryEntity(Integer idcategory) {
        this.idcategory = idcategory;
    }

    public Integer getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(Integer idcategory) {
        this.idcategory = idcategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
        hash += (idcategory != null ? idcategory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EveCategoryEntity)) {
            return false;
        }
        EveCategoryEntity other = (EveCategoryEntity) object;
        if ((this.idcategory == null && other.idcategory != null) || (this.idcategory != null && !this.idcategory.equals(other.idcategory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EveCategoryEntity[ idcategory=" + idcategory + " ]";
    }

}
*/
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "evecategory", schema = "dbbot", catalog = "")
public class EveCategoryEntity {
    private Integer idcategory;
    private String category;
    private List<EveEventEntity> eveeventsByIdcategory;

    @Id
    @Column(name = "idcategory", nullable = false)
    public Integer getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(Integer idcategory) {
        this.idcategory = idcategory;
    }

    @Basic
    @Column(name = "category", nullable = true, length = 45)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EveCategoryEntity that = (EveCategoryEntity) o;

        if (idcategory != null ? !idcategory.equals(that.idcategory) : that.idcategory != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcategory != null ? idcategory.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "evecategoryByIdcategory")
    public List<EveEventEntity> getEveeventsByIdcategory() {
        return eveeventsByIdcategory;
    }

    public void setEveeventsByIdcategory(List<EveEventEntity> eveeventsByIdcategory) {
        this.eveeventsByIdcategory = eveeventsByIdcategory;
    }
}
