package ru.gasworkers.dev.pages.dispatcher;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.visible;

public class MapDispatcherComponent extends BaseComponent {
    public final LoaderComponent loader;
    SelenideElement self = driver.$("div.map-container").as("Контейнер компонента карты"),
            headerLocator = driver.$("div.map-sticky__header").as("Шапка карты"),
            offersCounterLocator = headerLocator.$("div.map-sticky__header--offers").as("Счетчик новых тендеров"),
            legendDropdownLocator = headerLocator.$("div.map-sticky__header--legend").as("Выпадающий список легенды карты"),
            toggleFullMapLocator = headerLocator.$("div.map-sticky__header--full").as("Кнопка развернуть карту"),
            zoomPlusLocator = driver.$("[class*=zoom__plus]").as("Кнопка увеличения карты"),
            zoomMinusLocator = driver.$("[class*=zoom__minus]").as("Кнопка уменьшения карты"),
            mapContainerLocator = driver.$("div.map-into").as("Контейнер карты");


    public MapDispatcherComponent(RoleBrowser browser) {
        super(browser);
        loader = new LoaderComponent(browser);
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что карта загружена", () -> {
            self.shouldBe(visible);
            headerLocator.shouldBe(visible);
            loader.handleLoader();
            offersCounterLocator.shouldBe(visible);
            legendDropdownLocator.shouldBe(visible);
            toggleFullMapLocator.shouldBe(visible);
            zoomPlusLocator.shouldBe(visible);
            zoomMinusLocator.shouldBe(visible);
            mapContainerLocator.shouldBe(visible);
        });
    }

    public boolean isLoaderVisible() {
        return driver.$("img[src*='logo-blue-loader.svg']").isDisplayed();
    }


}
