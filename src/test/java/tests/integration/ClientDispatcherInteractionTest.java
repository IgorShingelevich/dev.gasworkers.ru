package tests.integration;

import extension.browser.Browser;
import model.Role;
import model.client.ClientOrderType;
import org.junit.jupiter.api.Test;
import pages.context.ClientPages;
import pages.context.DispatcherPages;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;

class ClientDispatcherInteractionTest extends TestBase {

    @Browser(role = Role.CLIENT, browserSize = "900x1000", browserPosition = "0x0")
    ClientPages clientPages;

    @Browser(role = Role.DISPATCHER, browserSize = "900x1000", browserPosition = "900x0")
    DispatcherPages dispatcherPages;

    String emailClient = "shingelevich@gmail.com",
        passwordClient = "123456";

    String emailDispatcher = "test_gw_dispatcher_sssr1@rambler.ru",
        passwordDispatcher = "123456";

    String emailMaster = "test_gas_master1@rambler.ru",
        passwordMaster = "123456";

    String smsCode = "123456";

    @Test
    public void testInteractionTwoRoles() {
        step("Client steps - place Order", () -> {
            clientPages.getLoginPage()
                    .open()
                    .login(emailClient, passwordClient);
            clientPages.getHomePage()
                    .checkFinishLoading()
                    .clickPlaceOrderButton();

            ClientOrderType orderType = ClientOrderType.MAINTENANCE;
            clientPages.getTypeOrdersPage().selectOrderType(orderType);
            clientPages.getInfoTypeOrderPage()
                    .checkTitle(orderType.getExpectedTitle())
                    .checkStepSequence(orderType.getExpectedStepSequence())
                    .clickNextButton();

            clientPages.getSelectObjectMaintenancePage().selectObjectByIndex(0);

            clientPages.getSelectDateMaintenancePage().pickNowDateAM();
            clientPages.getSelectDateMaintenancePage().submitOrder();
        });


        step("Dispatcher steps - Accept Order ", () -> {
        dispatcherPages.getLoginPage()
                .open()
                .login(emailDispatcher, passwordDispatcher);

        });


        sleep(5_000);




//        selectServicePage.isOpened();
//        selectServicePage.toOrder();
//        OrderCardClientPage.isOpened();
//        OrderCardClientPage.popUpClose();
//        OrderCardClientPage.getOrderNumber();
//        System.out.println("currentOrderNumber = " + OrderCardClientPage.getOrderNumber());
//        String currentOrderNumber = OrderCardClientPage.getOrderNumber();
//        OrderCardClientPage.toMap();
//        selectServicePage.isOpened();
//
//        WebDriverRunner.setWebDriver(driverDispatcher);
//
//        homeDispatcherPage.isOpened();
//        homeDispatcherPage.switchToListView();
//        homeDispatcherPage.popUpClose();
//
//        homeDispatcherPage.openOrderByNumber(currentOrderNumber);
//        orderCardPage.isOpened();
//        homeDispatcherPage.popUpClose();
//
//        WebDriverRunner.setWebDriver(driverClient);
//        selectServicePage.isOpened();
//        selectServicePage.waitForResponses();
//
//        selectServicePage.reviewFirstService();
//        selectInsurancePage.isOpened();
//        selectInsurancePage.next();
//        checkDocumentsClientPage.isOpened();
//        checkDocumentsClientPage.makeContract();
//        selectPaymentClientPage.isOpened();
//        selectPaymentClientPage.paySPB();
//        paymentWizardClientPage.isOpened();
//        paymentWizardClientPage.getQRCode();
//        signSMSClientPage.isOpened();
//        signSMSClientPage.inputSMSCode(smsCode);
    }

}
