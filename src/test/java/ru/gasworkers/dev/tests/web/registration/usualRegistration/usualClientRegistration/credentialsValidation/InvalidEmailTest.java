package ru.gasworkers.dev.tests.web.registration.usualRegistration.usualClientRegistration.credentialsValidation;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
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

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.REGULAR_REGISTRATION)
@Feature("Валидация учетных данных")
@Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.WEB)})
public class InvalidEmailTest extends BaseTest {
    //    SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    @Browser(role = Role.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.FIRST_ROLE)
    ClientPages clientPages;
    RandomClient randomClient = new RandomClient();

    //    @ValueSource(strings = {"user@example.c", "user@sub.sub.sub.com"})
    @CsvFileSource(files = "src/test/resources/web/registration/invalidEmailFormat.csv", numLinesToSkip = 1, delimiter = '|')
    @ParameterizedTest(name = "Убедиться, что при вводе невалидного email: {0} появляется ошибка: {1}")
    @Story("Валидация недопустимого email")
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Регистрация клиента с невалидным email: ")
    void registrationClientByInvalidEmail(String invalidEmail, String errorText) {
        step("Страница лендинга", () -> {
            clientPages.getLandingPage().open();
            clientPages.getLandingPage().checkFinishLoading();
            clientPages.getLandingPage().signUpClient();
        });
        step("Страница первого шага регистрации", () -> {
            clientPages.getRegistrationPage().firstStepClient.checkFirstStepFinishLoading();
            clientPages.getRegistrationPage().firstStepClient.byEmail(invalidEmail);
            clientPages.getRegistrationPage().firstStepClient.checkboxNotCheckedCState();
            clientPages.getRegistrationPage().firstStepClient.clickCheckbox();
            clientPages.getRegistrationPage().firstStepClient.checkboxCheckedCState();
            clientPages.getRegistrationPage().clickNext();
        });
        step("Убедиться, что при вводе невалидного email: " + invalidEmail + " появляется ошибка: " + errorText, () -> {
            clientPages.getRegistrationPage().firstStepClient.checkInvalidEmailError(invalidEmail, errorText);
            //        clientPages.getRegistrationPage().checkInvalidEmailError(invalidEmail, errorText, errorDescription);
        });
    }
}
// TODO registration validation  cases - all fields are empty, checkbox unchecked. existed phone and email

