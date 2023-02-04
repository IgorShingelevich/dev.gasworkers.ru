package tests.registration;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import extension.browser.Browser;
import model.Role;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.context.ClientPages;
import pages.context.DispatcherPages;
import pages.context.MasterPages;
import tests.BaseTest;
import utils.RandomClient;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationTest extends BaseTest {

//    SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));


    @Browser(role = Role.CLIENT, browserSize = "800x1000", browserPosition = "0x0")
    ClientPages clientPages;

    @Browser(role = Role.DISPATCHER, browserSize = "800x1000", browserPosition = "850x0")
    DispatcherPages dispatcherPages;

    @Browser(role = Role.MASTER, browserSize = "800x1000", browserPosition = "1700x0")
    MasterPages masterPages;

    RandomClient randomClient = new RandomClient();

    @Test
    @Owner("Igor Shingelevich")
    @Order(1)
    @Feature("Регистрация")
    @Story("Регистрация клиента")
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
        step("Кабинет клиента - начальное состояние", () -> {
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
                    clientPages.getProfilePage().navContactsTab.checkFinishLoading(randomClient.getEmail(), randomClient.getPhoneNumber());
                    clientPages.getProfilePage().navContactsTab.checkFilledStatus(randomClient.getEmail(), randomClient.getPhoneNumber());
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

    @Test
    @Owner("Igor Shingelevich")
    @Order(2)
    @Feature("Регистрация")
    @Story("Регистрация клиента")
    @Tags({@Tag("regression"), @Tag("client"), @Tag("registration"), @Tag("positive")})
    @DisplayName("Регистрация клиента по email и проверка состояния кабинета")
    void registrationClientByEmail() {
        step("Страница лендинга", () -> {
            clientPages.getLandingPage().open();
            clientPages.getLandingPage().checkFinishLoading();
            clientPages.getLandingPage().signUpClient();
        });
        step("Страница первого шага регистрации", () -> {
            clientPages.getRegistrationPage().checkFirstStepFinishLoading();
            clientPages.getRegistrationPage().byEmail(randomClient.getEmail());
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
            clientPages.getRegistrationPage().checkFourthStepByEmailFinishLoading(randomClient.getEmail());
            clientPages.getRegistrationPage().fillName(randomClient.getName());
            clientPages.getRegistrationPage().fillSurname(randomClient.getSurname());
            clientPages.getRegistrationPage().fillPatronymicName(randomClient.getPatronymicName());
            clientPages.getRegistrationPage().fillPhoneNumber(randomClient.getPhoneNumber());
            clientPages.getRegistrationPage().clickNext();
        });
        step("Страница успешная регистрация", () -> {
            clientPages.getRegistrationPage().checkFinishState();  //no buttons
        });
        step(("Страница начальный гид"), () -> {
            clientPages.getHomePage().checkInitialGuide();
            clientPages.getHomePage().clickLaterInitialModal();
        });
        step("Кабинет клиента - начальное состояние", () -> {
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
                    clientPages.getProfilePage().navContactsTab.checkFinishLoading(randomClient.getEmail(), randomClient.getPhoneNumber());
                    clientPages.getProfilePage().navContactsTab.checkFilledStatus(randomClient.getEmail(), randomClient.getPhoneNumber());
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
            //TODO profile  check - photo. rest of the fields
        });
    }

    @Test
    @Owner("Igor Shingelevich")
    @Order(3)
    @Feature("Регистрация")
    @Story("Генерация Надежного пароля")
    @Tags({@Tag("regression"), @Tag("client"), @Tag("registration"), @Tag("positive")})
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
        step("Кабинет клиента - начальное состояние", () -> {
            clientPages.getHomePage().checkInitialState(randomClient.getFullName(), randomClient.getSinceDate());
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

    @Test
    @Disabled
    @Owner("Igor Shingelevich")
    @Order(1)
    @Feature("Регистрация")
    @Story("Регистрация клиента с перехожом  на страницу создания объекта")
    @Tags({@Tag("regression"), @Tag("client"), @Tag("registration"), @Tag("positive")})
    @DisplayName("Начальный гид -  создание объекта")
    void registrationClientInitialGuideDialogCreate() {
        //TODO check initial guide leading to create an object
    }

    @ValueSource(strings = { "user@example.c", "user@sub.sub.sub.com" })
    @ParameterizedTest (name = "Убедиться, что при вводе допустимого  email: {0} возможна регистрация")
    @Owner("Igor Shingelevich")
    @Order(4)
    @Feature("Регистрация")
    @Story("Регистрация с допустимыми параметрами")
    @Tags({@Tag("regression"), @Tag("client"),@Tag("registration"), @Tag("positive")})
    @DisplayName("Регистрация клиента с допустимым email: ")
    void registrationClientByAcceptedEmail( String acceptedEmail){
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
        clientPages.getLandingPage().signUpClient();
        clientPages.getRegistrationPage().checkFirstStepFinishLoading();
        clientPages.getRegistrationPage().byEmail(acceptedEmail);
        clientPages.getRegistrationPage().checkboxNotCheckedCState();
        clientPages.getRegistrationPage().clickCheckbox();
        clientPages.getRegistrationPage().checkboxCheckedCState();
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().checkSecondStepFinishLoading();
    }

    @CsvFileSource(resources = "resources/invalidEmailFormat.csv", numLinesToSkip = 1, delimiter = '|')
    //    @CsvSource(value = {}) // poccible with implementation in the code
    @ParameterizedTest (name = "Убедиться, что при вводе невалидного email: {0} появляется ошибка: {1}")
    @Owner("Igor Shingelevich")
    @Order(5)
    @Feature("Регистрация")
    @Story("Регистрация с недопустимымой почтой")
    @Tags({@Tag("regression"), @Tag("registration"), @Tag("negative")})
    @DisplayName("Регистрация клиента с невалидным email: ")
    void registrationClientByInvalidEmail( String invalidEmail, String errorText ){
        step("Страница лендинга", () -> {
            clientPages.getLandingPage().open();
            clientPages.getLandingPage().checkFinishLoading();
            clientPages.getLandingPage().signUpClient();
        });
        step("Страница первого шага регистрации", () -> {
            clientPages.getRegistrationPage().checkFirstStepFinishLoading();
            clientPages.getRegistrationPage().byEmail(invalidEmail);
            clientPages.getRegistrationPage().checkboxNotCheckedCState();
            clientPages.getRegistrationPage().clickCheckbox();
            clientPages.getRegistrationPage().checkboxCheckedCState();
            clientPages.getRegistrationPage().clickNext();
        });
        step("Убедиться, что при вводе невалидного email: " + invalidEmail + " появляется ошибка: " + errorText, () -> {
            clientPages.getRegistrationPage().checkInvalidEmailError(invalidEmail, errorText);
            //        clientPages.getRegistrationPage().checkInvalidEmailError(invalidEmail, errorText, errorDescription);
        });
    }

    @CsvFileSource(resources = "resources/invalidPhoneNumbers.csv", numLinesToSkip = 1, delimiter = '|')
    @ParameterizedTest (name = "Убедиться, что при вводе невалидного номера телефона: {0} появляется ошибка: {1}")
    @Owner("Igor Shingelevich")
    @Order(6)
    @Tags({@Tag("regression"), @Tag("registration"), @Tag("negative")})
     @Feature("Регистрация")
    @Story("Регистрация с недопустимыми номером телефона")
    @DisplayName("Регистрация клиента с невалидным номером телефона")
    void registrationClientByInvalidPhoneNumber( String invalidPhoneNumber, String errorText){
        step("Страница лендинга", () -> {
            clientPages.getLandingPage().open();
            clientPages.getLandingPage().checkFinishLoading();
            clientPages.getLandingPage().signUpClient();
        });
        step("Страница первого шага регистрации", () -> {
            clientPages.getRegistrationPage().checkFirstStepFinishLoading();
            clientPages.getRegistrationPage().byWrongFormatPhone(invalidPhoneNumber);
            clientPages.getRegistrationPage().checkboxNotCheckedCState();
            clientPages.getRegistrationPage().clickCheckbox();
            clientPages.getRegistrationPage().checkboxCheckedCState();
            clientPages.getRegistrationPage().clickNext();
        });
        step("Убедиться, что при вводе невалидного номера телефона: " + invalidPhoneNumber + " появляется ошибка: " + errorText, () -> {
            clientPages.getRegistrationPage().checkInvalidPhoneNumberError(invalidPhoneNumber, errorText);
        });
    }


// TODO registration cases - all fields are empty, checkbox unchecked
}