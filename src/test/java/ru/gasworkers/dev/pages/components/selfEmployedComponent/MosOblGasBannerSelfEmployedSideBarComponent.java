package ru.gasworkers.dev.pages.components.selfEmployedComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class MosOblGasBannerSelfEmployedSideBarComponent extends BaseComponent {

    public MosOblGasBannerSelfEmployedSideBarComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
            titleText = "УЧЕБНО-КУРСОВОЙ КОМБИНАТ",
            description1Text = "Проходи профессиональное обучение",
            description2Text = "и становись востребованным специалистом",
            buttonText = "Подробнее";

    ElementsCollection
            descriptionTextCollection = driver.$$("span.mosoblgaz-block__text .d-block").as("Коллекция текстов описания");

    SelenideElement
            bannerBoxLocator = driver.$("div.container-lg.sidebar-wrapper-mosoblgaz.px-0.mb-32").as("Баннер Мособлгаза"),
            bannerLink = bannerBoxLocator.$("a").as("Ссылка баннера"),
            bannerTitle = bannerBoxLocator.$("span.text").as("Заголовок баннера"),
            bannerDescription1 = descriptionTextCollection.get(0).as("Описание баннера 1"),
            bannerDescription2 = descriptionTextCollection.get(1).as("Описание баннера 2"),
            bannerMoreButton = bannerBoxLocator.$("mosoblgaz-block__btn").as("Кнопка  еще");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что баннер отображается", () -> {
            bannerBoxLocator.shouldBe(visible);
            stepWithRole("Убедиться, что заголовок баннера отображается", () -> {
                bannerTitle.shouldHave(text(titleText));
            });
            stepWithRole("Убедиться, что описание баннера 1 отображается", () -> {
                bannerDescription1.shouldHave(text(description1Text));
            });
            stepWithRole("Убедиться, что описание баннера 2 отображается", () -> {
                bannerDescription2.shouldHave(text(description2Text));
            });
            stepWithRole("Убедиться, что кнопка баннера отображается", () -> {
                bannerMoreButton.shouldHave(text(buttonText));
            });
        });

    }
}
