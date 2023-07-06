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
public class InvalidPhoneTest extends BaseTest {
    //    SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    @Browser(role = Role.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.FIRST_ROLE)
    ClientPages clientPages;
    RandomClient randomClient = new RandomClient();

    //    @ValueSource(strings = {"user@example.c", "user@sub.sub.sub.com"})
    @CsvFileSource(files = "src/test/resources/web/registration/invalidPhoneFormat.csv", numLinesToSkip = 1, delimiter = '|')
    @ParameterizedTest(name = "Убедиться, что при вводе невалидного номера телефона: {0} появляется ошибка: {1}")
    @Story("Валидация недопустимого номера телефона")
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Регистрация клиента с невалидным номером телефона")
    void registrationClientByInvalidPhoneNumber(String invalidPhoneNumber, String errorText) {
        step("Страница лендинга", () -> {
            clientPages.getLandingPage().open();
            clientPages.getLandingPage().checkFinishLoading();
            clientPages.getLandingPage().signUpClient();
        });
        step("Страница первого шага регистрации", () -> {
            clientPages.getRegistrationPage().firstStepClient.checkFirstStepFinishLoading();
            clientPages.getRegistrationPage().firstStepClient.byWrongFormatPhone(invalidPhoneNumber);
            clientPages.getRegistrationPage().firstStepClient.checkboxNotCheckedCState();
            clientPages.getRegistrationPage().firstStepClient.clickCheckbox();
            clientPages.getRegistrationPage().firstStepClient.checkboxCheckedCState();
            clientPages.getRegistrationPage().clickNext();
        });
        step("Убедиться, что при вводе невалидного номера телефона: " + invalidPhoneNumber + " появляется ошибка: " + errorText, () -> {
            clientPages.getRegistrationPage().firstStepClient.checkInvalidPhoneNumberError(invalidPhoneNumber, errorText);
        });
    }
}
// TODO registration validation  cases - all fields are empty, checkbox unchecked. existed phone and email

