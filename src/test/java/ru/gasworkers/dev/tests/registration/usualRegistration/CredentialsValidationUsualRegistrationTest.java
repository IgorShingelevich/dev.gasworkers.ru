package ru.gasworkers.dev.tests.registration.usualRegistration;

import io.qameta.allure.*;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.Role;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.gasworkers.dev.model.browser.PositionBrowser;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.pages.context.DispatcherPages;
import ru.gasworkers.dev.pages.context.MasterPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.RandomClient;

import static io.qameta.allure.Allure.step;

//@Tag(AllureTag.REGISTRATION)
//@Tag(AllureTag.REGRESSION)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CredentialsValidationUsualRegistrationTest extends BaseTest {

//    SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));


    @Browser(role = Role.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.FIRST_ROLE)
    ClientPages clientPages;

    @Browser(role = Role.DISPATCHER, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.SECOND_ROLE)
    DispatcherPages dispatcherPages;

    @Browser(role = Role.MASTER, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.THIRD_ROLE)
    MasterPages masterPages;

    RandomClient randomClient = new RandomClient();



//    @ValueSource(strings = {"user@example.c", "user@sub.sub.sub.com"})
    @CsvFileSource(resources = "resources/registration/acceptedEmailFormat.csv")
    @ParameterizedTest(name = "Убедиться, что при вводе допустимого  email: {0} возможна регистрация")
    @Owner("Igor Shingelevich")
    @AllureId("1234")
    @Epic(AllureEpic.REGISTRATION)
    @Feature("Валидация учетных данных")
    @Story("Валидация допустимого email")
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT),  @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE)})
    @DisplayName("Регистрация клиента с допустимым email: ")
    void registrationClientByAcceptedEmail(String acceptedEmail) {
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
        clientPages.getLandingPage().signUpClient();
        clientPages.getRegistrationPage().firstStep.checkFirstStepFinishLoading();
        clientPages.getRegistrationPage().firstStep.byEmail(acceptedEmail);
        clientPages.getRegistrationPage().firstStep.checkboxNotCheckedCState();
        clientPages.getRegistrationPage().firstStep.clickCheckbox();
        clientPages.getRegistrationPage().firstStep.checkboxCheckedCState();
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().secondStep.checkSecondStepFinishLoading();
    }

    @CsvFileSource(resources = "resources/registration/invalidEmailFormat.csv", numLinesToSkip = 1, delimiter = '|')
    @ParameterizedTest(name = "Убедиться, что при вводе невалидного email: {0} появляется ошибка: {1}")
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature("Валидация учетных данных")
    @Story("Валидация недопустимого email")
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT),  @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.NEGATIVE)})
    @DisplayName("Регистрация клиента с невалидным email: ")
    void registrationClientByInvalidEmail(String invalidEmail, String errorText) {
        step("Страница лендинга", () -> {
            clientPages.getLandingPage().open();
            clientPages.getLandingPage().checkFinishLoading();
            clientPages.getLandingPage().signUpClient();
        });
        step("Страница первого шага регистрации", () -> {
            clientPages.getRegistrationPage().firstStep.checkFirstStepFinishLoading();
            clientPages.getRegistrationPage().firstStep.byEmail(invalidEmail);
            clientPages.getRegistrationPage().firstStep.checkboxNotCheckedCState();
            clientPages.getRegistrationPage().firstStep.clickCheckbox();
            clientPages.getRegistrationPage().firstStep.checkboxCheckedCState();
            clientPages.getRegistrationPage().clickNext();
        });
        step("Убедиться, что при вводе невалидного email: " + invalidEmail + " появляется ошибка: " + errorText, () -> {
            clientPages.getRegistrationPage().firstStep.checkInvalidEmailError(invalidEmail, errorText);
            //        clientPages.getRegistrationPage().checkInvalidEmailError(invalidEmail, errorText, errorDescription);
        });
    }

    @CsvFileSource(resources = "resources/registration/invalidPhoneFormat.csv", numLinesToSkip = 1, delimiter = '|')
    @ParameterizedTest(name = "Убедиться, что при вводе невалидного номера телефона: {0} появляется ошибка: {1}")
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.USUAL_REGISTRATION)
    @Story("Валидация недопустимого номера телефона")
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT),  @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.NEGATIVE)})
    @DisplayName("Регистрация клиента с невалидным номером телефона")
    void registrationClientByInvalidPhoneNumber(String invalidPhoneNumber, String errorText) {
        step("Страница лендинга", () -> {
            clientPages.getLandingPage().open();
            clientPages.getLandingPage().checkFinishLoading();
            clientPages.getLandingPage().signUpClient();
        });
        step("Страница первого шага регистрации", () -> {
            clientPages.getRegistrationPage().firstStep.checkFirstStepFinishLoading();
            clientPages.getRegistrationPage().firstStep.byWrongFormatPhone(invalidPhoneNumber);
            clientPages.getRegistrationPage().firstStep.checkboxNotCheckedCState();
            clientPages.getRegistrationPage().firstStep.clickCheckbox();
            clientPages.getRegistrationPage().firstStep.checkboxCheckedCState();
            clientPages.getRegistrationPage().clickNext();
        });
        step("Убедиться, что при вводе невалидного номера телефона: " + invalidPhoneNumber + " появляется ошибка: " + errorText, () -> {
            clientPages.getRegistrationPage().firstStep.checkInvalidPhoneNumberError(invalidPhoneNumber, errorText);
        });
    }

}
// TODO registration validation  cases - all fields are empty, checkbox unchecked. existed phone and email

