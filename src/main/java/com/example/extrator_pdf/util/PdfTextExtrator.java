package com.example.extrator_pdf.util;

import com.example.extrator_pdf.dto.PageContentDTO;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PdfTextExtrator {
    private PdfTextExtrator() {
        throw new IllegalStateException("classe utilitária");
    }

    public static List<PageContentDTO> extract(InputStream inputStream) throws IOException {
        List<PageContentDTO> pageContents = new ArrayList<>();
        Tesseract tesseract = new Tesseract();

        try (PDDocument document = PDDocument.load(inputStream)) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            int numberOfPages = document.getNumberOfPages();
            for (int i = 0; i < numberOfPages; i++) {
                String pageText;
                try {
                    pageText = tesseract.doOCR(pdfRenderer.renderImageWithDPI(i, 300));
                } catch (TesseractException e) {
                    pageText = "Erro ao processar a página: " + (i + 1);
                }
                pageContents.add(new PageContentDTO(i + 1, pageText));
            }
        }

        return pageContents;
    }
}
