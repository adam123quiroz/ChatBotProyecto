package edu.com.chatbotsoftI.entity;
/*
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "eveevent")
@XmlRootElement


public class EveEventEntity  implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idevent")
    private Integer idevent;
    @Size(max = 100)
    @Column(name = "nameevent")
    private String nameevent;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "starttime")
    @Temporal(TemporalType.TIME)
    private Date starttime;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idevent", fetch = FetchType.LAZY)
    private List<EveEventFileEntity> eveeventfileList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idevent", fetch = FetchType.LAZY)
    private List<EveBuyTicketEntity> evebuyticketList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idevent", fetch = FetchType.LAZY)
    private List<EveBookingEntity> evebookingList;
    @JoinColumn(name = "idcategory", referencedColumnName = "idcategory")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveCategoryEntity idcategory;
    @JoinColumn(name = "idaddress", referencedColumnName = "idaddress")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveAddressEntity idaddress;
    @JoinColumn(name = "idtypeevent", referencedColumnName = "idtypeevent")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveTypeEventEntity idtypeevent;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EveUserEntity iduser;

    public EveEventEntity() {
    }

    public EveEventEntity(Integer idevent) {
        this.idevent = idevent;
    }

    public Integer getIdevent() {
        return idevent;
    }

    public void setIdevent(Integer idevent) {
        this.idevent = idevent;
    }

    public String getNameevent() {
        return nameevent;
    }

    public void setNameevent(String nameevent) {
        this.nameevent = nameevent;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
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
    public List<EveEventFileEntity> getEveeventfileList() {
        return eveeventfileList;
    }

    public void setEveeventfileList(List<EveEventFileEntity> eveeventfileList) {
        this.eveeventfileList = eveeventfileList;
    }

    @XmlTransient
    public List<EveBuyTicketEntity> getEvebuyticketList() {
        return evebuyticketList;
    }

    public void setEvebuyticketList(List<EveBuyTicketEntity> evebuyticketList) {
        this.evebuyticketList = evebuyticketList;
    }

    @XmlTransient
    public List<EveBookingEntity> getEvebookingList() {
        return evebookingList;
    }

    public void setEvebookingList(List<EveBookingEntity> evebookingList) {
        this.evebookingList = evebookingList;
    }

    public EveCategoryEntity getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(EveCategoryEntity idcategory) {
        this.idcategory = idcategory;
    }

    public EveAddressEntity getIdaddress() {
        return idaddress;
    }

    public void setIdaddress(EveAddressEntity idaddress) {
        this.idaddress = idaddress;
    }

    public EveTypeEventEntity getIdtypeevent() {
        return idtypeevent;
    }

    public void setIdtypeevent(EveTypeEventEntity idtypeevent) {
        this.idtypeevent = idtypeevent;
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
        hash += (idevent != null ? idevent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EveEventEntity)) {
            return false;
        }
        EveEventEntity other = (EveEventEntity) object;
        if ((this.idevent == null && other.idevent != null) || (this.idevent != null && !this.idevent.equals(other.idevent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EveEventEntity[ idevent=" + idevent + " ]";
    }
}
*/
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "eveevent", schema = "dbbot", catalog = "")
public class EveEventEntity {
    private Integer idevent;
    private String nameevent;
    private BigDecimal price;
    private Date date;
    private Time starttime;
    private Integer status;
    private String txuser;
    private String txhost;
    private Date txdate;
    private List<EveBookingEntity> evebookingsByIdevent;
    private List<EveBuyTicketEntity> evebuyticketsByIdevent;
    private EveUserEntity eveuserByIduser;
    private EveTypeEventEntity evetypeeventByIdtypeevent;
    private EveCategoryEntity evecategoryByIdcategory;
    private EveAddressEntity eveaddressByIdaddress;
    private List<EveEventFileEntity> eveeventfilesByIdevent;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idevent", nullable = false)
    public Integer getIdevent() {
        return idevent;
    }

    public void setIdevent(Integer idevent) {
        this.idevent = idevent;
    }

    @Basic
    @Column(name = "nameevent", nullable = true, length = 100)
    public String getNameevent() {
        return nameevent;
    }

    public void setNameevent(String nameevent) {
        this.nameevent = nameevent;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 5)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "starttime", nullable = true)
    public Time getStarttime() {
        return starttime;
    }

    public void setStarttime(Time starttime) {
        this.starttime = starttime;
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

        EveEventEntity that = (EveEventEntity) o;

        if (idevent != null ? !idevent.equals(that.idevent) : that.idevent != null) return false;
        if (nameevent != null ? !nameevent.equals(that.nameevent) : that.nameevent != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (starttime != null ? !starttime.equals(that.starttime) : that.starttime != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (txuser != null ? !txuser.equals(that.txuser) : that.txuser != null) return false;
        if (txhost != null ? !txhost.equals(that.txhost) : that.txhost != null) return false;
        if (txdate != null ? !txdate.equals(that.txdate) : that.txdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idevent != null ? idevent.hashCode() : 0;
        result = 31 * result + (nameevent != null ? nameevent.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (starttime != null ? starttime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (txuser != null ? txuser.hashCode() : 0);
        result = 31 * result + (txhost != null ? txhost.hashCode() : 0);
        result = 31 * result + (txdate != null ? txdate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "eveeventByIdevent")
    public List<EveBookingEntity> getEvebookingsByIdevent() {
        return evebookingsByIdevent;
    }

    public void setEvebookingsByIdevent(List<EveBookingEntity> evebookingsByIdevent) {
        this.evebookingsByIdevent = evebookingsByIdevent;
    }

    @OneToMany(mappedBy = "eveeventByIdevent")
    public List<EveBuyTicketEntity> getEvebuyticketsByIdevent() {
        return evebuyticketsByIdevent;
    }

    public void setEvebuyticketsByIdevent(List<EveBuyTicketEntity> evebuyticketsByIdevent) {
        this.evebuyticketsByIdevent = evebuyticketsByIdevent;
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
    @JoinColumn(name = "idtypeevent", referencedColumnName = "idtypeevent", nullable = false)
    public EveTypeEventEntity getEvetypeeventByIdtypeevent() {
        return evetypeeventByIdtypeevent;
    }

    public void setEvetypeeventByIdtypeevent(EveTypeEventEntity evetypeeventByIdtypeevent) {
        this.evetypeeventByIdtypeevent = evetypeeventByIdtypeevent;
    }

    @ManyToOne
    @JoinColumn(name = "idcategory", referencedColumnName = "idcategory", nullable = false)
    public EveCategoryEntity getEvecategoryByIdcategory() {
        return evecategoryByIdcategory;
    }

    public void setEvecategoryByIdcategory(EveCategoryEntity evecategoryByIdcategory) {
        this.evecategoryByIdcategory = evecategoryByIdcategory;
    }

    @ManyToOne
    @JoinColumn(name = "idaddress", referencedColumnName = "idaddress", nullable = false)
    public EveAddressEntity getEveaddressByIdaddress() {
        return eveaddressByIdaddress;
    }

    public void setEveaddressByIdaddress(EveAddressEntity eveaddressByIdaddress) {
        this.eveaddressByIdaddress = eveaddressByIdaddress;
    }

    @OneToMany(mappedBy = "eveeventByIdevent")
    public List<EveEventFileEntity> getEveeventfilesByIdevent() {
        return eveeventfilesByIdevent;
    }

    public void setEveeventfilesByIdevent(List<EveEventFileEntity> eveeventfilesByIdevent) {
        this.eveeventfilesByIdevent = eveeventfilesByIdevent;
    }
}
