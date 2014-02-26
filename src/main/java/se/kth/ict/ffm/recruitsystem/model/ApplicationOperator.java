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
import se.kth.ict.ffm.recruitsystem.model.entity.Person;
import se.kth.ict.ffm.recruitsystem.util.dto.ApplicationFromViewDTO;
import se.kth.ict.ffm.recruitsystem.util.dto.AvailabilityFromView;

/**
 * Enterprise bean responsible for doing operations related to applications on 
 * entity model of persistence layer. 
 * 
 * Main responsibility is to persist applications.
 */
@Stateless
public class ApplicationOperator {
    @PersistenceContext(unitName = "se.kth.ict.ffm_RecruitSystem_war_1.0-SNAPSHOTPU")
    EntityManager entityManager;
    
    @EJB
    CompetenceOperator competenceOperator;
    
    /**
     * Creates an object graph with all entities needed to persist an application
     * and persists it.
     * @param application contents of application to persist
     */
    public void submitApplication(ApplicationFromViewDTO application) {
        //If person doesn't exist, create it in data store.
        Person person = findPerson(application);
        if (null == person) {
            person = createPerson(application);
        }
        
        //Create an Application
        Application applicationEntity = createAndAddApplication(person);  
        
        //Create and add availabilities
        createAndAddAvailabilities(application, applicationEntity);
 
        //Create and add competences
        competenceOperator.createAndAddCompetences(application, applicationEntity);

        entityManager.persist(person);
        entityManager.flush();
    }

    /**
     * Finds a Person entity by information in an ApplicationFromViewDTO.
     * @param application
     * @return found Person or null
     */
    public Person findPerson(ApplicationFromViewDTO application) {
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


    /**
     * Method for creating person entities according to information in an 
     * ApplicationFromViewDTO.
     * @param application
     * @return created Person entity
     */
    public Person createPerson(ApplicationFromViewDTO application) {
        Person person = new Person();
        person.setName(application.getFirstname());
        person.setSurname(application.getLastname());
        person.setBirthdate(application.getBirthDate());
        person.setEmail(application.getEmail());
        return person;
    }

    /**
     * Creates Availability entities and their relation to an Application entity
     * @param applicationDTO the information needed to create the entities
     * @param applicationEntity the Entity the new Availability entities have a
     * relation to.
     */
    public void createAndAddAvailabilities(ApplicationFromViewDTO applicationDTO, Application applicationEntity) {
        List<AvailabilityFromView> availabilities = applicationDTO.getAvailabilities();
        AvailabilityFromView av;
        Availability avEntity;
        for (Iterator<AvailabilityFromView> availIt = availabilities.iterator();
                availIt.hasNext();) {
            av = availIt.next();
            avEntity = new Availability();
            //set availability data
            avEntity.setFromdate(av.getFromDate());
            avEntity.setTodate(av.getToDate());
            //add application to availability
            avEntity.setApplicationid(applicationEntity);
            //add availability to application
            applicationEntity.getAvailabilityCollection().add(avEntity);
        }        
    } 
    
    public Application createAndAddApplication(Person person) {
        Application applicationEntity = new Application();
        applicationEntity.setPersonid(person);
        applicationEntity.setApplicationdate(new Date()); 
        person.getApplicationCollection().add(applicationEntity);
        return applicationEntity;
    }
}
