package ru.gasworkers.dev.tests.web.client.newClient.afterBG.selectService;


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
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.model.equipment.EquipmentType;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.ACCOUNT)
@Feature(AllureFeature.ACCOUNT_STATE)
@Story(AllureStory.MAINTENANCE)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.REGISTRATION)
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.POSITIVE)
@Tag(AllureTag.WEB)

public class MaintenanceSelectServiceTest extends BaseTest {
    @Browser(role = Role.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = "0x0")
    ClientPages clientPages;
    RandomClient randomClient = new RandomClient();

    @Disabled
    @Test
    @DisplayName("Состояние Кабинета СМЗ - Выбор Исполнителя на ТО")
    public void maintenanceSelectService() {
        Integer power = 20;
        EquipmentType GAS_BOILER_TYPE = EquipmentType.GAS_BOILER;
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
        step("Клиент заполняет форму фоновой регистрации", () -> {
            clientPages.getLandingPage().bgRegistration.checkFinishLoading();
            clientPages.getLandingPage().bgRegistration.fillBGMaintenanceRequest(randomClient.getObjectAddress(), GAS_BOILER_TYPE, 1, 1, power, randomClient.getPhone(), randomClient.getEmail());
        });
        clientPages.getLandingPage().bgRegistration.findOffers();
        clientPages.getLandingPage().confirmationCodeModalBG.fillCode(randomClient.getConfirmationCode(), "https://dev.gasworkers.ru/profile/client");
        step("Кабинет клиента - состояние после фоновой регистрации на ТО ", () -> {
            step("Гид  ТО по кабинету", () -> {
                clientPages.getHomePage().firstMaintenanceGuide.playSequence();
            });
            step("Страница Карта", () -> {
                //todo actual design
                clientPages.getSelectServicePage().checkFinishMaintenanceLoading();
                clientPages.getSelectServicePage().checkPublishedState();
            });
        });
    }

}
