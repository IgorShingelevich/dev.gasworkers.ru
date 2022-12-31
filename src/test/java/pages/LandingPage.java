package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LandingPage {


    SelenideElement primaryHeaderLocator = $(".primary-header");
    SelenideElement repairButtonLocator = $$(".main-block__btns--item").findBy(text("Ремонт"));
            //$x("//button[@class='btn btn-primary disable-outline'][contains(.,'Ремонт')]");
            // $(".main-block__btns .btn btn-primary disable-outline"),
    SelenideElement maintenanceButtonLocator = $$(".main-block__btns--item").findBy(text("Техобслуживание")),
            //$x("//button[@class='btn btn-primary disable-outline'][contains(.,'Техобслуживание')]");
            // $(".main-block__btns .btn btn-primary disable-outline"),
    //$(".main-block__btns .btn btn-primary disable-outline"),
    mainBlockTitleLocator = $(".main-block__title"),
    signInButtonLocator = $(".primary-header .link"),
    userProfileButtonLocator = $(".primary-header .link"),
    signUpDropdownLocator = $(".dropdown-title .arrow-down");





//    ElementsCollection primeButtonCollection = $$(".btn btn-primary disable-outline");
//    ElementsCollection signUpRolesCollection = (ElementsCollection) $$(".dropdown-wrapper__menu a");



public LandingPage open() {
    Selenide.open("https://dev.gasworkers.ru/");
    primaryHeaderLocator.shouldBe(visible);
        return this;
    }

    public LandingPage isOpened() {
        primaryHeaderLocator.shouldBe(visible);
        return this;
    }

    //  clickSignInButton
    public LandingPage clickUserProfile() {
        userProfileButtonLocator.shouldBe(visible).click();
        return this;
    }


        public LandingPage clickUserRepairButton() {

        repairButtonLocator.shouldHave(text("Ремонт")).shouldBe(visible).click();
        return this;
    }

    public LandingPage clickUserMaintenanceButton() {
        maintenanceButtonLocator.shouldHave(text("Техобслуживание")).shouldBe(visible).click();
        return this;
    }






}
