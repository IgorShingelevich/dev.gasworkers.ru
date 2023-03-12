package ru.gasworkers.dev.pages.components.registrationComponent.selfEmployedRegistrationStepComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.registrationComponent.HeaderRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationComponent.StepNumberRegistrationComponent;

import static com.codeborne.selenide.Condition.*;

public class FifthStepSelfEmployedRegistrationComponent extends BaseComponent {
    public final HeaderRegistrationComponent header;
    public final StepNumberRegistrationComponent stepNumber;

    public FifthStepSelfEmployedRegistrationComponent(RoleBrowser browser) {
        super(browser);
        header = new HeaderRegistrationComponent(browser);
        stepNumber = new StepNumberRegistrationComponent(browser);
    }

    private final String
            title = "Персональные данные",
            emailErrorText = "Поле E-Mail должно быть действительным электронным адресом.";

    SelenideElement
            titleLocator = driver.$("div h4").as("Заголовок"),
            inputFieldLocator = driver.$("div input").as("Поле ввода"),
            errorTextLocator = driver.$("div.gas-input__error").as("Текст ошибки"),
            forwardButtonLocator = driver.$("button.btn.btn-primary").as("Кнопка перехода к следующему шагу"),
            backButtonLocator = driver.$("button.btn.btn-outline-primary").as("Кнопка перехода к предыдущему шагу");

    public void checkFinishLoading(Integer stepCount) {
        stepWithRole("Убедиться, что представлены компоненты " +  stepCount + " шага регистрации", () -> {
            stepNumber.checkStepNumber(stepCount);
            header.checkFinishLoading();
            stepWithRole("Убедиться, что отображается заголовок: " + title, () -> {
                titleLocator.shouldHave(text(title));
            });
            stepWithRole("Убедиться, что отображается поле ввода", () -> {
                inputFieldLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что  кнопка перехода к следующему шагу неактивна", () -> {
                forwardButtonLocator.shouldBe(disabled);
            });
            stepWithRole("Убедиться, что отображается кнопка перехода к предыдущему шагу", () -> {
                backButtonLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что текст ошибки не отображается", () -> {
                errorTextLocator.shouldNotBe(visible);
            });
        });
    }

    public void setEmail(String email) {
        stepWithRole("Ввести email: " + email, () -> {
            inputFieldLocator.shouldHave(attribute("placeholder", "Электронная почта")).setValue(email);
        });
        System.out.println("email: " + email);
    }

    public void setPhone(String phone) {
        stepWithRole("Ввести телефон: " + phone, () -> {
            inputFieldLocator.shouldHave(attribute("placeholder", "Номер телефона*")).setValue(phone);
        });
        System.out.println("phone: " + phone);
    }

    public void forwardButton () {
        stepWithRole("Нажать кнопку перехода к следующему шагу", () -> {
            forwardButtonLocator.click();
        });
    }

}
//todo error handling