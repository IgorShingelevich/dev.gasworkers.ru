package ru.gasworkers.dev.pages.components.registrationComponent.selfEmployedRegistrationStepComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BannerTypeSelfEmployedComponent extends BaseComponent {
    public BannerTypeSelfEmployedComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
    titleText = "Я хочу стать самозанятым",
    description1Text = "Сделать это можно через",
    description2Text = ", на сайте ФНС или через банки. Помимо заказ на ремонт и видео консультаций, Вам будет доступно проведение работ по техническому обслуживанию ВДГО при заключении договора с сервисной компанией.",
    appMyTaxLinkText = "мобильное приложение «Мой налог»",
    instructionLinkText = "Подробная инструкция «Как стать самозанятым»";

    ElementsCollection
            linkCollection = driver.$$("div.notice-blue__text a");

    SelenideElement
     bannerBoxLocator = driver.$("div.notice-blue").as("Баннер"),
    titleLocator = driver.$("p.bold.h4.mx-2").as("Заголовок"),
    description1Locator = driver.$("div.notice-blue__text p").as("Описание 1"),
    description2Locator = driver.$("div.notice-blue__text p").as("Описание 2"),
    appMyTaxLinkLocator = linkCollection.get(0).as("Ссылка на мобильное приложение «Мой налог»"),
    instructionLinkLocator = linkCollection.get(1).as("Ссылка на подробную инструкцию «Как стать самозанятым»"),
    closeButtonLocator = driver.$("div.notice-blue__close").as("Кнопка закрытия баннера");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что отображается баннер", () -> {
            bannerBoxLocator.shouldBe(visible);
            stepWithRole("Убедиться, что отображается заголовок: " + titleText, () -> {
                titleLocator.shouldHave(text(titleText));
            });
            stepWithRole("Убедиться, что отображается описание 1: " + description1Text, () -> {
                description1Locator.shouldHave(text(description1Text));
            });
            stepWithRole("Убедиться, что отображается описание 2: " + description2Text, () -> {
                description2Locator.shouldHave(text(description2Text));
            });
            stepWithRole("Убедиться, что отображается ссылка на мобильное приложение «Мой налог»: " + appMyTaxLinkText, () -> {
                appMyTaxLinkLocator.shouldHave(text(appMyTaxLinkText));
            });
            stepWithRole("Убедиться, что отображается ссылка на подробную инструкцию «Как стать самозанятым»: " + instructionLinkText, () -> {
                instructionLinkLocator.shouldHave(text(instructionLinkText));
            });
            stepWithRole("Убедиться, что отображается кнопка закрытия баннера", () -> {
                closeButtonLocator.shouldBe(visible);
            });
        });
    }

    public void clickCloseButton() {
        stepWithRole("Нажать на кнопку закрытия баннера", () -> {
            closeButtonLocator.click();
        });
    }

    public void clickAppMyTaxLink() {
        stepWithRole("Нажать на ссылку на мобильное приложение «Мой налог»", () -> {
            appMyTaxLinkLocator.click();
        });
    }

    public void clickInstructionLink() {
        stepWithRole("Нажать на ссылку на подробную инструкцию «Как стать самозанятым»", () -> {
            instructionLinkLocator.click();
        });
    }
}
