package pages.profilePages.clientPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SelectObjectMaintenancePage {

    SelenideElement
            selectObjectTitleLocator = $("p.h3"),
            Object1ButtonLocator = $x("(//button[@class='btn btn-primary disable-outline'][contains(.,'Выбрать')])[1]"),
                    //$$(".btn btn-primary disable-outline").get(1),
            Object2ButtonLocator = $$(".btn.btn-primary.disable-outline").get(2),
            Object3ButtonLocator = $$(".btn.btn-primary.disable-outline").get(3);
           ElementsCollection ObjectButtonCollection = $$x("//button[contains(.,'Выбрать')]");
           // (//button[contains(.,'Выбрать')])[1]


public SelectObjectMaintenancePage FirstObject() {
    Object1ButtonLocator.shouldBe(visible).click();
//        selectDatePageTitleLocator.shouldBe(visible);
    return this;
    }



    public SelectObjectMaintenancePage randomObjectButtonVar2() {
        int randomIndex = (int) (Math.random() * ObjectButtonCollection.size());
        ObjectButtonCollection.get(randomIndex).click();
        return this;
    }


    public SelectObjectMaintenancePage isOpened() {
        selectObjectTitleLocator.shouldBe(visible);
        return this;
    }

    public SelectObjectMaintenancePage pick1thObject() {
        Object1ButtonLocator.shouldBe(visible).click();
//        selectDatePageTitleLocator.shouldBe(visible);
        return this;
    }

    public SelectObjectMaintenancePage clickObject2Button() {
        Object2ButtonLocator.shouldBe(visible).click();
//        selectDatePageTitleLocator.shouldBe(visible);
        return this;
    }

    public SelectObjectMaintenancePage clickObject3Button() {
        Object3ButtonLocator.shouldBe(visible).click();
//        selectDatePageTitleLocator.shouldBe(visible);
        return this;
    }



}
