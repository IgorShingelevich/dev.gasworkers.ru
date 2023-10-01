package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class UpperRepairInfoBoxComponent extends BaseComponent {
    public UpperRepairInfoBoxComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkPublishedState() {
        stepWithRole("Убедиться, что верхняя информационная панель Ремонта отображается", () -> {
            self.shouldBe(visible);
            firstTextLocator.shouldHave(text("Ожидайте подтверждения заказа диспетчером или мастером."));
            secondTextLocator.shouldHave(text("После отклика Gasworkers покажет предварительную стоимость ремонта."));
            closeButtonLocator.shouldBe(visible);
        });
    }

    public void noUpperInfoBox() {
        stepWithRole("Убедиться, что верхняя информационная панель Ремонта не отображается", () -> {
            self.shouldNotBe(visible);
        });
    }    SelenideElement
            self = driver.$("div.gas-tip.w-100").as("Верхняя информационная панель Ремонта"),
            firstTextLocator = self.$("p.first-text").as("Первый текст верхней информационной панели Ремонта"),
            secondTextLocator = self.$("p.second-text").as("Второй текст верхней информационной панели Ремонта"),
            closeButtonLocator = self.$("div.gas-tip__close").as("Кнопка закрытия верхней информационной панели Ремонта");


}
