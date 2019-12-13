package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "eve_ticket", schema = "dbbot", catalog = "")
public class EveTicketEntity {
    private int idTicket;
    private String numberTicket;
    private String txUser;
    private String txHost;
    private Date txDate;
    private int status;
    private List<EveBuyTicketEntity> eveBuyTicketsByIdTicket;
    private List<EvePaymentEntity> evePaymentsByIdTicket;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket", nullable = false)
    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    @Basic

    @Column(name = "number_ticket", nullable = true, length = 100)
    public String getNumberTicket() {
        return numberTicket;
    }

    public void setNumberTicket(String numberTicket) {
        this.numberTicket = numberTicket;
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

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EveTicketEntity that = (EveTicketEntity) o;

        if (idTicket != that.idTicket) return false;
        if (status != that.status) return false;
        if (numberTicket != null ? !numberTicket.equals(that.numberTicket) : that.numberTicket != null) return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txHost != null ? !txHost.equals(that.txHost) : that.txHost != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTicket;
        result = 31 * result + (numberTicket != null ? numberTicket.hashCode() : 0);
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txHost != null ? txHost.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }

    @OneToMany(mappedBy = "eveTicketByIdTicket")
    public List<EveBuyTicketEntity> getEveBuyTicketsByIdTicket() {
        return eveBuyTicketsByIdTicket;
    }

    public void setEveBuyTicketsByIdTicket(List<EveBuyTicketEntity> eveBuyTicketsByIdTicket) {
        this.eveBuyTicketsByIdTicket = eveBuyTicketsByIdTicket;
    }

    @OneToMany(mappedBy = "eveTicketByIdTicket")
    public List<EvePaymentEntity> getEvePaymentsByIdTicket() {
        return evePaymentsByIdTicket;
    }

    public void setEvePaymentsByIdTicket(List<EvePaymentEntity> evePaymentsByIdTicket) {
        this.evePaymentsByIdTicket = evePaymentsByIdTicket;
    }
}
