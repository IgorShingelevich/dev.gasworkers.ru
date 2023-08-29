package ru.gasworkers.dev.pages.components.sharedComponent.guideComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class BaseGuideComponent extends BaseComponent {
    SelenideElement
            self = driver.$("div.gas-guide-wrapper").as("Гайд бокс");
    ElementsCollection
            footerGuideButtonsCollection = self.$("div.gas-guide-footer").$$("button").as("Коллекция кнопок футера гида"),
            qrCodeDescriptionLocator = self.$$("div.mt-2.small").as("Коллекция Описание QR кодов");
    SelenideElement
            tipTextBoxLocator = self.$("div.gas-guide-tip-light").as("Баннер с информацией"),
            nextButtonLocator = self.$("div.gas-guide-body button").as("Кнопка Далее баннера"),
            skipButtonLocator = footerGuideButtonsCollection.get(0).as("Кнопка Пропустить баннера"),
            leftToggleButtonLocator = footerGuideButtonsCollection.get(1).as("Кнопка гида Назад"),
            rightToggleButtonLocator = footerGuideButtonsCollection.get(2).as("Кнопка гида Вперед"),
            setPriceModalWindowLocator = self.$("div.modal-content-wrapper").as("Модальное окно Установить цену");

    public BaseGuideComponent(RoleBrowser browser) {
        super(browser);
    }

    public void skipButton() {
        stepWithRole("Пропустить гид", () -> {
            skipButtonLocator.click();
            self.shouldNotBe(visible, Duration.ofSeconds(10));
        });
    }

    public void nextButton() {
        stepWithRole("Нажать кнопку гида  Далее", () -> {
            nextButtonLocator.click();
        });
    }

    public void rightToggleButton() {
        stepWithRole("Нажать кнопку гида  Вперед", () -> {
            rightToggleButtonLocator.click();
        });
    }

    public void leftToggleButton() {
        stepWithRole("Нажать кнопку гида  Назад", () -> {
            leftToggleButtonLocator.click();
        });
    }
}
