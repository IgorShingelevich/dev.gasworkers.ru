package ru.gasworkers.dev.pages.components.sharedComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;

public class CommonCodeInputModalWindowComponent extends BaseComponent {
    public CommonCodeInputModalWindowComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String TITLE = "Подтвердите номер телефона";
    private final String SUBTITLE = "Введите код подтверждения из СМС/письма пришедшего к вам на почту. Пожалуйста, проверьте папку СПАМ, если письмо с кодом подтверждения долго не приходит. После этого заказ разместится на платформе и будет виден сервисным компаниям.";
//    private final String SEND_AGAIN_TEXT = "Запросить повторно"; // cooldown 1 min

    SelenideElement
        titleLocator = driver.$("div.gas-sign h4").as("Заголовок модального окна"),
        subtitleLocator = driver.$("div.description.small.mb-20").as("Подзаголовок модального окна"),
        sendAgainTextLocator = driver.$("div.text-left.mb-3").as("Текст Запросить повторно"),
        sendAgainLinkLocator = driver.$("a[href='#']").as("Ссылка Запросить повторно"),
        submitButtonLocator = driver.$("button.btn.btn-primary.disable-outline").as("Кнопка Подтвердить"),
        closeButtonLocator = driver.$("div.close-btn").as("Кнопка Закрыть");

    ElementsCollection
        codeInputCollectionLocator = driver.$$("div.code-input input").as("Поля ввода кода");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что модальное окно ввода кода загружено", () -> {
            titleLocator.shouldHave(text(TITLE));
            subtitleLocator.shouldHave(text(SUBTITLE));
//            sendAgainTextLocator.shouldHave(text(SEND_AGAIN_TEXT)); // cooldown 1 min
//            sendAgainLinkLocator.shouldBe(visible);
            stepWithRole("Проверить, что кнопка Подтвердить видима", () -> {
                submitButtonLocator.shouldBe(hidden); // TODO disabled disabled="disabled"
            });
            codeInputCollectionLocator.shouldHave(size(6));
            closeButtonLocator.shouldBe(visible);
        });
    }

    public void sendCode (String code) {
        stepWithRole("Ввести код подтверждения", () -> {
                codeInputCollectionLocator.get(0).setValue(code);
        });
    }

}
