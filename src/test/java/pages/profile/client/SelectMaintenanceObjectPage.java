package pages.profile.client;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SelectMaintenanceObjectPage {

    SelenideElement
            selectObjectTitleLocator = $("p.h3"),
            Object1ButtonLocator = $x("(//button[@class='btn btn-primary disable-outline'][contains(.,'Выбрать')])[1]"),
                    //$$(".btn btn-primary disable-outline").get(1),
            Object2ButtonLocator = $$(".btn btn-primary disable-outline").get(2),
            Object3ButtonLocator = $$(".btn btn-primary disable-outline").get(3);





    public SelectMaintenanceObjectPage isOpened() {
        selectObjectTitleLocator.shouldBe(visible);
        return this;
    }

    public SelectMaintenanceObjectPage clickObject1thButton() {
        Object1ButtonLocator.shouldBe(visible).click();
//        selectDatePageTitleLocator.shouldBe(visible);
        return this;
    }

    public SelectMaintenanceObjectPage clickObject2Button() {
        Object2ButtonLocator.shouldBe(visible).click();
//        selectDatePageTitleLocator.shouldBe(visible);
        return this;
    }

    public SelectMaintenanceObjectPage clickObject3Button() {
        Object3ButtonLocator.shouldBe(visible).click();
//        selectDatePageTitleLocator.shouldBe(visible);
        return this;
    }



}
