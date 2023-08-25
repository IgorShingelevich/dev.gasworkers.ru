package ru.gasworkers.dev.tests.web.client;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.ServiceType;
import ru.gasworkers.dev.model.client.ClientRequestType;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;
import ru.gasworkers.dev.utils.userBuilder.UserBuilder;

import java.io.File;

import static io.qameta.allure.Allure.step;
import static ru.gasworkers.dev.model.Role.CLIENT;
@Disabled
class ClientFlowTest extends BaseTest {
    @Browser(role = CLIENT)
    ClientPages clientPages;

    UserBuilder client00 = new UserBuilder(
            "Игорь",
            "Сергеевич",
            "Шингелевич",
            "Зарегистрирован с 15 ноября 2022 года",
            "shingelevich@gmail.com",
            "123456",
            null,
            70012223344L);

    RandomClient randomClient = new RandomClient();

    @BeforeEach
    void clientLogin() {
        clientPages.getLoginPage().open();
        clientPages.getLoginPage().login(client00.email, client00.password);
    }

    @Test
    @Feature("Кабинет клиента")
    @Story("Смена пароля")
    @DisplayName("Клиент изменяет свой пароль")
    public void changePasswordClientProfilePage() {
        clientPages.getHomePage().sidebar.profile();
        clientPages.getProfilePage().checkFinishLoading();
        clientPages.getProfilePage().navPassword();
        clientPages.getProfilePage().navPasswordTab.checkFinishLoading();
        clientPages.getProfilePage().navPasswordTab.checkInitialState();
        clientPages.getProfilePage().navPasswordTab.generatePassword();
    }

    @Test
    @Disabled
    @Feature("Кабинет клиента")
    @Story("Незаполненный ( начальное состояние) кабинет")
    @DisplayName("Клиент наполняет свой Профиль в первый раз")
    public void fillingUpInitialClientProfilePage() {
        clientPages.getHomePage().sidebar.profile();
        clientPages.getProfilePage().checkFinishLoading();
        step("Вкладка Общие данные", () -> {
            clientPages.getProfilePage().navCommon();
            clientPages.getProfilePage().navCommonTab.checkFinishLoading();
            //TODO check  fullName fill up address and passport
        });
        step("Вкладка Контакты", () -> {
            clientPages.getProfilePage().navContacts();
            clientPages.getProfilePage().navContactsTab.checkFinishLoading();
            clientPages.getProfilePage().navContactsTab.checkInitialState(client00.email, String.valueOf(client00.phoneNumber));
            //TODO password and Notifications
        });
    }

    @Test
    @Feature("Кабинет клиента")
    @Story("Заполненный кабинет ")
    @DisplayName("Выбранный клиент просматривает заполненный кабинет")
    public void checkFilledCabinetState() {
        clientPages.getHomePage().checkFinishLoading(client00.fullName, client00.sinceDate);
        clientPages.getHomePage().sidebar.profile();
        clientPages.getProfilePage().checkFinishLoading();
        step("Вкладка Общие данные", () -> {
            clientPages.getProfilePage().navCommon();
            clientPages.getProfilePage().navCommonTab.checkFilledState();  // TODO implement CommonDataPickerComponent. Upload photo. check other fields info.
            File photoPath = new File("D:\\GW\\src\\test\\resources\\uploadFiles\\person\\test_person1.jpg");
            clientPages.getProfilePage().navCommonTab.uploadPhoto(photoPath);

        });
        step("Вкладка Контакты", () -> {
            clientPages.getProfilePage().navContacts();
            clientPages.getProfilePage().navContactsTab.checkFinishLoading();
            clientPages.getProfilePage().navContactsTab.checkInitialState(client00.email, String.valueOf(client00.phoneNumber));
        });
        //TODO password and Notifications
    }

    @Test
    @Feature("Кабинет клиента")
    @Story("Заполненный кабинет ")
    @DisplayName("Клиент просматривает объект с предложениями")
    public void checkGotOffersStateObject() {
        clientPages.getHomePage().checkFinishLoading(client00.fullName, client00.sinceDate);
        clientPages.getHomePage().sidebar.allObjects();
        clientPages.getAllObjectsPage().checkFinishLoading();
        clientPages.getAllObjectsPage().openObjectByIndex(0);
        clientPages.getObjectCardPage().checkFinishLoading();
        step("Убедиться, что баннер Нельзя редактировать присутствует на всех вкладках", () -> {
            clientPages.getObjectCardPage().tabObject.noEditBanner.isExist();
            clientPages.getObjectCardPage().navDistributorTab();
            clientPages.getObjectCardPage().tabDistributor.noEditBanner.isExist();
            clientPages.getObjectCardPage().navDocsTab();
            clientPages.getObjectCardPage().tabDocs.noEditBanner.isExist();
            clientPages.getObjectCardPage().navObjectTab();
        });
        clientPages.getObjectCardPage().tabObject.getName();
        clientPages.getObjectCardPage().tabObject.getEquipmentList();
    }

