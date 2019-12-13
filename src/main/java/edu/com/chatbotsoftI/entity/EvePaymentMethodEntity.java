package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "eve_payment_method", schema = "dbbot", catalog = "")
public class EvePaymentMethodEntity {
    private int idPaymentMethod;
    private String paymentMethod;
    private List<EveBuyTicketEntity> eveBuyTicketsByIdPaymentMethod;
    private List<EvePaymentEntity> evePaymentsByIdPaymentMethod;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment_method", nullable = false)
    public int getIdPaymentMethod() {
        return idPaymentMethod;
    }

    public void setIdPaymentMethod(int idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }

    @Basic

    @Column(name = "payment_method", nullable = true, length = 45)
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EvePaymentMethodEntity that = (EvePaymentMethodEntity) o;

        if (idPaymentMethod != that.idPaymentMethod) return false;
        if (paymentMethod != null ? !paymentMethod.equals(that.paymentMethod) : that.paymentMethod != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPaymentMethod;
        result = 31 * result + (paymentMethod != null ? paymentMethod.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "evePaymentMethodByIdPaymentMethod")
    public List<EveBuyTicketEntity> getEveBuyTicketsByIdPaymentMethod() {
        return eveBuyTicketsByIdPaymentMethod;
    }

    public void setEveBuyTicketsByIdPaymentMethod(List<EveBuyTicketEntity> eveBuyTicketsByIdPaymentMethod) {
        this.eveBuyTicketsByIdPaymentMethod = eveBuyTicketsByIdPaymentMethod;
    }

    @OneToMany(mappedBy = "evePaymentMethodByIdPaymentMethod")
    public List<EvePaymentEntity> getEvePaymentsByIdPaymentMethod() {
        return evePaymentsByIdPaymentMethod;
    }

    public void setEvePaymentsByIdPaymentMethod(List<EvePaymentEntity> evePaymentsByIdPaymentMethod) {
        this.evePaymentsByIdPaymentMethod = evePaymentsByIdPaymentMethod;
    }
}
