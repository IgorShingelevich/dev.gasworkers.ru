package ru.gasworkers.dev.pages.components.selfEmployedComponent.masterMode;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;

public class ViewSwitcherOrderHistorySelfEmployedComponent extends BaseComponent {
    public ViewSwitcherOrderHistorySelfEmployedComponent(RoleBrowser browser) {
        super(browser);
    }
    SelenideElement
            self = driver.$("div.btn-group-action").as("Бокс кнопок переключения вида");
    ElementsCollection
            selfCollection = self.$$(".action-btn").as("Коллекция кнопок переключения вида");

    SelenideElement
            buttonListLocator = selfCollection.get(0).as("Кнопка переключения вида на список"),
            buttonTableLocator = selfCollection.get(1).as("Кнопка переключения вида на таблицу");

    public void viewList(){
        stepWithRole("Нажать на кнопку переключения вида на список", () -> {
            buttonListLocator.click();
            checkListActive();
            stepWithRole("Убедиться, что кнопка переключения вида на таблицу не активна", () -> {
                buttonTableLocator.shouldNotHave(cssClass("active"));
            });
        });
    }

    public void viewTable(){
        stepWithRole("Нажать на кнопку переключения вида на таблицу", () -> {
            buttonTableLocator.click();
            checkTableActive();
            stepWithRole("Убедиться, что кнопка переключения вида на список не активна", () -> {
                buttonListLocator.shouldNotHave(cssClass("active"));
            });
        });
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что кнопки переключения вида загрузились", () -> {
            self.shouldBe(visible);
            buttonListLocator.shouldBe(visible);
            buttonTableLocator.shouldBe(visible);
        });
    }

    public void checkListActive(){
        stepWithRole("Убедиться, что кнопка переключения вида на список активна", () -> {
            buttonListLocator.shouldHave(cssClass("active"));
        });
    }

    public void checkTableActive(){
        stepWithRole("Убедиться, что кнопка переключения вида на таблицу активна", () -> {
            buttonTableLocator.shouldHave(cssClass("active"));
        });
    }


}
