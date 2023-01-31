package tests.registration;

import extension.browser.Browser;
import model.Role;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.context.ClientPages;
import pages.context.DispatcherPages;
import pages.context.MasterPages;
import tests.BaseTest;
import utils.RandomClient;


public class RegistrationTest extends BaseTest {

    @Browser(role = Role.CLIENT, browserSize = "800x1000", browserPosition = "0x0")
    ClientPages clientPages;

    @Browser(role = Role.DISPATCHER, browserSize = "800x1000", browserPosition = "850x0")
    DispatcherPages dispatcherPages;

    @Browser(role = Role.MASTER, browserSize = "800x1000", browserPosition = "1700x0")
    MasterPages masterPages;

    RandomClient randomClient = new RandomClient();

    @Test
    @Order(1)
    @Tags({@Tag("regression"), @Tag("client"), @Tag("registration"), @Tag("positive")})
    @DisplayName("Регистрация клиента по телефону")
    void registrationClientByPhone() {
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
        clientPages.getLandingPage().signUpClient();
        clientPages.getRegistrationPage().checkFirstStepFinishLoading();
        clientPages.getRegistrationPage().byPhone(randomClient.getPhoneNumber());
        clientPages.getRegistrationPage().checkboxNotCheckedCState();
        clientPages.getRegistrationPage().clickCheckbox();
        clientPages.getRegistrationPage().checkboxCheckedCState();
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().checkSecondStepFinishLoading();
        clientPages.getRegistrationPage().fillCode(randomClient.getConfirmationCode());
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().checkThirdStepFinishLoading();
        clientPages.getRegistrationPage().fillPassword(randomClient.getPassword());
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().checkInvalidPasswordNotification();
        // not match notification
        clientPages.getRegistrationPage().fillPasswordConfirmation(randomClient.getPassword());
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().checkFourthStepByPhoneFinishLoading(randomClient.getPhoneNumber());
        clientPages.getRegistrationPage().fillName(randomClient.getName());
        clientPages.getRegistrationPage().fillSurname(randomClient.getSurname());
        clientPages.getRegistrationPage().fillPatronymicName(randomClient.getPatronymicName());
        clientPages.getRegistrationPage().fillEmail(randomClient.getEmail());
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().checkFinishState();  //no buttons
        clientPages.getHomePage().checkInitialGuide();
        // InitialModal - put in another component?
        clientPages.getHomePage().clickLaterInitialModal();
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
        //TODO profile check
    }

    @Test
    @Order(2)
    @Tags({@Tag("regression"), @Tag("client"), @Tag("registration"), @Tag("positive")})
    @DisplayName("Регистрация клиента по email")
    void registrationClientByEmail() {
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
        clientPages.getLandingPage().signUpClient();
        clientPages.getRegistrationPage().checkFirstStepFinishLoading();
        clientPages.getRegistrationPage().byEmail(randomClient.getEmail());
        clientPages.getRegistrationPage().checkboxNotCheckedCState();
        clientPages.getRegistrationPage().clickCheckbox();
        clientPages.getRegistrationPage().checkboxCheckedCState();
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().checkSecondStepFinishLoading();
        clientPages.getRegistrationPage().fillCode(randomClient.getConfirmationCode());
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().checkThirdStepFinishLoading();
        clientPages.getRegistrationPage().fillPassword(randomClient.getPassword());
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().checkInvalidPasswordNotification();
        clientPages.getRegistrationPage().fillPasswordConfirmation(randomClient.getPassword());
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().checkFourthStepByEmailFinishLoading(randomClient.getEmail());
        clientPages.getRegistrationPage().fillName(randomClient.getName());
        clientPages.getRegistrationPage().fillSurname(randomClient.getSurname());
        clientPages.getRegistrationPage().fillPatronymicName(randomClient.getPatronymicName());
        clientPages.getRegistrationPage().fillPhoneNumber(randomClient.getPhoneNumber());
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().checkFinishState();
        clientPages.getHomePage().checkInitialGuide();
        // InitialModal - put in another component?
        clientPages.getHomePage().clickLaterInitialModal();
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
        //TODO profile check
    }

