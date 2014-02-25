/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.ict.ffm.recruitsystem.model.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author
 */
@Entity
@Table(name = "competenceprofile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Competenceprofile.findAll", query = "SELECT c FROM Competenceprofile c"),
    @NamedQuery(name = "Competenceprofile.findByCompetenceprofileid", query = "SELECT c FROM Competenceprofile c WHERE c.competenceprofileid = :competenceprofileid")})
public class Competenceprofile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "competenceprofileid")
    private Integer competenceprofileid;
    @JoinColumn(name = "personid", referencedColumnName = "personid")
    @ManyToOne(optional = false)
    private Person personid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competenceprofile")
    private Collection<Competenceinprofile> competenceinprofileCollection;

    public Competenceprofile() {
    }

    public Competenceprofile(Integer competenceprofileid) {
        this.competenceprofileid = competenceprofileid;
    }

    public Integer getCompetenceprofileid() {
        return competenceprofileid;
    }

    public void setCompetenceprofileid(Integer competenceprofileid) {
        this.competenceprofileid = competenceprofileid;
    }

    public Person getPersonid() {
        return personid;
    }

    public void setPersonid(Person personid) {
        this.personid = personid;
    }

    @XmlTransient
    public Collection<Competenceinprofile> getCompetenceinprofileCollection() {
        return competenceinprofileCollection;
    }

    public void setCompetenceinprofileCollection(Collection<Competenceinprofile> competenceinprofileCollection) {
        this.competenceinprofileCollection = competenceinprofileCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (competenceprofileid != null ? competenceprofileid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competenceprofile)) {
            return false;
        }
        Competenceprofile other = (Competenceprofile) object;
        if ((this.competenceprofileid == null && other.competenceprofileid != null) || (this.competenceprofileid != null && !this.competenceprofileid.equals(other.competenceprofileid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.ffm.recruitsystem.model.entity.Competenceprofile[ competenceprofileid=" + competenceprofileid + " ]";
    }
}
