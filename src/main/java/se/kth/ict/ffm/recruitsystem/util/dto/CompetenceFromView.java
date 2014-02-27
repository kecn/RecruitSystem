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

package se.kth.ict.ffm.recruitsystem.util.dto;

import java.io.Serializable;
import se.kth.ict.ffm.recruitsystem.model.entity.CompetencetranslationDTO;

/**
 * CompetenceFromView instances are objects representing data of a single competence
 * and experience in that field. They are created in the view and passed inside an
 * ApplicationFromViewDTO. Since they contain a CompetencetranslationDTO they 
 * are created in a certain locale, but can be used even after a change of Locale.
 */
public class CompetenceFromView implements Serializable {
    private CompetencetranslationDTO competenceTranslation;
    private int yearsOfExperience;

    public CompetenceFromView() {
    }

    /**
     * Creates a new CompetenceFromView with competenceTranslation and yearsOfExperience.
     * @param competenceTranslation
     * @param yearsOfExperience 
     */
    public CompetenceFromView(CompetencetranslationDTO competenceTranslation, 
            int yearsOfExperience) {
        this.competenceTranslation = competenceTranslation;
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     * @return the CompetenceTranslationDTO object of this CompetenceFromView
     */
    public CompetencetranslationDTO getCompetenceTranslation() {
        return competenceTranslation;
    }

    /**
     * @param competenceTranslation 
     */
    public void setCompetenceTranslation(CompetencetranslationDTO competenceTranslation) {
        this.competenceTranslation = competenceTranslation;
    }

    /**
     * @return years of experience with this competence
     */
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    /**
     * @param yearsOfExperience 
     */
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     * @return name of this competenceTranslation in the language of the Locale
     * that was selected when this CompetenceFromView was created
     */
    public String getName() {
        return competenceTranslation.getName();
    }
    
    /**
     * @return Locale that was selected when this CompetenceFromView was created
     */
    public String getLocale() {
        return competenceTranslation.getLocale();
    }
    
    /**
     * @return id of the Competence entity that the CompetenceTranslationDTO is a reference to
     */
    public int getCompetenceId() {
        return competenceTranslation.getCompetenceId();
    }
}
