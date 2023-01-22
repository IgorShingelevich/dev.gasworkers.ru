package tests.integration;

import extension.browser.Browser;
import model.Role;
import model.client.OrderStatus;
import model.client.OrderType;
import model.client.ClientRequestType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.context.ClientPages;
import pages.context.DispatcherPages;
import pages.context.MasterPages;
import tests.BaseTest;
import utils.User;

import java.util.NoSuchElementException;


import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;

class ClientDispatcherInteractionTest extends BaseTest {

    @Browser(role = Role.CLIENT, browserSize = "800x1000", browserPosition = "0x0")
    ClientPages clientPages;

    @Browser(role = Role.DISPATCHER, browserSize = "800x1000", browserPosition = "850x0")
    DispatcherPages dispatcherPages;

    @Browser(role = Role.MASTER, browserSize = "800x1000", browserPosition = "1700x0")
    MasterPages masterPages;

     User client = new User(
         "Игорь",
         "Сергеевич",
         "Шингелевич",
         "shingelevich@gmail.com",
         "123456",
         null,
         79288010225L

    );

     User dispatcher = new User(
        "ДиспетчерСССР1",
        "ДиспетчеровичСССР1",
        "ДиспетчеровСССР1",
        "test_gw_dispatcher_sssr1@rambler.ru",
        "123456",
        null,
        79288010225L
    );

     User master = new User(
         "Мастер1СССР",
         "Мастерович1СССР",
         "Мастеров1СССР",
         " test_gas_master_sssr1@rambler.ru",
         "123456",
         null,
         79917644241L
    );


   /* String emailDispatcher = "test_gw_dispatcher_sssr1@rambler.ru",
        passwordDispatcher = "123456";

    String emailMaster = "test_gas_master1@rambler.ru",
        passwordMaster = "123456";*/


//    String currentOrderNumber = OrderCardClientPage.getTitleNumber();



