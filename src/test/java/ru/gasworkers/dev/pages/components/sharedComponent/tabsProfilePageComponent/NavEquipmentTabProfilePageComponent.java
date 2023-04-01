package ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.model.equipment.EquipmentType;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.profilePageComponent.BaseProfileSelfEmployedComponent;

import static com.codeborne.selenide.Condition.*;

public class NavEquipmentTabProfilePageComponent extends BaseProfileSelfEmployedComponent {
    public NavEquipmentTabProfilePageComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
            titleText = "Выберите типы оборудования";

    SelenideElement
            titleLocator = driver.$("div.select-equipment .h4").as("Заголовок вкладки фильтров оборудования"),
            equipmentMarkInputFilterLocator = driver.$("input[placeholder='Марка оборудования']").as("Поле фильтра марки оборудования"),
            saveButtonLocator = driver.$("button.btn-primary").as("Кнопка Сохранить");

    ElementsCollection
            equipmentTypeFilterCollection = driver.$$("div.slick-list div.slick-slide").as("Коллекция фильтров оборудования"),
            markCollection = driver.$$("div.logos-wrapper .item").as("Коллекция марок оборудования");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что вкладка  фильтров оборудования загрузилась", () -> {
            titleLocator.shouldHave(text(titleText));
            equipmentTypeFilterCollection.shouldHave(CollectionCondition.size(11));
            stepWithRole("Убедиться, что видны  марки оборудования", () -> {
                markCollection.shouldHave(CollectionCondition.sizeGreaterThan(0));
            });
        });
    }

    public void checkInitialState() {
        stepWithRole("Убедиться, что вкладка  фильтров оборудования в начальном состоянии", () -> {
            titleLocator.shouldHave(text(titleText));
            stepWithRole("Убедиться, что видны все 11 фильтров оборудования", () -> {
                equipmentTypeFilterCollection.shouldHave(CollectionCondition.size(11));
            });
            checkNoneItemIsActive();
            stepWithRole("Убедиться, что видны все 164 марки оборудования", () -> {
                markCollection.shouldHave(CollectionCondition.size(164));
            });
            stepWithRole("Убедиться, что кнопка Сохранить неактивна", () -> {
                saveButtonLocator.shouldBe(disabled);
            });
            toOrderContextButtons.checkNoToOrderContextButtonsPresenceState();
        });
    }

    public void checkItemIsActive(EquipmentType type) {
        stepWithRole("Убедиться, что выбран фильтр оборудования: " + type.toString(), () -> {
            equipmentTypeFilterCollection.findBy(text(type.toString())).shouldHave(cssClass("is-active"));
        });
    }

    public void checkNoneItemIsActive() {
        stepWithRole("Убедиться, что ни один фильтр оборудования не выбран", () -> {
            equipmentTypeFilterCollection.findBy(cssClass("is-active")).shouldNot(exist);
        });
    }

    public void checkFirsOfferEvaluatedSEInitialState() {
        stepWithRole("Убедиться, что вкладка  фильтров оборудования в состоянии после расценки первого оффера", () -> {
            titleLocator.shouldHave(text(titleText));
            stepWithRole("Убедиться, что видны все 11 фильтров оборудования", () -> {
                equipmentTypeFilterCollection.shouldHave(CollectionCondition.size(11));
            });
            checkNoneItemIsActive();
            stepWithRole("Убедиться, что видны все 164 марки оборудования", () -> {
                markCollection.shouldHave(CollectionCondition.size(164));
            });
            stepWithRole("Убедиться, что кнопка Сохранить неактивна", () -> {
                saveButtonLocator.shouldBe(disabled);
            });
            toOrderContextButtons.checkToOrderContextButtonsPresenceState();
        });
    }
}
