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
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import mock.MockApplicationFromViewDTO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import se.kth.ict.ffm.recruitsystem.model.entity.Application;
import se.kth.ict.ffm.recruitsystem.model.entity.Person;
import se.kth.ict.ffm.recruitsystem.util.dto.ApplicationFromViewDTO;
import se.kth.ict.ffm.recruitsystem.util.dto.AvailabilityFromView;
import testutil.TestUtil;

/**
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ApplicationOperatorTest {
    
    public ApplicationOperatorTest() {
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
     * Test of submitApplication method, of class ApplicationOperator.
     */
    @Test
    public void testSubmitApplication() throws Exception {
        CompetenceOperator compOp = Mockito.mock(CompetenceOperator.class);
        EntityManager mockEm = Mockito.mock(EntityManager.class);
        //we need to spy on the instance, because we want to ignore one of its
        //void methods
        ApplicationOperator instance = Mockito.spy(new ApplicationOperator());
        //Set entityManager
        try {
            TestUtil.setPrivateField(ApplicationOperator.class, instance, "entityManager", mockEm);
            TestUtil.setPrivateField(ApplicationOperator.class, instance, "competenceOperator", compOp);
        } catch (Exception ex) {
            System.out.println("Problem setting private field");
            ex.printStackTrace();
        }   
        //mock input parameter
        ApplicationFromViewDTO mockAppFromView = new MockApplicationFromViewDTO();
        Person person = new Person(1, "Bruce", "Wayne", new Date(), "bruce@wayne.com");
        Mockito.doReturn(person).when(instance).findPerson(mockAppFromView);
        //run method
        instance.submitApplication(mockAppFromView);
        //Verify that the expected calls were done
        Mockito.verify(instance, Mockito.never()).createPerson(mockAppFromView);
        Mockito.verify(instance).findPerson(mockAppFromView);
        Mockito.verify(instance).createAndAddApplication(person);
    }
    
    /**
     * Test of findPerson method, of class ApplicationOperator.
     */
    @Test
    public void testFindPerson() throws Exception {
//        System.out.println("findPerson");
        EntityManager mockEm = Mockito.mock(EntityManager.class);
        Query mockQuery = Mockito.mock(Query.class);
        Person mockPerson = Mockito.mock(Person.class);
        //mock input parameter
        ApplicationFromViewDTO mockAppFromView = Mockito.mock(ApplicationFromViewDTO.class);
        ApplicationOperator instance = new ApplicationOperator();
        //Set entityManager
        try {
            TestUtil.setPrivateField(ApplicationOperator.class, instance, "entityManager", mockEm);
        } catch (Exception ex) {
            System.out.println("Problem setting private field");
            ex.printStackTrace();
        }
        //control behaviour of findPerson during test
        Mockito.when(mockEm.createNamedQuery("Person.findByAll")).
                thenReturn(mockQuery);
        Mockito.when(mockQuery.getSingleResult()).
                thenReturn(mockPerson);
        instance.findPerson(mockAppFromView);
    }

    /**
     * Test of createAndAddAvailabilities method, of class ApplicationOperator.
     * Dependent on Application entity
     */
    @Test
    public void testCreateAndAddAvailabilities() throws Exception {
        System.out.println("createAndAddAvailabilities");
        ApplicationFromViewDTO appFromView = Mockito.mock(ApplicationFromViewDTO.class);
        Application appEntity = new Application();
        ApplicationOperator instance = new ApplicationOperator();
        ArrayList<AvailabilityFromView> availabilitiesFromView = new ArrayList();
        Date date = new Date();
        availabilitiesFromView.add(new AvailabilityFromView(date, date));
        appFromView.setAvailabilities(availabilitiesFromView);
        instance.createAndAddAvailabilities(appFromView, appEntity);
    }    
}
