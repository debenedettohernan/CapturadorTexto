package com.example.CapturadorTexto;

import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Service
public class TesseractService {
    @Autowired
    private Tesseract tesseract;

    public String recognizedText(InputStream inputStream) throws IOException {
        BufferedImage image = ImageIO.read(inputStream);
        return doOCR(image);
    }

    public String recognizedTextFromUrl(BufferedImage image) throws IOException {
        return doOCR(image);
    }

    private String doOCR(BufferedImage image) {
        try {
            return tesseract.doOCR(image);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error durante el OCR: " + e.getMessage();
        }
    }
}
