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
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Composite key of Competencetranslation entity
 */
@Embeddable
public class CompetencetranslationPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "locale")
    private String locale;
    @Basic(optional = false)
    @NotNull
    @Column(name = "competenceid")
    private int competenceid;

    public CompetencetranslationPK() {
    }

    public CompetencetranslationPK(String locale, int competenceid) {
        this.locale = locale;
        this.competenceid = competenceid;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public int getCompetenceid() {
        return competenceid;
    }

    public void setCompetenceid(int competenceid) {
        this.competenceid = competenceid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locale != null ? locale.hashCode() : 0);
        hash += (int) competenceid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompetencetranslationPK)) {
            return false;
        }
        CompetencetranslationPK other = (CompetencetranslationPK) object;
        if ((this.locale == null && other.locale != null) || (this.locale != null && !this.locale.equals(other.locale))) {
            return false;
        }
        if (this.competenceid != other.competenceid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.ffm.recruitsystem.model.entity.CompetencetranslationPK[ locale=" + locale + ", competenceid=" + competenceid + " ]";
    }
    
}
