package ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class SelfEmployedActionsBlockComponent extends BaseComponent {

    public SelfEmployedActionsBlockComponent(RoleBrowser browser) {
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

    public SelfEmployedActionsBlockComponent checkFinishLoading () {
        stepWithRole("Убедиться, что все элементы блока действий хедера загрузились", () -> {
            actionsBlock.shouldBe(visible);
            notificationsButtonLocator.shouldBe(visible);
            messagesButtonLocator.shouldBe(visible);
            //todo add messagesButtonLocator - done
            dropdownArrowLocator.shouldBe(visible);
            clickDropdown();
            profileNameABLocator.shouldBe(visible);
            profileLinkABLocator.shouldBe(visible);
            logoutLinkABLocator.shouldBe(visible);
            clickDropdown();
        });
        return this;
    }

    public SelfEmployedActionsBlockComponent clickDropdown() {
        stepWithRole("Открыть дропдаун", () -> {
            dropdownArrowLocator.click();
        });
        return this;
    }

    public SelfEmployedActionsBlockComponent messages() {
        stepWithRole("Перейти на страницу Сообщения", () -> {
            messagesButtonLocator.shouldBe(visible).click();
        });
        return this;
    }

    public SelfEmployedActionsBlockComponent profile() {
        stepWithRole("Перейти на страницу Профиль", () -> {
            profileLinkABLocator.click();
        });
        return this;
    }

    public SelfEmployedActionsBlockComponent notifications() {
        stepWithRole("Перейти на страницу Уведомления", () -> {
            notificationsButtonLocator.click();
        });
        return this;
    }

    public SelfEmployedActionsBlockComponent logout() {
        stepWithRole("Выход из системы", () -> {
            dropdownArrowLocator.click();
            logoutLinkABLocator.click();
        });
        return this;
    }
}