    @Test
    @Order(3)
    @Tags({@Tag("regression"), @Tag("client"), @Tag("registration"), @Tag("positive")})
    @DisplayName("Регистрация клиента со Сгенерированным паролем по телефону")
    void registrationWithGeneratedPasswordByPhone() {
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
        clientPages.getLandingPage().signUpClient();
        clientPages.getRegistrationPage().checkFirstStepFinishLoading();
        clientPages.getRegistrationPage().byPhone(randomClient.getPhoneNumber());
        clientPages.getRegistrationPage().checkboxNotCheckedCState();
        clientPages.getRegistrationPage().clickCheckbox();
        clientPages.getRegistrationPage().checkboxCheckedCState();
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().checkSecondStepFinishLoading();
        clientPages.getRegistrationPage().fillCode(randomClient.getConfirmationCode());
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().checkThirdStepFinishLoading();
        clientPages.getRegistrationPage().generatePassword();
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().checkFourthStepByPhoneFinishLoading(randomClient.getPhoneNumber());
        clientPages.getRegistrationPage().fillName(randomClient.getName());
        clientPages.getRegistrationPage().fillSurname(randomClient.getSurname());
        clientPages.getRegistrationPage().fillPatronymicName(randomClient.getPatronymicName());
        clientPages.getRegistrationPage().fillEmail(randomClient.getEmail());
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().checkFinishState();
        clientPages.getHomePage().checkInitialGuide();
        clientPages.getHomePage().clickLaterInitialModal();
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
        //TODO profile check
    }



    @ValueSource(strings = { "user@example.c", "user@sub.sub.sub.com" })
    @DisplayName("Регистрация клиента с допустимым email: ")
    @ParameterizedTest (name = "Убедиться, что при вводе допустимого  email: {0} возможна регистрация")
    @Order(4)
    @Tags({@Tag("regression"), @Tag("client"), @Tag("registration"), @Tag("positive")})
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

