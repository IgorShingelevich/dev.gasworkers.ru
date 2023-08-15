package ru.gasworkers.dev.pages.components.clientComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.UrlCheckerSharedComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;

public class OffersCounterClientComponent extends BaseComponent {
    public final UrlCheckerSharedComponent urlChecker;

    SelenideElement
            offersCounterComponentLocator = driver.$("div.map-sticky__header--offers").as("Блок с предложениями");

    public OffersCounterClientComponent(RoleBrowser browser) {
        super(browser);
        urlChecker = new UrlCheckerSharedComponent(browser);
    }

    public void noOffers() {
        stepWithRole("Убедиться, что нет предложений", () -> {
            offersCounterComponentLocator.shouldHave(text("Нет предложений"), Duration.ofSeconds(10));
        });
    }

    public void amount(Integer count) {
        stepWithRole("Убедиться, что есть " + count + " предложений", () -> {
            offersCounterComponentLocator.shouldHave(partialText(count.toString()), Duration.ofSeconds(10));
        });
    }

    public void clickOffers() {
        stepWithRole("Нажать на компонент предложения", () -> {
            offersCounterComponentLocator.shouldBe(visible, Duration.ofSeconds(10)).click();
            urlChecker.urlContains("select-service");
        });
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что компонент предложения загрузился", () -> {
            offersCounterComponentLocator.shouldBe(visible);
        });
    }
}
