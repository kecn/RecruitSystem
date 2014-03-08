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
package se.kth.ict.ffm.recruitsystem.controller;

import com.itextpdf.text.DocumentException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import se.kth.ict.ffm.recruitsystem.logger.Log;
import se.kth.ict.ffm.recruitsystem.model.ApplicationOperator;
import se.kth.ict.ffm.recruitsystem.model.CompetenceOperator;
import se.kth.ict.ffm.recruitsystem.model.entity.CompetencetranslationDTO;
import se.kth.ict.ffm.recruitsystem.util.dto.ApplicationFromViewDTO;
import se.kth.ict.ffm.recruitsystem.util.pdf.PDFBean;

/**
 * ApplicationFacade is an EJB with the responsibility of starting operations
 * that have to do with applications.
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ApplicationFacade {

    @EJB
    private PDFBean pdfBean;
    @EJB
    private ApplicationOperator applicationOperator;
    @EJB
    private CompetenceOperator competenceOperator;

    /**
     * Gets all competences with their names in the current language
     * @param currentLanguage
     * @return a List with all available competences in the current language
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<CompetencetranslationDTO> getCompetences(String currentLanguage) {
        return competenceOperator.getCompetences(currentLanguage);
    }

    /**
     * Get a reference to a Competence when its name and language of name are
     * known
     * @param name of the competence that is requested
     * @param currentLanguage language the name of the competence is in
     * @return a reference (CompetencetranslationDTO) to a Competencetranslation
     * that doesn't give access to mutators
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public CompetencetranslationDTO getCompetenceTranslation(String name,
            String currentLanguage) {
        return competenceOperator.getCompetenceTranslation(name, currentLanguage);
    }

    /**
     * To submit an application.
     * @param application containing all the necessary information to register
     * the application
     */
    @Log
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void submitApplication(ApplicationFromViewDTO application) {
        //Validate all data
        applicationOperator.submitApplication(application);
    }

    /**
     * Download a PDF generated from the application that has been submitted.
     * @param application
     */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void downloadFile(ApplicationFromViewDTO application) throws DocumentException, IOException {
        
        ByteArrayOutputStream file = pdfBean.createRegistrationPDF(application);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.setHeader("Content-Disposition", "attachment;filename=Application.pdf");
        response.setContentType("application");
        response.setContentLength((int) file.size());

        ServletOutputStream sos = null;
        try {
            sos = response.getOutputStream();
            file.writeTo(sos);
            sos.flush();
        } catch (IOException err) {
            throw new IOException("Sending PDF failed!");
        } finally {
            try {
                if (sos != null) {
                    sos.close();
                    file = null;
                }
            } catch (IOException err) {
                //logg this!
                oiahosd
            }
        }
        facesContext.responseComplete();
    }
}
