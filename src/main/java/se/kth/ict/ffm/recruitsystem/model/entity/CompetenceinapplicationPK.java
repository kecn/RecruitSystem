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

/**
 *
 * @author
 */
@Embeddable
public class CompetenceinapplicationPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "competenceid")
    private int competenceid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "applicationid")
    private int applicationid;

    public CompetenceinapplicationPK() {
    }

    public CompetenceinapplicationPK(int competenceid, int applicationid) {
        this.competenceid = competenceid;
        this.applicationid = applicationid;
    }

    public int getCompetenceid() {
        return competenceid;
    }

    public void setCompetenceid(int competenceid) {
        this.competenceid = competenceid;
    }

    public int getApplicationid() {
        return applicationid;
    }

    public void setApplicationid(int applicationid) {
        this.applicationid = applicationid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) competenceid;
        hash += (int) applicationid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompetenceinapplicationPK)) {
            return false;
        }
        CompetenceinapplicationPK other = (CompetenceinapplicationPK) object;
        if (this.competenceid != other.competenceid) {
            return false;
        }
        if (this.applicationid != other.applicationid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.ffm.recruitsystem.model.entity.CompetenceinapplicationPK[ competenceid=" + competenceid + ", applicationid=" + applicationid + " ]";
    }
    
}
