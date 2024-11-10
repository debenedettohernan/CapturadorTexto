package com.example.CapturadorTexto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class TesseractController {
    @Autowired
    private TesseractService tesseractService;
    @PostMapping("/ocr")
    public String recognizedText(@RequestParam MultipartFile img) throws IOException {
        return tesseractService.recognizedText(img.getInputStream());
    }
}
