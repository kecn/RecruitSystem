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

import java.util.ArrayList;
import mock.MockApplicationFacade;
import mock.MockCompetencetranslationDTO;
import mock.MockLanguageBean;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import se.kth.ict.ffm.recruitsystem.model.entity.CompetencetranslationDTO;
import se.kth.ict.ffm.recruitsystem.util.dto.AvailabilityFromView;
import se.kth.ict.ffm.recruitsystem.util.dto.CompetenceFromView;
import testutil.TestUtil;

/**
 *
 * @author
 */
public class RegisterApplicationManagerTest {
    
    public RegisterApplicationManagerTest() {
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
     * Test of init method, of class RegisterApplicationManager.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        RegisterApplicationManager instance = new RegisterApplicationManager();
        MockLanguageBean mockLB = new MockLanguageBean();
        MockApplicationFacade mockAF = new MockApplicationFacade();
        //Set languageBean and applicationFacade 
        try {
            TestUtil.setPrivateField(RegisterApplicationManager.class, instance, "languageBean", mockLB);
            TestUtil.setPrivateField(RegisterApplicationManager.class, instance, "applicationFacade", mockAF);
        } catch (Exception ex) {
            System.out.println("Problem setting private field");
            ex.printStackTrace();
        }
        instance.init();
        assertFalse(instance.isSubmitted());
        assertNotNull(instance.getApplicantCompetences());
        assertNotNull(instance.getAvailabilities());
    }

    /**
     * Test of addCompetence method, of class RegisterApplicationManager.
     */
    @Test
    public void testAddCompetence() {
        System.out.println("addCompetence");
        RegisterApplicationManager instance = new RegisterApplicationManager();
        //set needed fields competenceTranslation, yearsOfExperience and applicantCompetences
        instance.setCompetenceTranslation((CompetencetranslationDTO) 
                new MockCompetencetranslationDTO());
        instance.setYearsOfExperience(1);
        instance.setApplicantCompetences(new ArrayList<CompetenceFromView>());
        //test method
        instance.addCompetence();
    }

    /**
     * Test of addAvailability method, of class RegisterApplicationManager.
     * Dependent on DateUtil
     */
    @Test
    public void testAddAvailability() {
        System.out.println("addAvailability");
        RegisterApplicationManager instance = new RegisterApplicationManager();
        instance.setFromDateString("770101");
        instance.setToDateString("770202");
        instance.setAvailabilities(new ArrayList<AvailabilityFromView>());
        instance.addAvailability();
    }

    /**
     * Test of submitApplication method, of class RegisterApplicationManager.
     * Dependent on DateUtil
     */
    @Test
    public void testSubmitApplication() {
        System.out.println("submitApplication");
        RegisterApplicationManager instance = new RegisterApplicationManager();
        MockApplicationFacade mockAF = new MockApplicationFacade();
        //Set languageBean and applicationFacade 
        try {
            TestUtil.setPrivateField(RegisterApplicationManager.class, instance, "applicationFacade", mockAF);
        } catch (Exception ex) {
            System.out.println("Problem setting private field");
            ex.printStackTrace();
        }    
        instance.setFirstname("Adam");
        instance.setLastname("Badam");
        instance.setBirthDateString("770101");
        instance.setEmail("mail@mail.com");
        instance.setApplicantCompetences(new ArrayList<CompetenceFromView>());
        instance.setAvailabilities(new ArrayList<AvailabilityFromView>());
        instance.submitApplication();
        assertTrue(instance.isSubmitted());
    }
}
