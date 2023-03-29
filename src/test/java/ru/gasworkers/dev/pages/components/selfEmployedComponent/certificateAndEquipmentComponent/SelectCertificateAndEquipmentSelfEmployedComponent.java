package ru.gasworkers.dev.pages.components.selfEmployedComponent.certificateAndEquipmentComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.time.Duration;

public class SelectCertificateAndEquipmentSelfEmployedComponent extends BaseComponent {
    public SelectCertificateAndEquipmentSelfEmployedComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
            self = driver.$("div.justify-content-start").as("Компонент выбора оборудования");

    ElementsCollection
            fieldCollection = self.$$("div.gas-select").as("Коллекция полей"),
            dropdownCollection = self.$$("div.gas-select__dropdown ").as("Коллекция контейнеров списков"),
            typeListCollection = dropdownCollection.get(0).$$("div.item").as("Коллекция элементов списка типов оборудования"),
            markListCollection = dropdownCollection.get(1).$$("div.item").as("Коллекция элементов списка марок оборудования");

    SelenideElement
            typeFieldLocator = fieldCollection.get(0).as("Поле типа оборудования"),
            markFieldLocator = fieldCollection.get(1).as("Поле марки оборудования");

    public void checkInitialState() {
        stepWithRole("Убедиться, что  компонент выбора оборудования в начальном состоянии", () -> {
            stepWithRole("Убедиться, что  оба дропдауна свернуты", () -> {
                checkDropdownCollapsed(typeFieldLocator);
                checkDropdownCollapsed(markFieldLocator);
            });
            stepWithRole("Убедиться, что  оба дропдауна содержат списки  оборудования", () -> {
                typeListCollection.shouldHave(CollectionCondition.size(11), Duration.ofSeconds(10));
                markListCollection.shouldHave(CollectionCondition.size(165), Duration.ofSeconds(10));
                //todo greater than ...

            });
        });
    }

    public void checkDropdownExpanded(SelenideElement field) {
        stepWithRole("Убедиться, что выпадающий список раскрыт у  поля: " + field.getAlias(), () -> {
            field.shouldHave(Condition.cssClass("is-open"));
        });
    }

    public void checkDropdownCollapsed(SelenideElement field) {
        stepWithRole("Убедиться, что выпадающий список свернут у  поля: " + field.getAlias(), () -> {
            field.shouldNotHave(Condition.cssClass("is-open"));
        });
    }


}
