package ru.gasworkers.dev.pages.components.sharedComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;

public class RegularRegistrationConfirmationCodeComponent extends BaseComponent {
    public RegularRegistrationConfirmationCodeComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
            errorText = "Неверный код для подписи";

    SelenideElement
            errorLocator = driver.$("div.gas-input__error").as("Ошибка"),
            sendAgainActiveLinkLocator = driver.$("a.link-dark-blue").as("Ссылка на повторную отправку кода");
    ElementsCollection
            confirmationCodeCollection = driver.$$(".code-input  input").as("Коллекция цифр кода подтверждения");

    public void fillCode(String code) {
        stepWithRole("Ввести код подтверждения: " + code, () -> {
            confirmationCodeCollection.get(0).setValue(code);
            System.out.println("code: " + code);
        });
    }

    public void sendAgain() {
        stepWithRole("Повторно отправить код подтверждения", () -> {
            sendAgainActiveLinkLocator.shouldHave(text("код подтверждения")).click();
        });
    }

    public void checkError() {
        stepWithRole("Проверить ошибку", () -> {
            confirmationCodeCollection.get(0).setValue("123456");
            errorLocator.shouldHave(text(errorText));
            //todo resend time counter
        });
    }

}

