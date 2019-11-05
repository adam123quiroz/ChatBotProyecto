/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.chatbotsoftI.domain;

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

/**
 *
 * @author Ray Silva
 */
@Entity
@Table(name = "evecategory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evecategory.findAll", query = "SELECT e FROM Evecategory e")
    , @NamedQuery(name = "Evecategory.findByIdcategory", query = "SELECT e FROM Evecategory e WHERE e.idcategory = :idcategory")
    , @NamedQuery(name = "Evecategory.findByCategory", query = "SELECT e FROM Evecategory e WHERE e.category = :category")})
public class Evecategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcategory")
    private Integer idcategory;
    @Size(max = 45)
    @Column(name = "category")
    private String category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcategory", fetch = FetchType.LAZY)
    private List<Eveevent> eveeventList;

    public Evecategory() {
    }

    public Evecategory(Integer idcategory) {
        this.idcategory = idcategory;
    }

    public Integer getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(Integer idcategory) {
        this.idcategory = idcategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @XmlTransient
    public List<Eveevent> getEveeventList() {
        return eveeventList;
    }

    public void setEveeventList(List<Eveevent> eveeventList) {
        this.eveeventList = eveeventList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcategory != null ? idcategory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evecategory)) {
            return false;
        }
        Evecategory other = (Evecategory) object;
        if ((this.idcategory == null && other.idcategory != null) || (this.idcategory != null && !this.idcategory.equals(other.idcategory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.com.chatbotsoftI.domain.Evecategory[ idcategory=" + idcategory + " ]";
    }
    
}
