package ru.gasworkers.dev.pages.components.selfEmployedComponent.tabCommonProfilePageComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent.BaseTabOrderCardComponent;

import static com.codeborne.selenide.Condition.empty;

public class fillNameCommonTabSelfEmployedComponent extends BaseTabOrderCardComponent {
    public fillNameCommonTabSelfEmployedComponent(RoleBrowser roleBrowser) {
        super(roleBrowser);
    }

    SelenideElement
    surnameInputLocator = driver.$("input[placeholder='Фамилия*']").as("Фамилия"),
    nameInputLocator = driver.$("input[placeholder='Имя*']").as("Имя"),
    patronymicInputLocator = driver.$("input[placeholder='Отчество']").as("Отчество");


    public void checkInitialState() {
        stepWithRole("Проверить начальное состояние блока с именем", () -> {
            stepWithRole("Убедиться, что поля ФИО пустые", () -> {
                surnameInputLocator.shouldBe(empty);
                nameInputLocator.shouldBe(empty);
                patronymicInputLocator.shouldBe(empty);
            });
            stepWithRole("Убедиться, что присутствует сообщение об ошибке в полях ФИО", () -> {
                checkErrorMsg(surnameInputLocator);
                checkErrorMsg(nameInputLocator);
                checkErrorMsg(patronymicInputLocator);
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

    public void checkFilledState(){
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
