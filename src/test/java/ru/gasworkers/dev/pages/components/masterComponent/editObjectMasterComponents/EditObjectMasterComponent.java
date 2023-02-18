package ru.gasworkers.dev.pages.components.masterComponent.editObjectMasterComponents;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class EditObjectMasterComponent extends BaseComponent {
    public final EditEquipmentModalMasterComponent equipmentModal;
    public EditObjectMasterComponent(RoleBrowser browser) {
        super(browser);
        equipmentModal = new EditEquipmentModalMasterComponent(browser);
    }

    SelenideElement
        objectNameLocator = driver.$("div.title h3").as("Название объекта"),
        toOrderButtonLocator = driver.$(byTagAndText("span", "К заказу")).as("Кнопка К заказу"),
        equipmentNameLocator = driver.$(" span.title-text").as("Название оборудования"),
        editButtonLocator = driver.$(byTagAndText("span", "Редактировать")).as("Кнопка Редактировать оборудование"),
        deleteButtonLocator = driver.$(byTagAndText("span", "Удалить")).as("Кнопка Удалить "),
        addressLocator = driver.$("div.address .value").as("Адрес объекта");

    ElementsCollection
        equipmentCollection = driver.$$("div.equipment-item ").as("Коллекция  оборудования");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что компонент Редактирование объекта загружен", () -> {
            String urlEditObjectMasterComponent = driver.url();
            objectNameLocator.shouldBe(visible);
            toOrderButtonLocator.shouldBe(visible);
            addressLocator.shouldBe(visible);
        });
    }

    public void toOrder () {
        stepWithRole("Нажать кнопку К заказу", () -> {
            toOrderButtonLocator.click();
        });
    }

    public void editEquipment ( Integer index ) {
        stepWithRole("Нажать кнопку Редактировать оборудование", () -> {
//            equipmentCollection.get(index).$(editButtonLocator).click(); // TODO how to handle those cases
        });
    }
}
