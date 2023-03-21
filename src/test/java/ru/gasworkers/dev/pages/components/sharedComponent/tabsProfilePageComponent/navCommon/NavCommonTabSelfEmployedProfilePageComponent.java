package ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.navCommon;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.landingComponent.bgRegistrationComponent.DateBGRegistrationLandingComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.tabCommonProfilePageComponent.*;

import static com.codeborne.selenide.Condition.text;

public class NavCommonTabSelfEmployedProfilePageComponent extends BaseComponent {
    public final DateBGRegistrationLandingComponent datePickerBlock;
    public final fillNameCommonTabSelfEmployedComponent fillNameBlock;
    public final fillPassportCommonTabSelfEmployedComponent fillPassportBlock;
    public final fillMasterIDCommonTabSelfEmployedComponent fillMasterIDBlock;
    public final fillBankAccountCommonTabSelfEmployedComponent fillBankAccountBlock;
    public final fillTaxpayerCertificateCommonTabSelfEmployedComponent fillTaxpayerCertificateBlock;

    public NavCommonTabSelfEmployedProfilePageComponent(RoleBrowser browser){
        super(browser);
        datePickerBlock = new DateBGRegistrationLandingComponent(browser);
        fillNameBlock = new fillNameCommonTabSelfEmployedComponent(browser);
        fillPassportBlock = new fillPassportCommonTabSelfEmployedComponent(browser);
        fillMasterIDBlock = new fillMasterIDCommonTabSelfEmployedComponent(browser);
        fillBankAccountBlock = new fillBankAccountCommonTabSelfEmployedComponent(browser);
        fillTaxpayerCertificateBlock = new fillTaxpayerCertificateCommonTabSelfEmployedComponent(browser);
    }

    private final String
            diplomaBannerText = "Обратите внимание! Необходимо указать банковские данные карты открытой для приема платежей в качестве самозанятого";

    SelenideElement
            diplomaBannerLocator = driver.$("div.diploma.diploma-blue").as("Баннер Диплом"),
            saveButtonLocator = driver.$("button.btn.btn-primary").as("Кнопка Сохранить"),
            toOrderBottomButtonLocator = driver.$("button.mr-2.mb-3.btn.btn-warning").as("Нижняя кнопка К заказу"),
            toOrderTopButtonLocator = driver.$(".w-100 button.btn.btn-warning.disable-outline").as("Верхняя кнопка К заказу");

    public void checkInitialState () {
        stepWithRole("Проверить начальное состояние", () -> {
            diplomaBannerLocator.shouldHave(text(diplomaBannerText));
            fillNameBlock.checkInitialState();
            fillPassportBlock.checkInitialState();
            fillMasterIDBlock.checkInitialState();
//            fillBankAccountBlock.checkInitialState();
            fillTaxpayerCertificateBlock.checkInitialState();
        });
    }


}
// TODO implement CommonDataPickerComponent. Upload photo.
