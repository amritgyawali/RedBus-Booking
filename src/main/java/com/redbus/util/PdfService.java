package com.redbus.util;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.redbus.user.payload.BookingDetailsDto;
import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

@Service
public class PdfService {
    public byte[] generatePdf(BookingDetailsDto bookingDetails) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PdfWriter writer = new PdfWriter(outputStream);
             PdfDocument pdfDocument = new PdfDocument(writer)) {

            try (Document document = new Document(pdfDocument)) {
                document.add(new Paragraph("Booking Details"));
                document.add(new Paragraph("Booking ID: " + bookingDetails.getBookingId()));
                document.add(new Paragraph("Bus Company: " + bookingDetails.getBusCompany()));
                document.add(new Paragraph("From: " + bookingDetails.getFrom()));
                document.add(new Paragraph("To: " + bookingDetails.getTo()));
                document.add(new Paragraph("First Name: " + bookingDetails.getFirstName()));
                document.add(new Paragraph("Last Name: " + bookingDetails.getLastName()));
                document.add(new Paragraph("Email: " + bookingDetails.getEmail()));
                document.add(new Paragraph("Mobile: " + bookingDetails.getMobile()));
                document.add(new Paragraph("Price: " + bookingDetails.getPrice()));
            }
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}