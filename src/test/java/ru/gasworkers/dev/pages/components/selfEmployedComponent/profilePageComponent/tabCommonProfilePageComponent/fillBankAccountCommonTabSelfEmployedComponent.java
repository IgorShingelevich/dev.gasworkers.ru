package ru.gasworkers.dev.pages.components.selfEmployedComponent.profilePageComponent.tabCommonProfilePageComponent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent.BaseTabOrderCardComponent;
import ru.gasworkers.dev.utils.userBuilder.RandomSelfEmployed;

public class fillBankAccountCommonTabSelfEmployedComponent extends BaseTabOrderCardComponent {
    public fillBankAccountCommonTabSelfEmployedComponent(RoleBrowser roleBrowser) {
        super(roleBrowser);
    }

    private final String
            bankDescriptionText = "Обратите внимание! Необходимо указать банковские данные карты открытой для приема платежей в качестве самозанятого",
            inputPlaceholderText = "Введите полный БИК банка*",
            bicErrorMsgEmptyFieldText = "БИК не найден. Выберите значение из справочника",
            checkingAccountErrorMsgEmptyFieldText = "Поле не заполнено";


    ElementsCollection
            rowBankCollectionLocator = driver.$$("div.row-wrap").as("Коллекция полейe Банковские реквизиты");

    SelenideElement
            selfLocator = driver.$("div.row.align-items-center.input-block.mt-2").as("Блок Банковские реквизиты"),
            bankDescriptionLocator = driver.$("div.diploma.diploma-blue").as("Описание блока Банковские реквизиты"),
            bicBoxLocator = rowBankCollectionLocator.get(0).as("Блок БИК"),
            bankNameBoxLocator = rowBankCollectionLocator.get(1).as("Блок Наименование банка"),
            correspondentAccountBoxLocator = rowBankCollectionLocator.get(2).as("Блок Корр. счёт"),
            checkingAccountBoxLocator = rowBankCollectionLocator.get(3).as("Блок Расчётный счёт"),
            bicTitleLocator = bicBoxLocator.$(".title").as("Заголовок БИК"),
            bicInputLocator = bicBoxLocator.$("input").as("Поле ввода БИК"),
            bicSuggestionsLocator = bicBoxLocator.$("div.company-suggestions").as("Список подсказок БИК"),
            bicSuggestionResultLocator = bicBoxLocator.$("div.result-item").as("Результат подсказки БИК");


    public void checkInitialState() {
        stepWithRole("Убедиться, что элемент банковских данных в начальном состоянии", () -> {
            selfLocator.shouldBe(Condition.visible);
            stepWithRole("Убедиться, что описание блока Банковские реквизиты в начальном состоянии", () -> {
                bankDescriptionLocator.shouldHave(Condition.text(bankDescriptionText));
            });
            stepWithRole("Убедиться, что блок БИК в начальном состоянии", () -> {
                bicTitleLocator.shouldHave(Condition.text("БИК"));
                bicInputLocator.shouldHave(Condition.attribute("placeholder", inputPlaceholderText));
                bicInputLocator.shouldBe(Condition.empty);
                bicSuggestionsLocator.shouldBe(Condition.visible);
                bicSuggestionResultLocator.shouldNotBe(Condition.visible);
            });
            stepWithRole("Убедиться, что блок Наименование банка в начальном состоянии", () -> {
                bankNameBoxLocator.$(".title").shouldHave(Condition.text("Банк"));
                checkNoTextDetails(bankNameBoxLocator);
            });
            stepWithRole("Убедиться, что блок Корр. счёт в начальном состоянии", () -> {
                correspondentAccountBoxLocator.$(".title").shouldHave(Condition.text("Корр. счёт"));
                checkNoTextDetails(correspondentAccountBoxLocator);
            });
            stepWithRole("Убедиться, что блок Расчетный счет в начальном состоянии", () -> {
                checkingAccountBoxLocator.$(".title").shouldHave(Condition.text("Расчётный счёт"));
                checkNoTextDetails(checkingAccountBoxLocator);
            });
        });
    }

    public void checkValidationTriggeredState() {
        stepWithRole("Убедиться, что блок банковские реквизиты не заполнен и присутсвует  сообщение об ошибке", () -> {
            selfLocator.shouldBe(Condition.visible);
            stepWithRole("Убедиться, что блок БИК не заполнен", () -> {
                bicTitleLocator.shouldHave(Condition.text("БИК"));
                bicInputLocator.shouldHave(Condition.attribute("placeholder", inputPlaceholderText));
//                bicSuggestionsLocator.shouldNotBe(Condition.visible);
                bicSuggestionResultLocator.shouldNotBe(Condition.visible);
            });
            stepWithRole("Убедиться, что блок Наименование банка не заполнен", () -> {
                bankNameBoxLocator.$(".title").shouldHave(Condition.text("Банк"));
                checkNoTextDetails(bankNameBoxLocator);
            });
            stepWithRole("Убедиться, что блок Корр. счёт не заполнен", () -> {
                correspondentAccountBoxLocator.$(".title").shouldHave(Condition.text("Корр. счёт"));
                checkNoTextDetails(correspondentAccountBoxLocator);
            });
            stepWithRole("Убедиться, что блок Расчетный счет не заполнен", () -> {
                checkingAccountBoxLocator.$(".title").shouldHave(Condition.text("Расчётный счёт"));
                //todo: Проверить, что в поле ввода расчетного счета есть placeholder
//                checkingAccountBoxLocator.$("input").shouldHave(Condition.attribute("placeholder", "Введите расчетный счет*"));
                checkingAccountBoxLocator.$("input").shouldBe(Condition.empty);
            });
            stepWithRole("Убедиться, что присутствуют сообщения об ошибках", () -> {
               validation.checkErrorMsgInBox(bicBoxLocator, bicErrorMsgEmptyFieldText);
               validation.checkErrorMsgInBox(checkingAccountBoxLocator, checkingAccountErrorMsgEmptyFieldText);
            });
        });
    }

    public void checkFilledState(RandomSelfEmployed randomSelfEmployed) {
        stepWithRole("Убедиться, что все поля банковских данных заполнены", () -> {
            //todo add filled atate to bic input
//            bicInputLocator.shouldHave(Condition.value(randomSelfEmployed.getBic()));
            bicSuggestionsLocator.shouldNotBe(Condition.visible);
            bicSuggestionResultLocator.shouldNotBe(Condition.visible);
            bankNameBoxLocator.$(".text").shouldHave(Condition.text(randomSelfEmployed.getBicResult()));
            correspondentAccountBoxLocator.$(".text").shouldHave(Condition.text("30101810400000000225"));
            checkingAccountBoxLocator.$("input").shouldHave(Condition.value("12345678901234567890"));
        });
    }


    private void checkSuggestionIsOpen() {
        stepWithRole("Убедиться, что список подсказок открыт", () -> {
            bicSuggestionsLocator.shouldHave(Condition.cssClass("is-open"));
        });
    }


    private void checkNoTextDetails(SelenideElement boxLocator) {
        stepWithRole("Убедиться, что в блоке  нет текста", () -> {
            boxLocator.$(".text").shouldBe(Condition.empty);
        });
    }

}
