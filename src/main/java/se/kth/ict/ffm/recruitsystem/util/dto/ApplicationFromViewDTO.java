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
import java.util.Date;
import java.util.List;

/**
 * An AppicationFronViewDTO instance is a data transfer object that contains
 * all the information necessary to register an application.
 */
public class ApplicationFromViewDTO implements Serializable {
    private String firstname;
    private String lastname;
    private Date birthDate;
    private String email;
    private List<CompetenceFromView> competences;
    private List<AvailabilityFromView> availabilities;

    /**
     * 
     * @param firstname
     * @param lastname
     * @param birthDate
     * @param email
     * @param competences
     * @param availabilities 
     */
    public ApplicationFromViewDTO(String firstname, String lastname, Date birthDate, 
            String email, List<CompetenceFromView> competences, List<AvailabilityFromView> availabilities) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.email = email;
        this.competences = competences;
        this.availabilities = availabilities;
    }

    /**
     * @return first name
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname 
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return last name
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname 
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return birth date
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate 
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return all competences in this application
     */
    public List<CompetenceFromView> getCompetences() {
        return competences;
    }

    /**
     * @param competences 
     */
    public void setCompetences(List<CompetenceFromView> competences) {
        this.competences = competences;
    }

    /**
     * @return all periods of availability in this application
     */
    public List<AvailabilityFromView> getAvailabilities() {
        return availabilities;
    }

    /**
     * @param availabilities 
     */
    public void setAvailabilities(List<AvailabilityFromView> availabilities) {
        this.availabilities = availabilities;
    }
}
