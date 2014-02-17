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

import se.kth.ict.ffm.recruitsystem.util.dto.CompetenceFromView;
import se.kth.ict.ffm.recruitsystem.util.dto.AvailabilityFromView;
import se.kth.ict.ffm.recruitsystem.util.dto.ApplicationDTO;
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
import se.kth.ict.ffm.recruitsystem.model.entity.CompetencetranslationDTO;
import se.kth.ict.ffm.recruitsystem.util.DateUtil;

/**
 *
 * @author
 */
@Named("registerApplicationManager")
@SessionScoped
public class RegisterApplicationManager implements Serializable {

    @EJB
    private ApplicationFacade applicationFacade;
    @EJB
    private LanguageBean languageBean;
    private String firstname;
    private String lastname;
    private String birthDateString;
    private String email;
//    private List<String> competenceNames;
    private List<CompetencetranslationDTO> competences;
    private List<CompetenceFromView> applicantCompetences;
    private List<AvailabilityFromView> availabilities;
    private int yearsOfExperience;
    private String competenceName;
    private CompetencetranslationDTO competenceTranslation;
    private String fromDateString;
    private String toDateString;
    private boolean submitted;
    private ApplicationDTO application;

    @PostConstruct
    public void init() {
//        competenceNames = applicationFacade.getCompetences();
        competences = applicationFacade.getCompetences(languageBean.getCurrentLanguage());
        applicantCompetences = new LinkedList();
        availabilities = new LinkedList();
        submitted = false;
    }

    public void addCompetence() {
        CompetenceFromView newCompetence = new CompetenceFromView(competenceTranslation, yearsOfExperience);
        applicantCompetences.add(newCompetence);
    }

    public void addAvailability() {
        try {
            Date fromDate = DateUtil.toDate(fromDateString);
            Date toDate = DateUtil.toDate(toDateString);
            AvailabilityFromView newAvailability = new AvailabilityFromView(fromDate, toDate);
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
            application = new ApplicationDTO(
                    firstname, lastname, birthDate, email, applicantCompetences,
                    availabilities);
            applicationFacade.submitApplication(application);
            //Indicates that all needed to make PDF is available
            submitted = true;

        } catch (ParseException ex) {
            //FIX LOGGING!
            Logger.getLogger(RegisterApplicationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Collection<CompetencetranslationDTO> getCompetences() {
        return competences;
    }

    public Collection<CompetenceFromView> getApplicantCompetences() {
        return applicantCompetences;
    }

    public void setApplicantCompetences(List<CompetenceFromView> applicantCompetences) {
        this.applicantCompetences = applicantCompetences;
    }

    public Collection<AvailabilityFromView> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<AvailabilityFromView> availabilities) {
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

    public CompetencetranslationDTO getCompetenceTranslation() {
        return competenceTranslation;
    }

    public void setCompetenceTranslation(CompetencetranslationDTO competenceTranslation) {
        this.competenceTranslation = competenceTranslation;
    }

    public String getCompetenceName() {
        return competenceName;
    }

    public void setCompetenceName(String competenceName) {
        this.competenceName = competenceName;
        competenceTranslation = applicationFacade.
                getCompetenceTranslation(competenceName, languageBean.getCurrentLanguage());
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }
    
    public void createPDF(){
        applicationFacade.downloadFile(application);
    }
}
