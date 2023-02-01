package pages.components.sharedComponent.profileTabsComponent.commonTab;

import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.BaseComponent;
import pages.components.sharedComponent.CommonDatePickerComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class CommonClientProfileComponent extends BaseComponent {

    public final CommonDatePickerComponent datePicker;
     public CommonClientProfileComponent(RoleBrowser browser) {
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
             saveButtonLocator = driver.$(byTagAndText("button", "Сохранить")).as("Кнопка Сохранить");


    public void checkInitialCabinetState() {
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
            saveButtonLocator.shouldBe(enabled);
        });
    }

    public void checkFilledCabinetState () {
        stepWithRole("Убедиться, что вкладка Общие данные заполнена", () -> {
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
            saveButtonLocator.shouldBe(disabled);
        });
    }



    public void checkInitialStatus(String name, String patronymicName, String surname) {
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
                seriesPassportLocator.shouldHave(value(""));
                numberPassportLocator.shouldHave(value(""));
                datePassportLocator.shouldHave(value(""));
                whoPassportLocator.shouldHave(value(""));
                registrationAddressLocator.shouldHave(value(""));
                apartmentLocator.shouldHave(value(""));
            });
        });
    }
}
