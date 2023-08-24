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
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.model.equipment.EquipmentType;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.BG_REGISTRATION)
@Story(AllureStory.REPAIR)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.REGISTRATION)
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.BG_REGISTRATION)
@Tag(AllureTag.POSITIVE)
@Tag(AllureTag.POSITIVE)
@Tag(AllureTag.WEB)
@Tag(AllureTag.WEB_REGISTRATION)
public class RepairBGRegistrationTest extends BaseTest {
    @Browser(role = Role.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = "0x0")
    ClientPages clientPages;
    RandomClient randomClient = new RandomClient();
    @Test
    @DisplayName("Фоновая Регистрация на Ремонт с указанием телефона и почты")
    public void bgRegistrationPhoneRepair() {
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
        clientPages.getLandingPage().bgRegistration.findOffers();
        clientPages.getLandingPage().confirmationCodeModalBG.fillCode(randomClient.getConfirmationCode(), "https://dev.gasworkers.ru/profile/client");
        clientPages.getHomePage().urlChecker.urlContains("profile/client");
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




