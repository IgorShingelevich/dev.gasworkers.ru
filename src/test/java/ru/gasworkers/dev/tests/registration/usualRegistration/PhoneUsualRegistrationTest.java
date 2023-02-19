package ru.gasworkers.dev.tests.registration.usualRegistration;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.Role;


import org.junit.jupiter.api.*;
import ru.gasworkers.dev.model.browser.BrowserSize;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.pages.context.DispatcherPages;
import ru.gasworkers.dev.pages.context.MasterPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.RandomClient;

import static io.qameta.allure.Allure.step;


public class PhoneUsualRegistrationTest extends BaseTest {

    @Browser(role = Role.CLIENT, browserSize = BrowserSize.DEFAULT, browserPosition = "0x0")
    ClientPages clientPages;

    @Browser(role = Role.DISPATCHER, browserSize = BrowserSize.DEFAULT, browserPosition = "850x0")
    DispatcherPages dispatcherPages;

    @Browser(role = Role.MASTER, browserSize = BrowserSize.DEFAULT, browserPosition = "1700x0")
    MasterPages masterPages;

    RandomClient randomClient = new RandomClient();



    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.BG_REGISTRATION)
    @Story("По телефону")
    @Tags({@Tag("regression"), @Tag("client"), @Tag("registration"), @Tag("positive")})
    @DisplayName("Регистрация клиента по телефону и проверка состояния кабинета")
    void registrationClientByPhone() {
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
            clientPages.getRegistrationPage().fillPassword(randomClient.getPassword());
            clientPages.getRegistrationPage().clickNext();
            clientPages.getRegistrationPage().checkInvalidPasswordNotification();
            clientPages.getRegistrationPage().fillPasswordConfirmation(randomClient.getPassword());
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
            clientPages.getHomePage().checkInitialState(randomClient.getFullName(), randomClient.getSinceDate());
            clientPages.getHomePage().sidebar.allObjects();
            clientPages.getAllObjectsPage().checkInitialState();
            clientPages.getAllObjectsPage().sidebar.allOrders();
            clientPages.getAllOrdersPage().checkInitialState();
            clientPages.getAllOrdersPage().sidebar.allInvoices();
            clientPages.getAllInvoicesPage().checkInitialState();
            clientPages.getAllInvoicesPage().actionsBlock.allNotifications();
            clientPages.getAllNotificationsPage().checkInitialState();
            step("Страница Профиль", () -> {
                clientPages.getHomePage().sidebar.profile();
                clientPages.getProfilePage().checkFinishLoading();
                step("Вкадка Общее данные", () -> {
                    clientPages.getProfilePage().navCommon();
                    clientPages.getProfilePage().navCommonTab.checkFinishLoading();
                    clientPages.getProfilePage().navCommonTab.checkInitialState(randomClient.getName(), randomClient.getPatronymicName(), randomClient.getSurname());
                });
                step("Вкадка Контакты", () -> {
                    clientPages.getProfilePage().navContacts();
                    clientPages.getProfilePage().navContactsTab.checkFinishLoading();
                    clientPages.getProfilePage().navContactsTab.checkFilledState(randomClient.getEmail(), randomClient.getPhoneNumber());
                });
                step("Вкадка Пароль", () -> {
                    clientPages.getProfilePage().navPassword();
                    clientPages.getProfilePage().navPasswordTab.checkFinishLoading();
                    clientPages.getProfilePage().navPasswordTab.checkInitialState();
                });
                step("Вкладка Уведомления", () -> {
                    clientPages.getProfilePage().navNotifications();
                    clientPages.getProfilePage().navNotificationsTab.checkFinishLoading();
                    clientPages.getProfilePage().navNotificationsTab.checkInitialState();
                });
            });
            clientPages.getHomePage().open();
        });
        //TODO profile check
    }



}
