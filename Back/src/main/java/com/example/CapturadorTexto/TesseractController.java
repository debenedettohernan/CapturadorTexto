package com.example.CapturadorTexto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@RestController
public class TesseractController {
    @Autowired
    private TesseractService tesseractService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/ocr")
    public String recognizedText(@RequestParam MultipartFile img) throws IOException {
        return tesseractService.recognizedText(img.getInputStream());
    }

    // Nuevo endpoint para imágenes desde URL
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/ocr-url")
    public String recognizedTextFromUrl(@RequestBody String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            BufferedImage image = ImageIO.read(url);
            return tesseractService.recognizedTextFromUrl(image);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al procesar la imagen desde la URL: " + e.getMessage();
        }
    }
}
