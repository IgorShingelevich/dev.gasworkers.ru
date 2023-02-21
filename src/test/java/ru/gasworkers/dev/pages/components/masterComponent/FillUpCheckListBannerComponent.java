package ru.gasworkers.dev.pages.components.masterComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent.BaseTabOrderCardComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class FillUpCheckListBannerComponent extends BaseTabOrderCardComponent {
    public FillUpCheckListBannerComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
        bannerTextLocator = driver.$("div.notice-blue__text").as("Текст баннера"),
        checkListLinkLocator = driver.$("div.notice-blue__text a").as("Ссылка на чек-лист"),
        closeBannerLocator = driver.$("div.notice-blue__close").as("Кнопка закрытия баннера");

    public void checkBannerDetails() {
        stepWithRole("Убедиться, что баннер Заполните чек лист присутствует", () -> {
            bannerTextLocator.shouldHave(text("Для дальнейшей работы вам необходимо заполнить вкладку"));
            checkListLinkLocator.shouldHave(text("«Чек— лист»"));
            closeBannerLocator.shouldBe(visible);
        });
    }

    public void closeBanner() {
        stepWithRole("Закрыть баннер Заполните чек лист", () -> {
            stepWithRole("Нажать на кнопку закрытия баннера", () -> {
                closeBannerLocator.click();
            });
            checkBannerIsNotPresent();
        });
    }

    public void clickOnCheckListLink() {
        stepWithRole("Нажать на ссылку на Чек-лист в Описании заказа", () -> {
            checkListLinkLocator.click();
        });
    }

    public void checkBannerIsNotPresent() {
        stepWithRole("Убедиться, что баннер Заполните чек лист отсутствует", () -> {
            bannerTextLocator.shouldNotBe(visible);
        });
    }
}
