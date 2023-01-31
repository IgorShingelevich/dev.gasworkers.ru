package pages.components.sharedComponent.profileTabsComponent;

import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

public class ContactsTabProfileComponent extends BaseComponent {
    public ContactsTabProfileComponent(RoleBrowser browser) {
        super(browser);
    }
    SelenideElement
    subtitleLocator = $("div .title").as("Подзаголовок Контактные данные"),
    emailLocator = $("input[placeholder*=почта]").as("Email"),
    phoneLocator = $("input[placeholder*=номер]").as("Телефон"),
    saveButtonLocator = $(byTagAndText("button", "Сохранить")).as("Кнопка Сохранить");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что вкладка Контакты загружена", () -> {
            subtitleLocator.shouldHave(visible, text("Контактные данные"));
            emailLocator.shouldBe(visible);
            phoneLocator.shouldBe(visible);
            saveButtonLocator.shouldBe(visible);
        });
    }

    public void checkFilledStatus(String email, String phone) {
        stepWithRole("Убедиться, что вкладка Контакты заполнена: ", () -> {
            subtitleLocator.shouldHave(visible, text("Контактные данные"));
            stepWithRole("Email: " + email, () ->
                emailLocator.shouldBe(visible).shouldHave(value(email))
            );
            stepWithRole("Телефон: " + phone, () ->
                phoneLocator.shouldBe(visible).shouldHave(value(phone))
            );
            stepWithRole("Кнопка Сохранить неактивна", () ->
                saveButtonLocator.shouldBe(disabled)
            );
        });
    }
}
