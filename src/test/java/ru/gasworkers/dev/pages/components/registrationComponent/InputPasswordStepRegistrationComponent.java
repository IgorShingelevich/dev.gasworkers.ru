package ru.gasworkers.dev.pages.components.registrationComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;

public class InputPasswordStepRegistrationComponent extends BaseComponent {
    public final HeaderRegistrationComponent header;

    public final StepNumberRegistrationComponent stepNumber;

    public InputPasswordStepRegistrationComponent(RoleBrowser browser) {
        super(browser);
        header = new HeaderRegistrationComponent(browser);
        stepNumber = new StepNumberRegistrationComponent(browser);
    }

    private final String
            titleText = "Придумайте себе пароль",
            passwordDescriptionText = "Пароль должен содержать не менее 4 знаков",
            passwordHintText = "Минимум 4 символа";

    ElementsCollection
            strongPasswordSubtitleCollection = driver.$$("p.small").as("Подзаголовок надежного пароля"),
            eyeIconCollection = driver.$$("div.eye-icon").as("Коллекция иконок отображения пароля");

    SelenideElement
            titleLocator = driver.$("div h4").as("Заголовок"),
            generatePasswordButtonLocator = driver.$("div button.lock-icon.btn.btn-link.disable-outline").as("Кнопка генерации кода"),
            passwordInputLocator = driver.$("input[placeholder*=Пароль]").as("Поле ввода пароля"),
            confirmPasswordInputLocator = driver.$("input[placeholder*=Подтвердить]").as("Поле ввода подтверждения пароля"),
            suggestedPasswordLocator = driver.$("input[readonly=readonly]").as("Предложенный пароль"),
            passwordHintLocator = driver.$("div span.text").as("Подсказка к паролю"),
            errorMessageLocator = driver.$("div.gas-input__error").as("Сообщение об ошибке"),
            forwardButtonLocator = driver.$("div button.btn.btn-primary").as("Кнопка перехода к следующему шагу"),
            backButtonLocator = driver.$("div button.btn.btn-outline-primary").as("Кнопка перехода к предыдущему шагу");

    public void checkFinishLoading(Integer stepCount) {
        stepWithRole("Убедиться, что представлены компоненты  шага регистрации ввод пароля: ", () -> {
            stepNumber.checkStepNumber(stepCount);
            header.checkFinishLoading();
            stepWithRole("Убедиться, что отображается заголовок: " + titleText, () -> {
                titleLocator.shouldHave(text(titleText));
            });
            stepWithRole("Убедиться, что отображается подсказка к паролю: " + passwordHintText, () -> {
                passwordHintLocator.shouldHave(text(passwordHintText)).as("Подсказка к паролю");
            });
            stepWithRole("Убедиться, что отображается плейсхолдер в поле ввода пароля", () -> {
                passwordInputLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что отображается плейсхолдер в поле ввода подтверждения пароля", () -> {
                confirmPasswordInputLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что отображается кнопка Сгенерировать надежный пароль", () -> {
                generatePasswordButtonLocator.shouldBe(visible).shouldHave(text("Сгенерировать надежный пароль"));
            });
            stepWithRole("Убедиться, что отображается описание надежного пароля: " + passwordDescriptionText, () -> {
                strongPasswordSubtitleCollection.get(0).shouldHave(text(passwordDescriptionText));
            });
            stepWithRole("Убедиться, что отображается активная кнопка Далее", () -> {
                forwardButtonLocator.shouldHave(text("Далее")).shouldBe(enabled);
            });
            stepWithRole("Убедиться, что отображается кнопка Назад", () -> {
                backButtonLocator.shouldHave(text("Назад"));
            });
        });
    }

    public void fillPassword(String password) {
        stepWithRole("Ввести пароль: " + password, () -> {
            //TODO
            passwordInputLocator.setValue(password);
            System.out.println("password: " + password);
        });
    }

    public void fillPasswordConfirmation(String password) {
        stepWithRole("Ввести подтверждение пароля: " + password, () -> {
            //TODO
            confirmPasswordInputLocator.setValue(password);
        });
    }

    public void checkInvalidPasswordNotification() {
        stepWithRole("Убедиться, что при несоответствии пароля и подтверждения пароля отображается уведомление: " + errorMessageLocator.getText(), () -> {
            errorMessageLocator.shouldHave(text("Пароли не совпадают"));
        });
    }

    public void generatePassword() {
        stepWithRole("Сгенерировать пароль", () -> {
            stepWithRole("Нажать на кнопку Сгенерировать пароль", () -> {
                generatePasswordButtonLocator.click();
            });
            stepWithRole("Убедиться, что сгенерирован Надежный  пароль: " + suggestedPasswordLocator.val().toString(), () -> {
                suggestedPasswordLocator.shouldBe(visible);
                System.out.println("suggestedPassword: " + suggestedPasswordLocator.val().toString());
            });
            stepWithRole("Убедиться что появился подзаголовок поля Надежный пароль " + strongPasswordSubtitleCollection.get(0).getText(), () -> {
                strongPasswordSubtitleCollection.get(0).as("подзаголовок поля Надежный пароль").shouldHave(text("Мы сгенерировали вам пароль"));
            });
            stepWithRole("Убедиться что появилось описание поля Надежный пароль " + strongPasswordSubtitleCollection.get(1).getText(), () -> {
                strongPasswordSubtitleCollection.get(1).as("описание поля Надежный пароль").shouldHave(text("Пароль должен содержать не менее 4 знаков"));
            });
            stepWithRole("Убедиться, что поле Пароль и Подтверждение пароля заполнены сгенерированным паролем: " + passwordInputLocator.val().toString(), () -> {
                stepWithRole("Нажать на иконку Показать  скрытый пароль в поле Пароль и Подтверждение Пароля", () -> {
                    stepWithRole("Убедиться, что иконки переходят из состояния Скрытый пароль в состояние Пароль Отображается", () -> {
                        eyeIconCollection.get(0).shouldNotHave(cssClass("visible")).click();
                        eyeIconCollection.get(0).shouldHave(cssClass("visible"));
                        eyeIconCollection.get(1).shouldNotHave(cssClass("visible")).click();
                        eyeIconCollection.get(1).shouldHave(cssClass("visible"));
                    });
                });
                stepWithRole("Убедиться, что в поле Пароль и Подтверждение Пароля отображается сгенерированный пароль: " + passwordInputLocator.val().toString(), () -> {
                    passwordInputLocator.shouldNotBe(empty);
                    confirmPasswordInputLocator.shouldNotBe(empty);
                });
                stepWithRole("Нажать на иконку Скрыть пароль в поле Пароль и Подтверждение Пароля", () -> {
                    eyeIconCollection.get(0).shouldHave(cssClass("visible")).click();
                    eyeIconCollection.get(0).shouldNotHave(cssClass("visible"));
                    eyeIconCollection.get(1).shouldHave(cssClass("visible")).click();
                    eyeIconCollection.get(1).shouldNotHave(cssClass("visible"));
                });
            });
        });
    }

    public void setPassword (String password) {
        stepWithRole("Установить пароль: " + password, () -> {
            fillPassword(password);
            fillPasswordConfirmation(password);
        });
    }

    public void forwardButton () {
        stepWithRole("Нажать на кнопку Далее", () -> {
            forwardButtonLocator.click();
            errorMessageLocator.shouldNotBe(visible);
        });
    }

    public void backButton () {
        stepWithRole("Нажать на кнопку Назад", () -> {
            backButtonLocator.click();
        });
    }

}
