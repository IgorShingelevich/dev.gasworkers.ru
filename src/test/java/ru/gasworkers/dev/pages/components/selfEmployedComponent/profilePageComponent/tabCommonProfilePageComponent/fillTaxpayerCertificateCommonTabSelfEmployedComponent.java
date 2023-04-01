package ru.gasworkers.dev.pages.components.selfEmployedComponent.profilePageComponent.tabCommonProfilePageComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.BoxValidationTabCommonSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.ValidationBellSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.DatePickerDocumentsComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.FileUploaderComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent.BaseTabOrderCardComponent;

import static com.codeborne.selenide.Condition.text;

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
            masterIDBoxLocator = driver.$("div[data-guide='profile-common-bottom']").as("Бокс удостоверение мастера"),
            taxpayerCertificateBoxLocator =driver.$( "div.row-wrap.align-items-center.mt-4").as(" Бокс удостоверение налогоплательщика"),
            descriptionLocator = taxpayerCertificateBoxLocator.$("div span").as("Описание"),
            dateBoxLocator = taxpayerCertificateBoxLocator.$(" input.mx-input").as("Дата окончания");


    public void checkInitialState() {
        stepWithRole("Убедиться, что удостоверение налогоплательщика не заполнено", () -> {
            boxValidation.checkNoRedState(taxpayerCertificateBoxLocator);
            descriptionLocator.shouldHave(text(descriptionText));
            bell.checkRedBellState(taxpayerCertificateBoxLocator);
            uploader.checkInitialState(taxpayerCertificateBoxLocator);
            datePicker.checkInitialState(taxpayerCertificateBoxLocator);
        });
    }

    public void checkValidationTriggeredState() {
        stepWithRole("Убедиться, что удостоверение налогоплательщика не заполнено и присутсвует сообщение об ошибке", () -> {
            boxValidation.checkRedBoxState(taxpayerCertificateBoxLocator);
            bell.checkRedBellState(taxpayerCertificateBoxLocator);
            datePicker.checkInitialState(masterIDBoxLocator);
            uploader.checkInitialState(taxpayerCertificateBoxLocator);
        });
    }

}
