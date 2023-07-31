package ru.gasworkers.dev.tests.web.registration.usualRegistration.usualClientRegistration;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import static io.qameta.allure.Allure.step;
@Owner("Igor Shingelevich")
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.REGULAR_REGISTRATION)
@Story("По телефону")
@Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.WEB), @Tag(AllureTag.POSITIVE)})
public class ByPhoneUsualClientRegistrationTest extends BaseTest {
    @Browser(role = Role.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = "0x0")
    ClientPages clientPages;
    RandomClient randomClient = new RandomClient();
    @Test
    @DisplayName("Регистрация клиента по телефону")
    void registrationClientByPhone() {
        step("Страница лендинга", () -> {
            clientPages.getLandingPage().open();
            clientPages.getLandingPage().checkFinishLoading();
            clientPages.getLandingPage().signUpClient();
        });
        step("Страница первого шага регистрации", () -> {
            clientPages.getRegistrationPage().firstStepClient.checkFirstStepFinishLoading();
            clientPages.getRegistrationPage().firstStepClient.byPhone(randomClient.getPhone());
            clientPages.getRegistrationPage().firstStepClient.checkboxNotCheckedCState();
            clientPages.getRegistrationPage().firstStepClient.clickCheckbox();
            clientPages.getRegistrationPage().firstStepClient.checkboxCheckedCState();
            clientPages.getRegistrationPage().clickNext();
        });
        step("Страница второго шага регистрации", () -> {
            clientPages.getRegistrationPage().secondStep.checkSecondStepFinishLoading();
            clientPages.getRegistrationPage().confirmationCode.fillCode(randomClient.getConfirmationCode());
            clientPages.getRegistrationPage().clickNext();
        });
        step("Страница третьего шага регистрации", () -> {
            clientPages.getRegistrationPage().thirdStep.checkFinishLoading(3);
            clientPages.getRegistrationPage().thirdStep.fillPassword(randomClient.getPassword());
            clientPages.getRegistrationPage().clickNext();
            clientPages.getRegistrationPage().thirdStep.checkInvalidPasswordNotification();
            clientPages.getRegistrationPage().thirdStep.fillPasswordConfirmation(randomClient.getPassword());
            clientPages.getRegistrationPage().clickNext();
        });
        step("Страница четвертого шага регистрации", () -> {
            clientPages.getRegistrationPage().forthStep.checkFourthStepByPhoneFinishLoading(randomClient.getPhone());
            clientPages.getRegistrationPage().forthStep.fillName(randomClient.getName());
            clientPages.getRegistrationPage().forthStep.fillSurname(randomClient.getSurname());
            clientPages.getRegistrationPage().forthStep.fillPatronymicName(randomClient.getMiddleName());
            clientPages.getRegistrationPage().forthStep.fillEmail(randomClient.getEmail());
            clientPages.getRegistrationPage().clickNext();
        });
        step("Страница успешная регистрация", () -> {
            clientPages.getRegistrationPage().successRegistrationStep.checkFinishLoading();
        });
        clientPages.getHomePage().urlChecker.urlContains("profile/client");
    }
}
