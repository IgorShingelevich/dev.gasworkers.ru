package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateRepair;

import static com.codeborne.selenide.Condition.visible;

public class SuggestedConsultationBannerComponent extends BaseComponent {
    public SuggestedConsultationBannerComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
            self = driver.$("div.consultation-offer-popup").as("Баннер предложения консультации"),
            openCloseButtonLocator = driver.$("button.consultation-offer-popup__close").as("Кнопка открытия/закрытия баннера"),
            titleLocator = driver.$("p.h3.text-center.mb-3").as("Заголовок баннера");

    ElementsCollection
            mastersSliderCollection = self.$$("div.slick-track .slick-slide").as("Слайдер мастеров");

    public void checkState(StateRepair state) {
        checkFinishLoading();
        switch (state) {
            case PUBLISHED:
            case PUBLISHED_STOPPED_COUNTDOWN:
                checkOpened();
                break;
            case HAS_SUPER_OFFER:
            case HAS_SERVICE_OFFER:
                checkClosed();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + state);
        }
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что баннер отображается", () -> {
            self.shouldBe(visible);
            openCloseButtonLocator.shouldBe(visible);
            titleLocator.shouldBe(visible);
            mastersSliderCollection.shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1));
        });
    }

    public void checkOpened() {
        stepWithRole("Убедиться, что компонент консультации открыт", () -> {
            return self.shouldHave(Condition.cssClass("active")).isDisplayed();
        });
    }

    public void checkClosed() {
        stepWithRole("Убедиться, что компонент консультации закрыт", () -> {
            return self.shouldNotHave(Condition.cssClass("active")).isDisplayed();
        });
    }


}
