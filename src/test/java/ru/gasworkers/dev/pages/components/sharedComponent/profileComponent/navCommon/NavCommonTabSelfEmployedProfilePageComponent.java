package ru.gasworkers.dev.pages.components.sharedComponent.profileComponent.navCommon;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.landingComponent.bgRegistrationComponent.DateBGRegistrationLandingComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.profilePageComponent.BaseProfileSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.profilePageComponent.tabCommonProfilePageComponent.*;
import ru.gasworkers.dev.utils.userBuilder.RandomSelfEmployedAndMaster;

public class NavCommonTabSelfEmployedProfilePageComponent extends BaseProfileSelfEmployedComponent {
    public final DateBGRegistrationLandingComponent datePickerBlock;
    public final fillNameCommonSelfEmployedComponent fillNameBlock;
    public final fillPassportCommonSelfEmployedComponent fillPassportBlock;
    public final fillMasterIDCommonSelfEmployedComponent fillMasterIDBlock;
    public final fillBankAccountCommonSelfEmployedComponent fillBankAccountBlock;
    public final fillTaxpayerCertificateCommonSelfEmployedComponent fillTaxpayerCertificateBlock;

    public NavCommonTabSelfEmployedProfilePageComponent(RoleBrowser browser) {
        super(browser);
        datePickerBlock = new DateBGRegistrationLandingComponent(browser);
        fillNameBlock = new fillNameCommonSelfEmployedComponent(browser);
        fillPassportBlock = new fillPassportCommonSelfEmployedComponent(browser);
        fillMasterIDBlock = new fillMasterIDCommonSelfEmployedComponent(browser);
        fillBankAccountBlock = new fillBankAccountCommonSelfEmployedComponent(browser);
        fillTaxpayerCertificateBlock = new fillTaxpayerCertificateCommonSelfEmployedComponent(browser);
    }


    SelenideElement
            saveButtonLocator = driver.$("button.btn.btn-primary").as("Кнопка Сохранить");


    public void checkInitialState() {
        stepWithRole("Проверить начальное состояние", () -> {
            fillNameBlock.checkInitialState();
            fillPassportBlock.checkInitialState();
            fillMasterIDBlock.checkInitialState();
            fillBankAccountBlock.checkInitialState();
            fillTaxpayerCertificateBlock.checkInitialState();
            validation.checkTotalNoErrors();
            toOrderContextButtons.checkNoToOrderContextButtonsPresenceState();
        });
    }

    public void checkFirsOfferEvaluatedInitialState() {
        stepWithRole("Проверить начальное состояние", () -> {
            fillNameBlock.checkInitialState();
            fillPassportBlock.checkInitialState();
            fillMasterIDBlock.checkFilledState();
            fillBankAccountBlock.checkValidationTriggeredState();
            fillTaxpayerCertificateBlock.checkInitialState();
        });
        toOrderContextButtons.checkToOrderContextButtonsPresenceState();
    }

    public void fillRandomData(RandomSelfEmployedAndMaster randomSelfEmployedAndMaster) {
        stepWithRole("Заполнить случайными данными все элементы вкладки общие данные", () -> {
//            fillNameBlock.fillRandomData(randomSelfEmployedAndMaster);
//            fillPassportBlock.fillRandomData(randomSelfEmployedAndMaster);
//            fillMasterIDBlock.fillRandomData(randomSelfEmployedAndMaster);
//            fillBankAccountBlock.fillRandomData(randomSelfEmployedAndMaster);
//            fillTaxpayerCertificateBlock.fillRandomData(randomSelfEmployedAndMaster);
        });
    }

    public void saveButton() {
        button.main.clickActive("Сохранить");
    }

    private void checkSaveButtonEnabledState() {
        stepWithRole("Убедиться, что кнопка Сохранить активна", () -> {
            saveButtonLocator.shouldBe(Condition.enabled);
        });
    }

    public void checkEmptyFormValidationTriggeredState() {
        stepWithRole("Убедиться, что валидация срабатывает у всех элементов вкладки общие данные", () -> {
            fillNameBlock.checkValidationTriggeredState();
            fillPassportBlock.checkValidationTriggeredState();
            fillBankAccountBlock.checkValidationTriggeredState();
            fillMasterIDBlock.checkValidationTriggeredState();
            fillTaxpayerCertificateBlock.checkValidationTriggeredState();
            validation.checkTotalErrorsCount(12);
            checkSaveButtonEnabledState();
        });
    }
}
// TODO implement CommonDataPickerComponent. Upload photo.
