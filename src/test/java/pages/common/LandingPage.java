package pages.common;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LandingPage extends BasePage {


public LandingPage(RoleBrowser browser) {
        super(browser);
    }

    ElementsCollection
        signUpProleMenuLocator = $$("div.dropdown-wrapper__menu a");

    SelenideElement
        primaryHeaderLocator = $(".primary-header"),
        repairButtonLocator = $$(".main-block__btns--item").findBy(text("Ремонт")),
        maintenanceButtonLocator = $$(".main-block__btns--item").findBy(text("Техобслуживание")),
        mainBlockTitleLocator = $(".main-block__title"),
        signInButtonLocator = $(".primary-header .link"),
        userProfileButtonLocator = $(".primary-header--nav .link"),
        signUpDropdownLocator = $(".dropdown-title .arrow-down"),
        signUpClientButtonLocator = signUpProleMenuLocator.findBy(text("Для клиента")),
        signUpCompanyButtonLocator = signUpProleMenuLocator.findBy(text("Для сервисной компании")),
        signUpMasterButtonLocator = signUpProleMenuLocator.findBy(text("Для мастера"));

public LandingPage open() {
    Selenide.open("https://dev.gasworkers.ru/");
    primaryHeaderLocator.shouldBe(visible);
        return this;
    }

    public LandingPage isOpened() {
        primaryHeaderLocator.shouldBe(appear);
        primaryHeaderLocator.shouldBe(visible);
        userProfileButtonLocator.shouldBe(appear);
        return this;
    }

    //  clickSignInButton
    public LandingPage clickUserProfile() {
        userProfileButtonLocator.click();
        return this;
    }

    public void signUpClient() {
        signUpDropdownLocator.click();
        signUpClientButtonLocator.click();
    }

    public void signUpCompany() {
        signUpDropdownLocator.click();
        signUpCompanyButtonLocator.click();
    }

    public void signUpMaster() {
        signUpDropdownLocator.click();
        signUpMasterButtonLocator.click();
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
