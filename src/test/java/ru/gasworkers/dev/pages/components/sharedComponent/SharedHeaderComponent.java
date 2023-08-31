package ru.gasworkers.dev.pages.components.sharedComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ClientActionsBlockComponent;

import static com.codeborne.selenide.Condition.visible;

public class SharedHeaderComponent extends BaseComponent {

    public final ClientActionsBlockComponent actionsBlock;

    public SharedHeaderComponent(RoleBrowser browser) {
        super(browser);
        actionsBlock = new ClientActionsBlockComponent(browser);
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться что хедер загрузился", () -> {
            self.shouldBe(visible);
            logoLocator.shouldBe(visible);
            actionsBlock.checkFinishLoading();
        });
    }

    public void clickLogo() {
        stepWithRole("Кликнуть на логотип", () -> {
            logoLocator.click();
        });
    }

    SelenideElement
            self = driver.$("#gas__content-header").as("Хедер сайта"),
            logoLocator = self.$("div.logo").as("Логотип");


}


