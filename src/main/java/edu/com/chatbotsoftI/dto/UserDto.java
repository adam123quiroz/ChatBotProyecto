package edu.com.chatbotsoftI.dto;

import edu.com.chatbotsoftI.domain.User;

public class UserDto {

    private Integer iduser;
    private String ci;
    private String name;
    private String age;
    private String email;
    private String nameuser;
    private String password;

    public UserDto(){
    }

    public UserDto(User user){
        this.iduser=user.getIduser();
        this.ci=user.getCi();
        this.name= user.getName();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.nameuser = user.getNameuser();
        this.password = user.getPassword();
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameuser() {
        return nameuser;
    }

    public void setNameuser(String nameuser) {
        this.nameuser = nameuser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
