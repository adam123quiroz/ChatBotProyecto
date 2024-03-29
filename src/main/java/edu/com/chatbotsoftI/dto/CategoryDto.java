package edu.com.chatbotsoftI.dto;


import edu.com.chatbotsoftI.entity.EveCategoryEntity;

public class CategoryDto {

    private Integer idcategory;
    private String category;

    public CategoryDto(EveCategoryEntity category) {
        this.idcategory = category.getIdCategory();
        this.category = category.getCategory();
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

    @Override
    public String toString() {
        return "CatagoryDto{" +
                "idcategory=" + idcategory +
                ", category='" + category + '\'' +
                '}';
    }
}
