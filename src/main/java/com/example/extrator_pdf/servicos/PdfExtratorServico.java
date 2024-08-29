package com.example.extrator_pdf.servicos;

import com.example.extrator_pdf.dto.PageContentDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.example.extrator_pdf.util.PdfTextExtrator.extractFromImage;
import static com.example.extrator_pdf.util.PdfTextExtrator.extractFromPdf;

@Service
public class PdfExtratorServico {

    public List<PageContentDTO> extractTextFromPdf(MultipartFile file) throws IOException {
        return extractFromPdf(file.getInputStream());
    }

    public PageContentDTO extractTextFromImage(MultipartFile file) throws IOException {
        return extractFromImage(file.getInputStream());
    }
}
