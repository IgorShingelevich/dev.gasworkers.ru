package pages.common;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class LandingPage extends BasePage {


public LandingPage(RoleBrowser browser) {
        super(browser);
    }

    ElementsCollection
        signUpRoleMenuLocator =driver.$$("div.dropdown-wrapper__menu a");

    SelenideElement
        primaryHeaderLocator = driver.$(".primary-header"),
        repairButtonLocator = driver.$(byTagAndText("button", "Ремонт")),
        maintenanceButtonLocator = driver.$(byTagAndText("button", "Техобслуживание")),
        videoButtonLocator = driver.$(byTagAndText("button", "Видеоконсультация")),

        mainBlockTitleLocator = driver.$(".main-block__title"),
        signInButtonLocator = driver.$(".primary-header .link"),
        userProfileButtonLocator = driver.$(".primary-header--nav .link"),
        signUpDropdownLocator = driver.$("span.dropdown-title"),
        signUpClientButtonLocator = signUpRoleMenuLocator.findBy(text("Для клиента")),
        signUpCompanyButtonLocator = signUpRoleMenuLocator.findBy(text("Для сервисной компании")),
        signUpMasterButtonLocator = signUpRoleMenuLocator.findBy(text("Для мастера"));

public LandingPage open() {
    stepWithRole("Открыть главную страницу", () -> {
        driver.open("https://dev.gasworkers.ru/");
        primaryHeaderLocator.shouldBe(visible);
    });
        return this;
    }

    public LandingPage checkFinishLoading() {
        stepWithRole("Убедиться, что главная страница загружена", () -> {
            primaryHeaderLocator.shouldBe(visible);
            primaryHeaderLocator.shouldBe(visible);
            userProfileButtonLocator.shouldBe(visible);
            signInButtonLocator.shouldBe(visible);
            signUpDropdownLocator.shouldBe(visible);
            repairButtonLocator.shouldBe(visible);
            maintenanceButtonLocator.shouldBe(visible);
            videoButtonLocator.shouldBe(visible);
        });

        return this;
    }

    //  clickSignInButton
    public LandingPage clickUserProfileSignIn() {
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

        repairButtonLocator.shouldHave(text("Ремонт")).click();
        return this;
    }

    public LandingPage clickUserMaintenanceButton() {
        maintenanceButtonLocator.shouldHave(text("Техобслуживание")).click();
        return this;
    }

    public LandingPage clickUserVideoButton() {
        videoButtonLocator.shouldHave(text("Видеоконсультация")).click();
        return this;
    }






}
