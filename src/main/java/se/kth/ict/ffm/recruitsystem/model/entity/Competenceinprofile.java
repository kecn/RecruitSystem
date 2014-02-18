/*    
 *     RecruitSystem - a distributed application to handle job applications.
 *     Copyright (C) 2014  Federico Klappenbach, Fredrik Johansson, Mikael Tenhunen
 * 
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *      along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package se.kth.ict.ffm.recruitsystem.model.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
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
        return "se.kth.ict.ffm.recruitsystem.model.Competenceinprofile[ competenceinprofilePK=" + competenceinprofilePK + " ]";
    }
    
}
