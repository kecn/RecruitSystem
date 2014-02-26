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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity for table competenceinapplication. Many to one relations to Application
 * and Competence
 */
@Entity
@Table(name = "competenceinapplication")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Competenceinapplication.findAll", query = "SELECT c FROM Competenceinapplication c"),
    @NamedQuery(name = "Competenceinapplication.findByYearsofexperience", query = "SELECT c FROM Competenceinapplication c WHERE c.yearsofexperience = :yearsofexperience"),
    @NamedQuery(name = "Competenceinapplication.findByCompetenceinapplicationid", query = "SELECT c FROM Competenceinapplication c WHERE c.competenceinapplicationid = :competenceinapplicationid"),
    @NamedQuery(name = "Competenceinapplication.findByCompetenceid", query = "SELECT c FROM Competenceinapplication c WHERE c.competenceid = :competenceid"),
    @NamedQuery(name = "Competenceinapplication.findByApplicationid", query = "SELECT c FROM Competenceinapplication c WHERE c.applicationid = :applicationid")})
public class Competenceinapplication implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "yearsofexperience")
    private Integer yearsofexperience;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "competenceinapplicationid")
    private Integer competenceinapplicationid;
    @JoinColumn(name = "applicationid", referencedColumnName = "applicationid")
    @ManyToOne(optional = false)
    private Application applicationid;
    @JoinColumn(name = "competenceid", referencedColumnName = "competenceid")
    @ManyToOne(optional = false)
    private Competence competenceid;

    public Competenceinapplication() {
    }

    public Competenceinapplication(Integer competenceinapplicationid) {
        this.competenceinapplicationid = competenceinapplicationid;
    }

    public Integer getYearsofexperience() {
        return yearsofexperience;
    }

    public void setYearsofexperience(Integer yearsofexperience) {
        this.yearsofexperience = yearsofexperience;
    }

    public Integer getCompetenceinapplicationid() {
        return competenceinapplicationid;
    }

    public void setCompetenceinapplicationid(Integer competenceinapplicationid) {
        this.competenceinapplicationid = competenceinapplicationid;
    }

    public Application getApplicationid() {
        return applicationid;
    }

    public void setApplicationid(Application applicationid) {
        this.applicationid = applicationid;
    }

    public Competence getCompetenceid() {
        return competenceid;
    }

    public void setCompetenceid(Competence competenceid) {
        this.competenceid = competenceid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (competenceinapplicationid != null ? competenceinapplicationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competenceinapplication)) {
            return false;
        }
        Competenceinapplication other = (Competenceinapplication) object;
        if ((this.competenceinapplicationid == null && other.competenceinapplicationid != null) || (this.competenceinapplicationid != null && !this.competenceinapplicationid.equals(other.competenceinapplicationid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.ffm.recruitsystem.model.entity.Competenceinapplication[ competenceinapplicationid=" + competenceinapplicationid + " ]";
    }
    
}
