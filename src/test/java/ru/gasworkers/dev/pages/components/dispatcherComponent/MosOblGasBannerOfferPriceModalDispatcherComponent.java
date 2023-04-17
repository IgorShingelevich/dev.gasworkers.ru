package ru.gasworkers.dev.pages.components.dispatcherComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class MosOblGasBannerOfferPriceModalDispatcherComponent extends BaseComponent {
    public MosOblGasBannerOfferPriceModalDispatcherComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
            descriptionText = "Стоимость технического обслуживания заявленного вами оборудования по прейскуранту компании МосОблГаз-Сервис составит";

    SelenideElement
    selfLocator = driver.$("div.mosoblgaz-box full").as("Баннер Мособлгаз"),
    descriptionTextLocator = selfLocator.$("p.fs-12.text-secondary").as("Текст описания");
    public void checkDescriptionText() {
        stepWithRole("Убедиться, что текст описания соответствует ожидаемому", () -> {
            descriptionTextLocator.shouldHave(text(descriptionText));
        });
    }

    public void checkFinishLoading(){
        stepWithRole("Убедиться, что баннер загрузился", () -> {
            selfLocator.shouldBe(visible);
            checkDescriptionText();
        });
    }

}
// add price check