package ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.navCommonTab;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.CommonDatePickerComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class NavCommonClientTabProfileComponent extends BaseComponent {

    public final CommonDatePickerComponent datePicker;
     public NavCommonClientTabProfileComponent(RoleBrowser browser) {
         super(browser);
            datePicker = new CommonDatePickerComponent(browser);
     }
     SelenideElement
             nameSubTitleLocator = driver.$$("div .title").get(0).as("Подзаголовок Личные данные"),
            passportSubTitleLocator = driver.$$("div .title").get(1).as("Подзаголовок Паспортные данные"),
             surnameLocator = driver.$("input[placeholder*=Фамилия]").as("Фамилия"),
             nameLocator = driver.$("input[placeholder*=Имя]").as("Имя"),
             patronymicLocator = driver.$("input[placeholder*=Отчество]").as("Отчество"),
             seriesPassportLocator = driver.$("input[placeholder*=Серия]").as("Серия паспорта"),
             numberPassportLocator = driver.$("input[placeholder*=Номер]").as("Номер паспорта"),
             datePassportLocator = driver.$("input[placeholder*=Дата]").as("Дата выдачи паспорта"),
             whoPassportLocator = driver.$("input[placeholder*=выдан]").as("Кем выдан паспорт"),
             registrationAddressLocator = driver.$("textarea[placeholder*=Адрес]").as("Адрес регистрации"),
             apartmentLocator = driver.$("textarea[placeholder*=квартиры]").as("Номер квартиры"),
             saveButtonLocator = driver.$("button.mb-3.btn.btn-primary ").as("Кнопка Сохранить");


    public void checkFinishLoading() {
        stepWithRole("Убедиться, что вкладка Общие в начальном состоянии", () -> {
            // TODO implement CommonDataPickerComponent. Upload photo.
            nameSubTitleLocator.shouldHave(visible, text("Личные данные"));
            passportSubTitleLocator.shouldHave(visible, text("Паспортные данные"));
            surnameLocator.shouldBe(visible);
            nameLocator.shouldBe(visible);
            patronymicLocator.shouldBe(visible);
            seriesPassportLocator.shouldBe(visible);
            numberPassportLocator.shouldBe(visible);
            datePassportLocator.shouldBe(visible);
            whoPassportLocator.shouldBe(visible);
            registrationAddressLocator.shouldBe(visible);
            apartmentLocator.shouldBe(visible);
            saveButtonLocator.shouldBe(visible).shouldHave(text("Сохранить"));
        });


    }

    public void checkInitialState(String name, String patronymicName, String surname) {
        stepWithRole("Убедиться, что поля Фамилия, Имя, Отчество заполнены: ", () -> {
            stepWithRole("Фамилия: " + surnameLocator.getValue(), () ->
                    surnameLocator.shouldHave(value(surname))
            );
            stepWithRole("Имя: " + nameLocator.getValue(), () ->
                    nameLocator.shouldHave(value(name))
            );
            stepWithRole("Отчество: " + patronymicLocator.getValue(), () ->
                    patronymicLocator.shouldHave(value(patronymicName))
            );
            stepWithRole("Убедиться, что остальные поля вкладки Общие данные пустые", () -> {
                seriesPassportLocator.shouldBe(empty);
                numberPassportLocator.shouldBe(empty);
                datePassportLocator.shouldBe(empty);
                whoPassportLocator.shouldBe(empty);
                registrationAddressLocator.shouldBe(empty);
                apartmentLocator.shouldBe(empty);
            });
            stepWithRole("Убедиться, что кнопка Сохранить актива", () -> {
                saveButtonLocator.shouldBe(enabled);
            });
        });
    }

    public void checkFilledState() {
        stepWithRole("Убедиться, что вкладка Общие данные заполнена", () -> {
            // TODO implement CommonDataPickerComponent. Upload photo. check other fields info.
            nameSubTitleLocator.shouldHave(visible, text("Личные данные"));
            passportSubTitleLocator.shouldHave(visible, text("Паспортные данные"));
            surnameLocator.shouldBe(visible);
            nameLocator.shouldBe(visible);
            patronymicLocator.shouldBe(visible);
            seriesPassportLocator.shouldBe(visible);
            numberPassportLocator.shouldBe(visible);
            datePassportLocator.shouldBe(visible);
            whoPassportLocator.shouldBe(visible);
            registrationAddressLocator.shouldBe(visible);
            apartmentLocator.shouldBe(visible);
        });
        stepWithRole("Убедиться, что кнопка Сохранить неактивна", () -> {
            saveButtonLocator.shouldBe(disabled);
        });
    }


}
