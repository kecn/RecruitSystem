/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.ict.ffm.recruitsystem.model;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import se.kth.ict.ffm.recruitsystem.model.entity.Application;
import se.kth.ict.ffm.recruitsystem.model.entity.Availability;
import se.kth.ict.ffm.recruitsystem.model.entity.Competenceinprofile;
import se.kth.ict.ffm.recruitsystem.model.entity.Competenceprofile;
import se.kth.ict.ffm.recruitsystem.model.entity.Person;
import se.kth.ict.ffm.recruitsystem.util.dto.ApplicationDTO;
import se.kth.ict.ffm.recruitsystem.util.dto.AvailabilityFromView;
import se.kth.ict.ffm.recruitsystem.util.dto.CompetenceFromView;

@Stateless
public class ApplicationOperator {
    @PersistenceContext(unitName = "se.kth.ict.ffm_RecruitSystem_war_1.0-SNAPSHOTPU")
    EntityManager entityManager;
    
    @EJB
    CompetenceOperator competenceOperator;
    
    public void submitApplication(ApplicationDTO application) {
        //If person doesn't exist, create it in data store.
        Person person = findPerson(application);
        if (null == person) {
            person = createPerson(application);
//            person = findPerson(application);
//            System.out.println("PersonId: " + person.getPersonid());
        }
//        entityManager.persist(person);
//        entityManager.flush();
        //Create availabilities
        List<AvailabilityFromView> availabilities = application.getAvailabilities();
        AvailabilityFromView av;
        Availability avEntity;
        for (Iterator<AvailabilityFromView> availIt = availabilities.iterator();
                availIt.hasNext();) {
            av = availIt.next();
            avEntity = createAvailability(av, person);
//            entityManager.persist(avEntity);
        }
        //Create a Competenceprofile
        Competenceprofile compProfile = competenceOperator.createCompetenceprofile(person);
//        entityManager.persist(compProfile);
//        entityManager.flush();
//        System.out.println("In submitApplication, compProfile id: " + compProfile.getCompetenceprofileid());
        //Add competences to profile
        
        
//        List<CompetenceFromView> competences = application.getCompetences();
//        CompetenceFromView comp;
//        Competenceinprofile compEntity;
//        for (Iterator<CompetenceFromView> compIt = competences.iterator();
//                compIt.hasNext();) {
//            comp = compIt.next();
//            compEntity = competenceOperator.createCompetenceinprofile(compProfile, comp);
//            compEntity.setYearsofexperience(comp.getYearsOfExperience());
////            entityManager.persist(compEntity);
//        }
        competenceOperator.createManyCompetenceinprofile(compProfile, application);
        
        //Create an Application
        Application applicationEntity = new Application();
        applicationEntity.setPersonid(person);
        applicationEntity.setApplicationdate(new Date());
//        entityManager.persist(applicationEntity);

        entityManager.persist(person);
        entityManager.flush();
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
}
