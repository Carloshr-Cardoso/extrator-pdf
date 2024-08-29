package com.example.extrator_pdf.util;

import com.example.extrator_pdf.dto.PageContentDTO;
import com.example.extrator_pdf.exception.InvalidExtractionException;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PdfTextExtrator {
    private PdfTextExtrator() {
        throw new IllegalStateException("classe utilitária");
    }

    public static List<PageContentDTO> extractFromPdf(InputStream inputStream) throws IOException {
        List<PageContentDTO> pageContents = new ArrayList<>();
        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("por");

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

    public static PageContentDTO extractFromImage(InputStream inputStream) throws IOException {
        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("por");

        try{
            return new PageContentDTO(1, tesseract.doOCR(ImageIO.read(inputStream)));
        } catch (TesseractException e) {
            throw new InvalidExtractionException(e.getMessage());
        }

    }
}
