package ru.gasworkers.dev.pdf;

import com.codeborne.pdftest.PDF;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPdfChecker {

    protected final List<String> lines;
    protected int cursor = 0;

    protected AbstractPdfChecker(PDF pdf) {
        lines = List.of(pdf.text.split("\n"));
    }

    protected void setCursorAfterSubtitle(String subtitle) {
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals(subtitle)) {
                cursor = i;
                return;
            }
        }

        throw new RuntimeException("Not found line with subtitle " + subtitle);
    }

    protected void setCursor(int index) {
        cursor = index;
    }

    protected List<String> getSectionDescriptionLines(int linesCount) {
        if (linesCount < 1)
            throw new RuntimeException("Wrong value lines count " + linesCount);

        List<String> results = new ArrayList<>();
        for (int i = 1; i <= linesCount; i++) {
            results.add(lines.get(cursor));
            cursor++;
        }
        return results;
    }

}
