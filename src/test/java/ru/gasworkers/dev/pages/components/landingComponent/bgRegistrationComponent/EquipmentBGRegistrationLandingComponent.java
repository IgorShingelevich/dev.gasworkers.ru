package ru.gasworkers.dev.pages.components.landingComponent.bgRegistrationComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.model.equipment.EquipmentType;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.visible;

public class EquipmentBGRegistrationLandingComponent extends BaseComponent {
    public EquipmentBGRegistrationLandingComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
        equipmentFieldLocator = driver.$("div.search-option__equipment--title.cursor-pointer").as("Поле оборудованиe"),
        addAnotherEquipmentButtonLocator = driver.$("div.position-absolute a").as("Кнопка добавить оборудование"),
        equipmentContainerLocator = driver.$("div.search-option__equipment--dropdown").as("Контейнер выбора оборудования"),
        typeFieldLocator = driver.$("div.gas-select__header").as("Дропдаун выбора типа оборудования"),
        markFieldLocator = driver.$("input[placeholder='Начните вводить название марки']").as("Поле выбора марки оборудования"),
        modelFieldLocator = driver.$("input[placeholder='Начните вводить модель']").as("Поле выбора модели оборудования"),
        powerFieldLocator = driver.$("input[placeholder='Введите мощность']").as("Поле выбора мощности оборудования"),
        uploadFileLinkLocator = driver.$("div.photo-uploader input").as("Ссылка загрузить файл"),
        attachedFileLocator = driver.$("div.photo-uploader img").as("Прикрепленный файл"),
        problemTextareaLocator = driver.$("textarea[placeholder='Кратко опишите проблему']").as("Поле Кратко опишите проблему"),
        approveButtonLocator = driver.$("div.search-option__equipment button.btn-fs-sm.btn.btn-primary").as("Кнопка подтвердить выбора оборудования"),
        clearButtonLocator = driver.$("div.search-option__equipment button.btn-fs-sm.mr-2.btn.btn-outline-primary").as("Кнопка очистить выбор оборудования");

    ElementsCollection
        typeCollection = driver.$$("div.gas-select__dropdown .item .text").as("Коллекция типов оборудования"),
        // brandCollectionLocator = brandDropdownLocator.$$("div.results .result-item").as("Коллекция марок оборудования"),
        markCollection = driver.$$(".result-item").as("Коллекция марок оборудования"),
        modelCollection = driver.$$("div.results .result-item").as("Коллекция моделей оборудования"),
        resultedEquipmentCollection = driver.$$("div.item-name__text").as("Результат выбора оборудования");



    public void checkFinishLoading() {
        stepWithRole(" Убедиться, что форма выбора оборудования отображается", () -> {
            equipmentFieldLocator.shouldBe(visible);
            equipmentFieldLocator.click();
            equipmentContainerLocator.shouldBe(visible);
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
                markFieldLocator.click();
                markFieldLocator.shouldHave(Condition.cssClass("has-results"));
                markCollection.get(brand).click();
                  stepWithRole("Убедиться, что список брендов оборудования скрыт", () -> {
                      markCollection.shouldHave(CollectionCondition.size(0));
                  });
              });

                stepWithRole("Выбрать модель оборудования: " , () -> { // + markCollectionLocator.get(mark).getText()
                    modelFieldLocator.click();
                    modelCollection.get(mark).click();
                    stepWithRole("Убедиться, что список моделей оборудования скрыт", () -> {
                        modelCollection.shouldHave(CollectionCondition.size(0));
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

    public void fillRepairEquipment(EquipmentType type, Integer model, Integer brand, Integer power, File photoOrVideo) {
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
                markFieldLocator.click();
                markFieldLocator.shouldHave(Condition.cssClass("has-results"));
                markCollection.get(brand).click();
                stepWithRole("Убедиться, что список марок оборудования скрыт", () -> {
                    markCollection.shouldHave(CollectionCondition.size(0));
                });
            });
            String errorText = stepWithRole("Выбрать модель оборудования: " , () -> { // + markCollectionLocator.get(model).getText()
                modelFieldLocator.click();
                stepWithRole("Убедиться, что список моделей оборудования отображается", () -> {
                    modelCollection.shouldHave(CollectionCondition.sizeGreaterThan(1));
                });
                stepWithRole("Выбрать: " + model + " модель оборудования", () -> {
                    modelCollection.get(model).click();
                });
                stepWithRole("Убедиться, что список моделей оборудования скрыт", () -> {
                    modelCollection.shouldHave(CollectionCondition.size(0));
                });
                String modelText = modelFieldLocator.getValue();
                return modelText;
            });
            stepWithRole("Выбрать мощность оборудования: " + power.toString(), () -> {
                powerFieldLocator.clear();
                powerFieldLocator.setValue(power.toString());
            });
            stepWithRole("Добавить описание неисправности", () -> {
                problemTextareaLocator.setValue(errorText + " неисправен. Нужен ремонт.");
                System.out.println("Описание неисправности: " + problemTextareaLocator.getValue());
            });
            stepWithRole("Прикрепить фото или видео", () -> {
                attachedFileLocator.shouldNotBe(visible);
                uploadFileLinkLocator.uploadFile(photoOrVideo);
                attachedFileLocator.shouldBe(visible);
            });
            approveEquipmentForm();
            // TODO add photo video
        });
    }

    public void fillVideoEquipment(EquipmentType type, Integer mark, Integer brand, Integer power, File equipmentVideoFile) {
        //todo  resultedEquipmentCollection should be checked and set as an argument
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
            stepWithRole("Выбрать марку оборудования: " , () -> {
                markFieldLocator.click();
                markFieldLocator.shouldHave(Condition.cssClass("has-results"));
                markCollection.get(brand).click();
                stepWithRole("Убедиться, что список марок оборудования скрыт", () -> {
                    markCollection.shouldHave(CollectionCondition.size(0));
                });
            });
            String modelFieldValue = stepWithRole("Выбрать модель оборудования: " , () -> {
                modelFieldLocator.click();
                modelCollection.get(mark).click();
                stepWithRole("Убедиться, что список моделей оборудования скрыт", () -> {
                    modelCollection.shouldHave(CollectionCondition.size(0));
                });
                String modelFieldText = modelFieldLocator.getValue();
                return modelFieldText;
            });
            stepWithRole("Выбрать мощность оборудования: " + power.toString(), () -> {
                powerFieldLocator.clear();
                powerFieldLocator.setValue(power.toString());
                System.out.println("Мощность оборудования: " + powerFieldLocator.getValue());
            });
            stepWithRole("Добавить описание неисправности", () -> {
                problemTextareaLocator.setValue(modelFieldValue + " неисправен. Нужен ремонт.");
                System.out.println("Описание неисправности: " + problemTextareaLocator.getValue());
            });
            stepWithRole("Прикрепить фото или видео", () -> {
                attachedFileLocator.shouldNotBe(visible);
                uploadFileLinkLocator.uploadFile(equipmentVideoFile);
                attachedFileLocator.shouldBe(visible);
            });
            approveEquipmentForm();
            String modelText = resultedEquipmentCollection.get(0).getValue();
            return modelText;
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

    public String getEquipmentName(Integer equipmentIndex) {
        return stepWithRole("Получить название оборудования", () -> {
            return resultedEquipmentCollection.get(equipmentIndex).getText();
        });
    }

    public String getErrorText() {
        return stepWithRole("Получить описание неисправности", () -> {
            return problemTextareaLocator.getValue();
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