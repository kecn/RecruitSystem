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

package se.kth.ict.ffm.recruitsystem.view;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import se.kth.ict.ffm.recruitsystem.controller.ApplicationFacade;
import se.kth.ict.ffm.recruitsystem.util.DateUtil;



/**
 *
 * @author
 */

@Named("registerApplicationManager")
@SessionScoped
public class RegisterApplicationManager implements Serializable{
    @EJB
    private ApplicationFacade applicationFacade;
    private String firstname;
    private String lastname;
    private String birthDateString;
    private String email;
    private List<String> competences;
    private List<Competence> applicantCompetences;
    private List<Availability> availabilities;
    private int yearsOfExperience;
    private String competenceName;
    private String fromDateString;
    private String toDateString;
    

    @PostConstruct
    public void init() {
        competences = applicationFacade.getCompetences();
        applicantCompetences = new LinkedList();
        availabilities = new LinkedList();
    }
    
    public void addCompetence() {
        Competence newCompetence = new Competence(competenceName, yearsOfExperience);
        applicantCompetences.add(newCompetence);
    }
    
    public void addAvailability() { 
        try {
            Date fromDate = DateUtil.toDate(fromDateString);
            Date toDate = DateUtil.toDate(toDateString);
            Availability newAvailability = new Availability(fromDate, toDate);
            availabilities.add(newAvailability);
        } catch (ParseException ex) {
            //FIX LOGGING!
            Logger.getLogger(RegisterApplicationManager.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void submitApplication() {
        Date birthDate;
        try {
            birthDate = DateUtil.toDate(birthDateString);
            ApplicationDTO application = new ApplicationDTO(
            firstname, lastname, birthDate, email, applicantCompetences, 
            availabilities);
            applicationFacade.submitApplication(application);
        } catch (ParseException ex) {
            //FIX LOGGING!
            Logger.getLogger(RegisterApplicationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Collection<String> getCompetences() {
        return competences;
    }

    public void setCompetences(List<String> competences) {
        this.competences = competences;
    }

    public Collection<Competence> getApplicantCompetences() {
        return applicantCompetences;
    }

    public void setApplicantCompetences(List<Competence> applicantCompetences) {
        this.applicantCompetences = applicantCompetences;
    }

    public Collection<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
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

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    public String getBirthDateString() {
        return birthDateString;
    }

    public void setBirthDateString(String birthDateString) {
        this.birthDateString = birthDateString;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getCompetenceName() {
        return competenceName;
    }

    public void setCompetenceName(String competenceName) {
        this.competenceName = competenceName;
    }

    public String getFromDateString() {
        return fromDateString;
    }

    public void setFromDateString(String fromDateString) {
        this.fromDateString = fromDateString;
    }

    public String getToDateString() {
        return toDateString;
    }

    public void setToDateString(String toDateString) {
        this.toDateString = toDateString;
    }

}
