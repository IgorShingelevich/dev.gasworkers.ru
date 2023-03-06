package ru.gasworkers.dev.tests.registration.bgRegistration;

import ru.gasworkers.dev.model.OrderType;
import ru.gasworkers.dev.tests.BaseTest;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import ru.gasworkers.dev.allure.AllureStory;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.Role;


import org.junit.jupiter.api.*;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.model.equipment.EquipmentType;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.pages.context.DispatcherPages;
import ru.gasworkers.dev.pages.context.MasterPages;
import ru.gasworkers.dev.utils.RandomClient;

import static io.qameta.allure.Allure.step;
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.REGISTRATION)
@Tag(AllureTag.REGRESSION)

public class VideoBGRegistrationTest extends BaseTest {


    @Browser(role = Role.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = "0x0")
    ClientPages clientPages;

    @Browser(role = Role.DISPATCHER, browserSize = SizeBrowser.DEFAULT, browserPosition = "850x0")
    DispatcherPages dispatcherPages;

    @Browser(role = Role.MASTER, browserSize = SizeBrowser.DEFAULT, browserPosition = "1700x0")
    MasterPages masterPages;

    RandomClient randomClient = new RandomClient();

    @Test
    @Owner("Igor Shingelevich")
    @Epic("Регистрация")
    @Feature("Фоновая регистрация")
    @Story(AllureStory.VIDEO)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT),  @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE)})
    @DisplayName("Фоновая Регистрация на Видео Сейчас с указанием телефона и почты на сегодняшнюю дату с одним оборудованием")
    public void bgRegistrationNowVideo() {
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
        step("Клиент заполняет форму фоновой регистрации", () -> {
            clientPages.getLandingPage().bgRegistration.checkFinishLoading();
            clientPages.getLandingPage().bgRegistration.fillBGVideoRequest(randomClient.getObjectAddress(), EquipmentType.GAS_BOILER, 1, 1, 20, randomClient.getPhoneNumber(), randomClient.getEmail());
           // TODO add photo video
//            String resultedAddress = clientPages.getLandingPage().bgRegistration.address.getResultedAddress();
//           return resultedAddress;
       });
       String resultedAddress = clientPages.getLandingPage().bgRegistration.address.getResultedAddress();
       String resultedEquipmentCollectionName = clientPages.getLandingPage().bgRegistration.equipment.getEquipmentName(0);
       //todo modify equipmentCollection index for  different equipment count
       String errorText = clientPages.getLandingPage().bgRegistration.equipment.getErrorText();
        clientPages.getLandingPage().bgRegistration.findOffers();
          clientPages.getLandingPage().confirmationCodeModalBG.fillCode(randomClient.getConfirmationCode());

        step("Кабинет клиента - состояние после фоновой регистрации на Видео", () -> {
            //todo video guide
            /*step("Гид  ТО по кабинету", () -> {
                clientPages.getHomePage().firstMaintenanceGuide.playSequence();
            });*/
            step("Страница Видеоконсультация", () -> {
                clientPages.getConsultationVideoPage().checkFinishLoading();
                clientPages.getConsultationVideoPage().defaultBGVideoState();
                clientPages.getConsultationVideoPage().rightNowTab.masterList.selectMasterByName("ИнжТехМастеров3 ИнжТехМастер3 ИнжТехМастерович3");
            });

            step("Страница Подтверждение деталей Видео консультации Сейчас", () -> {
                clientPages.getApproveMasterVideoPage().checkFinishLoading();
                step("Компонент прикрепленные данные", () -> {
                    clientPages.getApproveMasterVideoPage().attachmentsOrder.checkFinishLoading();
                    clientPages.getApproveMasterVideoPage().attachmentsOrder.checkBGRightNowState(errorText);
                });
                step("Компонент детали заказа", () -> {
                    clientPages.getApproveMasterVideoPage().detailsOrder.checkFinishLoading();
                    clientPages.getApproveMasterVideoPage().detailsOrder.checkMasterFullName("ИнжТехМастеров3 ИнжТехМастер3 ИнжТехМастерович3");
                    clientPages.getApproveMasterVideoPage().detailsOrder.checkPlaceWork("ООО \"ИНЖЕНЕРНЫЕ ТЕХНОЛОГИИ\"");
                    clientPages.getApproveMasterVideoPage().detailsOrder.checkQuantityOfCompletedOrders("12");
                    clientPages.getApproveMasterVideoPage().detailsOrder.checkOrderDate(randomClient.getSinceDate());
                    clientPages.getApproveMasterVideoPage().detailsOrder.checkRightNowTimeOrderState();
                    clientPages.getApproveMasterVideoPage().detailsOrder.checkPriceOrder("500");
                    clientPages.getApproveMasterVideoPage().detailsOrder.checkOrderType(OrderType.VIDEO);
                    clientPages.getApproveMasterVideoPage().detailsOrder.checkEquipment(resultedEquipmentCollectionName);
                    clientPages.getApproveMasterVideoPage().detailsOrder.checkPersonAddress(resultedAddress);
                    //todo  video attachments
                });
                clientPages.getApproveMasterVideoPage().clickPayButton();

                clientPages.getSelectPaymentVideoPage().checkFinishLoading();
                clientPages.getSelectPaymentVideoPage().payMir();











            });
            String orderNumber = step("", () -> {
                clientPages.getSelectServicePage().toOrderCard();
                clientPages.getOrderCardPage().checkFinishLoading();
                String currentNumber = clientPages.getOrderCardPage().getOrderNumber();
                return currentNumber;
            });
            step("Домашняя страница", () -> {
                clientPages.getOrderCardPage().sidebar.home();
                clientPages.getHomePage().checkBGInitialState(randomClient.getSinceDate());
            });
            step("Страница Уведомления", () -> {
                clientPages.getHomePage().actionsBlock.allNotifications();
                // TODO fix  Push notification  - not appear after Guide
//                clientPages.getAllNotificationsPage().checkInitialBGState(orderNumber);
//                clientPages.getAllNotificationsPage().readAll();

            });
            step("Страница Объекты", () -> {
                clientPages.getAllNotificationsPage().sidebar.allObjects();
                clientPages.getAllObjectsPage().checkFinishLoading();
                clientPages.getAllObjectsPage().initialBGState();
            });
            step("Страница Заказы", () -> {
                clientPages.getAllObjectsPage().sidebar.allOrders();
                clientPages.getAllOrdersPage().checkFinishLoading();
                clientPages.getAllOrdersPage().checkBGInitialState(orderNumber);
            });
            step("Страница Счета", () -> {
                clientPages.getAllOrdersPage().sidebar.allInvoices();
                clientPages.getAllInvoicesPage().checkFinishLoading();
                clientPages.getAllInvoicesPage().checkInitialState();
            });

            step("Страница Профиль", () -> {
                clientPages.getAllInvoicesPage().sidebar.profile();
                clientPages.getProfilePage().checkFinishLoading();
                step("Вкадка Общее данные", () -> {
                    clientPages.getProfilePage().navCommon();
                    clientPages.getProfilePage().navCommonTab.checkFinishLoading();
                    clientPages.getProfilePage().navCommonTab.checkInitialBGState();
                });
                step("Вкадка Контакты", () -> {
                    clientPages.getProfilePage().navContacts();
                    clientPages.getProfilePage().navContactsTab.checkFinishLoading();
                    clientPages.getProfilePage().navContactsTab.checkFilledState(randomClient.getEmail(), randomClient.getPhoneNumber());
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
                clientPages.getProfilePage().sidebar.home();
            });


        });
    }

}

// todo bg registration with no email - add email in the profile
//
//todo custom equipment brand and model
//todo only phone, only email
// TODO registration validation  cases - all fields are empty, checkbox unchecked. existed phone and email
// todo pick random address suggestion and equipment
// TODO registration validation  cases - all fields are empty, checkbox unchecked. existed phone and email