    @Test
    @Feature("Новый заказ ТО")
    @Story("Создание заказа")
    @DisplayName("Клиент создает заказ")
    public void clientPlaceMaintenanceRequest() {
        clientPages.getHomePage().checkFinishLoading(client00.fullName, client00.sinceDate);
        clientPages.getHomePage().popUp.close();
        clientPages.getHomePage().clickPlaceOrderButton();
        clientPages.getTypeOrdersPage().selectOrderType(ClientRequestType.MAINTENANCE); //  .toString()
        clientPages.getInfoTypeOrderPage()
//                    .checkTitle("Заказ на ТО")
//                    .checkStepSequence("Шаг 1 из 3")
                .clickNextButton();
        clientPages.getSelectObjectMaintenancePage().selectObjectByIndex(0);
        clientPages.getSelectDateMaintenancePage().pickNowDateAM();
        clientPages.getSelectDateMaintenancePage().submitOrder();
        clientPages.getSelectServicePage().checkFinishLoadingMaintenance();
        clientPages.getSelectServicePage().toOrderCard();
        clientPages.getOrderCardPage().checkFinishLoading();
        clientPages.getSelectServicePage().popUp.close();
        clientPages.getOrderCardPage().checkPublishedState(OrderStatus.NEW_ORDER, ServiceType.MAINTENANCE);
    }

    @Test
    @Feature("Новый заказ ТО")
    @Story("Создание заказа и его отмена")
    @DisplayName("Клиент создает заказ и отменяет его")
    public void clientPlaceMaintenanceRequestAndCancel() {
        clientPages.getHomePage().checkFinishLoading(client00.fullName, client00.sinceDate);
        clientPages.getHomePage().popUp.close();
        clientPages.getHomePage().clickPlaceOrderButton();
        clientPages.getTypeOrdersPage().selectOrderType(ClientRequestType.MAINTENANCE); //  .toString()
        clientPages.getInfoTypeOrderPage()
//                  .checkTitle("Заказ на ТО")
//                  .checkStepSequence("Шаг 1 из 3")
                .clickNextButton();
        clientPages.getSelectObjectMaintenancePage().selectObjectByIndex(0);
        clientPages.getSelectDateMaintenancePage().pickNowDateAM();
        clientPages.getSelectDateMaintenancePage().submitOrder();
        clientPages.getSelectServicePage().checkFinishLoadingMaintenance();
        clientPages.getSelectServicePage().toOrderCard();
        clientPages.getSelectServicePage().popUp.close();
        clientPages.getOrderCardPage().checkFinishLoading();
        clientPages.getOrderCardPage().popUp.close();
        clientPages.getOrderCardPage().buttons.cancelOrder();
        clientPages.getCancelOrderPage().checkFinishLoading();
        clientPages.getCancelOrderPage().backButton();
        clientPages.getOrderCardPage().checkFinishLoading();
        clientPages.getOrderCardPage().buttons.showOnMap();
        clientPages.getSelectServicePage().checkFinishLoadingMaintenance();
        clientPages.getSelectServicePage().toOrderCard();
        clientPages.getOrderCardPage().checkFinishLoading();
        clientPages.getOrderCardPage().buttons.cancelOrder();
        clientPages.getCancelOrderPage().checkFinishLoading();
        clientPages.getCancelOrderPage().yesButton();
        clientPages.getHomePage().checkFinishLoading(client00.fullName, client00.sinceDate);
    }

    @Test
    @Feature("Кабинет клиента")
    @Story("Просмотр заказа на ТО")
    @DisplayName("Клиент открывает заказ в состоянии Согласование даты заказа")
    public void clientCheckScheduleVisitOrderSate() {
        String checkedOrderNumber = "3659";
        clientPages.getHomePage().checkFinishLoading(client00.fullName, client00.sinceDate);
        clientPages.getHomePage().sidebar.allOrdersAndInvoicesDropdown();
        clientPages.getHomePage().sidebar.allOrders();
        clientPages.getAllOrdersPage().checkFinishLoading();
        clientPages.getAllOrdersPage().orderByNumber(checkedOrderNumber);
        clientPages.getOrderCardPage().checkFinishLoading();
        clientPages.getOrderCardPage().checkScheduleVisitState(OrderStatus.SCHEDULE_DATE, ServiceType.MAINTENANCE);
    }

    @Test
    @Feature("Кабинет клиента")
    @Story("Просмотр заказа на ТО")
    @DisplayName("Клиент открывает заказ в состоянии Мастер в пути")
    public void clientCheckMasterDispatchedOrderSate() {
        String checkedOrderNumber = "3695";
        clientPages.getHomePage().popUp.close();
        clientPages.getHomePage().checkFinishLoading(client00.fullName, client00.sinceDate);
        clientPages.getHomePage().sidebar.allOrdersAndInvoicesDropdown();
        clientPages.getHomePage().sidebar.allOrders();
        clientPages.getAllOrdersPage().checkFinishLoading();
        clientPages.getAllOrdersPage().orderByNumber(checkedOrderNumber);
        clientPages.getOrderCardPage().checkFinishLoading();
        clientPages.getOrderCardPage().checkScheduleVisitState(OrderStatus.WAIT_MASTER, ServiceType.MAINTENANCE);
    }

