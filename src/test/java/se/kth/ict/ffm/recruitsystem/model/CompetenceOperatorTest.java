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
package se.kth.ict.ffm.recruitsystem.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import se.kth.ict.ffm.recruitsystem.model.entity.Application;
import se.kth.ict.ffm.recruitsystem.model.entity.Competence;
import se.kth.ict.ffm.recruitsystem.model.entity.Competenceinapplication;
import se.kth.ict.ffm.recruitsystem.model.entity.CompetencetranslationDTO;
import se.kth.ict.ffm.recruitsystem.util.dto.ApplicationFromViewDTO;
import se.kth.ict.ffm.recruitsystem.util.dto.CompetenceFromView;
import testutil.TestUtil;

/**
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CompetenceOperatorTest {

    public CompetenceOperatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    
    /**
     * Test of getCompetences method, of class CompetenceOperator.
     */
    @Test
    public void testGetCompetences() throws Exception {
        System.out.println("getCompetences");
        String currentLanguage = "en";
        CompetenceOperator instance = new CompetenceOperator();
        EntityManager mockEm = Mockito.mock(EntityManager.class);
        //Set entityManager
        try {
            TestUtil.setPrivateField(CompetenceOperator.class, instance, "entityManager", mockEm);
        } catch (Exception ex) {
            System.out.println("Problem setting private field");
            ex.printStackTrace();
        }   
        Query mockQuery = Mockito.mock(Query.class);
        Mockito.when(mockEm.createNamedQuery("Competencetranslation.findByLocale")).
                thenReturn(mockQuery);
        Mockito.when(mockQuery.getResultList()).
                thenReturn(new ArrayList<CompetencetranslationDTO>());
        List<CompetencetranslationDTO> result = instance.getCompetences(currentLanguage);
        assertNotNull(result);
    }

    /**
     * Test of getCompetenceTranslation method, of class CompetenceOperator.
     */
    @Test
    public void testGetCompetenceTranslation() throws Exception {
        System.out.println("getCompetenceTranslation");
        String name = "java";
        String currentLanguage = "en";
        CompetenceOperator instance = new CompetenceOperator();
        EntityManager mockEm = Mockito.mock(EntityManager.class);
        //Set entityManager
        try {
            TestUtil.setPrivateField(CompetenceOperator.class, instance, "entityManager", mockEm);
        } catch (Exception ex) {
            System.out.println("Problem setting private field");
            ex.printStackTrace();
        }   
        Query mockQuery = Mockito.mock(Query.class);
        Mockito.when(mockEm.createNamedQuery("Competencetranslation.findByLocaleAndName")).
                thenReturn(mockQuery);
        CompetencetranslationDTO comptrans= Mockito.mock(CompetencetranslationDTO.class);
        Mockito.when(mockQuery.getSingleResult()).
                thenReturn(comptrans);
        CompetencetranslationDTO result = instance.getCompetenceTranslation(name, currentLanguage);
        assertEquals(comptrans, result);
    }

    /**
     * Test of createAndAddCompetences method, of class CompetenceOperator.
     */
    @Test
    public void testCreateAndAddCompetences() throws Exception {
        System.out.println("createAndAddCompetences");
        ApplicationFromViewDTO application = Mockito.mock(ApplicationFromViewDTO.class);
        Application applicationEntity = Mockito.mock(Application.class);
        CompetenceFromView compFromView = Mockito.mock(CompetenceFromView.class);
        Competenceinapplication compInApp = Mockito.mock(Competenceinapplication.class);
        Competence comp = Mockito.mock(Competence.class);
        EntityManager mockEm = Mockito.mock(EntityManager.class);
        //instance to test
        CompetenceOperator instance = new CompetenceOperator();
        //Set entityManager
        try {
            TestUtil.setPrivateField(CompetenceOperator.class, instance, "entityManager", mockEm);
        } catch (Exception ex) {
            System.out.println("Problem setting private field");
            ex.printStackTrace();
        }   
        //Create a list of CompetenceFromView and add a mock to it
        List<CompetenceFromView> compFromViewList = new ArrayList();
        compFromViewList.add(compFromView);
        //Create a list of Competenceinapplication and add a mock to it
        List<Competenceinapplication> compInAppList = new ArrayList();
        //what happens when during test
        Mockito.when(application.getCompetences()).thenReturn(compFromViewList);
        Mockito.when(applicationEntity.getCompetenceinapplicationCollection()).
                thenReturn(compInAppList);
        int compFromViewId = 1;
        Mockito.when(compFromView.getCompetenceId()).thenReturn(compFromViewId);
        Mockito.when(mockEm.find(Competence.class, compFromViewId)).
                thenReturn(comp);
        instance.createAndAddCompetences(application, applicationEntity);
        assertEquals(compInAppList.get(0), compInApp);
    }
}
