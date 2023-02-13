package ru.gasworkers.dev.pages.components.clientComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;

public class OffersClientComponent extends BaseComponent {
    public OffersClientComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
            offersComponentLocator = driver.$("div.map-sticky__header--offers").as("Блок с предложениями");


    public void noOffers() {
        stepWithRole("Убедиться, что нет предложений", () -> {
            offersComponentLocator.shouldHave(text("Нет предложений"), Duration.ofSeconds(10));
        });
    }

    public void haveOffers(Integer count) {
        stepWithRole("Убедиться, что есть " + count + " предложений", () -> {
            offersComponentLocator.shouldHave(partialText(count.toString()), Duration.ofSeconds(10));
        });
    }
    public void clickOffers() {
        stepWithRole("Нажать на компонент предложения", () -> {
            offersComponentLocator.shouldBe(visible, Duration.ofSeconds(30)).click();
        });
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что компонент предложения загрузился", () -> {
            offersComponentLocator.shouldBe(visible);
        });
    }
}
