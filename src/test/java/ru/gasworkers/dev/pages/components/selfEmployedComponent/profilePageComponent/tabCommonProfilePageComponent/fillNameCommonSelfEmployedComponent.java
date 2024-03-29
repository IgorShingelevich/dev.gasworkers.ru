package ru.gasworkers.dev.pages.components.selfEmployedComponent.profilePageComponent.tabCommonProfilePageComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.BaseOrderCardComponent;

import static com.codeborne.selenide.Condition.empty;

public class fillNameCommonSelfEmployedComponent extends BaseOrderCardComponent {
    public fillNameCommonSelfEmployedComponent(RoleBrowser roleBrowser) {
        super(roleBrowser);
    }

    private final String
            errorMsgEmptyFieldText = "Поле не заполнено";

    SelenideElement
            fullNameBoxLocator = driver.$$("div.section").get(0).as("Бокс  ФИО"),
            lastNameBoxLocator = fullNameBoxLocator.$$("div.item").get(0).as("Бокс  Фамилия"),
            firstNameBoxLocator = fullNameBoxLocator.$$("div.item").get(1).as("Бокс  Имя"),
            patronymicBoxLocator = fullNameBoxLocator.$$("div.item").get(2).as("Бокс  Отчество"),
            surnameInputLocator = driver.$("input[placeholder='Фамилия*']").as("Фамилия"),
            nameInputLocator = driver.$("input[placeholder='Имя*']").as("Имя"),
            patronymicInputLocator = driver.$("input[placeholder='Отчество']").as("Отчество");


    public void checkInitialState() {
        stepWithRole("Убедиться, что поля ФИО пустые", () -> {
            surnameInputLocator.shouldBe(empty);
            nameInputLocator.shouldBe(empty);
            patronymicInputLocator.shouldBe(empty);
            validation.checkNoErrorMsgInBox(surnameInputLocator);
        });
    }

    public void checkValidationTriggeredState() {
        stepWithRole("Убедиться, что блок ФИО не заполнен и присутсвует сообщение об ошибке", () -> {
            stepWithRole("Убедиться, что  блок ФИО не заполнен", () -> {
                surnameInputLocator.shouldBe(empty);
                nameInputLocator.shouldBe(empty);
                patronymicInputLocator.shouldBe(empty);
            });
            stepWithRole("Убедиться, что присутствует сообщение об ошибке в полях ФИО", () -> {
                validation.checkErrorMsgInBox(lastNameBoxLocator, errorMsgEmptyFieldText);
                validation.checkErrorMsgInBox(firstNameBoxLocator, errorMsgEmptyFieldText);
                validation.checkErrorMsgInBox(patronymicBoxLocator, errorMsgEmptyFieldText);
            });
        });
    }

    public void fillName(String surname, String name, String patronymic) {
        stepWithRole("Заполнить поля ФИО", () -> {
            stepWithRole("Заполнить поле Фамилия: " + surname, () -> {
                surnameInputLocator.setValue(surname);
            });
            stepWithRole("Заполнить поле Имя: " + name, () -> {
                nameInputLocator.setValue(name);
            });
            stepWithRole("Заполнить поле Отчество: " + patronymic, () -> {
                patronymicInputLocator.setValue(patronymic);
            });
        });
    }

    public void checkFilledState() {
        stepWithRole("Проверить состояние блока с ФИО после заполнения", () -> {
            stepWithRole("Убедиться, что  поля ФИО заполнены", () -> {
                stepWithRole("Поле Фамилия: " + surnameInputLocator.getValue(), () -> {
                    surnameInputLocator.shouldNotBe(empty);
                });
                stepWithRole("Поле Имя: " + nameInputLocator.getValue(), () -> {
                    nameInputLocator.shouldNotBe(empty);
                });
                stepWithRole("Поле Отчество: " + patronymicInputLocator.getValue(), () -> {
                    patronymicInputLocator.shouldNotBe(empty);
                });
            });

        });
    }
}
