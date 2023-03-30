package ru.gasworkers.dev.pages.components.selfEmployedComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.time.Duration;

public class ModeSwitcherSelfEmployedComponent extends BaseComponent {
    public ModeSwitcherSelfEmployedComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
            masterSwitcherItemText = "Мастер",
            dispatcherSwitcherItemText = "Диспетчер";

    SelenideElement
            modeSwitcherLocator = driver.$("div.switcher-wrapper ").as("Переключатель режимов");

    ElementsCollection
            switcherItemCollection = modeSwitcherLocator.$$("div.switcher-item").as("Элементы переключателя режимов");


        public void switchMaster() {
            stepWithRole("Переключиться на вид мастера", () -> {
                switcherItemCollection.filterBy(Condition.text(masterSwitcherItemText)).first().click();
            });
            checkMasterMode();
        }

        public void switchDispatcher() {
            stepWithRole("Переключиться на вид диспетчера", () -> {
                switcherItemCollection.filterBy(Condition.text(dispatcherSwitcherItemText)).first().click();
            });
            checkDispatcherMode();
        }

        public void checkMasterMode(){
            stepWithRole("Убедиться, что переключатель в положении мастер", () -> {
                switcherItemCollection.get(0).shouldHave(Condition.cssClass("active"));
            });
        }

        public void checkDispatcherMode () {
            stepWithRole("Убедиться, что переключатель в положении диспетчер", () -> {
                switcherItemCollection.get(1).shouldHave(Condition.cssClass("active"), Duration.ofSeconds(10));
            });
        }


    public void checkFinishLoading() {
        stepWithRole("Убедиться, что компонент переключатель режимов загрузился", () -> {
            modeSwitcherLocator.shouldBe(Condition.visible);
            switcherItemCollection.shouldHave(CollectionCondition.size( 2));
        });
    }
}
