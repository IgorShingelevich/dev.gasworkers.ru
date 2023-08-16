package ru.gasworkers.dev.tests.web.selfEmployed;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.ServiceType;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.SelfEmployedPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomSelfEmployedAndMaster;

import static io.qameta.allure.Allure.step;

@Disabled
@Owner("Igor Shingelevich")
@Epic(AllureEpic.SELF_EMPLOYED)
@Feature(AllureFeature.ACCOUNT_STATE)
@Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.SELF_EMPLOYED), @Tag(AllureTag.WEB), @Tag(AllureTag.POSITIVE)})
public class NewSelfEmployedAccountTest extends BaseTest {
    RandomSelfEmployedAndMaster randomSelfEmployedAndMaster = new RandomSelfEmployedAndMaster();
    @Browser(role = Role.SELF_EMPLOYED, browserSize = SizeBrowser.DEFAULT, browserPosition = "0x0")
    SelfEmployedPages selfEmployedPages;

    @Test
    @DisplayName("Кабинет СМЗ после регистрации")
    public void accountStateNewSelfEmployedTest() {
        String equipmentWorkPrice = "4000";
        String primaryVisitPrice = "3000";
        step("Страница лендинга", () -> {
            //todo RegistrationPage header
            selfEmployedPages.getLandingPage().open();
            selfEmployedPages.getLandingPage().checkFinishLoading();
            selfEmployedPages.getLandingPage().signUpMaster();
        });
        step("Страница первого шага регистрации СМЗ", () -> {
            selfEmployedPages.getRegistrationPage().firstStep.checkFinishLoading();
            selfEmployedPages.getRegistrationPage().firstStep.selectSE();
            selfEmployedPages.getRegistrationPage().firstStep.forwardButton();
        });
        step("Страница второго шага регистрации СМЗ", () -> {
            selfEmployedPages.getRegistrationPage().secondStep.checkFinishLoading();
            selfEmployedPages.getRegistrationPage().secondStep.byPhone(randomSelfEmployedAndMaster.getPhone());
            selfEmployedPages.getRegistrationPage().secondStep.forwardButton();
        });
        step("Страница третьего шага регистрации СМЗ", () -> {
            selfEmployedPages.getRegistrationPage().thirdStep.checkFinishLoading();
            selfEmployedPages.getRegistrationPage().thirdStep.fillCode(randomSelfEmployedAndMaster.getConfirmationCode());
        });
        step("Страница ввода пароля", () -> {
            selfEmployedPages.getRegistrationPage().forthStep.checkFinishLoading(4);
            selfEmployedPages.getRegistrationPage().forthStep.setPassword(randomSelfEmployedAndMaster.getPassword());
            selfEmployedPages.getRegistrationPage().forthStep.forwardButton();
        });
        step("Страница пятого шага регистрации СМЗ", () -> {
            selfEmployedPages.getRegistrationPage().fifthStep.checkFinishLoading(5);
            selfEmployedPages.getRegistrationPage().fifthStep.setEmail(randomSelfEmployedAndMaster.getEmail());
            selfEmployedPages.getRegistrationPage().fifthStep.forwardButton();
        });
        step("Страница успешной регистрации", () -> {
            selfEmployedPages.getRegistrationPage().successRegistration.checkFinishLoading();
        });
        step("Страница выбора типа самозанятого", () -> {
            selfEmployedPages.getAccountTypePage().checkFinishLoading();
            //todo radioDefaultState :after
            selfEmployedPages.getAccountTypePage().selectAlreadySE();
            selfEmployedPages.getAccountTypePage().selectIndividualEntrepreneurSE();
            selfEmployedPages.getAccountTypePage().selectAlreadySE();
            selfEmployedPages.getAccountTypePage().forwardButton();
        });
        step("Страница успешной регистрации", () -> {
            selfEmployedPages.getSuccessfulRegistrationPage().checkFinishLoading();
            selfEmployedPages.getSuccessfulRegistrationPage().clickForwardButton();
        });
        step("Гид СМЗ", () -> {
            selfEmployedPages.getHomePage().firstGuide.playSequenceFirstSEGuide();
        });
//        --------------------------------- Ui tests ---------------------------------
        step("Домашняя СМЗ после гида - режим карта и режим мастер", () -> {
            selfEmployedPages.getHomePage().checkInitialState();
        });
        step("Профиль СМЗ после гида", () -> {
            selfEmployedPages.getHomePage().header.burger.openBurger();
            selfEmployedPages.getHomePage().header.burger.profile();
            selfEmployedPages.getProfilePage().checkInitialState(randomSelfEmployedAndMaster.getEmail(), randomSelfEmployedAndMaster.getPhone());
            //todo mode switcher
            // todo sidebar unification
            //todo sidebar notifications block
        });
        step("Кабинет СМЗ в начальном состоянии после гида - режим мастера", () -> {
            selfEmployedPages.getProfilePage().mode.switchMaster();
            selfEmployedPages.getProfilePage().sidebarMaster.home();
            selfEmployedPages.getHomePage().modeMaster.checkInitialState();
        });
        step("Убедиться, что отсутствуют заказы в истории заказов", () -> {
            selfEmployedPages.getHomePage().modeMaster.sidebarMaster.expandHistoryDropdown();
            step("новые заказы", () -> {
                selfEmployedPages.getHomePage().modeMaster.sidebarMaster.allNewOrders();
                selfEmployedPages.getAllNewOrderHistoryPage().checkInitialState();
            });
            step("принятые заказы", () -> {
                selfEmployedPages.getHomePage().modeMaster.sidebarMaster.allScheduledOrders();
                selfEmployedPages.getAllScheduledOrderHistoryPage().checkInitialState();
            });
            step("выполненные заказы", () -> {
                selfEmployedPages.getHomePage().modeMaster.sidebarMaster.allCompletedOrders();
                selfEmployedPages.getAllCompletedOrderHistoryPage().checkInitialState();
            });
            selfEmployedPages.getAllCompletedOrderHistoryPage().sidebarMaster.closeHistoryDropdown();
        });
        step("Убедиться, что страница сертификаты и оборудование в начальном состоянии", () -> {
            selfEmployedPages.getAllCompletedOrderHistoryPage().sidebarMaster.certificatesAndEquipment();
            selfEmployedPages.getCertificatesAndEquipmentPage().checkInitialState();
        });
        step("Выбрать первый заказ", () -> {
            selfEmployedPages.getCertificatesAndEquipmentPage().sidebarMaster.home();
            selfEmployedPages.getHomePage().mode.switchDispatcher();
            selfEmployedPages.getHomePage().modeDispatcher.selectFirstMaintenanceOffer();
        });
        step("Модальное окно Предложить цену -появление сразу при незаполненном профиле при переходе в карточку заказа", () -> {
            selfEmployedPages.getOrderCardPage().offerPriceModalWindow.checkInitialState();
            selfEmployedPages.getOrderCardPage().offerPriceModalWindow.close();
        });
        step("Страница карточки заказа", () -> {
            selfEmployedPages.getOrderCardPage().checkInitialState();
        });
        step("Модальное окно Расценить заказ", () -> {
            selfEmployedPages.getOrderCardPage().offerPriceButton();
            selfEmployedPages.getOrderCardPage().fillUpOfferPriceBanner.checkFinishLoading();
            selfEmployedPages.getOrderCardPage().fillUpOfferPriceBanner.clickForwardButton();
            selfEmployedPages.getOrderCardPage().offerPriceModalWindow.checkInitialState();
            selfEmployedPages.getOrderCardPage().offerPriceModalWindow.fillCollectionPrices("5010", "5100", primaryVisitPrice);
            int totalOfferPrice = selfEmployedPages.getOrderCardPage().offerPriceModalWindow.totalOfferPrice();
            selfEmployedPages.getOrderCardPage().offerPriceModalWindow.fillMasterIdDocuments(randomSelfEmployedAndMaster.getMasterIDFile(), randomSelfEmployedAndMaster.getMasterIDValidTillDatePicker());
            selfEmployedPages.getOrderCardPage().offerPriceModalWindow.uploadAllBoilerEquipmentCertificateFileAndDate(randomSelfEmployedAndMaster.getEquipmentCertificateFile(), randomSelfEmployedAndMaster.getEquipmentCertificateValidTillDatePicker());
            selfEmployedPages.getOrderCardPage().offerPriceModalWindow.saveButton();
        });
        step(" !убрать баг - Дублированное модальное окно Расценить заказ", () -> {
            selfEmployedPages.getOrderCardPage().offerPriceModalWindow.close();
        });
        step("Страница карточки  расцененного заказа - первый заказ незаполненный профиль", () -> {
            selfEmployedPages.getOrderCardPage().checkNewTenderState(OrderStatus.NEW_TENDER, ServiceType.MAINTENANCE);
            selfEmployedPages.getOrderCardPage().initialStateAcceptOrder();
        });
        step("Страница информации о заполнении профиля", () -> {
            selfEmployedPages.getFillProfileInfoPage().checkFinishLoading();
            selfEmployedPages.getFillProfileInfoPage().toProfileButton();
        });
        step("Страница профиля СМЗ после перехода из первого расцененного заказа ", () -> {
            selfEmployedPages.getProfilePage().checkFirsOfferEvaluatedInitialState(randomSelfEmployedAndMaster.getEmail(), randomSelfEmployedAndMaster.getPhone());
            //todo sidebar unification in two modes
        });
        step("Заполнение вкладку общих данных профиля нового смз", () -> {
            selfEmployedPages.getProfilePage().fillCommonTabRandomData(randomSelfEmployedAndMaster);
        });
        step(" История заказов -  СМЗ  сайдбар - после после первого расцененного заказа", () -> {

        });
        step("Сертификаты и оборудование - СМЗ  сайдбар - после первого расцененного заказа", () -> {

        });




        /*step("Начальное состотояние кабинета СМЗ - модальное окно - расценить  тендер ", () -> {
            selfEmployedPages.getOrderCardSelfEmployedPage().offerPriceModalWindow.checkInitialState();
            selfEmployedPages.getOrderCardSelfEmployedPage().offerPriceModalWindow.fillOffer(randomSelfEmployedAndMaster.getMasterIDFile(), randomSelfEmployedAndMaster.getMasterIDValidTillDatePicker(), randomSelfEmployedAndMaster.getEquipmentCertificateFile(), randomSelfEmployedAndMaster.getEquipmentCertificateValidTillDatePicker(), equipmentWorkPrice, primaryVisitPrice);

        });*/


    }

}
//todo addPagination check for  appropriate pages
