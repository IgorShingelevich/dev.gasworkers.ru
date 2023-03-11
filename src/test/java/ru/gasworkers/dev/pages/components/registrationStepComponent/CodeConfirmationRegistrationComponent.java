package ru.gasworkers.dev.pages.components.registrationStepComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;

public class CodeConfirmationRegistrationComponent extends BaseComponent {

    public CodeConfirmationRegistrationComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
       titleText = "Введите код подтверждения",
       subtitleText = "Введите код подтверждения из SMS или письма пришедшего к вам на электронную почту. Пожалуйста, проверьте папку СПАМ в почте",
        newCodeSendPeriodText = "Повторный код можно выслать через",
        requestNewCodeText = "Запросить повторно",
        requestNewCodeSendLinkText = "код подтверждения",
        errorMessageText = "Неверный код проверки";

    ElementsCollection
            codeInputCollection = driver.$$("form.code-input input").as("Поля ввода кода");

    SelenideElement
        titleLocator = driver.$("div h4").as("Заголовок"),
        subtitleLocator = driver.$("div.description").as("Подзаголовок"),
        newCodeSendPeriodTextLocator = driver.$("p.small.text-secondary").as("Текст отправки нового кода"),
        requestNewCodeTextLocator = driver.$("div.description a").as("Текст повторная отправка кода"),
        requestNewCodeSendLinkLocator = driver.$("div.description a").as("Ссылка повторная отправка кода"),
        codeInputFirstDigitLocator = codeInputCollection.get(0).as("Первый символ кода"),
        errorMessageLocator = driver.$("div.gas-input__error").as("Ошибка ввода кода"),
        forwardButtonLocator = driver.$("div button.btn.btn-primary").as("Кнопка перехода к следующему шагу"),
        backButtonLocator = driver.$("div button.btn.btn-outline-primary").as("Кнопка перехода к предыдущему шагу");

    public void checkFinishLoading () {
        stepWithRole("Убедиться, что отображаются все элементы компонента ввода кода подтверждения: " , () -> {
            stepWithRole("Убедиться, что отображается заголовок", () -> {
                titleLocator.shouldHave(text(titleText));
            });
            stepWithRole("Убедиться, что отображается подзаголовок", () -> {
                subtitleLocator.shouldHave(text(subtitleText));
            });
            stepWithRole("Убедиться, что поля ввода кода подтверждения отображаются", () -> {
                codeInputCollection.shouldHave(size(6));
            });
            stepWithRole("Убедиться что поля ввода кода подтверждения не заполнены", () -> {
                for (SelenideElement codeInput : codeInputCollection) {
                    codeInput.shouldBe(empty);
                }
            });
            stepWithRole("Убедиться, что отображается текст о том, что новый код можно выслать через", () -> {
                newCodeSendPeriodTextLocator.shouldHave(partialText(newCodeSendPeriodText));
            });
            stepWithRole("Убедиться, что  текст запросить повторно код подтверждения не отображается", () -> {
                requestNewCodeTextLocator.shouldNotBe(visible);
            });
            stepWithRole("Убедиться, что  ссылка запросить повторно код подтверждения не отображается", () -> {
                requestNewCodeSendLinkLocator.shouldNotBe(visible);
            });
            stepWithRole("Убедиться, что  кнопка перехода к следующему шагу не активна", () -> {
                forwardButtonLocator.shouldHave(text("Далее")).shouldHave(attribute("disabled", "true"));
            });
            stepWithRole("Убедиться, что  кнопка перехода к предыдущему шагу отображается", () -> {
                backButtonLocator.shouldHave(text("Назад"));
            });
        });
    }

    public void fillCode (String code) {
       stepWithRole("Ввести код подтверждения: " + code, () -> {
            stepWithRole("Заполнить поля ввода кода подтверждения", () -> {
                for (int i = 0; i < code.length(); i++) {
                    codeInputCollection.get(i).setValue(String.valueOf(code.charAt(i)));
                }
            });
            stepWithRole("Убедиться, что кнопка перехода к следующему шагу активна", () -> {
                forwardButtonLocator.shouldHave(text("Далее")).shouldNotHave(attribute("disabled", "true"));
            });
            stepWithRole("Убедиться, что нет ошибки ввода кода", () -> {
                errorMessageLocator.shouldNotBe(visible);
            });
       });
    }



}
