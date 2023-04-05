package ru.gasworkers.dev.pages.components.selfEmployedComponent.mapMode;

import com.codeborne.selenide.ElementsCollection;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.FillProfileBannerSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.ModeSwitcherSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.ViewSwitcherSelfEmployedComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ModeDispatcherHomeSelfEmployedComponent extends BaseComponent {
    public final FillProfileBannerSelfEmployedComponent fillProfileBanner;
    public final ViewSwitcherSelfEmployedComponent viewSwitcher;
    public final ModeSwitcherSelfEmployedComponent mode;
    public final MapSelfEmployedComponent map;


    public ModeDispatcherHomeSelfEmployedComponent(RoleBrowser browser) {
        super(browser);
        mode = new ModeSwitcherSelfEmployedComponent(browser);
        map = new MapSelfEmployedComponent(browser);
        fillProfileBanner = new FillProfileBannerSelfEmployedComponent(browser);
        viewSwitcher = new ViewSwitcherSelfEmployedComponent(browser);
    }

    ElementsCollection
            offersBoxCollection = driver.$$("div.row.space-3>div").as("Коллекция блоков с предложениями"),
            offersNumberCollection = driver.$$(".small.d-inline-flex.align-items-start.link-dark-blue.mr-1").as("Коллекция номеров предложений");


    public void checkInitialState() {
        stepWithRole("Убедиться, что режим карты СМЗ в начальном состоянии", () -> {
            map.checkFinishLoading();
            fillProfileBanner.checkFinishLoading();
            checkOffersCount();
        });
    }

    public void selectOfferByNumber(String orderNumber) {
        stepWithRole("Выбрать по номеру: " + orderNumber + " предложение", () -> {
            offersBoxCollection.findBy(text(orderNumber)).find("button.btn-primary").click();
        });
    }

    public void  selectOfferByIndex(int index) {
        stepWithRole("Выбрать  по списку: " + index+1 + " предложение", () -> {
            offersBoxCollection.get(index).find("button.btn-primary").click();
        });
    }
    public void selectFirstMaintenanceOffer() {
        stepWithRole("Выбрать первое предложение по обслуживанию", () -> {
            offersBoxCollection.findBy(text("Договор ТО")).find("button.btn-primary").click();
        });
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что режим карты СМЗ загрузился", () -> {
            map.checkFinishLoading();
            checkOffersCount();
        });
    }

    private void checkOffersCount (){
        stepWithRole("Убедиться что количестов блоков с предложениями совпадает с количеством предложений в боксе на карте", () -> {
            offersBoxCollection.first().shouldBe(visible, Duration.ofSeconds(20));
            Integer currentOffersMapCount = map.offersCount();
            Integer currentOffersBoxCount = offersBoxCollection.size();
            if (currentOffersMapCount != currentOffersBoxCount) {
                throw new AssertionError("Количество предложений на карте не совпадает с количеством блоков с предложениями");
            }
        });
    }
}
