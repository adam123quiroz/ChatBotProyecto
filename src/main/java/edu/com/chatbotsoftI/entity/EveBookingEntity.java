package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "evebooking", schema = "dbbot", catalog = "")
public class EveBookingEntity {
    private Integer idbooking;
    private Integer quantity;
    private Timestamp date;
    private Integer status;
    private String txuser;
    private String txhost;
    private Date txdate;
    private EveUserEntity eveuserByIduser;
    private EveEventEntity eveeventByIdevent;
    private EveTicketEntity eveticketByIdticket;
    private List<EvePaymentEntity> evepaymentsByIdbooking;

    @Id
    @Column(name = "idbooking", nullable = false)
    public Integer getIdbooking() {
        return idbooking;
    }

    public void setIdbooking(Integer idbooking) {
        this.idbooking = idbooking;
    }

    @Basic
    @Column(name = "quantity", nullable = true)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

        EveBookingEntity that = (EveBookingEntity) o;

        if (idbooking != null ? !idbooking.equals(that.idbooking) : that.idbooking != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (txuser != null ? !txuser.equals(that.txuser) : that.txuser != null) return false;
        if (txhost != null ? !txhost.equals(that.txhost) : that.txhost != null) return false;
        if (txdate != null ? !txdate.equals(that.txdate) : that.txdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idbooking != null ? idbooking.hashCode() : 0;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (txuser != null ? txuser.hashCode() : 0);
        result = 31 * result + (txhost != null ? txhost.hashCode() : 0);
        result = 31 * result + (txdate != null ? txdate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "iduser", referencedColumnName = "iduser", nullable = false)
    public EveUserEntity getEveuserByIduser() {
        return eveuserByIduser;
    }

    public void setEveuserByIduser(EveUserEntity eveuserByIduser) {
        this.eveuserByIduser = eveuserByIduser;
    }

    @ManyToOne
    @JoinColumn(name = "idevent", referencedColumnName = "idevent", nullable = false)
    public EveEventEntity getEveeventByIdevent() {
        return eveeventByIdevent;
    }

    public void setEveeventByIdevent(EveEventEntity eveeventByIdevent) {
        this.eveeventByIdevent = eveeventByIdevent;
    }

    @ManyToOne
    @JoinColumn(name = "idticket", referencedColumnName = "idticket", nullable = false)
    public EveTicketEntity getEveticketByIdticket() {
        return eveticketByIdticket;
    }

    public void setEveticketByIdticket(EveTicketEntity eveticketByIdticket) {
        this.eveticketByIdticket = eveticketByIdticket;
    }

    @OneToMany(mappedBy = "evebookingByIdbooking")
    public List<EvePaymentEntity> getEvepaymentsByIdbooking() {
        return evepaymentsByIdbooking;
    }

    public void setEvepaymentsByIdbooking(List<EvePaymentEntity> evepaymentsByIdbooking) {
        this.evepaymentsByIdbooking = evepaymentsByIdbooking;
    }
}
