/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.ict.ffm.recruitsystem.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import se.kth.ict.ffm.recruitsystem.model.entity.Application;
import se.kth.ict.ffm.recruitsystem.model.entity.Competence;
import se.kth.ict.ffm.recruitsystem.model.entity.Competenceinapplication;
import se.kth.ict.ffm.recruitsystem.model.entity.CompetencetranslationDTO;

import se.kth.ict.ffm.recruitsystem.util.dto.ApplicationFromViewDTO;
import se.kth.ict.ffm.recruitsystem.util.dto.CompetenceFromView;

/**
 * Enterprise bean that provides methods that have to do with persisted and 
 * persisting Competence entities.
 */
@Stateless
public class CompetenceOperator {
    @PersistenceContext(unitName = "se.kth.ict.ffm_RecruitSystem_war_1.0-SNAPSHOTPU")
    EntityManager entityManager;
    
    /**
     * Gets all competences available as a collection of CompetencetranslationDTO
     * references in the current language.
     * @param currentLanguage
     * @return all available competences named in current language
     */
    public List<CompetencetranslationDTO> getCompetences(String currentLanguage) {
        Query query = entityManager.createNamedQuery("Competencetranslation.findByLocale");
        query.setParameter("locale", currentLanguage);
        return query.getResultList();
    }
    
    /**
     * Gets CompetencetranslationDTO reference to a CompetenceTranslation with
     * a certain name in a certain language.
     * @param name
     * @param currentLanguage
     * @return reference to a Competence that doesn't give access to mutators
     */
    public CompetencetranslationDTO getCompetenceTranslation(String name, 
            String currentLanguage) {
        Query query = entityManager.createNamedQuery("Competencetranslation.findByLocaleAndName");
        query.setParameter("locale", currentLanguage);
        query.setParameter("name", name);
        return (CompetencetranslationDTO) query.getSingleResult();
    }    

    /**
     * Creates Competenceinapplication entities and their relations to an Application
     * entity according to information sent in an ApplicationFromViewDTO
     * @param application information passed in
     * @param applicationEntity the Application entity that the competences will
     * have a relation to
     */
    public void createAndAddCompetences(ApplicationFromViewDTO application, Application applicationEntity) {
        List<CompetenceFromView> comps = application.getCompetences();
        CompetenceFromView comp;
        Collection<Competenceinapplication> competenceinapplicationCollection
                = applicationEntity.getCompetenceinapplicationCollection();
        Competenceinapplication competenceinapplicationEntity;
        Competence competence;

        //Iterate through all the competences in ApplicationFromViewDTO
        for (Iterator<CompetenceFromView> iterator = comps.iterator();
                iterator.hasNext();) {
            comp = iterator.next();
            //create the Competenceinapplication entity
            competenceinapplicationEntity = new Competenceinapplication();
            competenceinapplicationEntity.setYearsofexperience(comp.getYearsOfExperience());
            competence = entityManager.find(Competence.class, comp.getCompetenceId());
            //add Competence to Competenceinapplication
            competenceinapplicationEntity.setCompetenceid(competence);
            //add Competenceinapplication to Competence
            competence.getCompetenceinapplicationCollection().add(competenceinapplicationEntity);
            //add application to Competenceinapplication
            competenceinapplicationEntity.setApplicationid(applicationEntity);
            //add Competenceinapplication to application
            competenceinapplicationCollection.add(competenceinapplicationEntity);
        }
    }

}
