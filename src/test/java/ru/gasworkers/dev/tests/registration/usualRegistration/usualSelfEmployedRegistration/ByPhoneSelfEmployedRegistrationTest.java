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
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.SE_MEMBER),  @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE)})
    @DisplayName("Регистрация СМЗ в СК")
    public void byPhoneSEMemberRegistrationTest() {
        String equipmentWorkPrice ="4000";
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
            selfEmployedPages.getTypeSelfEmployedPage().checkFinishLoading();
            //todo radioDefaultState :after
            selfEmployedPages.getTypeSelfEmployedPage().selectAlreadySE();
            selfEmployedPages.getTypeSelfEmployedPage().selectIndividualEntrepreneurSE();
            selfEmployedPages.getTypeSelfEmployedPage().selectAlreadySE();
            selfEmployedPages.getTypeSelfEmployedPage().forwardButton();
        });
        step("Страница успешной регистрации", () -> {
            selfEmployedPages.getSuccessfulRegistrationSelfEmployedPage().checkFinishLoading();
            selfEmployedPages.getSuccessfulRegistrationSelfEmployedPage().clickForwardButton();
        });
        step("Гид СМЗ", () -> {
            selfEmployedPages.getHomeSelfEmployedPage().firstGuide.playSequence(15);
        });
        step("Домашняя СМЗ после гида - режим карта и режим мастер", () -> {
            selfEmployedPages.getHomeSelfEmployedPage().checkInitialState();
        });
        step("Профиль СМЗ после гида", () -> {
            selfEmployedPages.getHomeSelfEmployedPage().header.burger.openBurger();
            selfEmployedPages.getHomeSelfEmployedPage().header.burger.profile();
            selfEmployedPages.getProfileSelfEmployedPage().checkInitialState(randomSelfEmployed.getEmail(), randomSelfEmployed.getPhone());
            // todo sidebar unification
            //todo sidebar notifications block
        });

        step("Выбрать первый заказ", () -> {
            selfEmployedPages.getProfileSelfEmployedPage().sidebar.home();
            selfEmployedPages.getProfileSelfEmployedPage().modeSwitcher.checkMasterMode();
            selfEmployedPages.getProfileSelfEmployedPage().modeSwitcher.checkDispatcherMode();
            selfEmployedPages.getProfileSelfEmployedPage().modeSwitcher.checkDispatcherMode();
            selfEmployedPages.getHomeSelfEmployedPage().mapMode.selectFirstMaintenanceOffer();
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
        step("Страница карточки  расцененного заказа - первый заказ незаполненный профиль", () -> {
            selfEmployedPages.getOrderCardPage().checkNewTenderState(OrderStatus.NEW_TENDER, OrderType.MAINTENANCE);
            selfEmployedPages.getOrderCardPage().acceptOrderInitialState();
        });
        step("Страница информации о заполнении профиля", () -> {
            selfEmployedPages.getFillProfileInfo().checkFinishLoading();
            selfEmployedPages.getFillProfileInfo().toProfileButton();
        });
        step("Страница профиля СМЗ после перехода из первого расцененного заказа ", () -> {
            selfEmployedPages.getProfileSelfEmployedPage().checkFirsOfferEvaluatedInitialState(randomSelfEmployed.getEmail(), randomSelfEmployed.getPhone());
        });




        /*step("Начальное состотояние кабинета СМЗ - модальное окно - расценить  тендер ", () -> {
            selfEmployedPages.getOrderCardSelfEmployedPage().offerPriceModalWindow.checkInitialState();
            selfEmployedPages.getOrderCardSelfEmployedPage().offerPriceModalWindow.fillOffer(randomSelfEmployed.getMasterIDFile(), randomSelfEmployed.getMasterIDValidTillDatePicker(), randomSelfEmployed.getEquipmentCertificateFile(), randomSelfEmployed.getEquipmentCertificateValidTillDatePicker(), equipmentWorkPrice, primaryVisitPrice);

        });*/


    }

}
