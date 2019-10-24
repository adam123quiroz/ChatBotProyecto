package edu.com.chatbotsoftI.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ev_category")
@XmlRootElement
public class Category {

    private @Id @GeneratedValue Long id;
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
