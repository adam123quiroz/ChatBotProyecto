package edu.com.chatbotsoftI.entity;

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
