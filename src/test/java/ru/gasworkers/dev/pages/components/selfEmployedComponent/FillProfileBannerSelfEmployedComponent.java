package ru.gasworkers.dev.pages.components.selfEmployedComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class FillProfileBannerSelfEmployedComponent extends BaseComponent {

    public FillProfileBannerSelfEmployedComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
            bannerTitleText = "Заполните профиль",
            bannerDescriptionText = "Заполнение вкладок: Профиль и Общие данные необходимо для того, чтоб принимать заказы.",
            bannerButtonText = "перейти";

    SelenideElement
            bannerLocator = driver.$("div.notification-wrapper").as("Баннер заполнения профиля"),
            bannerTitle = bannerLocator.$("div.bold.mb-3 span").as("Заголовок баннера"),
            bannerContentText = bannerLocator.$$("div").get(4).as("Текст баннера"),
            bannerButton = bannerLocator.$("a.gas-box-move-link").as("Кнопка баннера"),
            bannerCloseButton = bannerLocator.$("div.close-btn").as("Кнопка закрытия баннера");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что баннер отображается", () -> {
            stepWithRole("Убедиться, что баннер отображается", () -> {
                bannerLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что заголовок баннера отображается", () -> {
                bannerTitle.shouldHave(text(bannerTitleText));
            });
            stepWithRole("Убедиться, что описание баннера отображается", () -> {
                bannerContentText.shouldHave(text(bannerDescriptionText));
            });
            stepWithRole("Убедиться, что кнопка баннера отображается", () -> {
                bannerButton.shouldHave(text(bannerButtonText));
            });
            stepWithRole("Убедиться, что кнопка закрытия баннера отображается", () -> {
                bannerCloseButton.shouldBe(visible);
            });
        });
    }
}
