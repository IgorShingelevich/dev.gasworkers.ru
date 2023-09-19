package ru.gasworkers.dev.pages.components.sharedComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ClientActionsBlockComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class MapDispatcherHeader extends BaseComponent {
    public final ClientActionsBlockComponent actionsBlock;

    ElementsCollection
            hamburgerItemsCollection = driver.$$("ul.fix-left-menu__nav li .link").as("Элементы меню");

    public MapDispatcherHeader(RoleBrowser browser) {
        super(browser);
        actionsBlock = new ClientActionsBlockComponent(browser);
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться что хедер загрузился", () -> {
            self.shouldBe(visible);
            logoLocator.shouldBe(visible);
            actionsBlock.checkFinishLoadingDispatcher();
            hamburgerDropdownLocator.shouldBe(visible);
            isOpenedHamburgerDropdown(false);
        });
    }

    public void clickLogo() {
        stepWithRole("Кликнуть на логотип", () -> {
            logoLocator.click();
        });
    }

    public void clickHamburgerDropdown() {
        stepWithRole("Кликнуть на гамбургер", () -> {
            hamburgerDropdownLocator.click();
        });
    }    SelenideElement self = driver.$("header.header-map").as("хедер режима карты диспетчера"),
            logoLocator = self.$("a.logo-h").as("Логотип"),
            hamburgerDropdownLocator = self.$("button.burger-h").as("Кнопка гамбургер"),
            mainHamburgerLinkLocator = hamburgerItemsCollection.findBy(text("Главная")).as("Ссылка на главную страницу"),
            mastersHamburgerLinkLocator = hamburgerItemsCollection.findBy(text("Список мастеров")).as("Ссылка на список мастеров"),
            profileHamburgerLinkLocator = hamburgerItemsCollection.findBy(text("Профиль")).as("Ссылка на профиль");

    public boolean isOpenedHamburgerDropdown(boolean expectedState) {
        String stateDescription = expectedState ? "открыт" : "закрыт";
        String description = "Убедиться, что гамбургер в состоянии " + stateDescription;

        return stepWithRole(description, () -> {
            if (expectedState) {
                return isHamburgerMenuDisplayed() && isVisibleHamburgerItems();
            } else {
                return !isHamburgerMenuDisplayed();
            }
        });
    }

    private boolean isHamburgerMenuDisplayed() {
        return driver.$("div.fix-left-menu").isDisplayed();
    }

    private boolean isVisibleHamburgerItems() {
        hamburgerItemsCollection.shouldHave(CollectionCondition.size(3));
        mainHamburgerLinkLocator.shouldBe(visible);
        mastersHamburgerLinkLocator.shouldBe(visible);
        profileHamburgerLinkLocator.shouldBe(visible);
        return true;
    }




}