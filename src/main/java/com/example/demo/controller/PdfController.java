package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.fontbox.FontBoxFont;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.File;
import java.io.IOException;

public class PdfController {
    @FXML
    private Label pdf;

    @FXML
    protected void initialize() throws IOException {
        PDDocument pdDocument = new PDDocument();
        PDPage pdPage = new PDPage();
        PDFont font = new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN);
        pdDocument.addPage(pdPage);
        PDPageContentStream pageContentStream = new PDPageContentStream(pdDocument, pdPage);
        pageContentStream.beginText();
        pageContentStream.newLineAtOffset(250, 250);
        pageContentStream.setFont(font, 12);
        pageContentStream.showText("This is test PDFBox!");
        pageContentStream.endText();
        pageContentStream.close();
        pdDocument.save(new File("./generate.pdf"));
        pdDocument.close();
        pdf.setText("PDF успешно создан");
    }
}
