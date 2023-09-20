package ru.gasworkers.dev.tests.web.registration.bgClientRegistration;

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
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.BG_REGISTRATION)
@Story(AllureStory.VIDEO)
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.REGISTRATION)
@Tag(AllureTag.BG_REGISTRATION)
@Tag(AllureTag.POSITIVE)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.REGISTRATION)
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.WEB)
@Tag(AllureTag.WEB_REGISTRATION)

public class VideoBGRegistrationTest extends BaseTest {
    @Browser(role = UserRole.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = "0x0")
    ClientPages clientPages;
    RandomClient randomClient = new RandomClient();
    @Test
    @DisplayName("Фоновая Регистрация на Видео Сейчас с указанием телефона и почты")
    public void bgRegistrationNowVideo() {
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
        // todo String desiredDate =
        // todo String desiredTime =
        //todo modify equipmentCollection index for  different equipment count
        String errorText = clientPages.getLandingPage().bgRegistration.equipment.getErrorText();
        clientPages.getLandingPage().bgRegistration.findOffers();
        clientPages.getLandingPage().confirmationCodeModalBG.fillCode(randomClient.getConfirmationCode(), "https://dev.gasworkers.ru/orders/consultation");
        step("Страница Видеоконсультация", () -> {
            clientPages.getConsultationVideoPage().urlChecker.urlContains("/orders/consultation/");
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
