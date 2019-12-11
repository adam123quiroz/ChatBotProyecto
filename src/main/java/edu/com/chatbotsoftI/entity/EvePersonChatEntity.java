package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "eve_person_chat", schema = "dbbot", catalog = "")
public class EvePersonChatEntity {
    private int idEveUserChat;
    private String userBotChatId;
    private String txUser;
    private String txHost;
    private Date txDate;
    private EvePersonEntity evePersonByIdPerson;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_eve_user_chat", nullable = false)
    public int getIdEveUserChat() {
        return idEveUserChat;
    }

    public void setIdEveUserChat(int idEveUserChat) {
        this.idEveUserChat = idEveUserChat;
    }

    @Basic
    @Column(name = "user_bot_chat_id", nullable = true, length = 45)
    public String getUserBotChatId() {
        return userBotChatId;
    }

    public void setUserBotChatId(String userBotChatId) {
        this.userBotChatId = userBotChatId;
    }

    @Basic
    @Column(name = "tx_user", nullable = true, length = 45)
    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    @Basic
    @Column(name = "tx_host", nullable = true, length = 100)
    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    @Basic
    @Column(name = "tx_date", nullable = true)
    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EvePersonChatEntity that = (EvePersonChatEntity) o;

        if (idEveUserChat != that.idEveUserChat) return false;
        if (userBotChatId != null ? !userBotChatId.equals(that.userBotChatId) : that.userBotChatId != null)
            return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txHost != null ? !txHost.equals(that.txHost) : that.txHost != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEveUserChat;
        result = 31 * result + (userBotChatId != null ? userBotChatId.hashCode() : 0);
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txHost != null ? txHost.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id_person", nullable = false)
    public EvePersonEntity getEvePersonByIdPerson() {
        return evePersonByIdPerson;
    }

    public void setEvePersonByIdPerson(EvePersonEntity evePersonByIdPerson) {
        this.evePersonByIdPerson = evePersonByIdPerson;
    }
}
