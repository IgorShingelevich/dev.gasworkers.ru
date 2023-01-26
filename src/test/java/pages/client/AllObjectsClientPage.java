package pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class AllObjectsClientPage extends BaseClientPage {

    public AllObjectsClientPage(RoleBrowser browser) {
        super(browser);
   }

    private final String OBJECTS_PAGE_TITLE_TEXT = "Объекты и оборудование";

    SelenideElement
        pageTitleLocator = $(".page-title"),
        createNewObjectButtonLocator = driver.$(byTagAndText("button", "Создать объект")),
        objectActionsDropdownLocator = $(".actions"),
        pickGasDistributorDropdownLocator = $(".gas-select .gas-select__header");
    ElementsCollection
        nameLinkCollection = $$("div.pointer");

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



    public AllObjectsClientPage open() {
        Selenide.open("/equipment");
        checkObjectsPageTitle();
        return this;
    }

    public AllObjectsClientPage checkObjectsPageTitle() {
        stepWithRole("Проверить заголовок страницы: " + OBJECTS_PAGE_TITLE_TEXT, () -> {
            pageTitleLocator.shouldHave(text(OBJECTS_PAGE_TITLE_TEXT));
        });
        return this;
    }

    public AllObjectsClientPage clickCreateNewObject() {
//        createNewObjectButtonLocator.shouldHave(text("Создать объект")).shouldNotBe(disabled).click();
        createNewObjectButtonLocator.shouldHave(text("Создать объект")).shouldNotBe(disabled).click();
        pickGasDistributorDropdownLocator.shouldBe(visible);
        return this;
    }


    public AllObjectsClientPage isOpened() {
        pageTitleLocator.shouldHave(text(OBJECTS_PAGE_TITLE_TEXT));
        return this;
    }

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
}


/*TODO
*  rewrite random methods and moove from  Math random to ThreadLocalRandom, because Math.random() is not thread safe and can cause problems in parallel tests
* */