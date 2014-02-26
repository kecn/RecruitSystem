/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.ict.ffm.recruitsystem.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author
 */
@Entity
@Table(name = "application")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Application.findAll", query = "SELECT a FROM Application a"),
    @NamedQuery(name = "Application.findByApplicationid", query = "SELECT a FROM Application a WHERE a.applicationid = :applicationid"),
    @NamedQuery(name = "Application.findByApplicationdate", query = "SELECT a FROM Application a WHERE a.applicationdate = :applicationdate")})
public class Application implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "applicationid")
    private Integer applicationid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "applicationdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicationdate;
    @JoinColumn(name = "personid", referencedColumnName = "personid")
    @ManyToOne(optional = false)
    private Person personid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "application")
    private Collection<Competenceinapplication> competenceinapplicationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationid")
    private Collection<Availability> availabilityCollection;

    public Application() {
        availabilityCollection = new ArrayList();
        competenceinapplicationCollection = new ArrayList();
    }

    public Application(Integer applicationid) {
        this.applicationid = applicationid;
    }

    public Application(Integer applicationid, Date applicationdate) {
        this.applicationid = applicationid;
        this.applicationdate = applicationdate;
    }

    public Integer getApplicationid() {
        return applicationid;
    }

    public void setApplicationid(Integer applicationid) {
        this.applicationid = applicationid;
    }

    public Date getApplicationdate() {
        return applicationdate;
    }

    public void setApplicationdate(Date applicationdate) {
        this.applicationdate = applicationdate;
    }

    public Person getPersonid() {
        return personid;
    }

    public void setPersonid(Person personid) {
        this.personid = personid;
    }

    @XmlTransient
    public Collection<Competenceinapplication> getCompetenceinapplicationCollection() {
        return competenceinapplicationCollection;
    }

    public void setCompetenceinapplicationCollection(Collection<Competenceinapplication> competenceinapplicationCollection) {
        this.competenceinapplicationCollection = competenceinapplicationCollection;
    }

    @XmlTransient
    public Collection<Availability> getAvailabilityCollection() {
        return availabilityCollection;
    }

    public void setAvailabilityCollection(Collection<Availability> availabilityCollection) {
        this.availabilityCollection = availabilityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applicationid != null ? applicationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        if ((this.applicationid == null && other.applicationid != null) || (this.applicationid != null && !this.applicationid.equals(other.applicationid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.ffm.recruitsystem.model.entity.Application[ applicationid=" + applicationid + " ]";
    }
    
}
