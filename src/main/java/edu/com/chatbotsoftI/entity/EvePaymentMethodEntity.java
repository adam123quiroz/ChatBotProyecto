package edu.com.chatbotsoftI.entity;

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
