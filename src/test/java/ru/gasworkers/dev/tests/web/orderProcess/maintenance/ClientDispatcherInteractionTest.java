package ru.gasworkers.dev.tests.web.orderProcess.maintenance;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.allure.AllureStory;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.ServiceType;
import ru.gasworkers.dev.model.browser.PositionBrowser;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.model.client.ClientRequestType;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.pages.context.DispatcherPages;
import ru.gasworkers.dev.pages.context.MasterPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.UserBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;

@Disabled
class ClientDispatcherInteractionTest extends BaseTest {

    @Browser(role = Role.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.FIRST_ROLE)
    ClientPages clientPages;

    @Browser(role = Role.DISPATCHER, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.SECOND_ROLE)
    DispatcherPages dispatcherPages;

    @Browser(role = Role.MASTER, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.THIRD_ROLE)
    MasterPages masterPages;

    UserBuilder client = new UserBuilder(
            "Игорь",
            "Сергеевич",
            "Шингелевич",
            "Зарегистрирован с 15 ноября 2022 года",
            "shingelevich@gmail.com",
            "123456",
            null,
            79312534936L);

    UserBuilder dispatcher = new UserBuilder(
            "ДиспетчерСССР1",
            "ДиспетчеровичСССР1",
            "ДиспетчеровСССР1",
            "не указано для данной роли",
            "test_gw_dispatcher_sssr1@rambler.ru",
            "123456",
            null,
            79288010225L);

    UserBuilder master = new UserBuilder(
            "Мастер1СССР",
            "Мастерович1СССР",
            "Мастеров1СССР",
            "Зарегистрирован с 11 января 2023 года",
            "test_gas_master_sssr1@rambler.ru",
            "123456",
            null,
            79917644241L);

