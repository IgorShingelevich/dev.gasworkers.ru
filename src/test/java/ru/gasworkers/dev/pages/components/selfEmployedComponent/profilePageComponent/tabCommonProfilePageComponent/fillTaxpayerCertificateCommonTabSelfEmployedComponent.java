package ru.gasworkers.dev.pages.components.selfEmployedComponent.profilePageComponent.tabCommonProfilePageComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.BoxValidationTabCommonSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.ValidationBellSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.DatePickerDocumentsComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.FileUploaderComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent.BaseTabOrderCardComponent;

public class fillTaxpayerCertificateCommonTabSelfEmployedComponent extends BaseTabOrderCardComponent {
    public final ValidationBellSelfEmployedComponent bell;
    public final FileUploaderComponent uploader;
    public final DatePickerDocumentsComponent datePicker;
    public final BoxValidationTabCommonSelfEmployedComponent boxValidation;

    public fillTaxpayerCertificateCommonTabSelfEmployedComponent(RoleBrowser roleBrowser) {
        super(roleBrowser);
        bell = new ValidationBellSelfEmployedComponent(roleBrowser);
        uploader = new FileUploaderComponent(roleBrowser);
        datePicker = new DatePickerDocumentsComponent(roleBrowser);
        boxValidation = new BoxValidationTabCommonSelfEmployedComponent(roleBrowser);
    }

    private final String
            descriptionText = "Прикрепите справку о постановке на учет физического лица в качестве налогоплательщика на профессиональный доход.";

    SelenideElement
            taxpayerCertificateBoxLocator = driver.$("div[data-guide='profile-common-bottom']").as("Удостоверение налогоплательщика"),
            descriptionLocator = taxpayerCertificateBoxLocator.$("div span").as("Описание"),
            dateBoxLocator = taxpayerCertificateBoxLocator.$(" input.mx-input").as("Дата окончания");




    public void checkInitialState() {
        stepWithRole("Убедиться, что удостоверение налогоплательщика не заполнено", () -> {
        });
    }
}
