package com.example.extrator_pdf.recursos;

import com.example.extrator_pdf.dto.PageContentDTO;
import com.example.extrator_pdf.servicos.PdfExtratorServico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ExtratorTextoRecurso {
    private final PdfExtratorServico servico;

    public ExtratorTextoRecurso(PdfExtratorServico servico) {
        this.servico = servico;
    }

    @PostMapping("pdf/extrair-conteudo")
    public ResponseEntity<List<PageContentDTO>> extractTextFromPdf(@RequestParam("file") MultipartFile file) throws IOException {
        List<PageContentDTO> pages = servico.extractTextFromPdf(file);
        return ResponseEntity.ok(pages);
    }

    @PostMapping("imagem/extrair-conteudo")
    public ResponseEntity<PageContentDTO> extractTextFromImage(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(servico.extractTextFromImage(file));
    }
}
