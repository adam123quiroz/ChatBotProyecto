package edu.com.chatbotsoftI.auxiliar;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class QrCreator {


    public BufferedImage QrCreator(String text, int width, int height) throws WriterException {
        BitMatrix matrix;
        Writer writer = new QRCodeWriter();
        matrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
                int grayValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
                image.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
            }
        }

        return image;
    }
    public void SaveQr(String content, String fileType, int size){
        try {
            String filePath = "./MyQRCode";
            UUID uuid = UUID.randomUUID();
            String randomUUIDString = uuid.toString();
            QRCodeWriter qrcode = new QRCodeWriter();
            BitMatrix matrix = qrcode.encode(content, BarcodeFormat.QR_CODE, size, size);
            File qrFile = new File(filePath  + "." + fileType);
            int matrixWidth = matrix.getWidth();
            BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, matrixWidth, matrixWidth);
            graphics.setColor(Color.BLACK);

            for (int b = 0; b < matrixWidth; b++) {
                for (int j = 0; j < matrixWidth; j++) {
                    if (matrix.get(b, j)) {
                        graphics.fillRect(b, j, 1, 1);
                    }
                }
            }
            ImageIO.write(image, fileType, qrFile);
        } catch (WriterException ex) {

        } catch (IOException ex) {

        }

    }
}