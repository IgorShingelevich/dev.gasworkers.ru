package tests.integration;

import com.codeborne.selenide.Selenide;
import extension.browser.Browser;
import model.Role;
import model.client.OrderStatus;
import model.client.OrderType;
import model.client.ClientRequestType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.context.ClientPages;
import pages.context.DispatcherPages;
import ru.sms_activate.error.base.SMSActivateBaseException;
import tests.TestBase;
import utils.User;

import java.util.NoSuchElementException;


import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;

class ClientDispatcherInteractionTest extends TestBase {

    @Browser(role = Role.CLIENT, browserSize = "800x1000", browserPosition = "0x0")
    ClientPages clientPages;

    @Browser(role = Role.DISPATCHER, browserSize = "800x1000", browserPosition = "850x0")
    DispatcherPages dispatcherPages;

     User client = new User(
         "Игорь",
         "Сергеевич",
         "Шингелевич",
         "shingelevich@gmail.com",
         "123456",
         null,
         79288010225L
    );

    /*String emailClient = "shingelevich@gmail.com",
        passwordClient = "123456";
*/
    String emailDispatcher = "test_gw_dispatcher_sssr1@rambler.ru",
        passwordDispatcher = "123456";

    String emailMaster = "test_gas_master1@rambler.ru",
        passwordMaster = "123456";


//    String currentOrderNumber = OrderCardClientPage.getTitleNumber();



    @DisplayName(" ТО Интеграция Клиент-Диспетчер-Клиент-Диспетчер")
    @Test
    public void IntegrationDispatcherAcceptClientMaintenanceRequest() throws NoSuchElementException {
        step("Авторизация клиента", () -> {
            clientPages.getLoginPage().open().login(client.email, client.password);
            clientPages.getHomePage().checkFinishLoading();
        });

        step("Авторизация диспетчера", () -> {
            dispatcherPages.getLoginPage().open().login(emailDispatcher, passwordDispatcher);
            dispatcherPages.getHomePage().checkFinishLoading();
        });

        step("Клиент размещает заказ на ТО", () -> {
            clientPages.getHomePage().clickPlaceOrderButton();
            clientPages.getTypeOrdersPage().selectOrderType(ClientRequestType.MAINTENANCE); //  .toString()
            clientPages.getInfoTypeOrderPage().clickNextButton();
//                    .checkTitle("Заказ на ТО")
//                    .checkStepSequence("Шаг 1 из 3")
            clientPages.getSelectObjectMaintenancePage().selectObjectByIndex(0);
            clientPages.getSelectDateMaintenancePage().pickNowDateAM();
            clientPages.getSelectDateMaintenancePage().submitOrder();
            clientPages.getSelectServicePage().checkFinishLoading();
            clientPages.getSelectServicePage().toOrder();
            clientPages.getOrderCardPage().checkFinishLoading();
            clientPages.getOrderCardPage().sidebar.home();
            clientPages.getHomePage().checkFinishLoading();
            clientPages.getHomePage().popUpClose();
            clientPages.getHomePage().lastOrderProfileClientComponent.lastOrderCard();
            clientPages.getOrderCardPage().checkFinishLoading();
//            clientPages.getOrderCardPage().checkFinishLoading();
            //get order number from order card to check it in dispatcher

//            clientPages.getSelectServicePage().popUpClose();
            step("Убедиться, что тип Заказа {orderType}", () -> {
                OrderType orderType = OrderType.MAINTENANCE;
                clientPages.getOrderCardPage().checkOrderType(orderType);
                // compare .checkOrderType and .checkOrderStatus what is better? more universal?
            });
            step("Убедиться, что статус Заказа {orderStatus}", () -> {
                clientPages.getOrderCardPage().checkOrderStatus(OrderStatus.NEW_TENDER);
                // how to make .checkOrderStatus  universal and also check corresponded buttons
            });
            clientPages.getOrderCardPage().clickOffersBlock();
            clientPages.getSelectServicePage().checkFinishLoading();
        });

        step("Диспетчер принимает заказ ", () -> {
            dispatcherPages.getHomePage().switchToListView();
            dispatcherPages.getHomePage().openOrderByIndex(0);
            dispatcherPages.getOrderCardPage().checkFinishLoading();
            dispatcherPages.getHomePage().popUpClose();
            dispatcherPages.getOrderCardPage().acceptOrder();
        });

        step("Клиент принимает предложение диспетчера", () -> {
            clientPages.getSelectServicePage().waitForResponses();
            //!! fall when go straight to .proceedWithFirstService() - cannot push button
            //check that quantity of responses in ServiceTabs is equal to number in ResponseCountBlock
            clientPages.getSelectServicePage().toOrder();
            clientPages.getOrderCardPage().checkFinishLoading();
            sleep(4000);
            clientPages.getOrderCardPage().popUpClose();
//            sleep(4000);
            //go back to order card and check that quantity of responses in ServiceTabs is equal to number in ResponseCountBlock
            clientPages.getOrderCardPage().clickOffersBlock();
            //!! fall because .showOnMap button is grayed out - mooved to .clickOffersBlock()
            clientPages.getSelectServicePage().checkFinishLoading();
            // find on the map
            //check Price with Insurance and Primary Visit Price
            //review first response Service Company
            // check first Master info Card
            clientPages.getSelectServicePage().proceedWithFirstService();
            clientPages.getSelectInsurancePage().checkFinishLoading();
            // toggle price - check wo Insurance and w Insurance difference
            //set w Insurance
            clientPages.getSelectInsurancePage().next();
            clientPages.getCheckDocumentsPage().checkFinishLoading();
//            driver.back();  // not working
            // check that Filial is not empty or set the Filial if it is empty
            // check that Address fnd Passport is not empty or set the Address and Passport if it is empty
            sleep(4000);
            clientPages.getCheckDocumentsPage().makeContract();
            clientPages.getSelectPaymentPage().checkFinishLoading();
            clientPages.getSelectPaymentPage().paySPB();
            clientPages.getPaymentWizardPage().checkFinishLoading();
            clientPages.getPaymentWizardPage().getQRCode();
            clientPages.getSignSMSPage().checkFinishLoading();
            client.getCodeFromNewSMS();
            Integer firstSMSCode = client.getCodeFromNewSMS();
            clientPages.getSignSMSPage().inputSMSCode(firstSMSCode);
            clientPages.getSignSMSPage().sign();
            sleep(5_000);








        });



















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
//        client.getCodeFromNewSMS();

//        signSMSClientPage.inputSMSCode (client.smsCode);

    }

}
