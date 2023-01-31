package pages.common;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class LandingPage extends BasePage {

public LandingPage(RoleBrowser browser) {
        super(browser);
    }

    private final String
        TITLE_TEXT = "Ремонт и техобслуживание газового оборудования от 499 ₽",
        SUBTITLE_TEXT = "Выезд мастера по Московской области в день обращения от 30 минут";

    ElementsCollection
        signUpRoleMenuLocator =driver.$$("div.dropdown-wrapper__menu a");

    SelenideElement
        primaryHeaderLocator = driver.$(".primary-header").as("Шапка сайта"),
        repairButtonLocator = driver.$(byTagAndText("button", "Ремонт")).as("Кнопка Ремонт"),
        maintenanceButtonLocator = driver.$(byTagAndText("button", "Техобслуживание")).as("Кнопка Техобслуживание"),
        videoButtonLocator = driver.$(byTagAndText("button", "Видеоконсультация")).as("Кнопка Видеоконсультация"),
        mainBlockTitleLocator = driver.$("p.main-block__title").as("Заголовок главной страницы"),
        mainBlockSubtitleLocator = driver.$("p.main-block__text").as("Подзаголовок главной страницы"),
        signInButtonLocator = driver.$(".primary-header .link").as("Кнопка Личный кабинет"),
        signUpDropdownLocator = driver.$("span.dropdown-title").as("Выпадающее меню Регистрация"),
        signUpClientButtonLocator = signUpRoleMenuLocator.findBy(text("Для клиента")).as("Кнопка Для клиента"),
        signUpCompanyButtonLocator = signUpRoleMenuLocator.findBy(text("Для сервисной компании")).as("Кнопка Для сервисной компании"),
        signUpMasterButtonLocator = signUpRoleMenuLocator.findBy(text("Для мастера")).as("Кнопка Для мастера");

public LandingPage open() {
    stepWithRole("Открыть главную страницу", () -> {
        driver.open("https://dev.gasworkers.ru/");
        primaryHeaderLocator.shouldBe(visible);
    });
        return this;
    }

    public LandingPage checkFinishLoading() {
        stepWithRole("Убедиться, что главная страница загружена", () -> {
            stepWithRole("Убедиться, что заголовок и подзаголовок Главной страницы отображается", () -> {
                stepWithRole("Заголовок: " + TITLE_TEXT, () -> {
                    mainBlockTitleLocator.shouldBe(visible).shouldHave(text(TITLE_TEXT));
                });
                stepWithRole("Подзаголовок: " + SUBTITLE_TEXT, () -> {
                    mainBlockSubtitleLocator.shouldBe(visible).shouldHave(text(SUBTITLE_TEXT));
                });
            });
            stepWithRole("Убедиться, что кнопка Личный кабинет отображается", () -> {
                signInButtonLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что выпадающее меню Регистрация отображается", () -> {
                signUpDropdownLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что кнопка Ремонт отображается", () -> {
                repairButtonLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что кнопка Техобслуживание отображается", () -> {
                maintenanceButtonLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что кнопка Видеоконсультация отображается", () -> {
                videoButtonLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что виджет Живой чат отображается", () -> {
                driver.$("#jivo-iframe-container").shouldBe(exist, Duration.ofSeconds(10));
            });
            // TODO add the rest  components checks
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
        signUpClientButtonLocator.shouldBe(visible, Duration.ofSeconds(10)).click();
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

    public LandingPage clickUserRepairButton() {
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
    }
}
