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
@Table(name = "competenceinprofile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Competenceinprofile.findAll", query = "SELECT c FROM Competenceinprofile c"),
    @NamedQuery(name = "Competenceinprofile.findByYearsofexperience", query = "SELECT c FROM Competenceinprofile c WHERE c.yearsofexperience = :yearsofexperience"),
    @NamedQuery(name = "Competenceinprofile.findByCompetenceprofileid", query = "SELECT c FROM Competenceinprofile c WHERE c.competenceinprofilePK.competenceprofileid = :competenceprofileid"),
    @NamedQuery(name = "Competenceinprofile.findByCompetenceid", query = "SELECT c FROM Competenceinprofile c WHERE c.competenceinprofilePK.competenceid = :competenceid")})
public class Competenceinprofile implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompetenceinprofilePK competenceinprofilePK;
    @Column(name = "yearsofexperience")
    private Integer yearsofexperience;
    @JoinColumn(name = "competenceid", referencedColumnName = "competenceid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Competence competence;
    @JoinColumn(name = "competenceprofileid", referencedColumnName = "competenceprofileid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Competenceprofile competenceprofile;

    public Competenceinprofile() {
    }

    public Competenceinprofile(CompetenceinprofilePK competenceinprofilePK) {
        this.competenceinprofilePK = competenceinprofilePK;
    }

    public Competenceinprofile(int competenceprofileid, int competenceid) {
        this.competenceinprofilePK = new CompetenceinprofilePK(competenceprofileid, competenceid);
    }

    public CompetenceinprofilePK getCompetenceinprofilePK() {
        return competenceinprofilePK;
    }

    public void setCompetenceinprofilePK(CompetenceinprofilePK competenceinprofilePK) {
        this.competenceinprofilePK = competenceinprofilePK;
    }

    public Integer getYearsofexperience() {
        return yearsofexperience;
    }

    public void setYearsofexperience(Integer yearsofexperience) {
        this.yearsofexperience = yearsofexperience;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    public Competenceprofile getCompetenceprofile() {
        return competenceprofile;
    }

    public void setCompetenceprofile(Competenceprofile competenceprofile) {
        this.competenceprofile = competenceprofile;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (competenceinprofilePK != null ? competenceinprofilePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competenceinprofile)) {
            return false;
        }
        Competenceinprofile other = (Competenceinprofile) object;
        if ((this.competenceinprofilePK == null && other.competenceinprofilePK != null) || (this.competenceinprofilePK != null && !this.competenceinprofilePK.equals(other.competenceinprofilePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.ffm.recruitsystem.model.entity.Competenceinprofile[ competenceinprofilePK=" + competenceinprofilePK + " ]";
    }
    
}
