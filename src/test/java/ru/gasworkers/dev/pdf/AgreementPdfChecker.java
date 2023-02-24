package ru.gasworkers.dev.pdf;

import com.codeborne.pdftest.PDF;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public final class AgreementPdfChecker extends AbstractPdfChecker {

    public static AgreementPdfChecker newInstance(File file) {
        try {
            PDF pdf = new PDF(file);
            return new AgreementPdfChecker(pdf);
        } catch (IOException e) {
            throw new RuntimeException("Error during open pdf file by path " + file.getAbsolutePath(), e);
        }
    }

    public static AgreementPdfChecker newInstance(String file) {
        return newInstance(new File(file));
    }

    public enum Section {
        AGREEMENT_SUBJECT("1. Предмет Договора", "1-agreement-subject.txt"),
        OBLIGATIONS("2. Обязанности и права Сторон", "2-obligations.txt");

        private final String subtitle;
        private final String fileName;

        Section(String subtitle, String fileName) {
            this.subtitle = subtitle;
            this.fileName = fileName;
        }

        public List<String> getSectionExpectedLines() {
            File file = new File("src/test/resources/pdf/agreement/" + fileName);
            try {
                List<String> lines = new ArrayList<>();
                String commonLine = Files.readString(file.toPath());
                Collections.addAll(lines, commonLine.split("\n"));

                if (this == Section.OBLIGATIONS) {
                    int index = 5;
                    // Здесь меняем даты
                    String modifierLine = lines.get(index).replace("нужд", "Новое");
                    lines.set(index, modifierLine);
                }
                return lines;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private AgreementPdfChecker(PDF pdf) {
        super(pdf);
    }

    public AgreementPdfChecker checkSection(Section section) {
        setCursorAfterSubtitle(section.subtitle);
        List<String> expectedLines = section.getSectionExpectedLines();
        List<String> actualLines = getSectionDescriptionLines(expectedLines.size());

        Assertions.assertEquals(expectedLines.size(), actualLines.size());
        for (int i = 0; i < expectedLines.size(); i++) {
            String expectedLine = expectedLines.get(i).trim();
            String actualLine = actualLines.get(i).trim();
            String message = "Expected is " + expectedLine + "\n" + "Actual is " + actualLine;
            Assertions.assertTrue(actualLine.contains(expectedLine), message);
        }

        return this;
    }

    public AgreementPdfChecker checkAllSections() {
        for (Section section : Section.values()) {
            checkSection(section);
        }
        return this;
    }

    @Step("Check document title")
    public AgreementPdfChecker checkTitle(String documentNumber) {
        setCursor(0);

        List<String> expectedSectionLines = List.of(
                "ДОГОВОР № " + documentNumber,
                "о техническом обслуживании, ремонте",
                "внутридомового газового оборудования домовладения",
                "и аварийно - диспетчерском обеспечении"
        );
        assertThat(getSectionDescriptionLines(4))
                .isEqualTo(expectedSectionLines);

        return this;
    }

    @Step("Check document addresses and bank details (п. 12)")
    // Executor - исполнитель, client - заказчик
    public AgreementPdfChecker checkAddressesAndBankDetails(List<String> expectedExecutorLines,
                                                            List<String> expectedClientLines) {
        setCursorAfterSubtitle("12. Адреса и банковские реквизиты Сторон");

        // Проверить блок исполнителя
        List<String> actualExecutor = getSectionDescriptionLines(expectedExecutorLines.size());
        assertThat(actualExecutor).isEqualTo(expectedExecutorLines);

        // Проверить блок заказчика
        List<String> actualCLient = getSectionDescriptionLines(expectedClientLines.size());
        assertThat(actualCLient).isEqualTo(expectedClientLines);

        return this;
    }


}
