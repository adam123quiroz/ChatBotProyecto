package edu.com.chatbotsoftI.dto;

import edu.com.chatbotsoftI.entity.EveUserEntity;

public class UserDto {
    private Integer iduser;
    private String nameuser;
    private String password;

    public UserDto(EveUserEntity eveUserEntity) {
        this.iduser = eveUserEntity.getIduser();
        this.nameuser = eveUserEntity.getNameuser();
        this.password = eveUserEntity.getPassword();
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

    @Override
    public String toString() {
        return "UserDto{" +
                "iduser=" + iduser +
                ", nameuser='" + nameuser + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
