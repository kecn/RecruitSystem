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

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import se.kth.ict.ffm.recruitsystem.util.dto.ApplicationFromViewDTO;
import se.kth.ict.ffm.recruitsystem.util.dto.AvailabilityFromView;
import se.kth.ict.ffm.recruitsystem.util.dto.CompetenceFromView;
import se.kth.ict.ffm.recruitsystem.view.LanguageBean;

/**
 * Creates a language specific pdf file with application information in memory
 * only and return reference to the pdf.
 */
@Stateless
public class PDFBean {

    @EJB
    private LanguageBean languageBean;
    private final Font Heading1 = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.UNDERLINE);
    private final Font Heading2 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    private final Font regularText = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
    private final float listIndentation = 15;
    private Document document;
    private ByteArrayOutputStream baosPDF;

    /**
     * Creates a language specific pdf file with application information in
     * memory only and return reference to the pdf.
     *
     * @param applicationDTO user information
     * @return the pdf reference
     */
    public ByteArrayOutputStream createRegistrationPDF(ApplicationFromViewDTO applicationDTO) throws DocumentException, IOException {
        try {
            //Get Locale
            ResourceBundle resbundle = ResourceBundle.getBundle("se.kth.ict.ffm.recruitsystem.properties.language", languageBean.getCurrentLocale());

            document = new Document();
            baosPDF = new ByteArrayOutputStream();
            PdfWriter pdfWriter = PdfWriter.getInstance(document, baosPDF);
            //Write PDF
            document.open();
            document.add(new Paragraph("Application Document", Heading1));

            Paragraph p = new Paragraph(resbundle.getString("firstName") + ": ", Heading2);
            p.setFont(regularText);
            p.add(" " + applicationDTO.getFirstname());
            document.add(p);

            p = new Paragraph(resbundle.getString("lastName") + ": ", Heading2);
            p.setFont(regularText);
            p.add(" " + applicationDTO.getLastname());
            document.add(p);

            p = new Paragraph(resbundle.getString("dateOfBirth") + ": ", Heading2);
            p.setFont(regularText);
            p.add(" " + applicationDTO.getBirthDate());
            document.add(p);

            document.add(new Paragraph(resbundle.getString("competences") + ": ", Heading2));
            for (CompetenceFromView competence : applicationDTO.getCompetences()) {
                p = new Paragraph();

                p.setIndentationLeft(listIndentation);
                p.setFont(regularText);
                p.add(competence.getName());
                p.add("     ");
                p.add(Integer.toString(competence.getYearsOfExperience()));

                document.add(p);
            }

            document.add(new Paragraph(resbundle.getString("availability") + ": ", Heading2));
            for (AvailabilityFromView availability : applicationDTO.getAvailabilities()) {
                p = new Paragraph();

                p.setIndentationLeft(listIndentation);
                p.setFont(regularText);
                p.add(availability.getFromDate().toString());
                p.add("     ");
                p.add(availability.getToDate().toString());
                document.add(p);
            }

            document.close();
            pdfWriter.flush();
            pdfWriter.close();
            baosPDF.close();
                    
        } catch (DocumentException ex) {
           throw new DocumentException("");
        } catch(IOException ex){
            throw new IOException("PDF creation failed!");
        }
        return baosPDF;
    }
}
