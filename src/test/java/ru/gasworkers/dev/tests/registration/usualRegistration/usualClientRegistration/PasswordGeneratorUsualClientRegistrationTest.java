package ru.gasworkers.dev.tests.registration.usualRegistration.usualClientRegistration;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.browser.PositionBrowser;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.pages.context.DispatcherPages;
import ru.gasworkers.dev.pages.context.MasterPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import static io.qameta.allure.Allure.step;

public class PasswordGeneratorUsualClientRegistrationTest extends BaseTest {

    @Browser(role = Role.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.FIRST_ROLE)
    ClientPages clientPages;

    RandomClient randomClient = new RandomClient();

    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.USUAL_REGISTRATION)
    @Story("Регистрация со Сгенерированным паролем")
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT),  @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE)})
    @DisplayName("Регистрация со Сгенерированным паролем")
    void registrationClientWithGeneratedPasswordByPhone() {
        step("Страница лендинга", () -> {
            clientPages.getLandingPage().open();
            clientPages.getLandingPage().checkFinishLoading();
            clientPages.getLandingPage().signUpClient();
        });
        step("Страница первого шага регистрации", () -> {
            clientPages.getRegistrationPage().firstStepClient.checkFirstStepFinishLoading();
            clientPages.getRegistrationPage().firstStepClient.byPhone(randomClient.getPhoneNumber());
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
            clientPages.getRegistrationPage().thirdStep.generatePassword();
            clientPages.getRegistrationPage().clickNext();
        });
        step("Страница четвертого шага регистрации", () -> {
            clientPages.getRegistrationPage().forthStep.checkFourthStepByPhoneFinishLoading(randomClient.getPhoneNumber());
            clientPages.getRegistrationPage().forthStep.fillName(randomClient.getName());
            clientPages.getRegistrationPage().forthStep.fillSurname(randomClient.getSurname());
            clientPages.getRegistrationPage().forthStep.fillPatronymicName(randomClient.getPatronymicName());
            clientPages.getRegistrationPage().forthStep.fillEmail(randomClient.getEmail());
            clientPages.getRegistrationPage().clickNext();
        });
        step("Страница успешная регистрация", () -> {
            clientPages.getRegistrationPage().successRegistrationStep.checkFinishLoading();
        });
        step(("Страница начальный гид"), () -> {
            clientPages.getHomePage().checkInitialGuide();
            clientPages.getHomePage().clickLaterInitialModal();
        });
        step("Кабинет клиента - состояние после Регистрации", () -> {
            //todo personSummaryComponent appperance
            /*clientPages.getHomePage().personSummaryComponent.checkFinishLoading(randomClient.getFullName(), randomClient.getSinceDate());
            clientPages.getHomePage().personSummaryComponent.checkInitialState(randomClient.getFullName(), randomClient.getSinceDate());*/
            clientPages.getHomePage().sidebar.allObjects();
            clientPages.getAllObjectsPage().checkInitialState();
            clientPages.getAllObjectsPage().sidebar.allOrders();
            clientPages.getAllOrdersPage().checkInitialState();
            clientPages.getAllOrdersPage().sidebar.allInvoices();
            clientPages.getAllInvoicesPage().checkInitialState();
            clientPages.getAllInvoicesPage().actionsBlock.notifications();
            clientPages.getAllNotificationsPage().checkInitialState();
            clientPages.getHomePage().open();
        });
        //TODO profile check
    }


}
