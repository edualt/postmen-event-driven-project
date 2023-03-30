package com.school.eventdrivenproject.utils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.school.eventdrivenproject.entities.Order;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class PDFGenerator {

    public static ByteArrayOutputStream generate(Order order) throws DocumentException, IOException {

        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(18);
        Font title = FontFactory.getFont(FontFactory.HELVETICA);
        title.setSize(26);

        document.add(new Paragraph("POSTMEN", title));
        document.add(new Paragraph("Tracking ID: " + order.getTrackingId(), font));
        document.add(new Paragraph("Address: " + order.getAddress(), font));
        document.add(new Paragraph("City: " + order.getCity(), font));
        document.add(new Paragraph("State: " + order.getState(), font));
        document.add(new Paragraph("Postal Code: " + order.getState(), font));
        document.add(new Paragraph("Ship To: " + order.getShipTo(), font));

        document.close();

        return baos;
    }
}
