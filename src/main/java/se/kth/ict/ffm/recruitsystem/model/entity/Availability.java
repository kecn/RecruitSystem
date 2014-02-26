/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.ict.ffm.recruitsystem.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author
 */
@Entity
@Table(name = "availability")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Availability.findAll", query = "SELECT a FROM Availability a"),
    @NamedQuery(name = "Availability.findByAvailabilityid", query = "SELECT a FROM Availability a WHERE a.availabilityid = :availabilityid"),
    @NamedQuery(name = "Availability.findByFromdate", query = "SELECT a FROM Availability a WHERE a.fromdate = :fromdate"),
    @NamedQuery(name = "Availability.findByTodate", query = "SELECT a FROM Availability a WHERE a.todate = :todate")})
public class Availability implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "availabilityid")
    private Integer availabilityid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fromdate")
    @Temporal(TemporalType.DATE)
    private Date fromdate;
    @Column(name = "todate")
    @Temporal(TemporalType.DATE)
    private Date todate;
    @JoinColumn(name = "applicationid", referencedColumnName = "applicationid")
    @ManyToOne(optional = false)
    private Application applicationid;

    public Availability() {
    }

    public Availability(Integer availabilityid) {
        this.availabilityid = availabilityid;
    }

    public Availability(Integer availabilityid, Date fromdate) {
        this.availabilityid = availabilityid;
        this.fromdate = fromdate;
    }

    public Integer getAvailabilityid() {
        return availabilityid;
    }

    public void setAvailabilityid(Integer availabilityid) {
        this.availabilityid = availabilityid;
    }

    public Date getFromdate() {
        return fromdate;
    }

    public void setFromdate(Date fromdate) {
        this.fromdate = fromdate;
    }

    public Date getTodate() {
        return todate;
    }

    public void setTodate(Date todate) {
        this.todate = todate;
    }

    public Application getApplicationid() {
        return applicationid;
    }

    public void setApplicationid(Application applicationid) {
        this.applicationid = applicationid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (availabilityid != null ? availabilityid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Availability)) {
            return false;
        }
        Availability other = (Availability) object;
        if ((this.availabilityid == null && other.availabilityid != null) || (this.availabilityid != null && !this.availabilityid.equals(other.availabilityid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.ffm.recruitsystem.model.entity.Availability[ availabilityid=" + availabilityid + " ]";
    }
    
}
