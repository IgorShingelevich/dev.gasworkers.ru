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
            self = driver.$("div.map-sticky__header--offers").as("Блок с предложениями");


    public OffersCounterClientComponent(RoleBrowser browser) {
        super(browser);
        urlChecker = new UrlCheckerSharedComponent(browser);
    }

    public void noComponent() {
        stepWithRole(this + "Убедиться, что нет компонента предложений", () -> {
            self.shouldNotBe(visible);
        });
    }

    public void noOffers() {
        stepWithRole(this + "Убедиться, что нет предложений", () -> {
            self.shouldHave(text("Нет предложений"), Duration.ofSeconds(10));
        });
    }

    public void amount(Integer count) {
        stepWithRole(this + "Убедиться, что есть " + count + " предложений", () -> {
            self.shouldHave(partialText(count.toString()), Duration.ofSeconds(10));
        });
    }

    public void clickOffers() {
        stepWithRole(this + "Нажать на предложения", () -> {
            self.shouldBe(visible, Duration.ofSeconds(10)).click();
            urlChecker.urlContains("select-service");
        });
    }

    public void checkFinishLoading() {
        stepWithRole(this + "Убедиться, что  загрузился", () -> {
            self.shouldBe(visible);
        });
    }

    @Override
    //class name to string
    public String toString() {
        return "Компонент счетчик предложений: ";
    }
}
