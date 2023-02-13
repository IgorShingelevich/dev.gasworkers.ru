package ru.gasworkers.dev.tests.integration;

import ru.gasworkers.dev.browser.Browser;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.OrderType;
import ru.gasworkers.dev.model.client.ClientRequestType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.pages.context.DispatcherPages;
import ru.gasworkers.dev.pages.context.MasterPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
            "Зарегистрирован с 15 ноября 2022 года",
            "shingelevich@gmail.com",
            "123456",
            null,
            79312534936L);

    User dispatcher = new User(
            "ДиспетчерСССР1",
            "ДиспетчеровичСССР1",
            "ДиспетчеровСССР1",
            "не указано для данной роли",
            "test_gw_dispatcher_sssr1@rambler.ru",
            "123456",
            null,
            79288010225L);

    User master = new User(
            "Мастер1СССР",
            "Мастерович1СССР",
            "Мастеров1СССР",
            "Зарегистрирован с 11 января 2023 года",
            " test_gas_master_sssr1@rambler.ru",
            "123456",
            null,
            79917644241L);

    @Test
    @Feature("Интерграция ролей")
    @Story("Интерграция Путь ТО")
    @DisplayName(" ТО Интеграция Клиент-Диспетчер-Клиент-Диспетчер")  // whu name 2 times in Allure?
    void integrationDispatcherAcceptClientMaintenanceRequest() {
        step("авторизация Клиента", () -> {
            clientPages.getLoginPage().open().login(client.email, client.password);
            clientPages.getHomePage().checkFinishLoading(client.fullName, client.sinceDate);
        });

        step("авторизация Диспетчера", () -> {
            dispatcherPages.getLoginPage().open().login(dispatcher.email, dispatcher.password);
            dispatcherPages.getHomePage().checkFinishLoading();
        });

        step("авторизация Мастера", () -> {
            masterPages.getLoginPage().open().login(master.email, master.password);
            masterPages.getHomePage().checkFinishLoading();
        });

        step("Test run credentials ", () -> {
            Allure.addAttachment("Client creds", client.fullName + ": " + client.email + "/" + client.password);
            Allure.addAttachment("Dispatcher creds", dispatcher.fullName + ": " + dispatcher.email + "/" + dispatcher.password);
            Allure.addAttachment("Master creds", master.fullName + ": " + master.email + "/" + master.password);
            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
            Allure.addAttachment("RunStartTime: ", date);
        });


        String orderNumber = step("Клиент размещает заказ на ТО", () -> {
            clientPages.getHomePage().clickPlaceOrderButton();
            clientPages.getTypeOrdersPage().selectOrderType(ClientRequestType.MAINTENANCE); //  .toString()
            clientPages.getInfoTypeOrderPage().clickNextButton();
//                    .checkTitle("Заказ на ТО")
//                    .checkStepSequence("Шаг 1 из 3")
//            step("Клиент выбирает объект", () -> {
                clientPages.getSelectObjectMaintenancePage().checkFinishLoading();
                clientPages.getSelectObjectMaintenancePage().selectObjectByIndex(0);
//            });
//            step("Клиент выбирает дату и время", () -> {
                clientPages.getSelectDateMaintenancePage().checkFinishLoading();
                clientPages.getSelectDateMaintenancePage().pickNowDateAM();
                clientPages.getSelectDateMaintenancePage().submitOrder();
//            });
            clientPages.getSelectServicePage().checkFinishLoading();
//            String orderNumber = step("Клиент просматривает заказ на домашней странице", () -> {
                clientPages.getSelectServicePage().toOrderCard();
                clientPages.getOrderCardPage().checkFinishLoading();
                String currentOrderNumber = clientPages.getOrderCardPage().getOrderNumber();
                clientPages.getOrderCardPage().sidebar.home();
                clientPages.getHomePage().checkFinishLoading(client.fullName, client.sinceDate);
                clientPages.getHomePage().popUpClose();
//            });
//            step("Клиент просматривает заказа в карточке объекта", () -> {
            clientPages.getHomePage().lastOrderComponent.lastOrderCard();
            clientPages.getOrderCardPage().checkFinishLoading();
            clientPages.getOrderCardPage().checkPublishedState(OrderStatus.PUBLISHED, OrderType.MAINTENANCE);
            //check notification - orderPublished
//            });
            clientPages.getOrderCardPage().clickOffersBlock();
            clientPages.getSelectServicePage().checkFinishLoading();
            return currentOrderNumber;
        });

        step("Диспетчер принимает заказ на ТО ", () -> {
            dispatcherPages.getHomePage().switchToListView();
            dispatcherPages.getHomePage().openOrderByNumber(orderNumber);
            dispatcherPages.getOrderCardPage().checkFinishLoading();
            dispatcherPages.getOrderCardPage().popUpClose();
            dispatcherPages.getOrderCardPage().checkNewTenderState(OrderStatus.NEW_TENDER, OrderType.MAINTENANCE);
            dispatcherPages.getOrderCardPage().acceptOrder();
            dispatcherPages.getOrderCardPage().checkParticipateTenderState(OrderStatus.PARTICIPATE_TENDER, OrderType.MAINTENANCE);
        });

        step("Клиент принимает предложение Диспетчера", () -> {
            //check notification - ServiceReady
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
//            driver.back();
            // check that Filial is not empty or set the Filial if it is empty
            // check that Address fnd Passport is not empty or set the Address and Passport if it is empty
//            SmsApi clientSmsApi1 = SmsApi.instance(Role.CLIENT); // for real number

            clientPages.getCheckDocumentsPage().makeContract();
            clientPages.getSelectPaymentPage().checkFinishLoading();
            clientPages.getSelectPaymentPage().paySPB();
            clientPages.getPaymentWizardPage().checkFinishLoading();
            clientPages.getPaymentWizardPage().getQRCode();
            clientPages.getSignSMSPage().checkFinishLoading();
            sleep(600);  //without  this cannot paste the code

//            String sms = clientSmsApi1.waitReceiveNewSms().getText(); // for real number
//            String code = sms.substring(0, 6); // for real number
            String mockCode = "111111";
            clientPages.getSignSMSPage().inputSMSCode(mockCode);
//            clientPages.getSignSMSPage().sign();
            clientPages.getSignSuccessPage().checkFinishLoading();
            clientPages.getSignSuccessPage().toHomePage();
            clientPages.getHomePage().checkFinishLoading(client.fullName, client.sinceDate);
            clientPages.getHomePage().popUpClose();
            clientPages.getHomePage().lastOrderComponent.lastOrderCard();
            clientPages.getOrderCardPage().checkFinishLoading();
            clientPages.getOrderCardPage().checkScheduleVisitState(OrderStatus.SCHEDULE_VISIT, OrderType.MAINTENANCE);
        });

        step("Диспетчер выбирает время и назначает Мастера", () -> {
            // start section from home page - need OrderNumber
            dispatcherPages.getOrderCardPage().checkFinishLoading();
            dispatcherPages.getOrderCardPage().popUpClose(); // too long
            dispatcherPages.getOrderCardPage().sidebar.generalMap();
            dispatcherPages.getHomePage().checkFinishLoading();
            dispatcherPages.getHomePage().switchToListView();
            dispatcherPages.getHomePage().navNew();
            dispatcherPages.getHomePage().searchByNumber(orderNumber);
            dispatcherPages.getHomePage().openOrderByNumber(orderNumber);
            dispatcherPages.getOrderCardPage().checkFinishLoading();
            dispatcherPages.getOrderCardPage().popUpClose();
            dispatcherPages.getOrderCardPage().checkScheduleVisitState(OrderStatus.SCHEDULE_VISIT, OrderType.MAINTENANCE);
            dispatcherPages.getOrderCardPage().selectTimeButton();
            dispatcherPages.getOrderCardPage().datePicker.selectNowDateAndTime();
            dispatcherPages.getOrderCardPage().selectMaster();
            dispatcherPages.getSelectMasterPage().checkFinishLoading();
            dispatcherPages.getSelectMasterPage().selectNewMasterByIndex(0);
            dispatcherPages.getOrderCardPage().checkFinishLoading();
            dispatcherPages.getOrderCardPage().checkMasterDispatchedState(OrderStatus.MASTER_DISPATCHED, OrderType.MAINTENANCE);
        });

        step("Мастер открывает заказ", () -> {
            masterPages.getHomePage().checkFinishLoading();
            masterPages.getHomePage().popUpClose();
            masterPages.getHomePage().sidebar.allOrdersHistoryDropdown();
            masterPages.getHomePage().sidebar.allNewOrders();
            masterPages.getAllNewOrdersPage().checkFinishLoading();
            masterPages.getAllNewOrdersPage().switchToTabView();
            masterPages.getAllNewOrdersPage().openByNumber(orderNumber);
            masterPages.getOrderCardPage().checkFinishLoading();
            masterPages.getOrderCardPage().checkMasterDispatchedOrderState(OrderStatus.MASTER_DISPATCHED, OrderType.MAINTENANCE);


        });

        step("Мастер открывает объект Клиента", () -> {
        });

        step("Мастер приступает к работе", () -> {
        });

        step("Мастер добавляет позицию в  таблицу Оборудования ТО", () -> {
        });

        step("Мастер подтверждает выполненную работу", () -> {
        });

        step("Клиент оставляет отзыв", () -> {
        });

        step("Мастер оставляет отзыв", () -> {
        });

    }

}