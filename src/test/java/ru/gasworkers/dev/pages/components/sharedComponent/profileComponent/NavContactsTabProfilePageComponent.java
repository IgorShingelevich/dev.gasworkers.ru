package ru.gasworkers.dev.pages.components.sharedComponent.profileComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.profilePageComponent.BaseProfileSelfEmployedComponent;

import static com.codeborne.selenide.Condition.*;

public class NavContactsTabProfilePageComponent extends BaseProfileSelfEmployedComponent {
    public NavContactsTabProfilePageComponent(RoleBrowser browser) {
        super(browser);
    }
    SelenideElement
    subtitleLocator = driver.$("div .title").as("Подзаголовок Контактные данные"),
    emailLocator = driver.$("input[placeholder*=почта]").as("Email"),
    phoneLocator = driver.$("input[placeholder*=Номер]").as("Телефон"),
    saveButtonLocator = driver.$(".footer button.mb-3.btn.btn-primary").as("Кнопка Сохранить");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что вкладка Контакты загружена", () -> {
            subtitleLocator.shouldHave(visible, text("Контактные данные"));
            emailLocator.shouldBe(visible);
            phoneLocator.shouldBe(visible);

            saveButtonLocator.shouldHave(visible, text("Сохранить"));
        });
    }

    public void checkInitialState(String email, String phone) {
        stepWithRole("Убедиться, что вкладка Контакты заполнена: ", () -> {
            subtitleLocator.shouldHave(visible, text("Контактные данные"));
            stepWithRole("Email: " + email, () ->
                    emailLocator.shouldBe(visible).shouldHave(value(email))
            );
            String formattedPhone = "+7(" + phone.substring(1, 4) + ")-" + phone.substring(4, 7) + "-" + phone.substring(7);
            stepWithRole("Телефон: " + formattedPhone, () ->
                    phoneLocator.shouldBe(visible).shouldHave(value(formattedPhone))
            );
            stepWithRole("Кнопка Сохранить неактивна", () ->
                    saveButtonLocator.shouldBe(disabled)
            );
           toOrderContextButtons.checkNoToOrderContextButtonsPresenceState();
        });
    }

    public void checkFirsOfferEvaluatedSEInitialState(String email, String phone) {
        stepWithRole("Убедиться, что вкладка Контакты заполнена: ", () -> {
            subtitleLocator.shouldHave(visible, text("Контактные данные"));
            stepWithRole("Email: " + email, () ->
                emailLocator.shouldBe(visible).shouldHave(value(email))
            );
            String formattedPhone = "+7(" + phone.substring(1, 4) + ")-" + phone.substring(4, 7) + "-" + phone.substring(7);
            stepWithRole("Телефон: " + formattedPhone, () ->
                phoneLocator.shouldBe(visible).shouldHave(value(formattedPhone))
            );
            stepWithRole("Кнопка Сохранить неактивна", () ->
                saveButtonLocator.shouldBe(disabled)
            );
            toOrderContextButtons.checkToOrderContextButtonsPresenceState();
        });
    }
}
