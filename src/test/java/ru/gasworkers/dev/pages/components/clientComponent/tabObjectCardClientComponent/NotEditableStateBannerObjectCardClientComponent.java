package ru.gasworkers.dev.pages.components.clientComponent.tabObjectCardClientComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;

public class NotEditableStateBannerObjectCardClientComponent extends BaseComponent {
    public NotEditableStateBannerObjectCardClientComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String NOT_EDITABLE_STATE_BANNER = "Объекты, участвующие в заказах нельзя редактировать";
    SelenideElement
            notEditableStateBannerLocator = driver.$("div.gas-tip").as("Баннер о невозможности редактирования объекта");

    public void isExist() {
        stepWithRole("Баннер о невозможности редактирования объекта присутствует", () -> {
            notEditableStateBannerLocator.shouldHave(text(NOT_EDITABLE_STATE_BANNER));
        });
    }

    public void notExist() {
        stepWithRole("Баннер о невозможности редактирования объекта отсутствует", () -> {
            notEditableStateBannerLocator.shouldNotHave(text(NOT_EDITABLE_STATE_BANNER));
        });
    }


}
