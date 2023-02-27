package ru.gasworkers.dev.pdf;

import org.junit.jupiter.api.Test;

public class AgreementPdfTest {

    private final AgreementPdfChecker pdfChecker = AgreementPdfChecker.newInstance("src/test/resources/pdf.pdf");

    @Test
    void checkAgreementPdf() {
        pdfChecker.checkSection(AgreementPdfChecker.Section.AGREEMENT_SUBJECT);
        pdfChecker.checkSection(AgreementPdfChecker.Section.OBLIGATIONS);
//        pdfChecker.checkAddressesAndBankDetailsV2();

        pdfChecker.checkAllSections();
    }

}
