
package edu.com.chatbotsoftI.auxiliar;

import java.io.*;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Date;
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

import javax.lang.model.element.Element;


public class InvoiceMaker {
    java.util.Date date;
    private String descript;
    private Long amount;
    int cantidad;

    public InvoiceMaker(Date date, String descript, int cantidad ,Long amount) {
        this.date = date;
        this.descript = descript;
        this.amount = amount;
        this.cantidad = cantidad;
    }

    public void createPdf(OutputStream outputStream) throws MalformedURLException {



        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outputStream));
        Document layoutDocument = new Document(pdfDocument);

// Facturacion de prueba.
        addTitle(layoutDocument);


        addCustomerReference(layoutDocument);
        addTable(layoutDocument, Arrays.asList(
                new Article(1, descript,cantidad,amount )));

        QrCreator qrCreator = new QrCreator();
        qrCreator.SaveQr("Nombre del evento: Avengers EndGame \n Fecha y hora:14-12-2019 | 14:31:07","png",150);
//        addTotal(layoutDocument,total);
        String imageFile = "./MyQRCode.png";
        ImageData data = ImageDataFactory.create(imageFile);
        Image img = new Image(data);
        img.setUnderline().setTextAlignment(TextAlignment.CENTER);
        layoutDocument.add(img);

        layoutDocument.close();
    }

    public static void addTitle(Document layoutDocument) throws MalformedURLException {
        String imageFile = "./BoltonLogo.jpg";
        ImageData data = ImageDataFactory.create(imageFile);
        Image img = new Image(data);
        img.scaleToFit(150,300);

        img.setUnderline().setTextAlignment(TextAlignment.LEFT);
        layoutDocument.add(img);
        layoutDocument.add(new Paragraph("INVOICE").setBold().setUnderline().setTextAlignment(TextAlignment.CENTER));

    }

    public static void addCustomerReference(Document layoutDocument)
    {
        layoutDocument.add(new Paragraph("BoltonBot Services").setTextAlignment(TextAlignment.LEFT).setMultipliedLeading(0.2f));
        layoutDocument.add(new Paragraph("La Paz, Bolivia").setMultipliedLeading(.2f));
        layoutDocument.add(new Paragraph("UCB Bolivia").setMultipliedLeading(.2f));
    }
    public void addTotal(Document layoutDocument, Long amount){

        Table table = new Table(UnitValue.createPointArray(new float[]{200f}));
        table.addCell(new Paragraph("Total:            "+amount).setBold());
        layoutDocument.add(table);
    }
    public void addTable(Document layoutDocument, List<Article> articleList)
    {
        Table table = new Table(UnitValue.createPointArray(new float[]{60f, 180f, 50f, 80f, 110f}));

        // headers
        table.addCell(new Paragraph("item").setBold());
        table.addCell(new Paragraph("Detalle").setBold());
        table.addCell(new Paragraph("Cantidad").setBold());
        table.addCell(new Paragraph("Precio").setBold());
        table.addCell(new Paragraph("Total en Bs.").setBold());

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