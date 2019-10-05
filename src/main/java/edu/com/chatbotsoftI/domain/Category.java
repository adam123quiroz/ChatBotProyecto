package edu.com.chatbotsoftI.domain;

public class Category {
    private Long id;
    private String nameCategory;

    public Category(Long id, String nameTypeEvent) {
        this.id = id;
        this.nameCategory = nameTypeEvent;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
