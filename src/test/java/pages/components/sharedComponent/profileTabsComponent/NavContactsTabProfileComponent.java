package pages.components.sharedComponent.profileTabsComponent;

import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

public class NavContactsTabProfileComponent extends BaseComponent {
    public NavContactsTabProfileComponent(RoleBrowser browser) {
        super(browser);
    }
    SelenideElement
    subtitleLocator = driver.$("div .title").as("Подзаголовок Контактные данные"),
    emailLocator = driver.$("input[placeholder*=почта]").as("Email"),
    phoneLocator = driver.$("input[placeholder*=Номер]").as("Телефон"),
    saveButtonLocator = driver.$("button.mb-3.btn.btn-primary.disable-outline").as("Кнопка Сохранить");

    public void checkFinishLoading(String email, String phone) {
        stepWithRole("Убедиться, что вкладка Контакты загружена", () -> {
            subtitleLocator.shouldHave(visible, text("Контактные данные"));
            emailLocator.shouldBe(visible);
            phoneLocator.shouldBe(visible);
            saveButtonLocator.shouldHave(visible, text("Сохранить"));
        });
        stepWithRole("Убедиться, что поля Email и Телефон содержат регистрационные данные", () -> {
            stepWithRole("Email: " + email, () -> {
                emailLocator.shouldHave(value(email));
            });
            String formattedPhone = "+7(" + phone.toString().substring(1, 4) + ")-" + phone.toString().substring(4, 7) + "-" + phone.toString().substring(7);
            stepWithRole("Телефон: " + formattedPhone, () ->{
                phoneLocator.shouldHave(value(formattedPhone));
             });
        });
        stepWithRole("Убедиться, что кнопка Сохранить неактивна", () -> {
            saveButtonLocator.shouldBe(disabled);
        });
    }

    public void checkFilledStatus(String email, String phone) {
        stepWithRole("Убедиться, что вкладка Контакты заполнена: ", () -> {
            subtitleLocator.shouldHave(visible, text("Контактные данные"));
            stepWithRole("Email: " + email, () ->
                emailLocator.shouldBe(visible).shouldHave(value(email))
            );
            String formattedPhone = "+7(" + phone.toString().substring(1, 4) + ")-" + phone.toString().substring(4, 7) + "-" + phone.toString().substring(7);
            stepWithRole("Телефон: " + formattedPhone, () ->
                phoneLocator.shouldBe(visible).shouldHave(value(formattedPhone))
            );
            stepWithRole("Кнопка Сохранить неактивна", () ->
                saveButtonLocator.shouldBe(disabled)
            );
        });
    }
}
