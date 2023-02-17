package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarClientComponent;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class AllObjectsClientPage extends BaseClientPage {
    public final ActionsBlockClientComponent actionsBlock;
    public final SidebarClientComponent sidebar;
    public AllObjectsClientPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarClientComponent(browser);
        actionsBlock = new ActionsBlockClientComponent(browser);
   }

    private final String
        OBJECTS_PAGE_TITLE_TEXT = "Объекты и оборудование";

    SelenideElement
        pageTitleLocator = driver.$(".page-title"),
        createNewObjectButtonLocator = driver.$(byTagAndText("span", "Создать объект")),
        objectActionsDropdownLocator = driver.$(".actions"),
        pickGasDistributorDropdownLocator = driver.$(".gas-select .gas-select__header");
    ElementsCollection
        nameLinkCollection = driver.$$("div.pointer");

    public void checkInitialState() {
        stepWithRole("Убедиться, что страница в  начальном состоянии", () -> {
            checkObjectsPageTitle();
            stepWithRole("Убедиться, что присутствует кнопка Создать объект", () -> {
                createNewObjectButtonLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что отсутствуют ранее созданные Объекты", () -> {
                nameLinkCollection.shouldHave(size(0));
            });
        });
    }

    public AllObjectsClientPage openRandomObject() {
            int objectRndNumber = (int) (Math.random() * nameLinkCollection.size());
             String thisObjectName = nameLinkCollection.get(objectRndNumber).getText();
        stepWithRole("Открыть случайный объект: " + thisObjectName, () -> {
            nameLinkCollection.get(objectRndNumber).click();
        });
        return this;
    }

    public AllObjectsClientPage openObjectByName(String objectName) {
        stepWithRole("Открыть объект с именем: " + objectName, () -> {
            nameLinkCollection.findBy(text(objectName)).click();
        });
        return this;
    }

    public AllObjectsClientPage openObjectByIndex(int objectIndex) {
        String objectName = nameLinkCollection.get(objectIndex).getText();
        Integer reportCount = objectIndex + 1;
        stepWithRole("Открыть: " + reportCount  + " объект по счету: "  + objectName , () -> {
            nameLinkCollection.get(objectIndex).click();
        });
        return this;
    }

    public AllObjectsClientPage checkObjectsPageTitle() {
        stepWithRole("Проверить заголовок страницы: " + OBJECTS_PAGE_TITLE_TEXT, () -> {
            pageTitleLocator.shouldHave(text(OBJECTS_PAGE_TITLE_TEXT));
        });
        return this;
    }

    public AllObjectsClientPage clickCreateNewObject() {
        stepWithRole("Нажать кнопку Создать объект", () -> {
            createNewObjectButtonLocator.click();
        });
        return this;
    }


    public AllObjectsClientPage checkFinishLoading() {
        stepWithRole("Проверить, что страница Объекты и оборудование загрузилась", () -> {
            checkObjectsPageTitle();
            stepWithRole("Убедиться, что присутствует кнопка Создать объект", () -> {
                createNewObjectButtonLocator.shouldBe(visible);
            });
        });
        return this;
    }

    public AllObjectsClientPage initialBGState() {
        stepWithRole("Убедиться, что страница в  состоянии после Фоновой регистрации", () -> {
            stepWithRole("Убедиться, что присутствует один объект", () -> {
                nameLinkCollection.shouldBe(size(1));
            });
        });
        return this;
    }
}