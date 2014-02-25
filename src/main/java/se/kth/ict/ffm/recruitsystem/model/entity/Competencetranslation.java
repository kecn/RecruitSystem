/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.ict.ffm.recruitsystem.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author
 */
@Entity
@Table(name = "competencetranslation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Competencetranslation.findAll", query = "SELECT c FROM Competencetranslation c"),
    @NamedQuery(name = "Competencetranslation.findByLocale", query = "SELECT c FROM Competencetranslation c WHERE c.competencetranslationPK.locale = :locale"),
    @NamedQuery(name = "Competencetranslation.findByName", query = "SELECT c FROM Competencetranslation c WHERE c.name = :name"),
    @NamedQuery(name = "Competencetranslation.findByCompetenceid", query = "SELECT c FROM Competencetranslation c WHERE c.competencetranslationPK.competenceid = :competenceid"),
    @NamedQuery(name = "Competencetranslation.findByLocaleAndName", query = "SELECT c FROM Competencetranslation c WHERE c.competencetranslationPK.locale = :locale "
            + "AND c.name = :name")})
public class Competencetranslation implements Serializable, CompetencetranslationDTO {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompetencetranslationPK competencetranslationPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "competenceid", referencedColumnName = "competenceid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Competence competence;

    public Competencetranslation() {
    }

    public Competencetranslation(CompetencetranslationPK competencetranslationPK) {
        this.competencetranslationPK = competencetranslationPK;
    }

    public Competencetranslation(CompetencetranslationPK competencetranslationPK, String name) {
        this.competencetranslationPK = competencetranslationPK;
        this.name = name;
    }

    public Competencetranslation(String locale, int competenceid) {
        this.competencetranslationPK = new CompetencetranslationPK(locale, competenceid);
    }

    public int getCompetenceId() {
        return competencetranslationPK.getCompetenceid();
    }    
    
    public CompetencetranslationPK getCompetencetranslationPK() {
        return competencetranslationPK;
    }

    public void setCompetencetranslationPK(CompetencetranslationPK competencetranslationPK) {
        this.competencetranslationPK = competencetranslationPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }
    
    public String getLocale() {
        return competencetranslationPK.getLocale();
    }    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (competencetranslationPK != null ? competencetranslationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competencetranslation)) {
            return false;
        }
        Competencetranslation other = (Competencetranslation) object;
        if ((this.competencetranslationPK == null && other.competencetranslationPK != null) || (this.competencetranslationPK != null && !this.competencetranslationPK.equals(other.competencetranslationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "se.kth.ict.ffm.recruitsystem.model.entity.Competencetranslation[ competencetranslationPK=" + competencetranslationPK + " ]";
        return name;
    }
    
}
