package edu.com.chatbotsoftI.dto;

import edu.com.chatbotsoftI.entity.EveUserEntity;

public class UserDto {
    private Integer iduser;
    private String nameuser;
    private String password;
    private String email;

    public UserDto(EveUserEntity eveUserEntity) {
        this.iduser = eveUserEntity.getIdUser();
        this.nameuser = eveUserEntity.getNameUser();
        this.password = eveUserEntity.getPassword();
        this.email = eveUserEntity.getEmail();
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
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

    public String getEmail(){return  email;}

    public  void setEmail(String email){this.email = email;}

    @Override
    public String toString() {
        return "UserDto{" +
                "iduser=" + iduser +
                ", nameuser='" + nameuser + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
