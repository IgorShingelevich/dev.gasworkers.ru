package ru.gasworkers.dev.tests.web.orderProcess.consultation;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureStory;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.ServiceType;
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
@Story(AllureStory.VIDEO)
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.POSITIVE)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.WEB)

public class ClientAfterPaymentVideoTest extends BaseTest {
    @Browser(role = UserRole.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = "0x0")
    ClientPages clientPages;
    RandomClient randomClient = new RandomClient();

    @Disabled
    @Test
    @DisplayName("Состояние Кабинета СМЗ - Кабинет после оплаты Видео Сейчас")
    public void clientAfterPaymentVideo() {
        Integer masterIndex = 0;
        Integer power = 20;
        EquipmentType GAS_BOILER_TYPE = EquipmentType.GAS_BOILER;
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
        step("Клиент заполняет форму фоновой регистрации", () -> {
            clientPages.getLandingPage().bgRegistration.checkFinishLoading();
            clientPages.getLandingPage().bgRegistration.fillBGVideoRequest(randomClient.getObjectAddress(), GAS_BOILER_TYPE, 1, 1, power, randomClient.getPhone(), randomClient.getEmail(), randomClient.getEquipmentVideoFile());
            // TODO add photo video
        });
//       String resultedAddress = clientPages.getLandingPage().bgRegistration.address.getResultedAddress(); // removed
        String resultedEquipmentCollectionName = clientPages.getLandingPage().bgRegistration.equipment.getEquipmentName(0);
        // todo String desiredDate =
        // todo String desiredTime =
        //todo modify equipmentCollection index for  different equipment count
        String errorText = clientPages.getLandingPage().bgRegistration.equipment.getErrorText();
        clientPages.getLandingPage().bgRegistration.findOffers();
        clientPages.getLandingPage().confirmationCodeModalBG.fillCode(randomClient.getConfirmationCode(), "https://dev.gasworkers.ru/orders/consultation");


        step("Страница Видеоконсультация", () -> {
            clientPages.getConsultationVideoPage().checkFinishLoading();
            clientPages.getConsultationVideoPage().defaultBGVideoState();
        });


        /*step("Выбрать Мастера по имени", () -> {
            String masterFullNameByName = clientPages.getConsultationVideoPage().rightNowTab.masterList.getCurrentMasterByName(masterИнжТехМастер3.fullName);
        });*/
        String masterFullNameByIndex = clientPages.getConsultationVideoPage().rightNowTab.masterList.getCurrentMasterByIndex(masterIndex);
        String masterPriceByIndex = clientPages.getConsultationVideoPage().rightNowTab.masterList.getMasterPriceByIndex(masterIndex);


        clientPages.getConsultationVideoPage().rightNowTab.masterList.selectMasterByName(masterFullNameByIndex);

        step("Страница Подтверждение деталей Видео консультации Сейчас", () -> {
            clientPages.getApproveMasterVideoPage().checkFinishLoading();
            step("Компонент прикрепленные данные", () -> {
                clientPages.getApproveMasterVideoPage().errorAttachments.checkFinishLoading();
                clientPages.getApproveMasterVideoPage().errorAttachments.checkBGRightNowState(errorText);
            });
            step("Компонент детали заказа", () -> {
                //todo workplace, fullName link or txt, review count
                clientPages.getApproveMasterVideoPage().details.checkFinishLoading();
                clientPages.getApproveMasterVideoPage().details.checkMasterFullName(masterFullNameByIndex);
//                clientPages.getApproveMasterVideoPage().details.checkPlaceWork("ООО \"ИНЖЕНЕРНЫЕ ТЕХНОЛОГИИ\"");
//                clientPages.getApproveMasterVideoPage().details.checkQuantityOfCompletedOrders("12");
                clientPages.getApproveMasterVideoPage().details.checkOrderDate(randomClient.getSinceTodayDate());
                clientPages.getApproveMasterVideoPage().details.checkRightNowTimeOrderState();
                clientPages.getApproveMasterVideoPage().details.checkPriceOrder(masterPriceByIndex);
                clientPages.getApproveMasterVideoPage().details.checkOrderType(ServiceType.CONSULTATION);
                clientPages.getApproveMasterVideoPage().details.checkEquipment(resultedEquipmentCollectionName);
//                clientPages.getApproveMasterVideoPage().details.checkPersonAddress(resultedAddress); // removed
                //todo  video attachments
            });
        });
        clientPages.getApproveMasterVideoPage().clickPayButton();

        String priceWithCommissions = step("Страница выбора способа оплаты", () -> {
            clientPages.getSelectPaymentVideoPage().checkFinishLoading();
            String commissionValue = clientPages.getSelectPaymentVideoPage().getCommissionValue(0);
            System.out.println("Комиссия СПБ: " + commissionValue);
            clientPages.getSelectPaymentVideoPage().payMir();
            return commissionValue;
        });

       /* step("Страница оплаты картой", () -> {
            clientPages.getPaymentWizardPage().checkFinishLoading(priceWithCommissions);
            clientPages.getPaymentWizardPage().payButton();
        });*/

        step("Страница успешной оплаты", () -> {
            clientPages.getSuccessPaymentVideoPage().checkFinishLoading();
            clientPages.getSuccessPaymentVideoPage().clickButton();
        });

        step("Кабинет клиента - состояние после фоновой регистрации на Видео", () -> {
            //todo video guide
            /*step("Гид  ТО по кабинету", () -> {
                clientPages.getHomePage().firstMaintenanceGuide.playSequence();
            });*/

            step("Домашняя страница", () -> {
                clientPages.getHomePage().checkVideoBGInitialState(randomClient.getSinceTodayDate(), masterFullNameByIndex);
            });
            step("Страница Уведомления", () -> {
                clientPages.getHomePage().actionsBlock.notifications();
                // TODO fix  Push notification  - not appear after Guide
//                clientPages.getAllNotificationsPage().checkInitialBGState(orderNumber);
//                clientPages.getAllNotificationsPage().readAll();

            });
            step("Страница Объекты", () -> {
                clientPages.getAllNotificationsPage().sidebar.allObjects();
                clientPages.getAllEquipmentPage().checkFinishLoading();
                // todo  through video  initial state - address removed
//                clientPages.getAllObjectsPage().initialBGState(GAS_BOILER_TYPE, resultedEquipmentCollectionName, power.toString(), resultedAddress);
            });
            step("Страница Заказы", () -> {
                clientPages.getAllEquipmentPage().sidebar.allOrders();
                clientPages.getAllOrdersPage().checkFinishLoading();
                clientPages.getAllOrdersPage().checkItemsAmount(1);
            });
            //todo OrderCardPage
            /*String orderNumber = step("Страница Карточка заказа", () -> {
                clientPages.getAllOrdersPage().toOrderCard();
                clientPages.getOrderCardPage().checkFinishLoading();
                clientPages.getOrderCardPage().checkRepairBGInitialState(resultedAddress, resultedEquipmentCollectionName, desiredDate, desiredTime, errorText);
                String currentNumber = clientPages.getOrderCardPage().getOrderNumber();
                return currentNumber;
            });*/

            step("Страница Счета", () -> {
                clientPages.getAllOrdersPage().sidebar.allInvoices();
                clientPages.getAllInvoicesPage().checkFinishLoading();
                clientPages.getAllInvoicesPage().checkItemsAmount(1);
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
//
//todo custom equipment brand and model
//todo only phone, only email
// TODO registration validation  cases - all fields are empty, checkbox unchecked. existed phone and email
// todo pick random address suggestion and equipment
// TODO registration validation  cases - all fields are empty, checkbox unchecked. existed phone and email
