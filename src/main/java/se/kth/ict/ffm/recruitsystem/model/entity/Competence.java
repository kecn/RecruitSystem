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
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "competence")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Competence.findAll", query = "SELECT c FROM Competence c"),
    @NamedQuery(name = "Competence.findByCompetenceid", query = "SELECT c FROM Competence c WHERE c.competenceid = :competenceid")})
public class Competence implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "competenceid")
    private Integer competenceid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competence")
    private Collection<Competencetranslation> competencetranslationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competence")
    private Collection<Competenceinprofile> competenceinprofileCollection;

    public Competence() {
    }

    public Competence(Integer competenceid) {
        this.competenceid = competenceid;
    }

    public Integer getCompetenceid() {
        return competenceid;
    }

    public void setCompetenceid(Integer competenceid) {
        this.competenceid = competenceid;
    }

    @XmlTransient
    public Collection<Competencetranslation> getCompetencetranslationCollection() {
        return competencetranslationCollection;
    }

    public void setCompetencetranslationCollection(Collection<Competencetranslation> competencetranslationCollection) {
        this.competencetranslationCollection = competencetranslationCollection;
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
        hash += (competenceid != null ? competenceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competence)) {
            return false;
        }
        Competence other = (Competence) object;
        if ((this.competenceid == null && other.competenceid != null) || (this.competenceid != null && !this.competenceid.equals(other.competenceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.ffm.recruitsystem.model.Competence[ competenceid=" + competenceid + " ]";
    }
    
}