    @DisplayName(" ТО Интеграция Клиент-Диспетчер-Клиент-Диспетчер")
    @Test
    public void IntegrationDispatcherAcceptClientMaintenanceRequest() throws NoSuchElementException {
        step("авторизация Клиента", () -> {
            clientPages.getLoginPage().open().login(client.email, client.password);
            clientPages.getHomePage().checkFinishLoading();
        });

        step("авторизация Диспетчера", () -> {
            dispatcherPages.getLoginPage().open().login(dispatcher.email, dispatcher.password);
            dispatcherPages.getHomePage().checkFinishLoading();
        });

        step("авторизация Мастера", () -> {
            masterPages.getLoginPage().open().login(master.email, master.password);
            masterPages.getHomePage().checkFinishLoading();
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
            clientPages.getSelectServicePage().toOrderCard();
            clientPages.getOrderCardPage().checkFinishLoading();
            clientPages.getOrderCardPage().sidebar.home();
            clientPages.getHomePage().checkFinishLoading();
            clientPages.getHomePage().popUpClose();
            clientPages.getHomePage().lastOrderProfileClientComponent.lastOrderCard();
            clientPages.getOrderCardPage().checkFinishLoading();
//            clientPages.getOrderCardPage().checkFinishLoading();
            //get order number from order card to check it in dispatcher

//            clientPages.getSelectServicePage().popUpClose();
            step("Убедиться, что тип Заказа: ", () -> {
                OrderType orderType = OrderType.MAINTENANCE;
                clientPages.getOrderCardPage().checkOrderType(orderType);
                // compare .checkOrderType and .checkOrderStatus what is better? more universal?
            });
            step("Убедиться, что статус Заказа: ", () -> {
                OrderStatus orderStatus = OrderStatus.NEW_ORDER;
                clientPages.getOrderCardPage().checkOrderStatusNew(orderStatus);
                // how to make .checkOrderStatus  universal and also check corresponded buttons
            });
            clientPages.getOrderCardPage().clickOffersBlock();
            clientPages.getSelectServicePage().checkFinishLoading();
        });

        step("Диспетчер принимает заказ на ТО ", () -> {
            dispatcherPages.getHomePage().switchToListView();
            dispatcherPages.getHomePage().openOrderByIndex(0);
            dispatcherPages.getOrderCardPage().checkFinishLoading();
            //check OrderStatus NEW_TENDER
            dispatcherPages.getHomePage().popUpClose();
            dispatcherPages.getOrderCardPage().acceptOrder();
            dispatcherPages.getOrderCardPage().checkParticipateTheTenderStatus(OrderStatus.NEW_TENDER);
            // check OrderStatus gray button- Уже участвуете
        });

        step("Клиент принимает предложение Диспетчера", () -> {
            //start from home page - need OrderNumber
            clientPages.getSelectServicePage().waitForResponses();
            //check that quantity of responses in ServiceTabs is equal to number in ResponseCountBlock
            clientPages.getSelectServicePage().toOrderCard();
            clientPages.getOrderCardPage().checkFinishLoading();
            clientPages.getOrderCardPage().popUpClose();
            //go back to order card and check that quantity of responses in ServiceTabs is equal to number in ResponseCountBlock
            clientPages.getOrderCardPage().clickOffersBlock();
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
            //driver.back();  // not working
            // check that Filial is not empty or set the Filial if it is empty
            // check that Address fnd Passport is not empty or set the Address and Passport if it is empty
            clientPages.getCheckDocumentsPage().makeContract();
            clientPages.getSelectPaymentPage().checkFinishLoading();
            clientPages.getSelectPaymentPage().paySPB();
            clientPages.getPaymentWizardPage().checkFinishLoading();
            clientPages.getPaymentWizardPage().getQRCode();
            clientPages.getSignSMSPage().checkFinishLoading();
//            client.getCodeFromNewSMS();
//            Integer firstSMSCode = client.getCodeFromNewSMS();
//            clientPages.getSignSMSPage().inputSMSCode(firstSMSCode);
//            clientPages.getSignSMSPage().sign();
            clientPages.getSignSuccsessPage().checkFinishLoading();
            clientPages.getSignSuccsessPage().toHomePage();
            clientPages.getHomePage().checkFinishLoading();
            clientPages.getHomePage().popUpClose();
            clientPages.getHomePage().lastOrderProfileClientComponent.lastOrderCard();
            clientPages.getOrderCardPage().checkFinishLoading();
            clientPages.getOrderCardPage().checkOrderStatusScheduleVisit(OrderStatus.SCHEDULE_VISIT);
        });

        step("Диспетчер выбирает время и назначает Мастера", () -> {
            // start section from home page - need OrderNumber
            dispatcherPages.getOrderCardPage().checkFinishLoading();
            dispatcherPages.getOrderCardPage().popUpClose();
            dispatcherPages.getOrderCardPage().sidebar.allOrders();
            dispatcherPages.getHomePage().checkFinishLoading();
            dispatcherPages.getHomePage().switchToListView();
            dispatcherPages.getHomePage().openOrderByIndex(0);
            dispatcherPages.getOrderCardPage().checkFinishLoading();
            dispatcherPages.getOrderCardPage().checkOrderStatusScheduleVisit(OrderStatus.SCHEDULE_VISIT);
            dispatcherPages.getOrderCardPage().selectTimeButton();
            dispatcherPages.getOrderCardPage().datePicker.selectNowDateAndTime();
            dispatcherPages.getOrderCardPage().selectMaster();
            dispatcherPages.getSelectMasterPage().checkFinishLoading();
            dispatcherPages.getSelectMasterPage().selectNewMasterByIndex(0);
            dispatcherPages.getOrderCardPage().checkFinishLoading();
            dispatcherPages.getOrderCardPage().checkMasterDispatchedStatus(OrderStatus.MASTER_DISPATCHED);
        });

        sleep(5_000);
    }

}
