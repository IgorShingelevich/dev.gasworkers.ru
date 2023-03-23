package ru.gasworkers.dev.pages.components.sharedComponent.headerComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.SelfEmployedActionsBlockComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class HeaderSelfEmployedComponent extends BaseComponent {
    public final SelfEmployedBurgerComponent burger;

    public final SelfEmployedActionsBlockComponent actionsBlock;
    public HeaderSelfEmployedComponent(RoleBrowser browser) {
        super(browser);
        burger = new SelfEmployedBurgerComponent(browser);
        actionsBlock = new SelfEmployedActionsBlockComponent(browser);
    }

    SelenideElement
    logoLocator = driver.$("img[src*='/images/logo']").as("Логотип"),
    profileSettingsButtonLocator = driver.$("button.btn.btn-warning").as("Кнопка  Настройки профиля");  //button[data-guide='profile-header-button']

    public void clickLogo() {
        stepWithRole("Кликнуть на логотип", () -> {
            logoLocator.click();
        });
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что все элементы хедера загрузились", () -> {
//            burger.checkFinishLoading();
            actionsBlock.checkFinishLoading();
            //todo add messagesButtonLocator
            stepWithRole("Убедиться что логотип загрузился", () -> {
                logoLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться что кнопка  Настройки профиля загрузилась", () -> {
                profileSettingsButtonLocator.shouldHave(text(" Настройки профиля"));
            });
        });
    }

}
