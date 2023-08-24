package ru.gasworkers.dev.tests.web.registration.usualRegistration.usualClientRegistration.credentialsValidation;

import io.qameta.allure.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.browser.PositionBrowser;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

@Disabled
@Owner("Igor Shingelevich")
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.REGULAR_REGISTRATION)
@Feature("Валидация учетных данных")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.REGISTRATION)
@Tag(AllureTag.WEB)
@Tag(AllureTag.WEB_REGISTRATION)
public class ValidEmailPTest extends BaseTest {
    //    SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    @Browser(role = Role.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.FIRST_ROLE)
    ClientPages clientPages;
    RandomClient randomClient = new RandomClient();

    //    @ValueSource(strings = {"user@example.c", "user@sub.sub.sub.com"})
    @CsvFileSource(files = "src/test/resources/web/registration/acceptedEmailFormat.csv")
    @ParameterizedTest(name = "Убедиться, что при вводе допустимого  email: {0} возможна регистрация")
    @AllureId("1234")
    @Story("Валидация допустимого email")
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Регистрация клиента с валидным email: ")
    void registrationClientByAcceptedEmail(String acceptedEmail) {
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
        clientPages.getLandingPage().signUpClient();
        clientPages.getRegistrationPage().firstStepClient.checkFirstStepFinishLoading();
        clientPages.getRegistrationPage().firstStepClient.byEmail(acceptedEmail);
        clientPages.getRegistrationPage().firstStepClient.checkboxNotCheckedCState();
        clientPages.getRegistrationPage().firstStepClient.clickCheckbox();
        clientPages.getRegistrationPage().firstStepClient.checkboxCheckedCState();
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().secondStep.checkSecondStepFinishLoading();
    }
}