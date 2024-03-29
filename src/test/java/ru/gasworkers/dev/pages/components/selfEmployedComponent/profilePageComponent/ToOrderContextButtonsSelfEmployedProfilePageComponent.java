package ru.gasworkers.dev.pages.components.selfEmployedComponent.profilePageComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.visible;

public class ToOrderContextButtonsSelfEmployedProfilePageComponent extends BaseComponent {
    public ToOrderContextButtonsSelfEmployedProfilePageComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
            toOrderBottomButtonLocator = driver.$("button.mr-2.mb-3.btn.btn-warning").as("Нижняя кнопка К заказу"),
            toOrderTopButtonLocator = driver.$(".w-100 button.btn.btn-warning.disable-outline").as("Верхняя кнопка К заказу");

    public void checkToOrderContextButtonsPresenceState() {
        stepWithRole("Убедиться что присутствуют кнопки к заказу", () -> {
            toOrderTopButtonLocator.shouldBe(visible);
            toOrderBottomButtonLocator.shouldBe(visible);
        });
    }

    public void checkNoToOrderContextButtonsPresenceState() {
        stepWithRole("Убедиться что отсутствуют кнопки к заказу", () -> {
            toOrderTopButtonLocator.shouldNotBe(visible);
            toOrderBottomButtonLocator.shouldNotBe(visible);
        });
    }

}
