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

package se.kth.ict.ffm.recruitsystem.model.entity;

import java.util.Collection;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author
 */
public class PersonTest {
    
    public PersonTest() {
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
     * Test of getApplicationCollection method, of class Person.
     * Test that it never returns null
     */
    @Test
    public void testGetApplicationCollection() {
        System.out.println("getApplicationCollection");
        Person instance = new Person();
        Collection<Application> result = instance.getApplicationCollection();
        assertNotNull(result);
        instance.setApplicationCollection(null);
        result = instance.getApplicationCollection();
        assertNotNull(result);
    }

    /**
     * Test of equals method, of class Person.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = (Object) new Person(1);
        Person instance = new Person(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        object = (Object) new Person(2);
        expResult = false;
        result = instance.equals(object);
        assertEquals(expResult, result);
    }    
}
