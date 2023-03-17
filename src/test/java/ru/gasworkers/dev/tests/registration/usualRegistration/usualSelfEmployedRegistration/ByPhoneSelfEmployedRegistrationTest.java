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
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.SelfEmployedPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomMaster;

import static io.qameta.allure.Allure.step;

public class ByPhoneSelfEmployedRegistrationTest extends BaseTest {
    RandomMaster randomMaster = new RandomMaster();


    @Browser(role = Role.SELF_EMPLOYED, browserSize = SizeBrowser.DEFAULT, browserPosition = "0x0")
    SelfEmployedPages selfEmployedPages;

    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.USUAL_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.SE_MEMBER),  @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE)})
    @DisplayName("Регистрация СМЗ в СК")
    public void  ByPhoneSEMemberRegistrationTest() {
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
            selfEmployedPages.getRegistrationPage().secondStep.byPhone(randomMaster.getPhone());
            selfEmployedPages.getRegistrationPage().secondStep.forwardButton();
        });
        step("Страница третьего шага регистрации СМЗ", () -> {
            selfEmployedPages.getRegistrationPage().thirdStep.checkFinishLoading();
            selfEmployedPages.getRegistrationPage().thirdStep.fillCode(randomMaster.getConfirmationCode());
        });
        step("Страница ввода пароля", () -> {
            selfEmployedPages.getRegistrationPage().forthStep.checkFinishLoading(4);
            selfEmployedPages.getRegistrationPage().forthStep.setPassword(randomMaster.getPassword());
            selfEmployedPages.getRegistrationPage().forthStep.forwardButton();
        });
        step("Страница пятого шага регистрации СМЗ", () -> {
            selfEmployedPages.getRegistrationPage().fifthStep.checkFinishLoading(5);
            selfEmployedPages.getRegistrationPage().fifthStep.setEmail(randomMaster.getEmail());
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
//        step("Страница профиля СМЗ - первый гид", () -> {
//                    selfEmployedPages.getHomeSelfEmployedPage().firstGuide.playSequence();
//        });
        step("Домашняя страница  СМЗ - пропустить гид", () -> {
            selfEmployedPages.getHomeSelfEmployedPage().firstGuide.playSequence(1);
            selfEmployedPages.getHomeSelfEmployedPage().firstGuide.skipGuide();
        });
        step("Домашняя СМЗ после гида", () -> {
            selfEmployedPages.getHomeSelfEmployedPage().checkInitialState();
        });

        step("Выбрать первый заказ", () -> {
//            selfEmployedPages.getHomeSelfEmployedPage().mapMode.selectOfferByIndex(0);
            selfEmployedPages.getHomeSelfEmployedPage().mapMode.selectOfferByNumber("4556");
        });

        step("Страница карточки заказа", () -> {
            selfEmployedPages.getOrderCardSelfEmployedPage().checkInitialState();
//            selfEmployedPages.getOrderCardSelfEmployedPage().checkNotEvaluatedOfferButtonState();
        });
        step("Модальное окно Расценить заказ", () -> {
            selfEmployedPages.getOrderCardSelfEmployedPage().offerPriceButton();
            selfEmployedPages.getOrderCardSelfEmployedPage().fillUpOfferPriceBanner.checkFinishLoading();
            selfEmployedPages.getOrderCardSelfEmployedPage().fillUpOfferPriceBanner.clickForwardButton();
            selfEmployedPages.getOrderCardSelfEmployedPage().offerPriceModalWindow.checkInitialState();
            selfEmployedPages.getOrderCardSelfEmployedPage().offerPriceModalWindow.checkOfferPriceEquipmentCount();
            selfEmployedPages.getOrderCardSelfEmployedPage().offerPriceModalWindow.fillCollectionPrices("4010", "4100", primaryVisitPrice);
            int currentOfferEquipmentPrice = selfEmployedPages.getOrderCardSelfEmployedPage().offerPriceModalWindow.allEquipmentCurrentPrice();
            int totalOfferPrice = selfEmployedPages.getOrderCardSelfEmployedPage().offerPriceModalWindow.totalOfferPrice();
            selfEmployedPages.getOrderCardSelfEmployedPage().offerPriceModalWindow.fillMasterIdDocuments(randomMaster.getMasterIDFile(), randomMaster.getMasterIDValidTillDatePicker());
            selfEmployedPages.getOrderCardSelfEmployedPage().offerPriceModalWindow.uploadAllBoilerEquipmentCertificateFileAndDate(randomMaster.getBoilerEquipmentCertificateFile(), randomMaster.getBoilerEquipmentCertificateValidTillDatePicker());
            selfEmployedPages.getOrderCardSelfEmployedPage().offerPriceModalWindow.saveButton();
        });
        step("Страница карточки  расцененного заказа", () -> {
//            selfEmployedPages.getOrderCardSelfEmployedPage().checkNewTenderState();
//            selfEmployedPages.getOrderCardSelfEmployedPage().checkEvaluatedOfferButtonsState();
        });




        /*step("Начальное состотояние кабинета СМЗ - модальное окно - расценить  тендер ", () -> {
            selfEmployedPages.getOrderCardSelfEmployedPage().offerPriceModalWindow.checkInitialState();
            selfEmployedPages.getOrderCardSelfEmployedPage().offerPriceModalWindow.fillOffer(randomMaster.getMasterIDFile(), randomMaster.getMasterIDValidTillDatePicker(), randomMaster.getEquipmentCertificateFile(), randomMaster.getEquipmentCertificateValidTillDatePicker(), equipmentWorkPrice, primaryVisitPrice);

        });*/


    }

}
