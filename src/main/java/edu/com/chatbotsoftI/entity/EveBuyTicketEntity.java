package edu.com.chatbotsoftI.entity;
/*
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "evebuyticket")
@XmlRootElement

public class EveBuyTicketEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbuy")
    private Integer idbuy;
    @Column(name = "quantity")
    private Integer quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private BigDecimal total;
    @Size(max = 45)
    @Column(name = "txuser")
    private String txuser;
    @Size(max = 100)
    @Column(name = "txhost")
    private String txhost;
    @Column(name = "txdate")
    @Temporal(TemporalType.DATE)
    private Date txdate;
    @JoinColumn(name = "idevent", referencedColumnName = "idevent")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveEventEntity idevent;
    @JoinColumn(name = "idpaymentmethod", referencedColumnName = "idpaymentmethod")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EvePaymentMethodEntity idpaymentmethod;
    @JoinColumn(name = "idticket", referencedColumnName = "idticket")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveTicketEntity idticket;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveUserEntity iduser;

    public EveBuyTicketEntity() {
    }

    public EveBuyTicketEntity(Integer idbuy) {
        this.idbuy = idbuy;
    }

    public Integer getIdbuy() {
        return idbuy;
    }

    public void setIdbuy(Integer idbuy) {
        this.idbuy = idbuy;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public EveEventEntity getIdevent() {
        return idevent;
    }

    public void setIdevent(EveEventEntity idevent) {
        this.idevent = idevent;
    }

    public EvePaymentMethodEntity getIdpaymentmethod() {
        return idpaymentmethod;
    }

    public void setIdpaymentmethod(EvePaymentMethodEntity idpaymentmethod) {
        this.idpaymentmethod = idpaymentmethod;
    }

    public EveTicketEntity getIdticket() {
        return idticket;
    }

    public void setIdticket(EveTicketEntity idticket) {
        this.idticket = idticket;
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
        hash += (idbuy != null ? idbuy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        //
        if (!(object instanceof EveBuyTicketEntity)) {
            return false;
        }
        EveBuyTicketEntity other = (EveBuyTicketEntity) object;
        if ((this.idbuy == null && other.idbuy != null) || (this.idbuy != null && !this.idbuy.equals(other.idbuy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EveBuyTicketEntity[ idbuy=" + idbuy + " ]";
    }
}
*/
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "evebuyticket", schema = "dbbot", catalog = "")
public class EveBuyTicketEntity {
    private Integer idbuy;
    private Integer quantity;
    private BigDecimal total;
    private String txuser;
    private String txhost;
    private Date txdate;
    private EveUserEntity eveuserByIduser;
    private EveEventEntity eveeventByIdevent;
    private EveTicketEntity eveticketByIdticket;
    private EvePaymentMethodEntity evepaymentmethodByIdpaymentmethod;

    @Id
    @Column(name = "idbuy", nullable = false)
    public Integer getIdbuy() {
        return idbuy;
    }

    public void setIdbuy(Integer idbuy) {
        this.idbuy = idbuy;
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
    @Column(name = "total", nullable = true, precision = 5)
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

        EveBuyTicketEntity that = (EveBuyTicketEntity) o;

        if (idbuy != null ? !idbuy.equals(that.idbuy) : that.idbuy != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (txuser != null ? !txuser.equals(that.txuser) : that.txuser != null) return false;
        if (txhost != null ? !txhost.equals(that.txhost) : that.txhost != null) return false;
        if (txdate != null ? !txdate.equals(that.txdate) : that.txdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idbuy != null ? idbuy.hashCode() : 0;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
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

    @ManyToOne
    @JoinColumn(name = "idpaymentmethod", referencedColumnName = "idpaymentmethod", nullable = false)
    public EvePaymentMethodEntity getEvepaymentmethodByIdpaymentmethod() {
        return evepaymentmethodByIdpaymentmethod;
    }

    public void setEvepaymentmethodByIdpaymentmethod(EvePaymentMethodEntity evepaymentmethodByIdpaymentmethod) {
        this.evepaymentmethodByIdpaymentmethod = evepaymentmethodByIdpaymentmethod;
    }
}
