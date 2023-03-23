package ru.gasworkers.dev.pages.components.sharedComponent.headerComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class SelfEmployedBurgerComponent extends BaseComponent {

    public SelfEmployedBurgerComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
            serviceText = "Служба поддержки",
            servicePhone = "8 800 302 89 04",
            homeLinkText = "Главная",
            certificatesAndEquipmentLinkText = "Сертификаты и оборудование",
            profileLinkText = "Профиль";

    ElementsCollection
            linkCollection = driver.$$(".fix-left-menu__nav div.link").as("Коллекция ссылок в бургере");


    SelenideElement
            openBurgerButtonLocator = driver.$("button.burger-h").as("Кнопка Открыть Бургер"),
            closeBurgerButtonLocator = driver.$("button.fix-left-menu__close").as("Кнопка Закрыть Бургер"),
            serviceLinkLocator = driver.$("div.fix-left-menu__support span").as("Текст Служба поддержки"),
            servicePhoneLocator = driver.$("div.fix-left-menu__support a").as("Телефон Служба поддержки"),
            homeLinkLocator = driver.$("a.link.active.nuxt-link-active").as("Ссылка на домашнюю страницу"),
            certificatesAndEquipmentLinkLocator = linkCollection.get(0).as("Ссылка на сертификаты и оборудование"),
            profileLinkLocator = linkCollection.get(1).as("Ссылка на профиль");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что все элементы бургера загрузились", () -> {
            openBurgerButtonLocator.shouldBe(visible);
            openBurger();
            closeBurgerButtonLocator.shouldBe(visible);
            serviceLinkLocator.shouldHave(text(serviceText));
            servicePhoneLocator.shouldHave(text(servicePhone));
            homeLinkLocator.shouldHave(text(homeLinkText));
            certificatesAndEquipmentLinkLocator.shouldHave(text(certificatesAndEquipmentLinkText));
            profileLinkLocator.shouldHave(text(profileLinkText));
            closeBurger();
            homeLinkLocator.shouldNotBe(visible);
        });
    }

    public void openBurger() {
        stepWithRole("Кликнуть на кнопку открыть бургер", () -> {
            openBurgerButtonLocator.click();
        });
    }

    public void closeBurger() {
        stepWithRole("Кликнуть на кнопку закрыть бургер", () -> {
            closeBurgerButtonLocator.click();
        });
    }

    public void home() {
        stepWithRole("Кликнуть на ссылку на домашнюю страницу", () -> {
            homeLinkLocator.click();
        });
    }

    public void certificatesAndEquipment() {
        stepWithRole("Кликнуть на ссылку на сертификаты и оборудование", () -> {
            certificatesAndEquipmentLinkLocator.click();
        });
    }

    public void profile() {
        stepWithRole("Кликнуть на ссылку на профиль", () -> {
            profileLinkLocator.click();
        });
    }

}
