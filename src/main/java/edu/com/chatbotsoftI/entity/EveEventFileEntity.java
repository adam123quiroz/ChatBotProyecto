package edu.com.chatbotsoftI.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "eve_event_file", schema = "dbbot", catalog = "")
public class EveEventFileEntity {
    private int idEveEventFile;
    private String txUser;
    private String txHost;
    private Date txDate;
    private EveEventEntity eveEventByIdEvent;
    private EveFileEntity eveFileByIdEveFile;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_eve_event_file", nullable = false)
    public int getIdEveEventFile() {
        return idEveEventFile;
    }

    public void setIdEveEventFile(int idEveEventFile) {
        this.idEveEventFile = idEveEventFile;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EveEventFileEntity that = (EveEventFileEntity) o;

        if (idEveEventFile != that.idEveEventFile) return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txHost != null ? !txHost.equals(that.txHost) : that.txHost != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEveEventFile;
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txHost != null ? txHost.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_event", referencedColumnName = "id_event", nullable = false)
    public EveEventEntity getEveEventByIdEvent() {
        return eveEventByIdEvent;
    }

    public void setEveEventByIdEvent(EveEventEntity eveEventByIdEvent) {
        this.eveEventByIdEvent = eveEventByIdEvent;
    }

    @ManyToOne
    @JoinColumn(name = "id_eve_file", referencedColumnName = "id_file", nullable = false)
    public EveFileEntity getEveFileByIdEveFile() {
        return eveFileByIdEveFile;
    }

    public void setEveFileByIdEveFile(EveFileEntity eveFileByIdEveFile) {
        this.eveFileByIdEveFile = eveFileByIdEveFile;
    }
}
