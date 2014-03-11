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
public class CompetencetranslationTest {
    
    public CompetencetranslationTest() {
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
     * Test of equals method, of class Competencetranslation.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = (Object) new Competencetranslation("en", 1);
        Competencetranslation instance = new Competencetranslation("en", 1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        object = (Object) new Competencetranslation("se", 1);
        expResult = false;
        result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Competencetranslation.
     * Should return name of Competencetranslation
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Competencetranslation instance = new Competencetranslation();
        String expResult = "java";
        instance.setName(expResult);
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
