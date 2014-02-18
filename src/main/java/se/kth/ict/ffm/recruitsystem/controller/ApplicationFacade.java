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
package se.kth.ict.ffm.recruitsystem.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import se.kth.ict.ffm.recruitsystem.model.entity.Application;
import se.kth.ict.ffm.recruitsystem.model.entity.Availability;
import se.kth.ict.ffm.recruitsystem.model.entity.Competenceinprofile;
import se.kth.ict.ffm.recruitsystem.model.entity.Competenceprofile;
import se.kth.ict.ffm.recruitsystem.model.entity.CompetencetranslationDTO;
import se.kth.ict.ffm.recruitsystem.model.entity.Person;
import se.kth.ict.ffm.recruitsystem.util.dto.ApplicationDTO;
import se.kth.ict.ffm.recruitsystem.util.dto.AvailabilityFromView;
import se.kth.ict.ffm.recruitsystem.util.dto.CompetenceFromView;
import se.kth.ict.ffm.recruitsystem.util.pdf.PDFBean;
import se.kth.ict.ffm.recruitsystem.view.LanguageBean;

@Stateless
public class ApplicationFacade {

    @EJB
    private LanguageBean languageBean;
    @EJB
    private PDFBean pdfBean;

    @PersistenceContext(unitName = "se.kth.ict.ffm_RecruitSystem_war_1.0-SNAPSHOTPU")
    EntityManager entityManager;

    public ApplicationFacade() {
    }

    public List<CompetencetranslationDTO> getCompetences() {
        String currentLanguage = languageBean.getCurrentLanguage();
        Query query = entityManager.createNamedQuery("Competencetranslation.findByLocale");
        query.setParameter("locale", currentLanguage);
        return query.getResultList();
    }
    
    public CompetencetranslationDTO getCompetenceTranslation(String name) {
        Query query = entityManager.createNamedQuery("Competencetranslation.findByLocaleAndName");
        query.setParameter("locale", languageBean.getCurrentLanguage());
        query.setParameter("name", name);
        return (CompetencetranslationDTO) query.getSingleResult();
    }
    
    public void submitApplication(ApplicationDTO application) {
        //If person doesn't exist, create it in data store.
        
        Person person = findPerson(application);
        if (person == null) {
            person = createPerson(application);
            entityManager.persist(person);
            //MÅSTE MAN VERKLIGEN HITTA PERSON ENTITY IGEN FÖR ATT FÅ ID?
            entityManager.flush();
            person = findPerson(application);
            System.out.println("PersonId: " + person.getPersonid());
        }
        //Create availabilities
        List<AvailabilityFromView> availabilities = application.getAvailabilities();
        AvailabilityFromView av;
        Availability avEntity;
        for (Iterator<AvailabilityFromView> availIt = availabilities.iterator();
                availIt.hasNext();) {
            av = availIt.next();
            avEntity = createAvailability(av, person);
            entityManager.persist(avEntity);
        }
        //Create a Competenceprofile
        Competenceprofile compProfile = createCompetenceprofile(person);
        entityManager.persist(compProfile);
        entityManager.flush();
        System.out.println("In submitApplication, compProfile id: " + compProfile.getCompetenceprofileid());
        //Add competences to profile
        List<CompetenceFromView> competences = application.getCompetences();
        CompetenceFromView comp;
        Competenceinprofile compEntity;
        for (Iterator<CompetenceFromView> compIt = competences.iterator();
                compIt.hasNext();) {
            comp = compIt.next();
            compEntity = createCompetenceinprofile(compProfile, comp);
            compEntity.setYearsofexperience(comp.getYearsOfExperience());
            entityManager.persist(compEntity);
        }
        //Create an Application
        Application applicationEntity = new Application();
        applicationEntity.setPersonid(person);
        applicationEntity.setApplicationdate(new Date());
        entityManager.persist(applicationEntity);

    }

    private Person findPerson(ApplicationDTO application) {
        Query query = entityManager.createNamedQuery("Person.findByAll");
        query.setParameter("name", application.getFirstname());
        query.setParameter("surname", application.getLastname());
        query.setParameter("birthdate", application.getBirthDate());
        query.setParameter("email", application.getEmail());
        Person person;
        try {
            person = (Person) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return person;
    }

    //factory method for creating person entities
    private Person createPerson(ApplicationDTO application) {
        Person person = new Person();
        person.setName(application.getFirstname());
        person.setSurname(application.getLastname());
        person.setBirthdate(application.getBirthDate());
        person.setEmail(application.getEmail());
        return person;
    }

    private Availability createAvailability(AvailabilityFromView av, Person person) {
        Availability availabilityEntity = new Availability();
        availabilityEntity.setFromdate(av.getFromDate());
        availabilityEntity.setTodate(av.getToDate());
        availabilityEntity.setUserid(person);
        return availabilityEntity;
    }

    private Competenceprofile createCompetenceprofile(Person person) {
        Competenceprofile competenceProfile = new Competenceprofile();
        competenceProfile.setPersonid(person);
        return competenceProfile;
    }

    private Competenceinprofile createCompetenceinprofile(Competenceprofile compProfile,
            CompetenceFromView comp) {
        System.out.println("Competenceprofile: " + compProfile + "\nCompetenceFromView: " + comp);
        Competenceinprofile compInProfile = new Competenceinprofile(compProfile.getCompetenceprofileid(),
                comp.getCompetenceId());
        return compInProfile;
    }

    public void downloadFile(ApplicationDTO application) {

        ByteArrayOutputStream file = pdfBean.createRegistrationPDF(application);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setHeader("Content-Disposition", "inline;filename=Application.pdf");
        response.setContentLength((int) file.size());
        ServletOutputStream sos = null;
        try {
            sos = response.getOutputStream();
            file.writeTo(sos);
            sos.flush();
        } catch (IOException err) {
            System.out.println(err.getMessage());
        } finally {
            try {
                if (sos != null) {
                    sos.close();
                    file = null;
                }
            } catch (IOException err) {
                System.out.println(err.getMessage());
            }
        }
    }
}
