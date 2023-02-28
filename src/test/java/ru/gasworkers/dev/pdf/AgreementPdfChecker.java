package ru.gasworkers.dev.pdf;

import com.codeborne.pdftest.PDF;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import ru.gasworkers.dev.utils.SSSRServiceBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public final class AgreementPdfChecker extends AbstractPdfChecker {
    static SSSRServiceBuilder sssrService = new SSSRServiceBuilder();
    String serviceNameText = sssrService.name;


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


        AGREEMENT_SUBJECT("1. Предмет Договора", "1-agreement-subject.txt", sssrService.name),
        OBLIGATIONS("2. Обязанности и права Сторон", "2-obligations.txt", sssrService.name);

        private final String subtitle;
        private final String fileName;
        private final String expectedText;

        Section(String subtitle, String fileName, String expectedText) {
            this.subtitle = subtitle;
            this.fileName = fileName;
            this.expectedText = expectedText;
        }

        public List<String> getSectionExpectedLines() {
            File file = new File("src/test/resources/pdf/agreement/" + fileName);
            try {
                // expected lines
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

    @Step("Check document titleV2")
    public AgreementPdfChecker checkTitleV2(String documentNumber) {
        setCursor(0);
        //  full string
        String expectedTitle = "ДОГОВОР № " + documentNumber +
                " о техническом обслуживании, ремонте внутридомового газового оборудования домовладения и аварийно - диспетчерском обеспечении";
        List<String> actualSubLines = getSectionDescriptionLines(4);
        // check if expectedTitle contains actualSubLines
        assertThat(actualSubLines)
                .allMatch(sectionDescriptionLines ->
                        expectedTitle.contains(sectionDescriptionLines));



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

    @Step("Check document addresses and bank details (п. 12) v2")
    // Executor - исполнитель, client - заказчик
    public AgreementPdfChecker checkAddressesAndBankDetailsV2(List<String> expectedExecutorLines,
                                                            List<String> expectedClientLines) {
        setCursorAfterSubtitle("12. Адреса и банковские реквизиты Сторон");



        // Проверить блок исполнителя
        List<String> actualExecutor = getSectionDescriptionLines(expectedExecutorLines.size());

        assertThat(actualExecutor).isEqualTo(expectedExecutorLines);
        /*assertThat(expectedExecutorLines)
                                .containsAnyElementsOf(actualExecutor);*/

        /*
        * To check that each string in List<String> subLines contains any part of String fullLine using AssertJ, you can use the anyMatch method of the List class and the contains method of the String class as follows:
        *
        * import org.assertj.core.api.Assertions;

Assertions.assertThat(subLines)
          .allMatch(subLine -> fullLine.contains(subLine))
          *
          * The allMatch method ensures that the condition holds true for all elements in the subLines list, and the lambda expression subLine -> fullLine.contains(subLine) checks whether fullLine contains subLine. If fullLine contains any part of subLine, then the condition holds true for that element, and the assertion passes. Otherwise, the assertion fails with an appropriate error message.

        * */

        // Проверить блок заказчика
        List<String> actualCLient = getSectionDescriptionLines(expectedClientLines.size());
        assertThat(actualCLient).isEqualTo(expectedClientLines);

        return this;
    }


}
