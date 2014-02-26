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
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author
 */
@Entity
@Table(name = "roletranslation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roletranslation.findAll", query = "SELECT r FROM Roletranslation r"),
    @NamedQuery(name = "Roletranslation.findByLocale", query = "SELECT r FROM Roletranslation r WHERE r.roletranslationPK.locale = :locale"),
    @NamedQuery(name = "Roletranslation.findByName", query = "SELECT r FROM Roletranslation r WHERE r.name = :name"),
    @NamedQuery(name = "Roletranslation.findByRoleid", query = "SELECT r FROM Roletranslation r WHERE r.roletranslationPK.roleid = :roleid")})
public class Roletranslation implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RoletranslationPK roletranslationPK;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "roleid", referencedColumnName = "roleid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Role role;

    public Roletranslation() {
    }

    public Roletranslation(RoletranslationPK roletranslationPK) {
        this.roletranslationPK = roletranslationPK;
    }

    public Roletranslation(String locale, String roleid) {
        this.roletranslationPK = new RoletranslationPK(locale, roleid);
    }

    public RoletranslationPK getRoletranslationPK() {
        return roletranslationPK;
    }

    public void setRoletranslationPK(RoletranslationPK roletranslationPK) {
        this.roletranslationPK = roletranslationPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roletranslationPK != null ? roletranslationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roletranslation)) {
            return false;
        }
        Roletranslation other = (Roletranslation) object;
        if ((this.roletranslationPK == null && other.roletranslationPK != null) || (this.roletranslationPK != null && !this.roletranslationPK.equals(other.roletranslationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.ffm.recruitsystem.model.entity.Roletranslation[ roletranslationPK=" + roletranslationPK + " ]";
    }
    
}
