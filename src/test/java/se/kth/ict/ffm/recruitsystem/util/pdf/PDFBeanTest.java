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

package se.kth.ict.ffm.recruitsystem.util.pdf;

import java.io.ByteArrayOutputStream;
import javax.ejb.embeddable.EJBContainer;
import mock.MockApplicationFromViewDTO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import se.kth.ict.ffm.recruitsystem.util.dto.ApplicationFromViewDTO;
import se.kth.ict.ffm.recruitsystem.view.LanguageBean;
import se.kth.ict.ffm.recruitsystem.view.RegisterApplicationManager;
import testutil.TestUtil;

/**
 *
 * @author
 */
public class PDFBeanTest {
    
    public PDFBeanTest() {
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
     * Test of createRegistrationPDF method, of class PDFBean.
     * Dependent on LanguageBean
     */
    @Test
    public void testCreateRegistrationPDF() throws Exception {
        System.out.println("createRegistrationPDF");
        ApplicationFromViewDTO applicationDTO = new MockApplicationFromViewDTO();
        PDFBean instance = new PDFBean();
        LanguageBean languageBean = new LanguageBean();
        //Set languageBean and applicationFacade 
        try {
            TestUtil.setPrivateField(PDFBean.class, instance, "languageBean", languageBean);
        } catch (Exception ex) {
            System.out.println("Problem setting private field");
            ex.printStackTrace();
        }        
        ByteArrayOutputStream result = instance.createRegistrationPDF(applicationDTO);
        int byteArrayMinSize = 1000;
        boolean bigEnough = result.toByteArray().length >= byteArrayMinSize;
        assertTrue(bigEnough);
    }
    
}
