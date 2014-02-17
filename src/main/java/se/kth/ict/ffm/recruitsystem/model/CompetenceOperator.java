/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.ict.ffm.recruitsystem.model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import se.kth.ict.ffm.recruitsystem.model.entity.Competenceinprofile;
import se.kth.ict.ffm.recruitsystem.model.entity.Competenceprofile;
import se.kth.ict.ffm.recruitsystem.model.entity.CompetencetranslationDTO;
import se.kth.ict.ffm.recruitsystem.model.entity.Person;
import se.kth.ict.ffm.recruitsystem.util.dto.CompetenceFromView;

@Stateless
public class CompetenceOperator {
    @PersistenceContext(unitName = "se.kth.ict.ffm_RecruitSystem_war_1.0-SNAPSHOTPU")
    EntityManager entityManager;
    
    public List<CompetencetranslationDTO> getCompetences(String currentLanguage) {
        Query query = entityManager.createNamedQuery("Competencetranslation.findByLocale");
        query.setParameter("locale", currentLanguage);
        return query.getResultList();
    }
    
    public CompetencetranslationDTO getCompetenceTranslation(String name, 
            String currentLanguage) {
        Query query = entityManager.createNamedQuery("Competencetranslation.findByLocaleAndName");
        query.setParameter("locale", currentLanguage);
        query.setParameter("name", name);
        return (CompetencetranslationDTO) query.getSingleResult();
    }    
    
    public Competenceprofile createCompetenceprofile(Person person) {
        Competenceprofile competenceProfile = new Competenceprofile();
        competenceProfile.setPersonid(person);
        return competenceProfile;
    }

    public Competenceinprofile createCompetenceinprofile(Competenceprofile compProfile,
            CompetenceFromView comp) {
        System.out.println("Competenceprofile: " + compProfile + "\nCompetenceFromView: " + comp);
        Competenceinprofile compInProfile = new Competenceinprofile(compProfile.getCompetenceprofileid(),
                comp.getCompetenceId());
        return compInProfile;
    }       
}
