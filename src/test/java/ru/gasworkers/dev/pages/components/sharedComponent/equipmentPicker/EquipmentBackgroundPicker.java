package ru.gasworkers.dev.pages.components.sharedComponent.equipmentPicker;

import com.codeborne.selenide.CollectionCondition;
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
    equipmentFieldLocator = driver.$("div.search-option__equipment--title.cursor-pointer").as("Поле оборудованиe"),
    addAnotherEquipmentButtonLocator = driver.$("div.position-absolute a").as("Кнопка добавить оборудование"),
    equipmentContainerLocator = driver.$("div.search-option__equipment--dropdown").as("Контейнер выбора оборудования"),
    typeFieldLocator = driver.$("div.gas-select__header").as("Дропдаун выбора типа оборудования"),
    brandFieldLocator = driver.$("input[placeholder='Начните вводить название марки']").as("Поле выбора марки оборудования"),
    markFieldLocator = driver.$("input[placeholder='Начните вводить модель']").as("Поле выбора модели оборудования"),
    powerFieldLocator = driver.$("input[placeholder='Введите мощность']").as("Поле выбора мощности оборудования"),
    problemTextareaLocator = driver.$("textarea[placeholder='Кратко опишите проблему']").as("Поле Кратко опишите проблему"),
    approveButtonLocator = driver.$("div.search-option__equipment button.btn-fs-sm.btn.btn-primary").as("Кнопка подтвердить выбора оборудования"),
    clearButtonLocator = driver.$("div.search-option__equipment button.btn-fs-sm.mr-2.btn.btn-outline-primary").as("Кнопка очистить выбор оборудования");

    ElementsCollection
            typeCollection = driver.$$("div.gas-select__dropdown .item .text").as("Коллекция типов оборудования"),
