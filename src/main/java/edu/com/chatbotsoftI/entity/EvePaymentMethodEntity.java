package edu.com.chatbotsoftI.entity;

/*
import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "evepaymentmethod")
@XmlRootElement

public class EvePaymentMethodEntity implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpaymentmethod")
    private Integer idpaymentmethod;
    @Size(max = 45)
    @Column(name = "paymentmethod")
    private String paymentmethod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpaymentmethod", fetch = FetchType.LAZY)
    private List<EvePaymentEntity> evepaymentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpaymentmethod", fetch = FetchType.LAZY)
    private List<EveBuyTicketEntity> evebuyticketList;

    public EvePaymentMethodEntity() {
    }

    public EvePaymentMethodEntity(Integer idpaymentmethod) {
        this.idpaymentmethod = idpaymentmethod;
    }

    public Integer getIdpaymentmethod() {
        return idpaymentmethod;
    }

    public void setIdpaymentmethod(Integer idpaymentmethod) {
        this.idpaymentmethod = idpaymentmethod;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    @XmlTransient
    public List<EvePaymentEntity> getEvepaymentList() {
        return evepaymentList;
    }

    public void setEvepaymentList(List<EvePaymentEntity> evepaymentList) {
        this.evepaymentList = evepaymentList;
    }

    @XmlTransient
    public List<EveBuyTicketEntity> getEvebuyticketList() {
        return evebuyticketList;
    }

    public void setEvebuyticketList(List<EveBuyTicketEntity> evebuyticketList) {
        this.evebuyticketList = evebuyticketList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpaymentmethod != null ? idpaymentmethod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvePaymentMethodEntity)) {
            return false;
        }
        EvePaymentMethodEntity other = (EvePaymentMethodEntity) object;
        if ((this.idpaymentmethod == null && other.idpaymentmethod != null) || (this.idpaymentmethod != null && !this.idpaymentmethod.equals(other.idpaymentmethod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EvePaymentMethodEntity[ idpaymentmethod=" + idpaymentmethod + " ]";
    }

}
*/
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "evepaymentmethod", schema = "dbbot", catalog = "")
public class EvePaymentMethodEntity {
    private Integer idpaymentmethod;
    private String paymentmethod;
    private List<EveBuyTicketEntity> evebuyticketsByIdpaymentmethod;
    private List<EvePaymentEntity> evepaymentsByIdpaymentmethod;

    @Id
    @Column(name = "idpaymentmethod", nullable = false)
    public Integer getIdpaymentmethod() {
        return idpaymentmethod;
    }

    public void setIdpaymentmethod(Integer idpaymentmethod) {
        this.idpaymentmethod = idpaymentmethod;
    }

    @Basic
    @Column(name = "paymentmethod", nullable = true, length = 45)
    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EvePaymentMethodEntity that = (EvePaymentMethodEntity) o;

        if (idpaymentmethod != null ? !idpaymentmethod.equals(that.idpaymentmethod) : that.idpaymentmethod != null)
            return false;
        if (paymentmethod != null ? !paymentmethod.equals(that.paymentmethod) : that.paymentmethod != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idpaymentmethod != null ? idpaymentmethod.hashCode() : 0;
        result = 31 * result + (paymentmethod != null ? paymentmethod.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "evepaymentmethodByIdpaymentmethod")
    public List<EveBuyTicketEntity> getEvebuyticketsByIdpaymentmethod() {
        return evebuyticketsByIdpaymentmethod;
    }

    public void setEvebuyticketsByIdpaymentmethod(List<EveBuyTicketEntity> evebuyticketsByIdpaymentmethod) {
        this.evebuyticketsByIdpaymentmethod = evebuyticketsByIdpaymentmethod;
    }

    @OneToMany(mappedBy = "evepaymentmethodByIdpaymentmethod")
    public List<EvePaymentEntity> getEvepaymentsByIdpaymentmethod() {
        return evepaymentsByIdpaymentmethod;
    }

    public void setEvepaymentsByIdpaymentmethod(List<EvePaymentEntity> evepaymentsByIdpaymentmethod) {
        this.evepaymentsByIdpaymentmethod = evepaymentsByIdpaymentmethod;
    }
}

