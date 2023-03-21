package ru.gasworkers.dev.pages.components.selfEmployedComponent.tabCommonProfilePageComponent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.ValidationBellSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.DatePickerDocumentsComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.FileUploaderComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent.BaseTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.navCommon.NavCommonTabSelfEmployedProfilePageComponent;

public class fillMasterIDCommonTabSelfEmployedComponent extends BaseTabOrderCardComponent {
    public final ValidationBellSelfEmployedComponent bell;
    public final FileUploaderComponent uploader;
    public final DatePickerDocumentsComponent datePicker;
    public final BoxValidationTabCommonSelfEmployedComponent boxValidation;
    public fillMasterIDCommonTabSelfEmployedComponent(RoleBrowser roleBrowser) {
        super(roleBrowser);
        bell = new ValidationBellSelfEmployedComponent(roleBrowser);
        uploader = new FileUploaderComponent(roleBrowser);
        datePicker = new DatePickerDocumentsComponent(roleBrowser);
        boxValidation = new BoxValidationTabCommonSelfEmployedComponent(roleBrowser);
    }

    private final String
            descriptionText = " Прикрепите удостоверение мастера и укажите срок действия (jpeg, word, pdf)*",
            uploadLinkText = "Прикрепить файл";

    SelenideElement
            masterIDBoxLocator = driver.$("div[data-guide='profile-common-bottom']").as("Удостоверение мастера"),
            descriptionLocator = masterIDBoxLocator.$("div span").as("Описание"),
            dateBoxLocator = masterIDBoxLocator.$(" input.mx-input").as("Дата окончания");

    public void checkInitialState() {
        stepWithRole("Убедиться, что удостоверение мастера не заполнено", () -> {
            boxValidation.checkRedState(masterIDBoxLocator);
            descriptionLocator.shouldHave(Condition.text(descriptionText));
            bell.checkRedBellState(masterIDBoxLocator);
            uploader.checkInitialState(masterIDBoxLocator);
            datePicker.checkInitialState(dateBoxLocator);
        });
    }

}
