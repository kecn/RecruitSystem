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

import se.kth.ict.ffm.recruitsystem.model.entity.CompetencetranslationDTO;

/**
 *
 * @author
 */
public class CompetenceFromView {
    private CompetencetranslationDTO competenceTranslation;
    private int yearsOfExperience;

    public CompetenceFromView() {
    }

    public CompetenceFromView(CompetencetranslationDTO competenceTranslation, 
            int yearsOfExperience) {
        this.competenceTranslation = competenceTranslation;
        this.yearsOfExperience = yearsOfExperience;
    }

    public CompetencetranslationDTO getCompetenceTranslation() {
        return competenceTranslation;
    }

    public void setCompetenceTranslation(CompetencetranslationDTO competenceTranslation) {
        this.competenceTranslation = competenceTranslation;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
    
    public String getName() {
        return competenceTranslation.getName();
    }
    public String getLocale() {
        return competenceTranslation.getLocale();
    }
    public int getCompetenceId() {
        return competenceTranslation.getCompetenceId();
    }
}
