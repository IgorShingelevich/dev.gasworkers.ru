package ru.gasworkers.dev.tests.web.client.newClient.afterBG;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureStory;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.UserRole;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.model.equipment.EquipmentType;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.ACCOUNT)
@Feature(AllureFeature.ACCOUNT_STATE)
@Story(AllureStory.REPAIR)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.POSITIVE)
@Tag(AllureTag.WEB)

public class AfterRepairBGRegistrationTest extends BaseTest {
    @Browser(role = UserRole.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = "0x0")
    ClientPages clientPages;
    RandomClient randomClient = new RandomClient();

    @Test
    @Owner("Igor Shingelevich")
    @DisplayName("Состояние Кабинета СМЗ - Кабинет после Фоновой Регистрации на Ремонт с указанием телефона и почты")
    public void clientAfterBGRepair() {
        Integer masterIndex = 0;
        Integer power = 20;
        EquipmentType GAS_BOILER_TYPE = EquipmentType.GAS_BOILER;
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
        step("Клиент заполняет форму фоновой регистрации", () -> {
            clientPages.getLandingPage().bgRegistration.checkFinishLoading();
            clientPages.getLandingPage().bgRegistration.fillBGRepairRequest(randomClient.getObjectAddress(), GAS_BOILER_TYPE, 1, 1, power, randomClient.getPhone(), randomClient.getEmail(), randomClient.getEquipmentRandomPhotoFile());
            //TODO add photo video
        });
        String resultedAddress = clientPages.getLandingPage().bgRegistration.address.getResultedAddress();
        String resultedEquipmentCollectionName = clientPages.getLandingPage().bgRegistration.equipment.getEquipmentName(0);
        String errorText = clientPages.getLandingPage().bgRegistration.equipment.getErrorText();

        clientPages.getLandingPage().bgRegistration.findOffers();
        clientPages.getLandingPage().confirmationCodeModalBG.fillCode(randomClient.getConfirmationCode(), "https://dev.gasworkers.ru/profile/client");
        step("Кабинет клиента - состояние после фоновой регистрации на Ремонт", () -> {
            step("Гид  Ремонт по кабинету", () -> {
                clientPages.getHomePage().guide.playSequenceFirstRepairGuide();
            });
            step("Страница Карта", () -> {
                clientPages.getSelectServicePage().checkFinishLoadingRepair();
                clientPages.getSelectServicePage().checkPublishedState();
            });
            // todo OrderCardPage
            String orderNumber = step("Страница Карточка заказа", () -> {
                clientPages.getSelectServicePage().toOrderCard();
                clientPages.getOrderCardPage().checkFinishLoading();
//                clientPages.getOrderCardPage().checkRepairBGInitialState(resultedAddress, resultedEquipmentCollectionName, desiredDate, desiredTime, errorText);
                String currentNumber = clientPages.getOrderCardPage().getOrderNumber();
                return currentNumber;
            });
            step("Домашняя страница", () -> {
                clientPages.getOrderCardPage().sidebar.home();
                clientPages.getHomePage().checkBGInitialState(randomClient.getSinceTodayDate());
            });
            step("Страница Уведомления", () -> {
                clientPages.getHomePage().actionsBlock.notifications();
                // TODO fix  Push notification  - not appear after Guide
//                clientPages.getAllNotificationsPage().checkInitialBGState(orderNumber);
//                clientPages.getAllNotificationsPage().readAll();

            });
            step("Страница Объекты", () -> {
                clientPages.getAllNotificationsPage().sidebar.allObjects();
                clientPages.getAllObjectsPage().checkFinishLoading();
                clientPages.getAllObjectsPage().initialBGState(GAS_BOILER_TYPE, resultedEquipmentCollectionName, power.toString(), resultedAddress);
            });
            step("Страница Заказы", () -> {
                clientPages.getAllObjectsPage().sidebar.allOrders();
                clientPages.getAllOrdersPage().checkFinishLoading();
                clientPages.getAllOrdersPage().checkBGInitialState(orderNumber);
                // todo orderBoxDetails
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
                    clientPages.getProfilePage().navCommonTab.uploadPhoto(randomClient.getAvatarRandomPhotoFile());
                });
                step("Вкадка Контакты", () -> {
                    clientPages.getProfilePage().navContacts();
                    clientPages.getProfilePage().navContactsTab.checkFinishLoading();
                    clientPages.getProfilePage().navContactsTab.checkInitialState(randomClient.getEmail(), randomClient.getPhone());
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
//todo custom equipment brand and model input
// TODO registration validation  cases - all fields are empty, checkbox unchecked. existed phone and email
// todo pick random address suggestion and equipment
//todo only phone, only email
//todo add equipment to request, delete, add two  other random equipments
// todo form validation no address, no equipment, no equipment brand, no equipment model, no date, no time, no phone, no email
// todo add delete add  photo video, media types validation




