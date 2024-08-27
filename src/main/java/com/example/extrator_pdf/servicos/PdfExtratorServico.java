package com.example.extrator_pdf.servicos;

import com.example.extrator_pdf.dto.PageContentDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.example.extrator_pdf.util.PdfTextExtrator.extract;

@Service
public class PdfExtratorServico {

    public List<PageContentDTO> extractText(MultipartFile file) throws IOException {
        return extract(file.getInputStream());
    }
}
