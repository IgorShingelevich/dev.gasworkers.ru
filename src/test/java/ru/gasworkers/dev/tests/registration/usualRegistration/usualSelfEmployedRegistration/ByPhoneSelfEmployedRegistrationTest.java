package ru.gasworkers.dev.tests.registration.usualRegistration.usualSelfEmployedRegistration;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.OrderType;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.SelfEmployedPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomSelfEmployed;

import static io.qameta.allure.Allure.step;

public class ByPhoneSelfEmployedRegistrationTest extends BaseTest {
    RandomSelfEmployed randomSelfEmployed = new RandomSelfEmployed();


    @Browser(role = Role.SELF_EMPLOYED, browserSize = SizeBrowser.DEFAULT, browserPosition = "0x0")
    SelfEmployedPages selfEmployedPages;

    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.USUAL_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.SE_MEMBER), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE)})
    @DisplayName("Регистрация СМЗ в СК")
    public void byPhoneSEMemberRegistrationTest() {
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
            selfEmployedPages.getRegistrationPage().secondStep.byPhone(randomSelfEmployed.getPhone());
            selfEmployedPages.getRegistrationPage().secondStep.forwardButton();
        });
        step("Страница третьего шага регистрации СМЗ", () -> {
            selfEmployedPages.getRegistrationPage().thirdStep.checkFinishLoading();
            selfEmployedPages.getRegistrationPage().thirdStep.fillCode(randomSelfEmployed.getConfirmationCode());
        });
        step("Страница ввода пароля", () -> {
            selfEmployedPages.getRegistrationPage().forthStep.checkFinishLoading(4);
            selfEmployedPages.getRegistrationPage().forthStep.setPassword(randomSelfEmployed.getPassword());
            selfEmployedPages.getRegistrationPage().forthStep.forwardButton();
        });
        step("Страница пятого шага регистрации СМЗ", () -> {
            selfEmployedPages.getRegistrationPage().fifthStep.checkFinishLoading(5);
            selfEmployedPages.getRegistrationPage().fifthStep.setEmail(randomSelfEmployed.getEmail());
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
            selfEmployedPages.getHomePage().firstGuide.playSequence(15);
        });
        step("Домашняя СМЗ после гида - режим карта и режим мастер", () -> {
            selfEmployedPages.getHomePage().checkInitialState();
        });
        step("Профиль СМЗ после гида", () -> {
            selfEmployedPages.getHomePage().header.burger.openBurger();
            selfEmployedPages.getHomePage().header.burger.profile();
            selfEmployedPages.getProfilePage().checkInitialState(randomSelfEmployed.getEmail(), randomSelfEmployed.getPhone());
            //todo mode switcher
            // todo sidebar unification
            //todo sidebar notifications block
        });
        step("Кабинет СМЗ в начальном состоянии после гида - режим мастера", () -> {
            selfEmployedPages.getProfilePage().mode.switchMaster();
            selfEmployedPages.getProfilePage().sidebarMaster.home();
            selfEmployedPages.getHomePage().masterMode.checkInitialState();
        });
        step("Убедиться, что отсутствуют заказы в истории заказов", () -> {
            selfEmployedPages.getHomePage().masterMode.sidebarMaster.expandHistoryDropdown();
            step("новые заказы", () -> {
                selfEmployedPages.getHomePage().masterMode.sidebarMaster.allNewOrders();
                selfEmployedPages.getAllNewOrderHistoryPage().checkInitialState();
            });
            step("принятые заказы", () -> {
                selfEmployedPages.getHomePage().masterMode.sidebarMaster.allScheduledOrders();
                selfEmployedPages.getAllScheduledOrderHistoryPage().checkInitialState();
            });
            step("выполненные заказы", () -> {
                selfEmployedPages.getHomePage().masterMode.sidebarMaster.allCompletedOrders();
                selfEmployedPages.getAllCompletedOrderHistoryPage().checkInitialState();
            });
            selfEmployedPages.getAllCompletedOrderHistoryPage().sidebarMaster.closeHistoryDropdown();
//            selfEmployedPages.getAllCompletedOrderHistorySelfEmployedPage().sidebarMaster.home();
//            selfEmployedPages.getHomeSelfEmployedPage().masterMode.mode.switchDispatcher();
        });
        step("Убедиться, что страница сертификаты и оборудование в начальном состоянии", () -> {
            selfEmployedPages.getAllCompletedOrderHistoryPage().sidebarMaster.certificatesAndEquipment();
//            selfEmployedPages.getCertificatesAndEquipmentPage().checkInitialState();
        });
        step("Выбрать первый заказ", () -> {
//            selfEmployedPages.getProfileSelfEmployedPage().sidebarDispatcher.home();
//            selfEmployedPages.getProfileSelfEmployedPage().mode.checkDispatcherMode();
            selfEmployedPages.getAllCompletedOrderHistoryPage().sidebarMaster.home();
            selfEmployedPages.getHomePage().mode.switchDispatcher();
            selfEmployedPages.getHomePage().mapMode.selectFirstMaintenanceOffer();
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
            selfEmployedPages.getOrderCardPage().offerPriceModalWindow.fillMasterIdDocuments(randomSelfEmployed.getMasterIDFile(), randomSelfEmployed.getMasterIDValidTillDatePicker());
            selfEmployedPages.getOrderCardPage().offerPriceModalWindow.uploadAllBoilerEquipmentCertificateFileAndDate(randomSelfEmployed.getBoilerEquipmentCertificateFile(), randomSelfEmployed.getBoilerEquipmentCertificateValidTillDatePicker());
            selfEmployedPages.getOrderCardPage().offerPriceModalWindow.saveButton();
        });
        step(" убрать баг - Дублированное модальное окно Расценить заказ", () -> {
            selfEmployedPages.getOrderCardPage().offerPriceModalWindow.close();
        });
        step("Страница карточки  расцененного заказа - первый заказ незаполненный профиль", () -> {
            selfEmployedPages.getOrderCardPage().checkNewTenderState(OrderStatus.NEW_TENDER, OrderType.MAINTENANCE);
            selfEmployedPages.getOrderCardPage().initialStateAcceptOrder();
        });
        step("Страница информации о заполнении профиля", () -> {
            selfEmployedPages.getFillProfileInfoPage().checkFinishLoading();
            selfEmployedPages.getFillProfileInfoPage().toProfileButton();
        });
        step("Страница профиля СМЗ после перехода из первого расцененного заказа ", () -> {
            selfEmployedPages.getProfilePage().checkFirsOfferEvaluatedInitialState(randomSelfEmployed.getEmail(), randomSelfEmployed.getPhone());
            //todo sidebar unification in two modes
        });
        step(" История заказов -  СМЗ  сайдбар - после после первого расцененного заказа", () -> {

        });
        step("Сертификаты и оборудование - СМЗ  сайдбар - после первого расцененного заказа", () -> {

        });




        /*step("Начальное состотояние кабинета СМЗ - модальное окно - расценить  тендер ", () -> {
            selfEmployedPages.getOrderCardSelfEmployedPage().offerPriceModalWindow.checkInitialState();
            selfEmployedPages.getOrderCardSelfEmployedPage().offerPriceModalWindow.fillOffer(randomSelfEmployed.getMasterIDFile(), randomSelfEmployed.getMasterIDValidTillDatePicker(), randomSelfEmployed.getEquipmentCertificateFile(), randomSelfEmployed.getEquipmentCertificateValidTillDatePicker(), equipmentWorkPrice, primaryVisitPrice);

        });*/


    }

}
//todo addPagination check for  appropriate pages
