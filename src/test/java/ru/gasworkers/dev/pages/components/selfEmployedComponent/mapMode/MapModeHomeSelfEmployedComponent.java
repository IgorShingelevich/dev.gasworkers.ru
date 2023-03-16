package ru.gasworkers.dev.pages.components.selfEmployedComponent.mapMode;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.FillProfileBannerSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.ModeSwitcherSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.ViewSwitcherSelfEmployedComponent;
import ru.gasworkers.dev.pages.selfEmployed.BaseSelfEmployedPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class MapModeHomeSelfEmployedComponent extends BaseComponent {
    public final FillProfileBannerSelfEmployedComponent fillProfileBanner;
    public final ViewSwitcherSelfEmployedComponent viewSwitcher;
    public final ModeSwitcherSelfEmployedComponent mode;
    public final MapSelfEmployedComponent map;


    public MapModeHomeSelfEmployedComponent(RoleBrowser browser) {
        super(browser);
        mode = new ModeSwitcherSelfEmployedComponent(browser);
        map = new MapSelfEmployedComponent(browser);
        fillProfileBanner = new FillProfileBannerSelfEmployedComponent(browser);
        viewSwitcher = new ViewSwitcherSelfEmployedComponent(browser);
    }

    ElementsCollection
            offersBoxCollection = driver.$$("div.col-xl-6.mb-3").as("Коллекция блоков с предложениями"),
            offersNumberCollection = driver.$$(".small.d-inline-flex.align-items-start.link-dark-blue.mr-1").as("Коллекция номеров предложений");


    public void checkInitialState() {
        stepWithRole("Проверить начальное состояние режима карты", () -> {
            map.checkFinishLoading();
            fillProfileBanner.checkFinishLoading();
            stepWithRole("Убедиться что количестов блоков с предложениями совпадает с количеством предложений на карте", () -> {
                offersBoxCollection.first().shouldBe(visible, Duration.ofSeconds(20));
                Integer currentOffersMapCount = map.offersCount();
               Integer currentOffersBoxCount = offersBoxCollection.size();
                if (currentOffersMapCount != currentOffersBoxCount) {
                    throw new AssertionError("Количество предложений на карте не совпадает с количеством блоков с предложениями");
                }
            });
        });
    }

    public void selectOfferByNumber(String number) {
        stepWithRole("Выбрать предложение по номеру", () -> {
            offersBoxCollection.findBy(text(number)).find("button.btn-primary").click();
        });
    }

    public void  selectOfferByIndex(int index) {
        stepWithRole("Выбрать предложение по индексу", () -> {
            offersBoxCollection.get(index).find("button.btn-primary").click();
        });
    }
}
