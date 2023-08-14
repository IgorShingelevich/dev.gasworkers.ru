package ru.gasworkers.dev.pages.sharedPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.BasePage;
import ru.gasworkers.dev.pages.components.landingComponent.BackgroundRegistrationComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.ConfirmationCodeModalWindowBGComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.JivoMessengerComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class LandingPage extends BasePage {

    public final BackgroundRegistrationComponent bgRegistration;
    public final JivoMessengerComponent jivoMessengerComponent;
    public final ConfirmationCodeModalWindowBGComponent confirmationCodeModalBG;
    private final String
            TITLE_TEXT = "Ремонт и техобслуживание газового оборудования от 499 ₽",
            SUBTITLE_TEXT = "Выезд мастера по Московской области в день обращения от 30 минут";
    ElementsCollection
            signUpRoleMenuLocator = driver.$$("div.dropdown-wrapper__menu a");
    SelenideElement
            primaryHeaderLocator = driver.$("div.primary-header-blue").as("Хедер сайта"),
    //        repairButtonLocator = driver.$(byTagAndText("span", "Ремонт")).as("Кнопка Ремонт"),
//        maintenanceButtonLocator = driver.$(byTagAndText("span", "Техобслуживание")).as("Кнопка Техобслуживание"),
//        videoButtonLocator = driver.$(byTagAndText("span", "Видеоконсультация")).as("Кнопка Видеоконсультация"),
//        mainBlockTitleLocator = driver.$("p.main-block__title").as("Заголовок главной страницы"),
//        mainBlockSubtitleLocator = driver.$("p.main-block__text").as("Подзаголовок главной страницы"),
    signInButtonLocator = driver.$("#header-login-icon").as("Кнопка Личный кабинет"),
            signUpDropdownLocator = driver.$("#type-of-register-dropdown").as("Выпадающее меню Регистрация"),
            signUpClientButtonLocator = signUpRoleMenuLocator.findBy(text("Для клиента")).as("Кнопка Для клиента"),
            signUpCompanyButtonLocator = signUpRoleMenuLocator.findBy(text("Для сервисной компании")).as("Кнопка Для сервисной компании"),
            signUpMasterButtonLocator = signUpRoleMenuLocator.findBy(text("Для мастера")).as("Кнопка Для мастера");

    public LandingPage(RoleBrowser browser) {
        super(browser);
        bgRegistration = new BackgroundRegistrationComponent(browser);
        jivoMessengerComponent = new JivoMessengerComponent(browser);
        confirmationCodeModalBG = new ConfirmationCodeModalWindowBGComponent(browser);
    }

    public LandingPage open() {
        stepWithRole("Открыть главную страницу", () -> {
            driver.open("https://dev.gasworkers.ru/");
            primaryHeaderLocator.shouldBe(visible);
        });
        return this;
    }

    public LandingPage checkFinishLoading() {
        stepWithRole("Убедиться, что главная страница загружена", () -> {
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/");
            Selenide.sleep(1000);
            stepWithRole("Убедиться, что кнопка Личный кабинет отображается", () -> {
                signInButtonLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что выпадающее меню Регистрация отображается", () -> {
                signUpDropdownLocator.shouldBe(visible);
            });

//            bgRegistration.checkFinishLoading();
// todo falls  during tabs switching


            // TODO add the rest  components checks
            // todo footer components checks
        });
        return this;
    }

    public LandingPage clickUserProfileSignIn() {
        stepWithRole("Нажать кнопку Личный кабинет", () -> {
            signInButtonLocator.click();
        });
        return this;
    }

    public void signUpClient() {
        stepWithRole("Нажать кнопку Зарегистрироваться и выбрать Для клиента", () -> {
            signUpDropdownLocator.click();
            signUpClientButtonLocator.shouldBe(visible, Duration.ofSeconds(5)).click();
        });
    }

    public void signUpCompany() {
        stepWithRole("Нажать кнопку Зарегистрироваться и выбрать Для сервисной компании", () -> {
            signUpDropdownLocator.click();
            signUpCompanyButtonLocator.click();
        });
    }

    public void signUpMaster() {
        stepWithRole("Нажать кнопку Зарегистрироваться и выбрать Для мастера", () -> {
            signUpDropdownLocator.click();
            signUpMasterButtonLocator.click();
        });
    }

    public void wrongButton() {
        stepWithRole("Нажать кнопку несуществующую кнопку", () -> {
            driver.$("div.wrongButton").click();
        });
    }

    public void close() {
        stepWithRole("Закрыть главную страницу", () -> {
            driver.close();
        });
    }

    public void swichToTab(int tabNumber) {
        stepWithRole("Переключиться на вкладку " + tabNumber, () -> {
            driver.switchTo().window(tabNumber);
        });
    }



    /*public LandingPage clickUserRepairButton() {
    stepWithRole("Нажать кнопку Ремонт", () -> {
    repairButtonLocator.shouldHave(text("Ремонт")).click();
    });
    return this;
    }

    public LandingPage clickUserMaintenanceButton() {
    stepWithRole("Нажать кнопку Техобслуживание", () -> {
        maintenanceButtonLocator.shouldHave(text("Техобслуживание")).click();
    });
    return this;
    }

    public LandingPage clickUserVideoButton() {
    stepWithRole("Нажать кнопку Видеоконсультация", () -> {
        videoButtonLocator.shouldHave(text("Видеоконсультация")).click();
    });
    return this;
    }*/
}
