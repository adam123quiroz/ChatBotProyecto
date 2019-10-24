package edu.com.chatbotsoftI.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class TypeEvent {

    private @Id @GeneratedValue Long id;
    private String nameTypeEvent;

    public TypeEvent(Long id, String nameTypeEvent) {
        this.id = id;
        this.nameTypeEvent = nameTypeEvent;
    }

    public TypeEvent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTypeEvent() {
        return nameTypeEvent;
    }

    public void setNameTypeEvent(String nameTypeEvent) {
        this.nameTypeEvent = nameTypeEvent;
    }
}
