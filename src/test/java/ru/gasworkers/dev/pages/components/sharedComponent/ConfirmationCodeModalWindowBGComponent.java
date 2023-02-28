package ru.gasworkers.dev.pages.components.sharedComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ConfirmationCodeModalWindowBGComponent extends BaseComponent {
    public ConfirmationCodeModalWindowBGComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
        modalTitle = "Подтвердите номер телефона",
        modalText = "Введите код подтверждения из СМС/письма пришедшего к вам на почту. Пожалуйста, проверьте папку СПАМ, если письмо с кодом подтверждения долго не приходит. После подтверждения номера заказ разместиться на платформе и будет виден сервисным компаниям.";
    SelenideElement
        modalWindowLocator = driver.$("div.modal-wrapper").as("Модальное окно"),
        modalTitleLocator = driver.$("div.gas-sign h4").as("Заголовок модального окна"),
        modalTextLocator = driver.$("div.description.small.mb-20").as("Текст модального окна"),
        sendAgainActiveLinkLocator = driver.$("a.link-dark-blue").as("Ссылка на повторную отправку кода");


    ElementsCollection
            confirmationCodeCollection = driver.$$(".code-input  input").as("Коллекция цифр кода подтверждения");


    public void fillCode (String code) {
        stepWithRole("Ввести код подтверждения: " + code, () -> {
            modalWindowLocator.shouldBe(visible);
            modalTitleLocator.shouldHave(text(modalTitle));
            modalTextLocator.shouldHave(text(modalText));
            confirmationCodeCollection.get(0).setValue(code);
            System.out.println("code: " + code);
        });
    }

    public void sendAgain () {
        sendAgainActiveLinkLocator.shouldHave(text("код подтверждения")).click();
        driver.$(".small.text-secondary").shouldHave(text("Повторный код можно выслать через"));

    }



}
