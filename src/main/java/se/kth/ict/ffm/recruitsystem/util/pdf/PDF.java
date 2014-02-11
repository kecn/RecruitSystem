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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import javax.ejb.EJB;
import se.kth.ict.ffm.recruitsystem.controller.LanguageBean;

import se.kth.ict.ffm.recruitsystem.view.ApplicationDTO;
import se.kth.ict.ffm.recruitsystem.view.Competence;

public class PDF {

    @EJB
    private LanguageBean lb;

    public File createRegistrationPDF(ApplicationDTO applicationDTO ) {
         System.out.println("GALEN OUTPUT!!!  "+lb.getCurrentLanguage());
        Document document = new Document();
        document.setPageSize(new Rectangle(PageSize.LETTER));
        Font Heading1 = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.UNDERLINE);
        Font Heading2 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
        Font regularText = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
        File file = null;
        float listIndentation = 15;

       
        String currProperty = "language_" + lb.getCurrentLanguage() + ".properties";

        Properties prop = new Properties();
        InputStream input;

        try {
            input = new FileInputStream("src\\main\\resources\\se\\kth\\ict\\ffm\\recruitsystem\\properties\\" + currProperty);
            prop.load(input);

            file = new File("Application.pdf");
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            document.add(new Paragraph("Application Document", Heading1));

            //Firstname
            Paragraph p = new Paragraph(prop.getProperty("First_name") + ": ", Heading2);
            p.setFont(regularText);
            p.add(" " + applicationDTO.getFirstname());
            document.add(p);

            p = new Paragraph(": ", Heading2);
            p.setFont(regularText);
            p.add(" " + applicationDTO.getLastname());
            document.add(p);

            document.add(new Paragraph("Competences     Years of experience: ", Heading2));
            for (Competence competence : applicationDTO.getCompetences()) {
                p = new Paragraph();

                p.setIndentationLeft(listIndentation);
                p.setFont(regularText);
                p.add(competence.getCompetenceName());
                p.add("     ");
                p.add(Integer.toString(competence.getYearsOfExperience()));

                document.add(p);
            }

            document.close();
        } catch (Exception ex) {
            System.out.println("Fail!");
        }
        return file;
    }
}