    @CsvSource(value = {
            "user| Поле E-Mail должно быть действительным электронным адресом.| email without @",
            "user@| Поле E-Mail должно быть действительным электронным адресом.| email without domain",
            "user@example| Поле E-Mail   должно быть действительным электронным адресом.| email without domain extension",
            "user@example.| Поле E-Mail должно быть действительным электронным адресом.| email without domain extension",
            "user@-example.| Поле E-Mail должно быть действительным электронным адресом.| email with invalid domain",
            "user@example-.| Поле E-Mail должно быть действительным электронным адресом.| email with invalid domain",
            "user@example..| Поле E-Mail должно быть действительным электронным адресом.| email with invalid domain",
            "user@.com.c| Поле E-Mail должно быть действительным электронным адресом.| email with missing top-level domain",
            "user@.com| Поле E-Mail должно быть действительным электронным адресом.| email with invalid domain name",
            "user@example-.com| Поле E-Mail должно быть действительным электронным адресом.| email with invalid domain name",
            "user@-example.com| Поле E-Mail должно быть действительным электронным адресом.| email with invalid domain name",
            "user@example..com| Поле E-Mail должно быть действительным электронным адресом.| email with invalid domain name",
            "user@verylongdomainnameeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee.com| Поле E-Mail должно быть действительным электронным адресом.| email with top-level domain too long",
            "user@example.verylongdomainnameeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee| Поле E-Mail должно быть действительным электронным адресом.| email with top-level domain too long",
            "user@sub-.com | Поле E-Mail должно быть действительным электронным адресом.| email with invalid character in the domain name",
            "user@sub_sub.com | Поле E-Mail должно быть действительным электронным адресом.|email with invalid character in the domain name",
            "user@sub sub.com | Поле E-Mail должно быть действительным электронным адресом.|email with invalid character in the domain name",
            "user@sub;sub.com | Поле E-Mail должно быть действительным электронным адресом.|email with invalid character in the domain name",
            "user@sub:sub.com | Поле E-Mail должно быть действительным электронным адресом.|email with invalid character in the domain name",
            "user@sub'sub.com | Поле E-Mail должно быть действительным электронным адресом.|email with invalid character in the domain name",
            "user@sub?sub.com | Поле E-Mail должно быть действительным электронным адресом.|email with invalid character in the domain name",
            "user@sub*sub.com | Поле E-Mail должно быть действительным электронным адресом.|email with invalid character in the domain name",
            "user@sub(sub.com | Поле E-Mail должно быть действительным электронным адресом.|email with invalid character in the domain name",
            "user@sub)sub.com | Поле E-Mail должно быть действительным электронным адресом.|email with invalid character in the domain name",
            "user@sub[sub.com | Поле E-Mail должно быть действительным электронным адресом.|email with invalid character in the domain name",
            "user@sub]sub.com | Поле E-Mail должно быть действительным электронным адресом.|email with invalid character in the domain name",
            "user@sub{sub.com | Поле E-Mail должно быть действительным электронным адресом.|email with invalid character in the domain name",
            "user@sub}sub.com | Поле E-Mail должно быть действительным электронным адресом.|email with invalid character in the domain name",
            "user@sub=sub.com | Поле E-Mail должно быть действительным электронным адресом.|email with invalid character in the domain name",
            "user@sub+sub.com | Поле E-Mail должно быть действительным электронным адресом.|email with invalid character in the domain name",
            "user@sub$sub.com | Поле E-Mail должно быть действительным электронным адресом.|email with invalid character in the domain name",
            "user@sub!sub.com | Поле E-Mail должно быть действительным электронным адресом.|email with invalid character in the domain name"
            }, delimiter = '|'

    )
    @ParameterizedTest (name = "Убедиться, что при вводе невалидного email: {0} появляется ошибка: {1}")
    @Order(5)
    @Tags({@Tag("regression"), @Tag("client"), @Tag("registration"), @Tag("negative")})
    @DisplayName("Регистрация клиента с невалидным email: ")
    void registrationClientByInvalidEmail( String invalidEmail, String errorText ){
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
        clientPages.getLandingPage().signUpClient();
        clientPages.getRegistrationPage().checkFirstStepFinishLoading();
        clientPages.getRegistrationPage().byEmail(invalidEmail);
        clientPages.getRegistrationPage().checkboxNotCheckedCState();
        clientPages.getRegistrationPage().clickCheckbox();
        clientPages.getRegistrationPage().checkboxCheckedCState();
        clientPages.getRegistrationPage().clickNext();
//        clientPages.getRegistrationPage().checkInvalidEmailError(invalidEmail, errorText, errorDescription);
        clientPages.getRegistrationPage().checkInvalidEmailError(invalidEmail, errorText);
    }





    /*@CsvFileSource(resources = "/resources/invalidPhoneNumbers.csv", numLinesToSkip = 1, delimiter = '|')
    @ParameterizedTest (name = "Убедиться, что при вводе невалидного номера телефона: {0} появляется ошибка: {1}")
    @Tags({@Tag("regression"), @Tag("client"), @Tag("registration"), @Tag("negative")})
    @DisplayName("Регистрация клиента с невалидным номером телефона")
    void registrationClientByInvalidPhoneNumber( String invalidPhoneNumber, String errorText, String errorDescription){
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
        clientPages.getLandingPage().signUpClient();
        clientPages.getRegistrationPage().checkFirstStepFinishLoading();
        clientPages.getRegistrationPage().byPhone(invalidPhoneNumber);
        clientPages.getRegistrationPage().checkboxNotCheckedCState();
        clientPages.getRegistrationPage().clickCheckbox();
        clientPages.getRegistrationPage().checkboxCheckedCState();
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().checkInvalidPhoneNumberError(invalidPhoneNumber, errorText, errorDescription);
    }*/
// TODO registration cases - all fields are empty, checkbox uncheked
}
