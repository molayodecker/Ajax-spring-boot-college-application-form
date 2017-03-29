package gh.gov.moh.admissionsportal.web;

/**
 * Created by molayodecker on 08/03/2017.
 */


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import gh.gov.moh.admissionsportal.model.CertificateProgramme;
import gh.gov.moh.admissionsportal.model.Exams;
import gh.gov.moh.admissionsportal.model.School;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


public class PDFBuilder extends AbstractITextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception{
        // get data model which is passed by the Spring container
        List<CertificateProgramme> certificates = (List<CertificateProgramme>) model.get("certPDF");
        List<Exams> exams = (List<Exams>) model.get("certPDF2");
        List<School> schools = (List<School>) model.get("certPDF3");

        document.add(new Paragraph("HEALTH TRAINING INSTITUTION FORM"));

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{3.0f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);

        // write table header
        cell.setPhrase(new Phrase("Full Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Gender", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);


        // write table row data
        for (CertificateProgramme c : certificates) {
            table.addCell(c.getFullName());
            table.addCell(c.getGender());
            table.addCell(String.valueOf(c.getDate()));
        }

        // write table header
        cell.setPhrase(new Phrase("Nationality", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Martial Status", font));
        table.addCell(cell);


        // write table row data
        for (CertificateProgramme c : certificates) {
            table.addCell(c.getNationality());
            table.addCell(c.getEmail());
            table.addCell(c.getMarried());
        }

        // write table header
        cell.setPhrase(new Phrase("Telephone Number", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Language", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Guardian FullName", font));
        table.addCell(cell);

        // write table row data
        for (CertificateProgramme c : certificates) {
            table.addCell(String.valueOf(c.getTelephoneNumber()));
            table.addCell(c.getLanguage());
            table.addCell(c.getGuardianFullName());
        }

        // write table header
        cell.setPhrase(new Phrase("Guardian Telephone", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Course Offered", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("", font));
        table.addCell(cell);
        // write table row data
        for (CertificateProgramme c : certificates) {
            table.addCell(String.valueOf(c.getGuardianTelephoneNumber()));
            table.addCell(c.getCourseOffered());
            table.addCell("");
        }


        PdfPTable table1 = new PdfPTable(5);
        table1.setWidthPercentage(100.0f);
        table1.setWidths(new float[]{2.0f, 2.0f, 2.0f, 2.0f, 2.0f});
        table1.setSpacingBefore(10);

        // define font for table header row
        Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
        font1.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell1 = new PdfPCell();
        cell1.setBackgroundColor(BaseColor.BLUE);
        cell1.setPadding(5);

        // write table header
        cell1.setPhrase(new Phrase("Index Number", font1));
        table1.addCell(cell1);

        cell1.setPhrase(new Phrase("Grade", font1));
        table1.addCell(cell1);

        cell1.setPhrase(new Phrase("ExamType", font1));
        table1.addCell(cell1);

        cell1.setPhrase(new Phrase("Subject", font1));
        table1.addCell(cell1);

        cell1.setPhrase(new Phrase("Year", font1));
        table1.addCell(cell1);

        // write table row data
        for (Exams c : exams) {
            table1.addCell(String.valueOf(c.getIndexNumber()));
            table1.addCell(c.getGrade());
            table1.addCell(c.getExamType());
            table1.addCell(c.getSubject());
            table1.addCell(String.valueOf(c.getGradeYear()));
        }

        PdfPTable table2 = new PdfPTable(2);
        table2.setWidthPercentage(100.0f);
        table2.setWidths(new float[]{2.0f, 2.0f});
        table2.setSpacingBefore(10);

        // define font for table header row
        Font font2 = FontFactory.getFont(FontFactory.HELVETICA);
        font2.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell2 = new PdfPCell();
        cell2.setBackgroundColor(BaseColor.BLUE);
        cell2.setPadding(5);

        // write table header
        cell2.setPhrase(new Phrase("Program Type", font1));
        table2.addCell(cell2);

        cell2.setPhrase(new Phrase("Choice Of School", font1));
        table2.addCell(cell2);

        // write table row data
        for (School c : schools) {
            table2.addCell(c.getProgramChoice());
            table2.addCell(c.getSchoolChoice());
        }

        document.add(table);
        document.add(table1);
        document.add(table2);

    }


}

