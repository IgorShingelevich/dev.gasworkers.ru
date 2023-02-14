package ru.gasworkers.dev.pages.components.sharedComponent.equipmentPicker;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.model.equipment.EquipmentType;
import ru.gasworkers.dev.pages.components.BaseComponent;

public class EquipmentBackgroundPicker extends BaseComponent {
    public EquipmentBackgroundPicker(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
    equipmentFieldLocator = driver.$("div.search-option__equipment--title.cursor-pointer").as("Поле выбора оборудования"),
    addAnotherEquipmentButtonLocator = driver.$("div.position-absolute a").as("Кнопка добавить оборудование"),
    equipmentContainerLocator = driver.$("div.search-option__equipment--dropdown").as("Контейнер выбора оборудования"),
    typeDropdownLocator = driver.$("div.gas-select__header").as("Дропдаун выбора типа оборудования"),
    brandDropdownLocator = driver.$("input[placeholder='Начните вводить название марки']").as("Дропдаун выбора марки оборудования"),
    markDropdownLocator = driver.$("input[placeholder='Начните вводить модель']").as("Дропдаун выбора модели оборудования"),
    powerFieldLocator = driver.$("input[placeholder='Введите мощность']").as("Поле выбора мощности оборудования"),
    approveButtonLocator = driver.$("div.search-option__equipment button.btn-fs-sm.btn.btn-primary").as("Кнопка подтвердить выбора оборудования"),
    clearButtonLocator = driver.$("div.search-option__equipment button.btn-fs-sm.mr-2.btn.btn-outline-primary").as("Кнопка очистить выбор оборудования");

    ElementsCollection
        typeCollectionLocator = driver.$$("div.gas-select__dropdown .item .text").as("Коллекция типов оборудования"),
//        brandCollectionLocator = brandDropdownLocator.$$("div.results .result-item").as("Коллекция марок оборудования"),
        brandCollectionLocator = driver.$$(".result-item").as("Коллекция марок оборудования"),
        markCollectionLocator = driver.$$("div.results .result-item").as("Коллекция моделей оборудования");


    public void checkFinishLoading() {
        stepWithRole(" Убедиться, что форма выбора оборудования отображается", () -> {
            equipmentFieldLocator.shouldBe(Condition.visible);
            equipmentFieldLocator.click();
            equipmentContainerLocator.shouldBe(Condition.visible);
            equipmentFieldLocator.click();
        });


    }
 public void fillEquipment(EquipmentType type, Integer mark, Integer brand, Integer power) {
        stepWithRole("Выбрать оборудование", () -> {
           stepWithRole("Выбрать тип оборудования: " + type.toString(), () -> {
               equipmentFieldLocator.click();
               typeDropdownLocator.click();
               typeCollectionLocator.get(type.ordinal()).click();
              });
              stepWithRole("Выбрать марку оборудования: " , () -> { //+ brandCollectionLocator.get(brand).getText()
                brandDropdownLocator.click();
                brandDropdownLocator.shouldHave(Condition.cssClass("has-results"));
                brandCollectionLocator.get(brand).click();
              });
                stepWithRole("Выбрать модель оборудования: " , () -> { // + markCollectionLocator.get(mark).getText()
                    markDropdownLocator.click();
                    markCollectionLocator.get(mark).click();
                });
                stepWithRole("Выбрать мощность оборудования: " + power.toString(), () -> {
                    powerFieldLocator.clear();
                    powerFieldLocator.setValue(power.toString());
                });
        });
     approveEquipmentForm();
    }

    public void approveEquipmentForm() {
        stepWithRole("Подтвердить выбор оборудования", () -> {
            approveButtonLocator.shouldHave(Condition.text("Подтвердить")).click();
            stepWithRole("Убедиться, что появилась кнопка добавить еще одно оборудование", () -> {
                addAnotherEquipmentButtonLocator.shouldHave(Condition.text("+добавить еще одно оборудование"));
            });
        });
    }

    public void clearEquipment() {
        stepWithRole("Очистить выбор оборудования", () -> {
            clearButtonLocator.click();
        });
    }

    public void deleteEquipment() {
        stepWithRole("Удалить оборудование", () -> {
            // TODO delete equipment dialog
        });
    }
}
// TODO hand input