    @Test
    @Owner("Igor Shingelevich")
    @Epic("Клиент-Диспетчер-Мастер")
    @Feature("Минимально позитивный путь")
    @Story(AllureStory.MAINTENANCE)
    @Tags({@Tag(AllureTag.REGRESSION),  @Tag(AllureTag.POSITIVE)})
    @DisplayName(" ТО Интеграция Клиент-Диспетчер-Мастер")
    void integrationDispatcherAcceptClientMaintenanceRequest() {
        step("авторизация Ролей ", ()-> {
            step("авторизация Клиента", () -> {
                clientPages.getLoginPage().open().login(client.email, client.password);
//            clientPages.getHomePage().checkFinishLoading(client.fullName, client.sinceDate);  // TODO fix fullName order
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
        });


        String orderNumber = step("Клиент размещает заказ на ТО", () -> {
            step("Выбирает тип заказа - ТО", () -> {
                clientPages.getHomePage().checkFinishLoading(client.fullName, client.sinceDate);
                clientPages.getHomePage().clickPlaceOrderButton();
                clientPages.getTypeOrdersPage().selectOrderType(ClientRequestType.MAINTENANCE); //  .toString()
                clientPages.getInfoTypeOrderPage().clickNextButton();
//                    .checkTitle("Заказ на ТО")
//                    .checkStepSequence("Шаг 1 из 3")
            });
            step("Клиент выбирает объект", () -> {
                clientPages.getSelectObjectMaintenancePage().checkFinishLoading();
                clientPages.getSelectObjectMaintenancePage().selectObjectByIndex(0);
            });
            step("Клиент выбирает дату и время", () -> {
                clientPages.getSelectDateMaintenancePage().checkFinishLoading();
                clientPages.getSelectDateMaintenancePage().pickNowDateAM();
                clientPages.getSelectDateMaintenancePage().submitOrder();
            });
            step("Клиент просматривает карту в состоянии заказ опубликован", () -> {
                clientPages.getSelectServicePage().checkFinishLoadingMaintenance();
                clientPages.getSelectServicePage().checkPublishedState();
            });
           /* step("Клиент просматривает опубликованный заказ на домашней странице", () -> {
                 clientPages.getSelectServicePage().toOrderCard();
                 clientPages.getOrderCardPage().checkFinishLoading();
                 clientPages.getOrderCardPage().sidebar.home();
                 clientPages.getHomePage().checkFinishLoading(client.fullName, client.sinceDate);
                 clientPages.getHomePage().popUp.close();
            });*/
            step("Клиент просматривает опубликованный заказа в карточке объекта", () -> {
                clientPages.getSelectServicePage().toOrderCard();
                clientPages.getOrderCardPage().checkFinishLoading();
                clientPages.getOrderCardPage().checkPublishedState(OrderStatus.PUBLISHED, ServiceType.MAINTENANCE);
                //check notification - orderPublished
            });
            String currentOrderNumber = clientPages.getOrderCardPage().getOrderNumber();
            clientPages.getOrderCardPage().offersCounter.clickOffers();
            clientPages.getSelectServicePage().checkFinishLoadingMaintenance();
            return currentOrderNumber;
        });

        step("Диспетчер принимает опубликованный заказ на ТО ", () -> {
            dispatcherPages.getHomePage().checkFinishLoading();
            // todo map check
            dispatcherPages.getHomePage().popUp.close();
            dispatcherPages.getHomePage().switchToListView();
            dispatcherPages.getHomePage().openOrderByNumber(orderNumber);
            dispatcherPages.getOrderCardPage().checkFinishLoading();
            dispatcherPages.getOrderCardPage().popUp.close();
            dispatcherPages.getOrderCardPage().checkNewTenderState(OrderStatus.NEW_TENDER, ServiceType.MAINTENANCE);
            dispatcherPages.getOrderCardPage().acceptOrder();
            dispatcherPages.getOrderCardPage().checkParticipateTenderState(OrderStatus.PARTICIPATE_TENDER, ServiceType.MAINTENANCE);
        });

        step("Клиент получает предложение Диспетчера СК", () -> {
            step("Клиент получает предложение от Диспетчера СК", () -> {
                //check notification - ServiceReady
                clientPages.getSelectServicePage().waitForResponses();
//                clientPages.getSelectServicePage().checkHasOfferState(1);
                //check that quantity of responses in ServiceTabs is equal to number in ResponseCountBlock
                clientPages.getSelectServicePage().toOrderCard();
                clientPages.getOrderCardPage().checkFinishLoading();
                clientPages.getOrderCardPage().popUp.close();
                clientPages.getOrderCardPage().checkReviewOffersState(OrderStatus.PUBLISHED, ServiceType.MAINTENANCE, 1);
                //go back to order card and check that quantity of responses in ServiceTabs is equal to number in ResponseCountBlock
                clientPages.getOrderCardPage().offersCounter.clickOffers();
                clientPages.getSelectServicePage().checkFinishLoadingMaintenance();
            });
            step("Клиент рассматривает предложение от Диспетчера СК", () -> {
                // find on the map
                //check Price with Insurance and Primary Visit Price
                //review first response ServiceCompanyStaff Company
                // check first Master info Card
                clientPages.getSelectServicePage().proceedWithFirstService();
                clientPages.getSelectInsurancePage().checkFinishLoading();
                // toggle price - check wo Insurance and w Insurance difference
                //set w Insurance
                clientPages.getSelectInsurancePage().next();
                clientPages.getCheckDocumentsPage().checkFinishLoading();
                //driver.back();
                // check that Filial is not empty or set the Filial if it is empty
                // check that Address fnd Passport is not empty or set the Address and Passport if it is empty
                //SmsApi clientSmsApi1 = SmsApi.instance(Role.CLIENT); // for real number
            });
            step("Клиент заполняет документы", () -> {
                //todo check that all documents are filled up
            });
            step("Клиент оплачивает предложение от Диспетчера СК", () -> {
                clientPages.getCheckDocumentsPage().makeContract();
                clientPages.getSelectPaymentMaintenancePage().checkFinishLoading();
                String priceWithCommissions = clientPages.getSelectPaymentMaintenancePage().getCommissionValue(1);
                clientPages.getSelectPaymentMaintenancePage().paySPB();
                clientPages.getPaymentWizardPage().checkFinishLoading(priceWithCommissions);
                clientPages.getPaymentWizardPage().payButton();
            });
            step("Клиент подписывает договор", () -> {
                clientPages.getSignSMSPage().checkFinishLoading();
                sleep(600);  //without  this cannot paste the code
                //String sms = clientSmsApi1.waitReceiveNewSms().getText(); // for real number
                //String code = sms.substring(0, 6); // for real number
                String mockCode = "111111";
                clientPages.getSignSMSPage().inputSMSCode(mockCode);
                //todo: check that sms is inputed
                //todo check spinner disappear
                //clientPages.getSignSMSPage().sign();
                clientPages.getSignSuccessPage().checkFinishLoading();
                clientPages.getSignSuccessPage().toHomePage();
            });
            step("Клиент ожидает согласования даты и времени", () -> {
                clientPages.getHomePage().checkFinishLoading(client.fullName, client.sinceDate);
                clientPages.getHomePage().popUp.close();
                clientPages.getHomePage().lastOrderComponent.toOrderCard();
                clientPages.getOrderCardPage().checkFinishLoading();
                clientPages.getOrderCardPage().checkScheduleVisitState(OrderStatus.SCHEDULE_DATE, ServiceType.MAINTENANCE);
            });
        });

        step("Диспетчер выбирает время и назначает Мастера", () -> {
            step("Диспетчер ");
            // start section from home page - need OrderNumber
            dispatcherPages.getOrderCardPage().checkFinishLoading();
            dispatcherPages.getOrderCardPage().popUp.close(); // too long
            dispatcherPages.getOrderCardPage().sidebar.generalMap();
            dispatcherPages.getHomePage().checkFinishLoading();
            dispatcherPages.getHomePage().switchToListView();
            dispatcherPages.getHomePage().navNew();
            dispatcherPages.getHomePage().searchByNumber(orderNumber);
            dispatcherPages.getHomePage().openOrderByNumber(orderNumber);
            dispatcherPages.getOrderCardPage().checkFinishLoading();
            dispatcherPages.getOrderCardPage().popUp.close();
            dispatcherPages.getOrderCardPage().checkScheduleVisitState(OrderStatus.SCHEDULE_DATE, ServiceType.MAINTENANCE);
            dispatcherPages.getOrderCardPage().selectTimeButton();
            dispatcherPages.getOrderCardPage().datePicker.selectNowDateAndTime();
            dispatcherPages.getOrderCardPage().selectMaster();
            dispatcherPages.getSelectMasterPage().checkFinishLoading();
            dispatcherPages.getSelectMasterPage().selectNewMasterByIndex(0);
            dispatcherPages.getOrderCardPage().checkFinishLoading();
            dispatcherPages.getOrderCardPage().checkMasterDispatchedState(OrderStatus.WAIT_MASTER, ServiceType.MAINTENANCE);
        });

        step("Мастер открывает заказ в состоянии Мастер в пути", () -> {
            masterPages.getHomePage().checkFinishLoading();
            masterPages.getHomePage().popUp.close();
            masterPages.getHomePage().sidebar.allOrdersHistoryDropdown();
            masterPages.getHomePage().sidebar.allNewOrders();
            masterPages.getAllNewOrdersPage().checkFinishLoading();
            masterPages.getAllNewOrdersPage().switchToTabView();
            masterPages.getAllNewOrdersPage().openByNumber(orderNumber);
            masterPages.getOrderCardPage().checkFinishLoading();
            masterPages.getOrderCardPage().checkMasterDispatchedOrderState(OrderStatus.WAIT_MASTER, ServiceType.MAINTENANCE);
            // todo add OrderStatusBoxComponent to  OrderCardPage
            //todo:default checklist state

        });

        step("Мастер открывает объект Клиента", () -> {
            masterPages.getHomePage().popUp.close();
            masterPages.getOrderCardPage().editObject();
            masterPages.getEditObjectPage().checkFinishLoading();
            masterPages.getEditObjectPage().navGasBranch();
            masterPages.getEditObjectPage().editDistributorTab.checkFinishLoading();
            //TODO check Distributor details
            masterPages.getEditObjectPage().editDistributorTab.editDistributor();
            //TODO check Distributor  Modal details
            masterPages.getEditObjectPage().editDistributorTab.distributorModal.checkFinishLoading();
            // TODO fix modalTitleLocator

            masterPages.getEditObjectPage().editDistributorTab.distributorModal.cancel();
            masterPages.getEditObjectPage().editDistributorTab.checkFinishLoading();
            masterPages.getEditObjectPage().navObject();
            masterPages.getEditObjectPage().editObjectTab.checkFinishLoading();
            masterPages.getEditObjectPage().editObjectTab.toOrder();
            // TODO checkFinishLoading - expand  docs check, order properties check, price check
        });

        step("Мастер прибывает на обьект", () -> {
            step("Мастер заполняет чеклист", () -> {
                masterPages.getOrderCardPage().startWork();
                masterPages.getOrderCardPage().checkFillingCheckListState(OrderStatus.WAIT_MASTER, ServiceType.MAINTENANCE);
                //todo check checklist default state
                //todo: add test for checklist changes and its affect on order state
            });
            step("Мастер заполнил чеклист и приступил к работе", () -> {
                masterPages.getOrderCardPage().navCheckList();
                masterPages.getOrderCardPage().saveCheckList();
            });
            step("Мастер добавляет позицию в  таблицу Оборудования ТО", () -> {
                //todo check table state - equipment list and default values
                //todo add equipment and check buttons behavior
                //todo add description
                //todo add media
                //todo pay cash

                //todo no checkBox - triggering error
            });


        });


        step("Мастер подтверждает выполненную работу", () -> {
        });

        step("Клиент оставляет отзыв", () -> {
        });

        step("Мастер оставляет отзыв", () -> {
        });

    }

}