//        brandCollectionLocator = brandDropdownLocator.$$("div.results .result-item").as("Коллекция марок оборудования"),
        brandCollection = driver.$$(".result-item").as("Коллекция марок оборудования"),
        markCollection = driver.$$("div.results .result-item").as("Коллекция моделей оборудования");


    public void checkFinishLoading() {
        stepWithRole(" Убедиться, что форма выбора оборудования отображается", () -> {
            equipmentFieldLocator.shouldBe(Condition.visible);
            equipmentFieldLocator.click();
            equipmentContainerLocator.shouldBe(Condition.visible);
            equipmentFieldLocator.click();
        });


    }
 public void fillMaintenanceEquipment(EquipmentType type, Integer mark, Integer brand, Integer power) {
        stepWithRole("Выбрать оборудование", () -> {
           stepWithRole("Выбрать тип оборудования: " + type.toString(), () -> {
               equipmentFieldLocator.click();
               typeFieldLocator.click();
               typeCollection.get(type.ordinal()).click();
                stepWithRole("Убедиться, что тип оборудования отмечен", () -> {
                    //find sibling with class="box checked"
                    typeCollection.get(type.ordinal()).preceding(0).shouldHave(Condition.cssClass("checked"));

                });
              });
              stepWithRole("Выбрать марку оборудования: " , () -> { //+ brandCollectionLocator.get(brand).getText()
                brandFieldLocator.click();
                brandFieldLocator.shouldHave(Condition.cssClass("has-results"));
                brandCollection.get(brand).click();
                  stepWithRole("Убедиться, что список брендов оборудования скрыт", () -> {
                      brandCollection.shouldHave(CollectionCondition.size(0));
                  });
              });

                stepWithRole("Выбрать модель оборудования: " , () -> { // + markCollectionLocator.get(mark).getText()
                    markFieldLocator.click();
                    markCollection.get(mark).click();
                    stepWithRole("Убедиться, что список моделей оборудования скрыт", () -> {
                        markCollection.shouldHave(CollectionCondition.size(0));
                    });
                });
                stepWithRole("Выбрать мощность оборудования: " + power.toString(), () -> {
                    powerFieldLocator.clear();
                    powerFieldLocator.setValue(power.toString());
                });
            approveEquipmentForm();
            // TODO add photo video
        });
    }

    public void fillRepairEquipment(EquipmentType type, Integer mark, Integer brand, Integer power) {
        stepWithRole("Выбрать оборудование", () -> {
            stepWithRole("Выбрать тип оборудования: " + type.toString(), () -> {
                equipmentFieldLocator.click();
                typeFieldLocator.click();
                typeCollection.get(type.ordinal()).click();
                stepWithRole("Убедиться, что тип оборудования отмечен ", () -> {
                    //find sibling with class="box checked"
                    typeCollection.get(type.ordinal()).preceding(0).shouldHave(Condition.cssClass("checked"));

                });
            });
            stepWithRole("Выбрать марку оборудования: " , () -> { //+ brandCollectionLocator.get(brand).getText()
                brandFieldLocator.click();
                brandFieldLocator.shouldHave(Condition.cssClass("has-results"));
                brandCollection.get(brand).click();
                stepWithRole("Убедиться, что список марок оборудования скрыт", () -> {
                    brandCollection.shouldHave(CollectionCondition.size(0));
                });
            });
            String errorText = stepWithRole("Выбрать модель оборудования: " , () -> { // + markCollectionLocator.get(mark).getText()
                markFieldLocator.click();
                markCollection.get(mark).click();
                stepWithRole("Убедиться, что список моделей оборудования скрыт", () -> {
                    markCollection.shouldHave(CollectionCondition.size(0));
                });
                String markText = markFieldLocator.getValue();
                return markText;
            });
            stepWithRole("Выбрать мощность оборудования: " + power.toString(), () -> {
                powerFieldLocator.clear();
                powerFieldLocator.setValue(power.toString());
            });
            stepWithRole("Добавить описание неисправности", () -> {
                problemTextareaLocator.setValue(errorText + " неисправен. Нужен ремонт.");
            });
            approveEquipmentForm();
            // TODO add photo video
        });
    }

    public void fillVideoEquipment(EquipmentType type, Integer mark, Integer brand, Integer power) {
        stepWithRole("Выбрать оборудование", () -> {
            stepWithRole("Выбрать тип оборудования: " + type.toString(), () -> {
                equipmentFieldLocator.click();
                typeFieldLocator.click();
                typeCollection.get(type.ordinal()).click();
                stepWithRole("Убедиться, что тип оборудования отмечен ", () -> {
                    //find sibling with class="box checked"
                    typeCollection.get(type.ordinal()).preceding(0).shouldHave(Condition.cssClass("checked"));

                });
            });
            stepWithRole("Выбрать марку оборудования: " , () -> { //+ brandCollectionLocator.get(brand).getText()
                brandFieldLocator.click();
                brandFieldLocator.shouldHave(Condition.cssClass("has-results"));
                brandCollection.get(brand).click();
                stepWithRole("Убедиться, что список марок оборудования скрыт", () -> {
                    brandCollection.shouldHave(CollectionCondition.size(0));
                });
            });
            String errorText = stepWithRole("Выбрать модель оборудования: " , () -> { // + markCollectionLocator.get(mark).getText()
                markFieldLocator.click();
                markCollection.get(mark).click();
                stepWithRole("Убедиться, что список моделей оборудования скрыт", () -> {
                    markCollection.shouldHave(CollectionCondition.size(0));
                });
                String markText = markFieldLocator.getValue();
                return markText;
            });
            stepWithRole("Выбрать мощность оборудования: " + power.toString(), () -> {
                powerFieldLocator.clear();
                powerFieldLocator.setValue(power.toString());
            });
            stepWithRole("Добавить описание неисправности", () -> {
                problemTextareaLocator.setValue(errorText + " неисправен. Нужен ремонт.");
            });
            approveEquipmentForm();
            // TODO add photo video
        });
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