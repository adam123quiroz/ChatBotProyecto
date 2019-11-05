package edu.com.chatbotsoftI.dto;

import edu.com.chatbotsoftI.domain.EveCategory;

public class CatagoryDto {

    private Integer idcategory;
    private String category;

    public CatagoryDto(EveCategory category) {
        this.idcategory = category.getIdcategory();
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
