package it.tino.restmovieapp.export;

import it.tino.restmovieapp.error.MovieAppException;
import it.tino.restmovieapp.user.User;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.pdf.JRPdfExporter;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PdfGenerator {

    public static byte[] generateUserPdf(User user) throws JRException {
        return generatePdf(Collections.singleton(user), "/pdf_report_templates/user.jrxml");
    }

    /**
     * Generates a PDF file using some objects to fill the existing placeholders.
     * @param beans The Java objects which getters are used to fill the Jersey
     *              Report template's fields.
     * @param templatePath The name of the file placed in the resources' folder. It must
     *                     match the name of the fields of the java beans.
     * @return The PDF file converted to an array of bytes.
     * @throws JRException In case of errors parsing and generating the PDF file.
     */
    private static <T> byte[] generatePdf(Collection<T> beans, String templatePath) throws JRException {
        InputStream templateStream = PdfGenerator.class.getResourceAsStream(templatePath);
        if (templateStream == null) {
            throw new MovieAppException("Could not generate the Jersey Report. Template not found: " + templatePath);
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(templateStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(beans);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CollectionBeanParam", dataSource);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfOutputStream));
        exporter.exportReport();

        return pdfOutputStream.toByteArray();
    }
}
