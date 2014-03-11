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

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import se.kth.ict.ffm.recruitsystem.util.dto.CompetenceFromView;
import se.kth.ict.ffm.recruitsystem.util.dto.AvailabilityFromView;
import se.kth.ict.ffm.recruitsystem.util.dto.ApplicationFromViewDTO;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Digits;
import se.kth.ict.ffm.recruitsystem.controller.ApplicationFacade;
import se.kth.ict.ffm.recruitsystem.exception.CompetenceInDBException;
import se.kth.ict.ffm.recruitsystem.exception.PdfCreationException;
import se.kth.ict.ffm.recruitsystem.exception.SubmitApplicationToDBException;
import se.kth.ict.ffm.recruitsystem.model.entity.CompetencetranslationDTO;
import se.kth.ict.ffm.recruitsystem.util.DateUtil;
import se.kth.ict.ffm.recruitsystem.util.validation.*;

/**
 * Backing bean for registering an application.
 *
 * The getter and setter are there primarily for JSF
 */
@Named("registerApplicationManager")
@SessionScoped
public class RegisterApplicationManager implements Serializable {

    @EJB
    private ApplicationFacade applicationFacade;
    @EJB
    private LanguageBean languageBean;
    @ValidName
    private String firstname;
    @ValidName
    private String lastname;
    @ValidDate
    private String birthDateString;
    @ValidEmail
    private String email;
    private List<CompetencetranslationDTO> competences;
    private List<CompetenceFromView> applicantCompetences;
    private List<AvailabilityFromView> availabilities;
    @Digits(integer = 2, fraction = 0)
    private int yearsOfExperience;
    private String competenceName;
    private CompetencetranslationDTO competenceTranslation;
    @ValidDate
    private String fromDateString;
    @ValidDate
    private String toDateString;
    private boolean submitted;
    private ApplicationFromViewDTO application;

    /**
     * Initialization
     */
    @PostConstruct
    public void init() {
        updateCompetenceTranslations(languageBean.getCurrentLanguage());
        applicantCompetences = new LinkedList();
        availabilities = new LinkedList();
        submitted = false;
    }

    /**
     * Updates the competences that are shown to be available. Needs to be done
     * at initialization and when language has been changed
     */
    private void updateCompetenceTranslations(@Observes String localeChangedEvent) {
        try {
            competences = applicationFacade.getCompetences(localeChangedEvent);
        } catch (CompetenceInDBException ex) {
            errorMsg("errorInDB");
        }
    }

    /**
     * Adds newCompetence to applicantCompetences
     */
    public void addCompetence() {
        CompetenceFromView newCompetence = new CompetenceFromView(competenceTranslation, yearsOfExperience);
        applicantCompetences.add(newCompetence);
    }


    /**
     * Adds availability (from fromDate to toDate) to availabilities
     */
    public void addAvailability() {
        try {
            Date fromDate = DateUtil.toDate(fromDateString);
            Date toDate = DateUtil.toDate(toDateString);
            AvailabilityFromView newAvailability = new AvailabilityFromView(fromDate, toDate);
            availabilities.add(newAvailability);
        } catch (ParseException ex) {
            errorMsg("badFormat");            
        }
    }

    /**
     * Submits the entered application.
     */
    public void submitApplication() {
        Date birthDate;
        try {
            birthDate = DateUtil.toDate(birthDateString);
            application = new ApplicationFromViewDTO(
                    firstname, lastname, birthDate, email, applicantCompetences,
                    availabilities);
            try {
                applicationFacade.submitApplication(application);
            } catch (SubmitApplicationToDBException ex) {
                errorMsg("errorInDB");
            }
            //Indicates that all needed to make PDF is available
            submitted = true;

        } catch (ParseException ex) {
           errorMsg("badFormat");            
        }
    }

    /**
     * @return translations of the available competences
     */
    public Collection<CompetencetranslationDTO> getCompetences() {
        return competences;
    }

    /**
     * @return competences the applicant has added to application
     */
    public Collection<CompetenceFromView> getApplicantCompetences() {
        return applicantCompetences;
    }

    /**
     * @param applicantCompetences
     */
    public void setApplicantCompetences(List<CompetenceFromView> applicantCompetences) {
        this.applicantCompetences = applicantCompetences;
    }

    /**
     * @return availabilities the applicant has added to application
     */
    public Collection<AvailabilityFromView> getAvailabilities() {
        return availabilities;
    }

    /**
     * @param availabilities
     */
    public void setAvailabilities(List<AvailabilityFromView> availabilities) {
        this.availabilities = availabilities;
    }

    /**
     * @return the first name that the applicant has entered
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
     * @return the last name that the applicant has entered
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastName
     */
    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    /**
     * @return the birth date that the applicant has entered
     */
    public String getBirthDateString() {
        return birthDateString;
    }

    /**
     * @param birthDateString
     */
    public void setBirthDateString(String birthDateString) {
        this.birthDateString = birthDateString;
    }

    /**
     * @return the email that the applicant has entered
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
     * @return the email that the applicant has entered
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
     * @return the "from date" that the applicant has entered for an
     * availability period
     */
    public String getFromDateString() {
        return fromDateString;
    }

    /**
     * @param fromDateString
     */
    public void setFromDateString(String fromDateString) {
        this.fromDateString = fromDateString;
    }

    /**
     * @return the "to date" that the applicant has entered for an availability
     * period
     */
    public String getToDateString() {
        return toDateString;
    }

    /**
     * @param toDateString
     */
    public void setToDateString(String toDateString) {
        this.toDateString = toDateString;
    }

    /**
     * @param competenceTranslation
     */
    public void setCompetenceTranslation(CompetencetranslationDTO competenceTranslation) {
        this.competenceTranslation = competenceTranslation;
    }

    /**
     * @return the name of the competence chosen by applicant
     */
    public String getCompetenceName() {
        return competenceName;
    }

    /**
     * Sets competenceName and corresponding CompetenceTranslationDTO
     *
     * @param competenceName
     */
    public void setCompetenceName(String competenceName) {
        this.competenceName = competenceName;
        try {
            competenceTranslation = applicationFacade.
                    getCompetenceTranslation(competenceName, languageBean.getCurrentLanguage());
        } catch (CompetenceInDBException ex) {
            errorMsg("errorDB");
        }
    }

    /**
     * @return whether the application has been submitted successfully
     */
    public boolean isSubmitted() {
        return submitted;
    }

    /**
     * @param submitted
     */
    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    /**
     * Called to generate and download a PDF file.
     */
    public void createPDF() {
        try {        
            applicationFacade.downloadFile(application);            
        } catch (PdfCreationException ex) {
            errorMsg("PDF");
        }
    }

    /**
     * Adds localized message to <h:messages> tag
     * @param msg 
     */
    private void errorMsg(String msg) {
        FacesContext fc = FacesContext.getCurrentInstance();

        ResourceBundle bundle = ResourceBundle.getBundle(
                "se.kth.ict.ffm.recruitsystem.properties.language",
                fc.getViewRoot().getLocale());

        fc.addMessage(null, new FacesMessage(bundle.getString("error") + " : " + bundle.getString(msg)));
    }
}
