package edu.com.chatbotsoftI.dto;

import edu.com.chatbotsoftI.entity.EvePersonEntity;

public class PersonDto {

    private Integer idperson;
    private String ci;
    private String name;
    private String lastname;
    private String botUserId;

    public PersonDto(){
    }

    public PersonDto(EvePersonEntity user){
        this.idperson = user.getIdPerson();
        this.ci = user.getCi();
        this.name = user.getName();
        this.lastname = user.getLastName();
        this.botUserId = user.getBotUserId();
    }

    public Integer getIdperson() {
        return idperson;
    }

    public void setIdperson(Integer idperson) {
        this.idperson = idperson;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBotUserId() {
        return botUserId;
    }

    public void setBotUserId(String botUserId) {
        this.botUserId = botUserId;
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "idperson=" + idperson +
                ", ci='" + ci + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", botUserId='" + botUserId + '\'' +
                '}';
    }
}