    @Test
    @Feature("Кабинет клиента")
    @Story("Просмотр заказа на ТО")
    @DisplayName("Клиент открывает заказ в состоянии Мастер в пути  и скачивает документы")
    public void clientDownloadDocsMasterDispatchedOrderSate() throws Exception {
        String checkedOrderNumber = "3816";
        clientPages.getHomePage().popUp.close();
        clientPages.getDriverManager().screenshot("Фотка №1");
        clientPages.getHomePage().checkFinishLoading(client00.fullName, client00.sinceDate);
        clientPages.getHomePage().sidebar.allOrdersAndInvoicesDropdown();
        clientPages.getHomePage().sidebar.allOrders();
        clientPages.getDriverManager().screenshot("Фотка №2");
        clientPages.getAllOrdersPage().checkFinishLoading();
        clientPages.getAllOrdersPage().orderByNumber(checkedOrderNumber);
        clientPages.getOrderCardPage().checkFinishLoading();
        clientPages.getDriverManager().screenshot("Фотка №3");
        clientPages.getOrderCardPage().checkScheduleVisitState(OrderStatus.WAIT_MASTER, ServiceType.MAINTENANCE);
        clientPages.getOrderCardPage().nav.docs();
        clientPages.getDriverManager().screenshot("Фотка №4");
        clientPages.getOrderCardPage().docsTab.downloadAgreement();
        clientPages.getOrderCardPage().docsTab.downloadAgreement();
        clientPages.getDriverManager().screenshot("Фотка №5");
        clientPages.getOrderCardPage().docsTab.downloadInsurance();
    }

    @Test
    @Feature("Кабинет клиента")
    @Story("Просмотр заказа на ТО")
    @DisplayName("Клиент открывает заказ ТО в статусе без Отзыва Завершен")
    public void checkNotReviewedCompletedOrderState() {
        String checkedOrderNumber = "3674";
        clientPages.getHomePage().popUp.close();
        clientPages.getHomePage().checkFinishLoading(client00.fullName, client00.sinceDate);
        clientPages.getHomePage().sidebar.allOrdersAndInvoicesDropdown();
        clientPages.getHomePage().sidebar.allOrders();
        clientPages.getAllOrdersPage().checkFinishLoading();
        clientPages.getAllOrdersPage().orderByNumber(checkedOrderNumber);
        clientPages.getOrderCardPage().checkFinishLoading();
        clientPages.getOrderCardPage().checkNotReviewedCompletedOrderState(OrderStatus.CLIENT_SIGN_ACT, ServiceType.MAINTENANCE);
//        clientPages.getOrderCardPage().navDocsTab.downloadAgreement()
    }

    @Test
    @Feature("Кабинет клиента")
    @Story("Просмотр заказа на ТО")
    @DisplayName("Клиент открывает заказ в состоянии с Отзывом Завершен")
    public void clientSubmittedReviewOrderState() {
        String checkedOrderNumber = "3674";
        clientPages.getHomePage().checkFinishLoading(client00.fullName, client00.sinceDate);
        clientPages.getHomePage().sidebar.allOrdersAndInvoicesDropdown();
        clientPages.getHomePage().sidebar.allOrders();
        clientPages.getAllOrdersPage().checkFinishLoading();
        clientPages.getAllOrdersPage().orderByNumber(checkedOrderNumber);
        clientPages.getOrderCardPage().checkFinishLoading();
        clientPages.getOrderCardPage().checkReviewedCompletedOrderState(OrderStatus.CLIENT_SIGN_ACT, ServiceType.MAINTENANCE);
//        clientPages.getOrderCardPage().navDocsTab.downloadAgreement()
    }

    @Test
    public void dellMe() {
       /* clientPages.getHomePage().conferenceNotification.toOrderChatButton();
        clientPages.getHomePage().checkFinishLoading(client00.fullName, client00.sinceDate);
        clientPages.getHomePage().sidebar.allOrdersAndInvoicesDropdown();
        clientPages.getHomePage().sidebar.allOrders();
        clientPages.getAllOrdersPage().checkFinishLoading();
        clientPages.getAllOrdersPage().orderByNumber(checkedOrderNumber);
        clientPages.getOrderCardPage().checkFinishLoading();
        clientPages.getOrderCardPage().checkNotReviewedCompletedOrderState(OrderStatus.COMPLETED, ServiceType.MAINTENANCE);
//        clientPages.getOrderCardPage().navDocsTab.downloadAgreement()*/
    }

}
// TODO client submitReview
