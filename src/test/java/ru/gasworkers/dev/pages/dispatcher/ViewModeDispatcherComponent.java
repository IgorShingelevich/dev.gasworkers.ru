package ru.gasworkers.dev.pages.dispatcher;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.visible;

public class ViewModeDispatcherComponent extends BaseComponent {

    public ViewModeDispatcherComponent(RoleBrowser browser) {
        super(browser);
    }

    public boolean isButtonActive(SelenideElement button, boolean expectedState) {
        String stateDescription = expectedState ? "активна" : "неактивна";
        String description = "Убедиться, что кнопка " + button.getText() + " " + stateDescription;
        stepWithRole(description, () -> {
        });
        return expectedState == button.getAttribute("class").contains("active");
    }    /*<div data-v-8b5ec9b0="" class="col-12 col-md-3 mb-2"><div data-v-8b5ec9b0="" class="btn-group-action justify-content-end"><div data-v-8b5ec9b0="" class="action-btn map-type active"></div> <div data-v-8b5ec9b0="" class="action-btn list-type"></div> <div data-v-8b5ec9b0="" class="action-btn card-type"></div></div></div>*/
    SelenideElement self = driver.$("div.btn-group-action").as("Контейнер компонента переключения вида"),
            mapButtonLocator = self.$("div.map-type").as("Кнопка переключения вида карты"),
            listButtonLocator = self.$("div.list-type").as("Кнопка переключения вида списка"),
            cardButtonLocator = self.$("div.card-type").as("Кнопка переключения вида карточки");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что компонент переключения вида загружен", () -> {
            self.shouldBe(visible);
            mapButtonLocator.shouldBe(visible);
            listButtonLocator.shouldBe(visible);
            cardButtonLocator.shouldBe(visible);
        });
    }

    public void checkDefaultState() {
        stepWithRole("Убедиться, что по умолчанию выбран режим карты", () -> {
            isButtonActive(mapButtonLocator, true);
            isButtonActive(listButtonLocator, false);
            isButtonActive(cardButtonLocator, false);
        });
    }

    public void navMap() {
        stepWithRole("Нажать на кнопку переключения вида карты", () -> {
            mapButtonLocator.click();
            isButtonActive(mapButtonLocator, true);
        });
    }

    public void navList() {
        stepWithRole("Нажать на кнопку переключения вида списка", () -> {
            listButtonLocator.click();
            isButtonActive(listButtonLocator, true);
        });
    }

    public void navCard() {
        stepWithRole("Нажать на кнопку переключения вида карточки", () -> {
            cardButtonLocator.click();
            isButtonActive(cardButtonLocator, true);
        });
    }


}
