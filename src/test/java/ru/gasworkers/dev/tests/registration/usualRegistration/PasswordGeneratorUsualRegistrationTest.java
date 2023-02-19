package ru.gasworkers.dev.tests.registration.usualRegistration;

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
import ru.gasworkers.dev.utils.RandomClient;

import static io.qameta.allure.Allure.step;

public class PasswordGeneratorUsualRegistrationTest extends BaseTest {

    @Browser(role = Role.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.FIRST_ROLE)
    ClientPages clientPages;

    @Browser(role = Role.DISPATCHER, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.SECOND_ROLE)
    DispatcherPages dispatcherPages;

    @Browser(role = Role.MASTER, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.THIRD_ROLE)
    MasterPages masterPages;

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
            clientPages.getRegistrationPage().checkFirstStepFinishLoading();
            clientPages.getRegistrationPage().byPhone(randomClient.getPhoneNumber());
            clientPages.getRegistrationPage().checkboxNotCheckedCState();
            clientPages.getRegistrationPage().clickCheckbox();
            clientPages.getRegistrationPage().checkboxCheckedCState();
            clientPages.getRegistrationPage().clickNext();
        });
        step("Страница второго шага регистрации", () -> {
            clientPages.getRegistrationPage().checkSecondStepFinishLoading();
            clientPages.getRegistrationPage().fillCode(randomClient.getConfirmationCode());
            clientPages.getRegistrationPage().clickNext();
        });
        step("Страница третьего шага регистрации", () -> {
            clientPages.getRegistrationPage().checkThirdStepFinishLoading();
            clientPages.getRegistrationPage().generatePassword();
            clientPages.getRegistrationPage().clickNext();
        });
        step("Страница четвертого шага регистрации", () -> {
            clientPages.getRegistrationPage().checkFourthStepByPhoneFinishLoading(randomClient.getPhoneNumber());
            clientPages.getRegistrationPage().fillName(randomClient.getName());
            clientPages.getRegistrationPage().fillSurname(randomClient.getSurname());
            clientPages.getRegistrationPage().fillPatronymicName(randomClient.getPatronymicName());
            clientPages.getRegistrationPage().fillEmail(randomClient.getEmail());
            clientPages.getRegistrationPage().clickNext();
        });
        step("Страница успешная регистрация", () -> {
            clientPages.getRegistrationPage().checkFinishState();  //no buttons
        });
        step(("Страница начальный гид"), () -> {
            clientPages.getHomePage().checkInitialGuide();
            clientPages.getHomePage().clickLaterInitialModal();
        });
        step("Кабинет клиента - состояние после Регистрации", () -> {
            clientPages.getHomePage().personSummaryComponent.checkFinishLoading(randomClient.getFullName(), randomClient.getSinceDate());
            clientPages.getHomePage().personSummaryComponent.checkInitialState(randomClient.getFullName(), randomClient.getSinceDate());
            clientPages.getHomePage().sidebar.allObjects();
            clientPages.getAllObjectsPage().checkInitialState();
            clientPages.getAllObjectsPage().sidebar.allOrders();
            clientPages.getAllOrdersPage().checkInitialState();
            clientPages.getAllOrdersPage().sidebar.allInvoices();
            clientPages.getAllInvoicesPage().checkInitialState();
            clientPages.getAllInvoicesPage().actionsBlock.allNotifications();
            clientPages.getAllNotificationsPage().checkInitialState();
            clientPages.getHomePage().open();
        });
        //TODO profile check
    }


}
