package edu.com.chatbotsoftI.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "cp_person")
@XmlRootElement
public class User extends org.telegram.telegrambots.meta.api.objects.User {
    private String nameUser;
    private String password;

    public User() {
    }

    public User(Integer id, String firstName, Boolean isBot, String lastName, String userName, String languageCode, String nameUser, String password) {
        super(id, firstName, isBot, lastName, userName, languageCode);
        this.nameUser = nameUser;
        this.password = password;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
