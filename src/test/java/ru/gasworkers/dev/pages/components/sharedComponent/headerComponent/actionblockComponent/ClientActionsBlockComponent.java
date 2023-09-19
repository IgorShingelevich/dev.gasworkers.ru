package ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ClientActionsBlockComponent extends BaseComponent {

    public ClientActionsBlockComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
            self = driver.$(".actions-block").as("Блок действий"),
            notificationsButtonLocator = driver.$("div.notifications.icon").as("Уведомления"),
            notificationsCounterLocator = driver.$("div.notifications.icon .count").as("Счетчик Уведомлений"),
            messagesButtonLocator = driver.$("div.messages.icon").as("Сообщения"),
            dropdownArrowLocator = driver.$(".actions-block .arrow-down").as("Дропдаун Стрелка вниз"),
            nameABLocator = driver.$(".profile-menu .profile-menu__link.text-primary").as("Ссылка Имя профиля"),
            profileLinkABLocator = driver.$("a[href='/profile/edit']").as("Ссылка на профиль"),
            reviewLinkABLocator = driver.$("a[href='/profile/reviews']").as("Ссылка на отзывы"),
            supportLinkABLocator = driver.$("a[href='/profile/messages/chats/support']").as("Ссылка на службу поддержки"),
            logoutLinkABLocator = driver.$$("button.profile-menu__link").findBy(text("Выйти")).as("Ссылка на выход");

    public void checkFinishLoadingClient() {
        stepWithRole("Убедиться что все компоненты блока действий загрузились", () -> {
            self.shouldBe(visible);
            //todo add messagesButtonLocator
            notificationsButtonLocator.shouldBe(visible);
            messagesButtonLocator.shouldBe(visible);
            dropdownArrowLocator.shouldBe(visible);
            clickDropdown();
            nameABLocator.shouldBe(visible);
            profileLinkABLocator.shouldBe(visible);
            reviewLinkABLocator.shouldBe(visible);
            supportLinkABLocator.shouldBe(visible);
            logoutLinkABLocator.shouldBe(visible);
            clickDropdown();
        });
    }

    public void checkFinishLoadingDispatcher() {
        stepWithRole("Убедиться что все компоненты блока действий загрузились", () -> {
            self.shouldBe(visible);
            //todo add messagesButtonLocator
            notificationsButtonLocator.shouldBe(visible);
            messagesButtonLocator.shouldBe(visible);
            dropdownArrowLocator.shouldBe(visible);
            clickDropdown();
            nameABLocator.shouldBe(visible);
            profileLinkABLocator.shouldBe(visible);
            reviewLinkABLocator.shouldNotBe(visible);
            supportLinkABLocator.shouldBe(visible);
            logoutLinkABLocator.shouldBe(visible);
            clickDropdown();
        });

    }

    public void clickDropdown() {
        stepWithRole("Открыть дропдаун", () -> {
            dropdownArrowLocator.click();
        });
    }

    public void messages() {
        stepWithRole("Перейти на страницу Сообщения", () -> {
            messagesButtonLocator.shouldBe(visible).click();
        });
    }

    public void profile() {
        stepWithRole("Перейти на страницу Профиль", () -> {
            profileLinkABLocator.click();
        });
    }

    public void notifications() {
        stepWithRole("Перейти на страницу Уведомления", () -> {
            notificationsButtonLocator.click();
        });
    }

    public void checkNotificationsCounter(String counter) {
        stepWithRole("Убедиться что счетчик уведомлений равен: " + counter, () -> {
            notificationsCounterLocator.shouldHave(text(counter));
        });
    }

    public void logout() {
        stepWithRole("Выйти из системы", () -> {
            dropdownArrowLocator.click();
            logoutLinkABLocator.click();
        });
    }

}
