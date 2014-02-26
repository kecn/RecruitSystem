/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.ict.ffm.recruitsystem.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author
 */
@Entity
@Table(name = "competenceinapplication")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Competenceinapplication.findAll", query = "SELECT c FROM Competenceinapplication c"),
    @NamedQuery(name = "Competenceinapplication.findByYearsofexperience", query = "SELECT c FROM Competenceinapplication c WHERE c.yearsofexperience = :yearsofexperience"),
    @NamedQuery(name = "Competenceinapplication.findByCompetenceid", query = "SELECT c FROM Competenceinapplication c WHERE c.competenceinapplicationPK.competenceid = :competenceid"),
    @NamedQuery(name = "Competenceinapplication.findByApplicationid", query = "SELECT c FROM Competenceinapplication c WHERE c.competenceinapplicationPK.applicationid = :applicationid")})
public class Competenceinapplication implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompetenceinapplicationPK competenceinapplicationPK;
    @Column(name = "yearsofexperience")
    private Integer yearsofexperience;
    @JoinColumn(name = "applicationid", referencedColumnName = "applicationid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Application application;
    @JoinColumn(name = "competenceid", referencedColumnName = "competenceid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Competence competence;

    public Competenceinapplication() {
    }

    public Competenceinapplication(CompetenceinapplicationPK competenceinapplicationPK) {
        this.competenceinapplicationPK = competenceinapplicationPK;
    }

    public Competenceinapplication(int competenceid, int applicationid) {
        this.competenceinapplicationPK = new CompetenceinapplicationPK(competenceid, applicationid);
    }

    public CompetenceinapplicationPK getCompetenceinapplicationPK() {
        return competenceinapplicationPK;
    }

    public void setCompetenceinapplicationPK(CompetenceinapplicationPK competenceinapplicationPK) {
        this.competenceinapplicationPK = competenceinapplicationPK;
    }

    public Integer getYearsofexperience() {
        return yearsofexperience;
    }

    public void setYearsofexperience(Integer yearsofexperience) {
        this.yearsofexperience = yearsofexperience;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (competenceinapplicationPK != null ? competenceinapplicationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competenceinapplication)) {
            return false;
        }
        Competenceinapplication other = (Competenceinapplication) object;
        if ((this.competenceinapplicationPK == null && other.competenceinapplicationPK != null) || (this.competenceinapplicationPK != null && !this.competenceinapplicationPK.equals(other.competenceinapplicationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.ffm.recruitsystem.model.entity.Competenceinapplication[ competenceinapplicationPK=" + competenceinapplicationPK + " ]";
    }
    
}
