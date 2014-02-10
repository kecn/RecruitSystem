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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import se.kth.ict.ffm.recruitsystem.view.ApplicationDTO;

public class PDF {

    public void createPDF(ApplicationDTO applicationDTO) throws FileNotFoundException {
        Document document = new Document();
        document.setPageSize(new Rectangle(PageSize.LETTER));
        Font Heading1 = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
        Font Heading2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font reguarText = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

        try {
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("Application.pdf"));
            document.open();

            document.add(new Paragraph("Application Document", Heading1));
            Paragraph paragraph = new Paragraph("Firstname: ", Heading2);
            paragraph.setFont(reguarText);
            paragraph.add(applicationDTO.getFirstname());
            document.add(new Paragraph(applicationDTO.getFirstname(), Heading2));
            document.add(new Paragraph("Lastname: " + applicationDTO.getLastname(), Heading2));
            

            document.close();
        } catch (Exception ex) {
            System.out.println("Fail!");
        }
    }
}
