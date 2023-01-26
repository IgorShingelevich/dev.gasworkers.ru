package tests.registration;

import extension.browser.Browser;
import model.Role;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
    @DisplayName("Регистрация клиента по телефону")
    void registrationClientByPhone() {
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
        clientPages.getLandingPage().signUpClient();
        clientPages.getRegistrationPage().checkFirstStepFinishLoading();
        clientPages.getRegistrationPage().byPhone(randomClient.getPhoneNumber());
        clientPages.getRegistrationPage().checkSecondStepFinishLoading();
//        clientPages.getRegistrationPage().fillCode("");
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().checkThirdStepFinishLoading();
        clientPages.getRegistrationPage().fillPassword(randomClient.getPassword());
        clientPages.getRegistrationPage().fillPasswordConfirmation(randomClient.getPassword());
        clientPages.getRegistrationPage().clickNext();
        clientPages.getRegistrationPage().checkFourthStepFinishLoading(randomClient.getPhoneNumber());
        clientPages.getRegistrationPage().fillName(randomClient.getName());
        clientPages.getRegistrationPage().fillSurname(randomClient.getSurname());
        clientPages.getRegistrationPage().fillPatronymicName(randomClient.getPatronymicName());
        clientPages.getRegistrationPage().fillEmail(randomClient.getEmail());
        clientPages.getRegistrationPage().clickNext();
        clientPages.getHomePage().checkInitialModal();
        // InitialModal - put in another component?
        clientPages.getHomePage().laterInitialModal();
        clientPages.getHomePage().checkInitialState();




    }

    @Test
    @DisplayName("Регистрация клиента по email")
    void registrationClientByEmail() {
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
        clientPages.getLandingPage().signUpClient();
        clientPages.getRegistrationPage().checkFirstStepFinishLoading();
        clientPages.getRegistrationPage().byEmail(randomClient.getEmail());
        clientPages.getRegistrationPage().checkSecondStepFinishLoading();


//        Selenide.sleep(20000);
    }













}
