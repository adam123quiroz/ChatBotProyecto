package edu.com.chatbotsoftI.domain;

import javax.validation.constraints.Email;

public class User extends Person {
    private String nameUser;
    private String password;

    public User() {
    }

    public User(long ci, String name, String lastName, String age, Email email, String nameUser, String password) {
        super(ci, name, lastName, age, email);
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
