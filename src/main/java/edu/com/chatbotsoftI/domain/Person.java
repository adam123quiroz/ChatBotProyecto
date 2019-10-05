package edu.com.chatbotsoftI.domain;

import javax.validation.constraints.Email;

public class Person {
    private long ci;
    private String name;
    private String lastName;
    private String age;
    private Email email;


    public Person() {
    }

    public Person(long ci, String name, String lastName, String age, Email email) {
        this.ci = ci;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public long getCi() {
        return ci;
    }

    public void setCi(long ci) {
        this.ci = ci;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
}
