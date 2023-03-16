package ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;

public class ClientActionsBlockComponent extends BaseComponent {

    public ClientActionsBlockComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
            mainPageTitleLocator = driver.$(".primary-header"),
            actionsBlock = driver.$(".actions-block"),
            notificationsButtonLocator = driver.$("div.notifications.icon").as("Уведомления"),
            messagesButtonLocator = driver.$("div.messages.icon").as("Сообщения"),
            dropdownArrowLocator = driver.$(".actions-block .arrow-down").as("Дропдаун Стрелка вниз"),
            profileNameABLocator = driver.$(".profile-menu .profile-menu__link.text-primary").as("Ссылка Имя профиля"),
            profileLinkABLocator = driver.$("a[href='/profile/edit']").as("Ссылка на профиль"),
            reviewLinkABLocator = driver.$("a[href='/profile/reviews']").as("Ссылка на отзывы"),
            logoutLinkABLocator = driver.$$("button.profile-menu__link").findBy(text("Выйти")).as("Ссылка на выход");

    public ClientActionsBlockComponent checkFinishLoading () {
        stepWithRole("Убедиться что все компоненты загрузились", () -> {
            actionsBlock.shouldBe(visible);
            //todo add messagesButtonLocator
            notificationsButtonLocator.shouldBe(visible);
            messagesButtonLocator.shouldBe(visible);
            dropdownArrowLocator.shouldBe(visible);
            clickDropdown();
            profileNameABLocator.shouldBe(visible);
            profileLinkABLocator.shouldBe(visible);
            reviewLinkABLocator.shouldBe(visible);
            logoutLinkABLocator.shouldBe(visible);
            clickDropdown();
        });
        return this;
    }

    public ClientActionsBlockComponent clickDropdown() {
        stepWithRole("Открыть дропдаун", () -> {
            dropdownArrowLocator.click();
        });
        return this;
    }

    public ClientActionsBlockComponent messages() {
        stepWithRole("Перейти на страницу Сообщения", () -> {
            messagesButtonLocator.shouldBe(visible).click();
        });
        return this;
    }

    public ClientActionsBlockComponent profile() {
        stepWithRole("Перейти на страницу Профиль", () -> {
            profileLinkABLocator.click();
        });
        return this;
    }

    public ClientActionsBlockComponent notifications() {
        stepWithRole("Перейти на страницу Уведомления", () -> {
            notificationsButtonLocator.click();
        });
        return this;
    }

    public ClientActionsBlockComponent logout() {
        stepWithRole("Выход из системы", () -> {
            dropdownArrowLocator.click();
            logoutLinkABLocator.click();
        });
        return this;
    }
}
