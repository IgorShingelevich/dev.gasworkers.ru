package ru.gasworkers.dev.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class ConvertPDFtoHTML {


    public static void main(String[] args) throws IOException {
        // Load the PDF file
        File file = new File("/path/to/pdf/file.pdf");
        PDDocument document = PDDocument.load(file);

        // Extract the text from the PDF
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);

        // Close the document
        document.close();

        // Convert the text to HTML
        String html = textToHtml(text);

        // Do something with the HTML...
    }

    private static String textToHtml(String text) {
        // Replace newlines with <br> tags
        String html = text.replace("\n", "<br>");
        // Wrap the text in <html> and <body> tags
        html = "<html><body>" + html + "</body></html>";
        return html;
    }
}



