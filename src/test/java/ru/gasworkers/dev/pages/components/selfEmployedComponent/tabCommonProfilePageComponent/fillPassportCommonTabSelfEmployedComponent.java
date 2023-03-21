package ru.gasworkers.dev.pages.components.selfEmployedComponent.tabCommonProfilePageComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.DatePickerDocumentsComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent.BaseTabOrderCardComponent;

import static com.codeborne.selenide.Condition.empty;

public class fillPassportCommonTabSelfEmployedComponent extends BaseTabOrderCardComponent {
    public final DatePickerDocumentsComponent datePicker;

    public fillPassportCommonTabSelfEmployedComponent(RoleBrowser roleBrowser) {
        super(roleBrowser);
        datePicker = new DatePickerDocumentsComponent(roleBrowser);
    }

    SelenideElement
            passportBoxLocator = driver.$("div[data-guide='passport-data']").as("Бокс Паспорт"),
            numberPassportInputLocator = driver.$("input[placeholder='Номер паспорта']").as("Номер паспорта"),
            seriesPassportInputLocator = driver.$("input[placeholder='Серия']").as("Серия паспорта"),
            passportDateComponentLocator = driver.$("div[placeholder='Дата выдачи паспорта']").as("Дата выдачи паспорта"),
            issuedByPassportInputLocator = driver.$("input[placeholder='Кем выдан']").as("Кем выдан паспорт"),
            addressPassportInputLocator = driver.$("input[placeholder='Адрес регистрации']").as("Адрес регистрации"),
            apartmentNumberInputLocator = driver.$("input[placeholder='Номер квартиры']").as("Номер квартиры");

    public void fillPassport(String seriesPassport, String numberPassport, String passportDate, String issuedByPassport, String addressPassport, String apartmentNumber) {
        stepWithRole("Заполнить поля паспорта", () -> {
            fillSeriesPassport(seriesPassport);
            fillNumberPassport(numberPassport);
            datePicker.setDate(passportBoxLocator, passportDate);
            fillIssuedByPassport(issuedByPassport);
            fillAddressPassport(addressPassport);
            fillApartmentNumber(apartmentNumber);
        });
    }


    public void checkPassportError() {
        stepWithRole("Убедиться, что присутствует сообщение об ошибке в полях паспорта", () -> {
            checkErrorMsg(seriesPassportInputLocator);
            checkErrorMsg(numberPassportInputLocator);
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
            datePicker.checkInitialState(passportDateComponentLocator);
            issuedByPassportInputLocator.shouldBe(empty);
            addressPassportInputLocator.shouldBe(empty);
            apartmentNumberInputLocator.shouldBe(empty);


        });
        stepWithRole("Убедиться, что присутствует сообщение об ошибке в полях паспорта", () -> {
            checkErrorMsg(numberPassportInputLocator);
            checkErrorMsg(seriesPassportInputLocator);
            checkErrorMsg(issuedByPassportInputLocator);
            checkErrorMsg(addressPassportInputLocator);
            checkErrorMsg(apartmentNumberInputLocator);
        });
    }
}
