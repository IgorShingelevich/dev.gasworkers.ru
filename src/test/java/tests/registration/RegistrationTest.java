package tests.registration;

import extension.browser.Browser;
import model.Role;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import pages.context.ClientPages;
import pages.context.DispatcherPages;
import pages.context.MasterPages;
import tests.BaseTest;
import utils.UserRandom;


public class RegistrationTest extends BaseTest {

    @Browser(role = Role.CLIENT, browserSize = "800x1000", browserPosition = "0x0")
    ClientPages clientPages;

    @Browser(role = Role.DISPATCHER, browserSize = "800x1000", browserPosition = "850x0")
    DispatcherPages dispatcherPages;

    @Browser(role = Role.MASTER, browserSize = "800x1000", browserPosition = "1700x0")
    MasterPages masterPages;

    UserRandom randomClient = new UserRandom();
    UserRandom randomDispatcher = new UserRandom();
    UserRandom randomMaster = new UserRandom();

    @Test
    // tags
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
//        clientPages.getRegistrationPage().fillCode("");
        clientPages.getRegistrationPage().clickNext();  //manual code input
        clientPages.getRegistrationPage().checkThirdStepFinishLoading();
        clientPages.getRegistrationPage().fillPassword(randomClient.getPassword());
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
//        clientPages.getRegistrationPage().fillCode("");
        //check nestButton active state
        clientPages.getRegistrationPage().clickNext();  //manual code input
        clientPages.getRegistrationPage().checkThirdStepFinishLoading();
        clientPages.getRegistrationPage().fillPassword(randomClient.getPassword());
        // not match notification
        clientPages.getRegistrationPage().fillPasswordConfirmation(randomClient.getPassword());
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().checkFourthStepByEmailFinishLoading(randomClient.getEmail());
        clientPages.getRegistrationPage().fillName(randomClient.getName());
        clientPages.getRegistrationPage().fillSurname(randomClient.getSurname());
        clientPages.getRegistrationPage().fillPatronymicName(randomClient.getPatronymicName());
        clientPages.getRegistrationPage().fillPhoneNumber(randomClient.getPhoneNumber());
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

    @CsvSource(value = {
            "user| Поле E-Mail должно быть действительным электронным адресом.| email without @",
            "user@| Поле E-Mail должно быть действительным электронным адресом.| email without domain",
            "user@example| Поле E-Mail   должно быть действительным электронным адресом.| email without domain extension",
            "user@example.| Поле E-Mail должно быть действительным электронным адресом.| email without domain extension",
            "user@-example.| Поле E-Mail должно быть действительным электронным адресом.| email with invalid domain",
            "user@example-.| Поле E-Mail должно быть действительным электронным адресом.| email with invalid domain",
            "user@example..| Поле E-Mail должно быть действительным электронным адресом.| email with invalid domain",
//            "user@example.c| Поле E-Mail должно быть действительным электронным адресом.| email with missing top-level domain",  //excluded
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
//            "user@sub.sub.sub.com | Поле E-Mail должно быть действительным электронным адресом.|email with invalid character in the domain name",  //excluded
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
            //ignore line 1

    )
            @ParameterizedTest (name = "Убедиться, что при вводе невалидного email: {0} появляется ошибка: {1}")
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

    @CsvFileSource(resources = "/resources/invalidPhoneNumbers.csv", numLinesToSkip = 1, delimiter = '|')
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
    }





// TODO registration cases - all fields are empty, checkbox uncheked








}
