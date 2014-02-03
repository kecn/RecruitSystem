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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        return "se.kth.ict.ffm.recruitsystem.model.Competenceprofile[ competenceprofileid=" + competenceprofileid + " ]";
    }
    
}
