
package edu.com.chatbotsoftI.auxiliar;

import java.io.*;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.element.Image;
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
public class InvoiceMaker {

    public void createPdf(OutputStream outputStream) throws MalformedURLException {



        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outputStream));
        Document layoutDocument = new Document(pdfDocument);

        // title
        addTitle(layoutDocument);

        // customer reference information
        addCustomerReference(layoutDocument);
        addTable(layoutDocument, Arrays.asList(
                new Article(1, "VentaTicket",1, 60),
                new Article(2, "ServicioBot", 1, 1)));

        // articles

        String imageFile = "./MyQRCode.png";
        ImageData data = ImageDataFactory.create(imageFile);
        Image img = new Image(data);
        layoutDocument.add(img);
        layoutDocument.close();
    }

    public static void addTitle(Document layoutDocument)
    {
        layoutDocument.add(new Paragraph("BOLTON BOT INVOICE").setBold().setUnderline().setTextAlignment(TextAlignment.CENTER));
    }

    public static void addCustomerReference(Document layoutDocument)
    {
        layoutDocument.add(new Paragraph("BoltonBot Services").setTextAlignment(TextAlignment.LEFT).setMultipliedLeading(0.2f));
        layoutDocument.add(new Paragraph("La Paz, Bolivia").setMultipliedLeading(.2f));
        layoutDocument.add(new Paragraph("UCB Bolivia").setMultipliedLeading(.2f));
    }

    public void addTable(Document layoutDocument, List<Article> articleList)
    {
        Table table = new Table(UnitValue.createPointArray(new float[]{60f, 180f, 50f, 80f, 110f}));

        // headers
        table.addCell(new Paragraph("S.N.O.").setBold());
        table.addCell(new Paragraph("PARTICULARS").setBold());
        table.addCell(new Paragraph("QTY").setBold());
        table.addCell(new Paragraph("RATE").setBold());
        table.addCell(new Paragraph("AMOUNT IN RS.").setBold());

        // items
        for(Article a : articleList)
        {
            table.addCell(new Paragraph(a.SNO+""));
            table.addCell(new Paragraph(a.description));
            table.addCell(new Paragraph(a.quantity+""));
            table.addCell(new Paragraph(a.unitPrice+""));
            table.addCell(new Paragraph((a.quantity * a.unitPrice)+""));
        }

        layoutDocument.add(table);
    }
}