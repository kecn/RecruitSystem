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

import java.util.Date;
import java.util.List;
import se.kth.ict.ffm.recruitsystem.util.validation.ValidDate;
import se.kth.ict.ffm.recruitsystem.util.validation.ValidEmail;
import se.kth.ict.ffm.recruitsystem.util.validation.ValidName;

/**
 *
 * @author
 */
public class ApplicationDTO {
    @ValidName
    private String firstname;
    @ValidName
    private String lastname;
    @ValidEmail    
    private String email;
    @ValidDate    
    private Date birthDate;    
    private List<CompetenceFromView> competences;
    private List<AvailabilityFromView> availabilities;

    public ApplicationDTO(String firstname, String lastname, Date birthDate, 
            String email, List<CompetenceFromView> competences, List<AvailabilityFromView> availabilities) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.email = email;
        this.competences = competences;
        this.availabilities = availabilities;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CompetenceFromView> getCompetences() {
        return competences;
    }

    public void setCompetences(List<CompetenceFromView> competences) {
        this.competences = competences;
    }

    public List<AvailabilityFromView> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<AvailabilityFromView> availabilities) {
        this.availabilities = availabilities;
    }
}
