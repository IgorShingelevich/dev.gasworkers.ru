package ru.gasworkers.dev.pages.components.selfEmployedComponent.profilePageComponent.tabCommonProfilePageComponent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.BoxValidationTabCommonSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.ValidationBellSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.DatePickerDocumentsComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.FileUploaderComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.BaseOrderCardComponent;

public class fillMasterIDCommonSelfEmployedComponent extends BaseOrderCardComponent {
    public final ValidationBellSelfEmployedComponent bell;
    public final FileUploaderComponent uploader;
    public final DatePickerDocumentsComponent datePicker;
    public final BoxValidationTabCommonSelfEmployedComponent boxValidation;

    public fillMasterIDCommonSelfEmployedComponent(RoleBrowser roleBrowser) {
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
            boxValidation.checkNoRedState(masterIDBoxLocator);
            descriptionLocator.shouldHave(Condition.text(descriptionText));
            bell.checkRedBellState(masterIDBoxLocator);
            uploader.checkInitialState(masterIDBoxLocator);
            datePicker.checkInitialState(masterIDBoxLocator);
        });
    }

    public void checkValidationTriggeredState() {
        stepWithRole("Убедиться, что удостоверение мастера не заполнено и присутсвует сообщение об ошибке", () -> {
            boxValidation.checkRedBoxState(masterIDBoxLocator);
            descriptionLocator.shouldHave(Condition.text(descriptionText));
            bell.checkRedBellState(masterIDBoxLocator);
            uploader.checkInitialState(masterIDBoxLocator);
            datePicker.checkInitialState(masterIDBoxLocator);
        });
    }

    public void checkFilledState() {
        stepWithRole("Убедиться, что удостоверение мастера заполнено", () -> {
            boxValidation.checkNoRedState(masterIDBoxLocator);
            descriptionLocator.shouldHave(Condition.text(descriptionText));
            bell.checkBlueBellState(masterIDBoxLocator);
            uploader.checkFilledState(masterIDBoxLocator, 1);
            datePicker.checkFilledState(masterIDBoxLocator);
        });
    }

}
