package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "eveticket", schema = "dbbot", catalog = "")
public class EveTicketEntity {
    private Integer idticket;
    private String numberticket;
    private String txuser;
    private String txhost;
    private Date txdate;
    private List<EveBookingEntity> evebookingsByIdticket;
    private List<EveBuyTicketEntity> evebuyticketsByIdticket;

    @Id
    @Column(name = "idticket", nullable = false)
    public Integer getIdticket() {
        return idticket;
    }

    public void setIdticket(Integer idticket) {
        this.idticket = idticket;
    }

    @Basic
    @Column(name = "numberticket", nullable = true, length = 100)
    public String getNumberticket() {
        return numberticket;
    }

    public void setNumberticket(String numberticket) {
        this.numberticket = numberticket;
    }

    @Basic
    @Column(name = "txuser", nullable = true, length = 45)
    public String getTxuser() {
        return txuser;
    }

    public void setTxuser(String txuser) {
        this.txuser = txuser;
    }

    @Basic
    @Column(name = "txhost", nullable = true, length = 100)
    public String getTxhost() {
        return txhost;
    }

    public void setTxhost(String txhost) {
        this.txhost = txhost;
    }

    @Basic
    @Column(name = "txdate", nullable = true)
    public Date getTxdate() {
        return txdate;
    }

    public void setTxdate(Date txdate) {
        this.txdate = txdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EveTicketEntity that = (EveTicketEntity) o;

        if (idticket != null ? !idticket.equals(that.idticket) : that.idticket != null) return false;
        if (numberticket != null ? !numberticket.equals(that.numberticket) : that.numberticket != null) return false;
        if (txuser != null ? !txuser.equals(that.txuser) : that.txuser != null) return false;
        if (txhost != null ? !txhost.equals(that.txhost) : that.txhost != null) return false;
        if (txdate != null ? !txdate.equals(that.txdate) : that.txdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idticket != null ? idticket.hashCode() : 0;
        result = 31 * result + (numberticket != null ? numberticket.hashCode() : 0);
        result = 31 * result + (txuser != null ? txuser.hashCode() : 0);
        result = 31 * result + (txhost != null ? txhost.hashCode() : 0);
        result = 31 * result + (txdate != null ? txdate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "eveticketByIdticket")
    public List<EveBookingEntity> getEvebookingsByIdticket() {
        return evebookingsByIdticket;
    }

    public void setEvebookingsByIdticket(List<EveBookingEntity> evebookingsByIdticket) {
        this.evebookingsByIdticket = evebookingsByIdticket;
    }

    @OneToMany(mappedBy = "eveticketByIdticket")
    public List<EveBuyTicketEntity> getEvebuyticketsByIdticket() {
        return evebuyticketsByIdticket;
    }

    public void setEvebuyticketsByIdticket(List<EveBuyTicketEntity> evebuyticketsByIdticket) {
        this.evebuyticketsByIdticket = evebuyticketsByIdticket;
    }
}
