package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "eve_category", schema = "dbbot", catalog = "")
public class EveCategoryEntity {
    private int idCategory;
    private String category;
    private List<EveEventEntity> eveEventsByIdCategory;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category", nullable = false)
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
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

        if (idCategory != that.idCategory) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCategory;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "eveCategoryByIdCategory")
    public List<EveEventEntity> getEveEventsByIdCategory() {
        return eveEventsByIdCategory;
    }

    public void setEveEventsByIdCategory(List<EveEventEntity> eveEventsByIdCategory) {
        this.eveEventsByIdCategory = eveEventsByIdCategory;
    }
}
