package ru.gasworkers.dev.pages.components.selfEmployedComponent.profilePageComponent.tabCommonProfilePageComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.DatePickerDocumentsComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.BaseOrderCardComponent;

import static com.codeborne.selenide.Condition.empty;

public class fillPassportCommonSelfEmployedComponent extends BaseOrderCardComponent {
    public final DatePickerDocumentsComponent datePicker;

    public fillPassportCommonSelfEmployedComponent(RoleBrowser roleBrowser) {
        super(roleBrowser);
        datePicker = new DatePickerDocumentsComponent(roleBrowser);
    }

    private final String
            errorMsgEmptyFieldText = "Поле не заполнено";

    SelenideElement
            passportSelfBoxLocator = driver.$$("div.input-blocks").get(1).as("Бокс паспортный данных"),
            seriesAndNumberBoxLocator = passportSelfBoxLocator.$$("div.item").get(0).as("Бокс серия и номер паспорта"),
            issuedDateBoxLocator = passportSelfBoxLocator.$$("div.item").get(1).as("Бокс дата выдачи паспорта"),
            issuedByBoxLocator = passportSelfBoxLocator.$$("div.item").get(2).as("Бокс кем выдан паспорт"),
            addersBoxLocator = passportSelfBoxLocator.$$("div.item").get(3).as("Бокс адрес регистрации"),
            passportBoxLocator = driver.$("div[data-guide='passport-data']").as("Бокс Паспорт"),
            numberPassportInputLocator = driver.$("input[placeholder='Номер паспорта']").as("Номер паспорта"),
            seriesPassportInputLocator = driver.$("input[placeholder='Серия']").as("Серия паспорта"),
            passportDateComponentLocator = driver.$("div[placeholder='Дата выдачи паспорта']").as("Дата выдачи паспорта"),
            issuedByPassportInputLocator = driver.$("input[placeholder='Кем выдан']").as("Кем выдан паспорт"),
            addressPassportInputLocator = driver.$("textarea[placeholder='Адрес регистрации']").as("Адрес регистрации"),
            apartmentNumberInputLocator = driver.$("textarea[placeholder='Номер квартиры']").as("Номер квартиры");


    public void fillPassport(String seriesPassport, String numberPassport, String passportDate, String issuedByPassport, String addressPassport, String apartmentNumber) {
        stepWithRole("Заполнить поля паспорта", () -> {
            fillSeriesPassport(seriesPassport);
            fillNumberPassport(numberPassport);
            datePicker.setDate(issuedDateBoxLocator, passportDate);
            fillIssuedByPassport(issuedByPassport);
            fillAddressPassport(addressPassport);
            fillApartmentNumber(apartmentNumber);
        });
    }


    public void checkPassportError() {
        stepWithRole("Убедиться, что присутствует сообщение об ошибке в полях паспорта", () -> {
            validation.checkErrorMsgInBox(seriesAndNumberBoxLocator, errorMsgEmptyFieldText);
//            checkErrorMsg(numberPassportInputLocator, errorMsgEmptyFieldText);
            datePicker.checkErrorMsg(passportBoxLocator);
        });
    }

    public void fillNumberPassport(String numberPassport) {
        stepWithRole("Заполнить поле номер пасспорта", () -> {
            numberPassportInputLocator.setValue(numberPassport);
        });
    }

    public void fillSeriesPassport(String seriesPassport) {
        stepWithRole("Заполнить поле серия пасспорта", () -> {
            seriesPassportInputLocator.setValue(seriesPassport);
        });
    }

    public void fillIssuedByPassport(String issuedByPassport) {
        stepWithRole("Заполнить поле кем выдан пасспорт", () -> {
            issuedByPassportInputLocator.setValue(issuedByPassport);
        });
    }

    public void fillAddressPassport(String addressPassport) {
        stepWithRole("Заполнить поле адрес регистрации", () -> {
            addressPassportInputLocator.setValue(addressPassport);
        });
    }

    public void fillApartmentNumber(String apartmentNumber) {
        stepWithRole("Заполнить поле номер квартиры", () -> {
            apartmentNumberInputLocator.setValue(apartmentNumber);
        });
    }

    public void checkInitialState() {
        stepWithRole("Убедиться, что все поля паспорта пустые", () -> {
            numberPassportInputLocator.shouldBe(empty);
            seriesPassportInputLocator.shouldBe(empty);
            datePicker.checkInitialState(issuedDateBoxLocator);
            issuedByPassportInputLocator.shouldBe(empty);
            addressPassportInputLocator.shouldBe(empty);
            apartmentNumberInputLocator.shouldBe(empty);
        });
    }

    public void checkValidationTriggeredState() {
        stepWithRole("Убедиться, что блок  деталей пасспорта не заполнен и присутсвует валиидационное сообщение", () -> {
            stepWithRole("Убедиться, что блок  деталей пасспорта не заполнен", () -> {
                numberPassportInputLocator.shouldBe(empty);
                seriesPassportInputLocator.shouldBe(empty);
                datePicker.checkInitialState(issuedDateBoxLocator);
                issuedByPassportInputLocator.shouldBe(empty);
                addressPassportInputLocator.shouldBe(empty);
                apartmentNumberInputLocator.shouldBe(empty);
            });
            stepWithRole("Убедиться, что присутствует сообщение об ошибке в полях паспорта", () -> {
                validation.checkErrorMsgInBox(seriesAndNumberBoxLocator, errorMsgEmptyFieldText);
                validation.checkErrorMsgInBox(seriesAndNumberBoxLocator, errorMsgEmptyFieldText);
                validation.checkErrorMsgInBox(issuedByBoxLocator, errorMsgEmptyFieldText);
                validation.checkErrorMsgInBox(addersBoxLocator, errorMsgEmptyFieldText);
//                checkErrorMsgInBox(apartmentNumberInputLocator, errorMsgEmptyFieldText);
            });
        });
    }
}
