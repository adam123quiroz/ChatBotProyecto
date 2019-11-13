package edu.com.chatbotsoftI.entity;
/*
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "evebooking")
@XmlRootElement

public class EveBookingEntity  implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbooking")
    private Integer idbooking;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "status")
    private Integer status;
    @Size(max = 45)
    @Column(name = "txuser")
    private String txuser;
    @Size(max = 100)
    @Column(name = "txhost")
    private String txhost;
    @Column(name = "txdate")
    @Temporal(TemporalType.DATE)
    private Date txdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbooking", fetch = FetchType.LAZY)
    private List<EvePaymentEntity> evepaymentList;
    @JoinColumn(name = "idticket", referencedColumnName = "idticket")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveTicketEntity idticket;
    @JoinColumn(name = "idevent", referencedColumnName = "idevent")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveEventEntity idevent;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveUserEntity iduser;

    public EveBookingEntity() {
    }

    public EveBookingEntity(Integer idbooking) {
        this.idbooking = idbooking;
    }

    public Integer getIdbooking() {
        return idbooking;
    }

    public void setIdbooking(Integer idbooking) {
        this.idbooking = idbooking;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTxuser() {
        return txuser;
    }

    public void setTxuser(String txuser) {
        this.txuser = txuser;
    }

    public String getTxhost() {
        return txhost;
    }

    public void setTxhost(String txhost) {
        this.txhost = txhost;
    }

    public Date getTxdate() {
        return txdate;
    }

    public void setTxdate(Date txdate) {
        this.txdate = txdate;
    }

    @XmlTransient
    public List<EvePaymentEntity> getEvepaymentList() {
        return evepaymentList;
    }

    public void setEvepaymentList(List<EvePaymentEntity> evepaymentList) {
        this.evepaymentList = evepaymentList;
    }

    public EveTicketEntity getIdticket() {
        return idticket;
    }

    public void setIdticket(EveTicketEntity idticket) {
        this.idticket = idticket;
    }

    public EveEventEntity getIdevent() {
        return idevent;
    }

    public void setIdevent(EveEventEntity idevent) {
        this.idevent = idevent;
    }

    public EveUserEntity getIduser() {
        return iduser;
    }

    public void setIduser(EveUserEntity iduser) {
        this.iduser = iduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbooking != null ? idbooking.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        //
        if (!(object instanceof EveBookingEntity)) {
            return false;
        }
        EveBookingEntity other = (EveBookingEntity) object;
        if ((this.idbooking == null && other.idbooking != null) || (this.idbooking != null && !this.idbooking.equals(other.idbooking))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EveBookingEntity[ idbooking=" + idbooking + " ]";
    }

}
*/
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
