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
@Table(name = "eveticket")
@XmlRootElement

public class EveTicketEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idticket")
    private Integer idticket;
    @Size(max = 100)
    @Column(name = "numberticket")
    private String numberticket;
    @Size(max = 45)
    @Column(name = "txuser")
    private String txuser;
    @Size(max = 100)
    @Column(name = "txhost")
    private String txhost;
    @Column(name = "txdate")
    @Temporal(TemporalType.DATE)
    private Date txdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idticket", fetch = FetchType.LAZY)
    private List<EveBuyTicketEntity> evebuyticketList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idticket", fetch = FetchType.LAZY)
    private List<EveBookingEntity> evebookingList;

    public EveTicketEntity() {
    }

    public EveTicketEntity(Integer idticket) {
        this.idticket = idticket;
    }

    public Integer getIdticket() {
        return idticket;
    }

    public void setIdticket(Integer idticket) {
        this.idticket = idticket;
    }

    public String getNumberticket() {
        return numberticket;
    }

    public void setNumberticket(String numberticket) {
        this.numberticket = numberticket;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idticket != null ? idticket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EveTicketEntity)) {
            return false;
        }
        EveTicketEntity other = (EveTicketEntity) object;
        if ((this.idticket == null && other.idticket != null) || (this.idticket != null && !this.idticket.equals(other.idticket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EveTicketEntity[ idticket=" + idticket + " ]";
    }
}
*/
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
