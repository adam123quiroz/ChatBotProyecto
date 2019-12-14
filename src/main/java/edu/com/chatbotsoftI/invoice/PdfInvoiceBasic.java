/*
 * This example was written by Bruno Lowagie in the context of a book.
 * See http://developers.itextpdf.com/content/zugferd-future-invoicing/5-creating-pdf-invoices-basic-profile
 */
package edu.com.chatbotsoftI.invoice;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import com.itextpdf.kernel.pdf.*;
import edu.com.chatbotsoftI.invoice.*;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

/**
 * Reads invoice data from a test database and creates ZUGFeRD invoices
 * (Basic profile).
 * @author Bruno Lowagie
 */
public class PdfInvoiceBasic {

//    public void createPdf(OutputStream outputStream) {
//
//
//
//        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outputStream));
//        Document layoutDocument = new Document(pdfDocument);
//
//        // title
//        addTitle(layoutDocument);
//
//        // customer reference information
//        addCustomerReference(layoutDocument);
//        addTable(layoutDocument, Arrays.asList(
//                new Article(1, "Envelopes",2000, 1.70),
//                new Article(2, "Voucher Book", 50, 41)));
//
//        // articles
//        layoutDocument.close();
//    }
//
//    public static void addTitle(Document layoutDocument)
//    {
//        layoutDocument.add(new Paragraph("RETAIL INVOICE").setBold().setUnderline().setTextAlignment(TextAlignment.CENTER));
//    }
//
//    public static void addCustomerReference(Document layoutDocument)
//    {
//        layoutDocument.add(new Paragraph("M/s Indian Convent School").setTextAlignment(TextAlignment.LEFT).setMultipliedLeading(0.2f));
//        layoutDocument.add(new Paragraph("y Pocket-3, Sector-24, Rohini Delhi-110085").setMultipliedLeading(.2f));
//        layoutDocument.add(new Paragraph("b 011-64660271").setMultipliedLeading(.2f));
//    }
//
//    public void addTable(Document layoutDocument, List<Article> articleList)
//    {
//        Table table = new Table(UnitValue.createPointArray(new float[]{60f, 180f, 50f, 80f, 110f}));
//
//        // headers
//        table.addCell(new Paragraph("S.N.O.").setBold());
//        table.addCell(new Paragraph("PARTICULARS").setBold());
//        table.addCell(new Paragraph("QTY").setBold());
//        table.addCell(new Paragraph("RATE").setBold());
//        table.addCell(new Paragraph("AMOUNT IN RS.").setBold());
//
//        // items
//        for(Article a : articleList)
//        {
//            table.addCell(new Paragraph(a.SNO+""));
//            table.addCell(new Paragraph(a.description));
//            table.addCell(new Paragraph(a.quantity+""));
//            table.addCell(new Paragraph(a.unitPrice+""));
//            table.addCell(new Paragraph((a.quantity * a.unitPrice)+""));
//        }
//
//        layoutDocument.add(table);
//    }
}
