/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.ict.ffm.recruitsystem.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author
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
