package ru.gasworkers.dev.pages.components.selfEmployedComponent.orderPageComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class FillUpOfferBannerOrderPageSelfEmployedComponent extends BaseComponent {
    public FillUpOfferBannerOrderPageSelfEmployedComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
            fillUpOfferBannerTitleText = "Внимание!",
            fillUpOfferBannerDescriptionText = "Участие в тендере не гарантирует получение заказа, внимательно отнеситесь к определению цены на заказ, корректность указания сертификатов и удостоверений. Все это влияет на принятие решения клиентом при выборе вас в качестве исполнителя.";

    SelenideElement
            fillUpOfferBannerLocator = driver.$("div.modal-content-wrapper").as("Баннер расценки предложения"),
            fillUpOfferBannerTitleLocator = fillUpOfferBannerLocator.$("div.order-price-title.d-flex.justify-content-between span.bold").as("Заголовок баннера расценки предложения"),
            fillUpOfferBannerDescriptionLocator = fillUpOfferBannerLocator.$("div.w-100.text-center.my-4").as("Описание баннера расценки предложения"),
            closeBannerButtonLocator = fillUpOfferBannerLocator.$("div.close-btn").as("Кнопка закрытия баннера расценки предложения"),
            forwardButtonLocator = fillUpOfferBannerLocator.$("button.btn.btn-primary ").as("Кнопка Далее"),
            cancelButtonLocator = fillUpOfferBannerLocator.$("button.btn.btn-outline-primary").as("Кнопка Отмена");

    public void checkFinishLoading () {
        stepWithRole("Убедиться, что баннер расценки предложения загрузился", () -> {
            fillUpOfferBannerLocator.shouldBe(visible);
            fillUpOfferBannerTitleLocator.shouldHave(text(fillUpOfferBannerTitleText));
            fillUpOfferBannerDescriptionLocator.shouldHave(text(fillUpOfferBannerDescriptionText));
            closeBannerButtonLocator.shouldBe(visible);
            forwardButtonLocator.shouldBe(visible);
            cancelButtonLocator.shouldBe(visible);
        });
    }

    public void clickForwardButton() {
        stepWithRole("Нажать кнопку Далее", () -> {
            forwardButtonLocator.click();
        });
    }

    public void clickCancelButton () {
        stepWithRole("Нажать кнопку Отмена", () -> {
            cancelButtonLocator.click();
        });
    }

    public void clickCloseBannerButton () {
        stepWithRole("Нажать кнопку Закрыть баннер", () -> {
            closeBannerButtonLocator.click();
        });
    }


}
