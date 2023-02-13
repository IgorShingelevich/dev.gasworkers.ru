package ru.gasworkers.dev.pages.client.maintenance;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.model.stepper.ClientStepper;
import ru.gasworkers.dev.pages.client.BaseClientPage;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.FocusHeaderComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.stepperComponent.StepperComponent;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;

public final class SelectObjectMaintenanceClientPage extends BaseClientPage {

    public final FocusHeaderComponent focusHeader;
    public final StepperComponent stepper;

    public SelectObjectMaintenanceClientPage(RoleBrowser browser) {
        super(browser);
        focusHeader = new FocusHeaderComponent(browser);
        stepper = new StepperComponent(browser);
    }

    SelenideElement
        pageTitleLocator = driver.$("div p.h3").as("Заголовок страницы выбора объекта"),
        createObjectButtonLocator = driver.$(byTagAndText("span", "Создать объект")).as("Кнопка создания объекта");

    ElementsCollection
        objectCardLocator = driver.$$("div.card-object").as("Карточка объекта"),
        objectNameLocator = driver.$$("div.card-object .card-object__name").as("Название объекта"),
        objectAddressLocator = driver.$$("div.card-object .card-object__text").as("Адрес объекта"),
        objectButtonLocator = driver.$$("div.card-object button").as("Кнопка выбора объекта");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что страница выбора объекта загружена", () -> {
            pageTitleLocator.shouldHave(text("Выберите объект"));
            stepper.checkFinishLoading();
            createObjectButtonLocator.shouldBe(visible);
        });
    }

    public void selectObjectByIndex(int index) {
       String objectName = objectNameLocator.get(index).getText();
        Integer printCount = index + 1;
        stepWithRole("Выбрать: " + printCount  + " объект по счету: "  + objectName , () -> {
            objectButtonLocator
                    .shouldHave(sizeGreaterThanOrEqual(index))
                    .get(index).click();
        });
        System.out.println("Select Object index: "+ index + " name: " + objectName);
    }
}