package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LandingPage {



    SelenideElement primaryHeaderLocator = $(".primary-header");
    SelenideElement repairButtonLocator = $x("//button[@class='btn btn-primary disable-outline'][contains(.,'Ремонт')]");
            // $(".main-block__btns .btn btn-primary disable-outline"),
    SelenideElement maintenanceButtonLocator = $x("//button[@class='btn btn-primary disable-outline'][contains(.,'Техобслуживание')]"),
    //$(".main-block__btns .btn btn-primary disable-outline"),
    mainBlockTitleLocator = $(".main-block__title"),
    signInButtonLocator = $(".primary-header .link"),
    signUpDropdownLocator = $(".dropdown-title .arrow-down");





//    ElementsCollection primeButtonCollection = $$(".btn btn-primary disable-outline");
//    ElementsCollection signUpRolesCollection = (ElementsCollection) $$(".dropdown-wrapper__menu a");



public LandingPage open() {
    Selenide.open("https://dev.gasworkers.ru/");
    primaryHeaderLocator.shouldBe(visible);
        return this;
    }

    public LandingPage visibilityPrimaryHeaderLocator() {
        primaryHeaderLocator.shouldBe(visible);
        return this;
    }
    public LandingPage clickRepairButton() {
        repairButtonLocator.shouldHave(text("Ремонт")).shouldBe(visible).click();
        return this;
    }

    public LandingPage clickMaintenanceButton() {
        maintenanceButtonLocator.shouldHave(text("Техобслуживание")).shouldBe(visible).click();
        return this;
    }






}
