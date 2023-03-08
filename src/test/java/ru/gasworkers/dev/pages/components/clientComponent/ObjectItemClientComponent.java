package ru.gasworkers.dev.pages.components.clientComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.model.equipment.EquipmentType;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;

public class ObjectItemClientComponent extends BaseComponent {
    public ObjectItemClientComponent(RoleBrowser browser) {
        super(browser);
    }

    ElementsCollection
     objectItemCollection = driver.$$("div.object-item").as("Коллекция объектов");

    public void checkObjectItemCollectionSize(Integer size) {
        stepWithRole("Убедиться, что коллекция объектов содержит: " + size + " элементов", () -> {
            objectItemCollection.shouldHave(CollectionCondition.size(1));
        });
    }

    public void checkObjectName (Integer objectIndex, String objectName) {
        stepWithRole("Убедиться, что объект: " + objectName + " содержится в списке объектов", () -> {
            objectItemCollection.get(objectIndex).$("div.title.link-blue.text-primary.pointer").shouldHave(text(objectName));
        });
        System.out.println("Объект по индексу: " + objectIndex +", названия объекта: " + objectItemCollection.get(objectIndex).$("div.title.link-blue.text-primary.pointer").getText());
    }

    public void checkObjectEquipment (Integer objectIndex, EquipmentType type, String equipment, String power) {
        stepWithRole("Убедиться, что объект содержит оборудование и мощность: " + equipment + " " + power + " кВт", () -> {
            String formattedEquipment = type.toString() + " " + equipment + " " + power + " кВт";
            objectItemCollection.get(objectIndex).$("div.equipment-item").shouldHave(text(formattedEquipment));
        });
        System.out.println("Объект по индексу: " + objectIndex +", первое оборудование: " + objectItemCollection.get(objectIndex).$("div.equipment-item").getText());
    }

    public void checkObjectAddress (Integer objectIndex, String address) {
        stepWithRole("Убедиться, что объект содержит адрес: " + address, () -> {
            objectItemCollection.get(objectIndex).$("div.address-string").shouldHave(text(address));
        });
        System.out.println("Объект по индексу: " + objectIndex +", адрес: " + objectItemCollection.get(objectIndex).$("div.address").getText());
    }

    public void checkBGInitialState(Integer objectIndex, String objectName, EquipmentType type, String equipment, String power, String address) {
        checkObjectItemCollectionSize(1);
        checkObjectName(objectIndex, objectName);
        checkObjectEquipment(objectIndex, type, equipment, power);
        checkObjectAddress(objectIndex, address);
    }





}
