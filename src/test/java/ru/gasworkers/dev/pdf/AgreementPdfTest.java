package ru.gasworkers.dev.pdf;

import org.junit.jupiter.api.Test;

public class AgreementPdfTest {

    private final AgreementPdfChecker pdfChecker = AgreementPdfChecker.newInstance("src/test/resources/test_pdf.pdf");

    @Test
    void checkAgreementPdf() {
        pdfChecker.checkSection(AgreementPdfChecker.Section.AGREEMENT_SUBJECT);
        pdfChecker.checkSection(AgreementPdfChecker.Section.OBLIGATIONS);
//        pdfChecker.checkAddressesAndBankDetailsV2();

        pdfChecker.checkAllSections();
    }

    @Test
    void checkTitleV2() {
        pdfChecker.checkTitleV2("45/23/077/003816");
    }

}
