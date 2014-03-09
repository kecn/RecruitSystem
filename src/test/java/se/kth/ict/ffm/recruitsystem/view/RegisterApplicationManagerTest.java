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
import java.util.Collection;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import se.kth.ict.ffm.recruitsystem.model.entity.Competencetranslation;
import se.kth.ict.ffm.recruitsystem.model.entity.CompetencetranslationDTO;
import se.kth.ict.ffm.recruitsystem.util.dto.AvailabilityFromView;
import se.kth.ict.ffm.recruitsystem.util.dto.CompetenceFromView;

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

//    /**
//     * Test of init method, of class RegisterApplicationManager.
//     */
//    @Test
//    public void testInit() {
//        System.out.println("init");
//        RegisterApplicationManager instance = new RegisterApplicationManager();
//        instance.init();
//        assertFalse(instance.isSubmitted());
//        assertNotNull(instance.getApplicantCompetences());
//        assertNotNull(instance.getAvailabilities());
//    }

    /**
     * Test of addCompetence method, of class RegisterApplicationManager.
     */
    @Test
    public void testAddCompetence() {
        System.out.println("addCompetence");
        RegisterApplicationManager instance = new RegisterApplicationManager();
        //set needed fields competenceTranslation, yearsOfExperience and applicantCompetences
        instance.setCompetenceTranslation((CompetencetranslationDTO) 
                new Competencetranslation("java", 1));
        instance.setYearsOfExperience(1);
        instance.setApplicantCompetences(new ArrayList<CompetenceFromView>());
        //test method
        instance.addCompetence();
    }

    /**
     * Test of addAvailability method, of class RegisterApplicationManager.
     * Dependent on DateUtil using a certain date format
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
     */
//    @Test
//    public void testSubmitApplication() {
//        System.out.println("submitApplication");
//        RegisterApplicationManager instance = new RegisterApplicationManager();
//        instance.submitApplication();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }


//    /**
//     * Test of setCompetenceName method, of class RegisterApplicationManager.
//     */
//    @Test
//    public void testSetCompetenceName() {
//        System.out.println("setCompetenceName");
//        String competenceName = "";
//        RegisterApplicationManager instance = new RegisterApplicationManager();
//        instance.setCompetenceName(competenceName);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//
//    /**
//     * Test of createPDF method, of class RegisterApplicationManager.
//     */
//    @Test
//    public void testCreatePDF() {
//        System.out.println("createPDF");
//        RegisterApplicationManager instance = new RegisterApplicationManager();
//        instance.createPDF();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
