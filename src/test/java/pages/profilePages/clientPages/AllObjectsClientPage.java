package pages.profilePages.clientPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AllObjectsClientPage extends BaseClientPage {

    private final String OBJECTS_PAGE_TITLE_TEXT = "Объекты и оборудование";

    SelenideElement allObjectsPageTitleLocator = $(".page-title"),

            createNewObjectButtonLocator = $x("//button[@class='mb-4 btn btn-primary disable-outline'][contains(.,'Создать объект')]"),
                            //$(".button"),
                            //$x("//button[@class='mb-4 btn btn-primary disable-outline'][contains(.,'Создать объект')]"),
                            //$(".mb-4 btn btn-primary disable-outline"),
            object1thCollectionLocator = $$(".objects .object").get(0),
            object$$ActionsDropdownCollectionLocator = $(".actions"),
            pickGasDistributorDropdownLocator = $(".gas-select .gas-select__header");

    ElementsCollection
            objectsCollection = $$(By.xpath("//div[contains(@class, 'title link-blue text-primary pointer')]"));





    public AllObjectsClientPage openRandomObject() {
        int objectNumber = (int) (Math.random() * objectsCollection.size());
//        objectsCollection.get(objectNumber).find(By.linkText("Перейти")).click();
        objectsCollection.get(objectNumber).click();
        return this;
    }

    //open particular object by name
    public AllObjectsClientPage openObjectByName(String objectName) {
        objectsCollection.find(text(objectName)).find(By.linkText("Перейти")).click();
        return this;
    }

    //open particular object by number in the collection
    public AllObjectsClientPage openObjectByNumber(int objectNumber) {
        objectsCollection.get(objectNumber).find(By.linkText("Перейти")).click();
        return this;
    }



    public AllObjectsClientPage open() {
        Selenide.open("/equipment");
        checkObjectsPageTitle();
        return this;
    }

    public AllObjectsClientPage checkObjectsPageTitle() {
        allObjectsPageTitleLocator.shouldHave(text(OBJECTS_PAGE_TITLE_TEXT));
        return this;
    }

    public AllObjectsClientPage clickCreateNewObject() {
//        createNewObjectButtonLocator.shouldHave(text("Создать объект")).shouldNotBe(disabled).click();
        createNewObjectButtonLocator.shouldHave(text("Создать объект")).shouldNotBe(disabled).click();
        pickGasDistributorDropdownLocator.shouldBe(visible);
        return this;
    }


    public AllObjectsClientPage isOpened() {
        allObjectsPageTitleLocator.shouldHave(text(OBJECTS_PAGE_TITLE_TEXT));
        return this;
    }
}


/*TODO
*  rewrite random methods and moove from  Math random to ThreadLocalRandom, because Math.random() is not thread safe and can cause problems in parallel tests
* */