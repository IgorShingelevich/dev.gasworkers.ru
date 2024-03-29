package ru.gasworkers.dev.pages.components.registrationComponent.clientRegistrationStepComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.registrationComponent.HeaderRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationComponent.StepNumberRegistrationComponent;

import static com.codeborne.selenide.Condition.*;

public class FirstStepClientRegistrationComponent extends BaseComponent {
    public final HeaderRegistrationComponent header;

    public final StepNumberRegistrationComponent stepNumber;

    public FirstStepClientRegistrationComponent(RoleBrowser browser) {
        super(browser);
        header = new HeaderRegistrationComponent(browser);
        stepNumber = new StepNumberRegistrationComponent(browser);
    }

    private final String
            FIRST_TITLE = "Регистрация пользователя",
            FIRST_SUBTITLE = "Для регистрации",
            CHECKBOX_TEXT = "Я даю согласие на обработку персональных данных и принимаю условия";

    SelenideElement
            titleLocator = driver.$("div h4").as("Заголовок"),
            subtitleLocator = driver.$("div.description").as("Подзаголовок"),
            alreadyRegisteredLocator = driver.$("a.link-gray").as("Ссылка на страницу входа зарегистрированного пользователя"),
            inputPhoneLocator = driver.$("input[placeholder*=Номер]").as("Поле ввода номера телефона"),
            inputEmailLocator = driver.$("input[placeholder*=почта]").as("Поле ввода электронной почты"),
            checkboxLocator = driver.$("div input[type=checkbox]").as("Чекбокс"),
            userAgreementLinkLocator = driver.$("a.link-blue").as("Ссылка на пользовательское соглашение"),
            forwardButtonLocator = driver.$("div button.btn.btn-primary").as("Кнопка перехода к следующему шагу"),
            errorMessageLocator = driver.$("div.gas-input__error").as("Сообщение об ошибке"),
            backButtonLocator = driver.$("div button.btn.btn-outline-primary").as("Кнопка перехода к предыдущему шагу");

    public void checkFirstStepFinishLoading() {
        stepWithRole("Убедиться, что представлены компоненты первого шага регистрации: ", () -> {
            stepNumber.checkStepNumber(1);
            header.checkFinishLoading();
            stepWithRole("Убедиться, что отображается заголовок: " + FIRST_TITLE, () -> {
                titleLocator.shouldHave(text(FIRST_TITLE));
            });
            stepWithRole("Убедиться, что отображается описание: " + FIRST_SUBTITLE , () -> {
                subtitleLocator.shouldHave(text(FIRST_SUBTITLE));
            });
            stepWithRole("Убедиться, что отображается плейсхолдер в поле ввода номера телефона" , () -> {
                inputPhoneLocator.shouldBe(visible);
                // TODO placeholder
            });
            stepWithRole("Убедиться, что отображается плейсхолдер в поле ввода электронной почты" , () -> {
                inputEmailLocator.shouldBe(visible);
                // TODO placeholder
            });
            stepWithRole("Убедиться, что чекбокс не отмечен" , () -> {
                checkboxLocator.shouldNotBe(checked);
            });
            stepWithRole("Убедиться, что отображается текст чекбокса: " + CHECKBOX_TEXT , () -> {
                driver.$$("div span").get(5).shouldHave(text(CHECKBOX_TEXT));
            });
            stepWithRole("Убедиться, что отображается ссылка на пользовательское соглашение" , () -> {
                userAgreementLinkLocator.shouldHave(text("Пользовательского соглашения"));
            });
            stepWithRole("Убедиться, что отображается активная кнопка Далее" , () -> {
                forwardButtonLocator.shouldHave(text("Далее")).shouldBe(enabled);
            });
            stepWithRole("Убедиться, что отображается кнопка Отменить" , () -> {
                backButtonLocator.shouldHave(text("Отменить"));
            });

        });
    }

    public void checkboxNotCheckedCState () {
        stepWithRole("Убедиться, что чекбокс не отмечен" , () -> {
            checkboxLocator.shouldNotBe(checked);
        });
    }
    public void clickCheckbox () {
        stepWithRole("Нажать на чекбокс" , () -> {
            checkboxLocator.click();
        });
    }
    public void checkboxCheckedCState () {
        stepWithRole("Убедиться, что чекбокс отмечен" , () -> {
            checkboxLocator.shouldBe(checked);
        });
    }

    public void byPhone (String phone) {
        String formatPhoneNumber ="+ " + phone.charAt(0) + "(" + phone.substring(1, 4) + ")-" + phone.substring(4, 7) + "-" + phone.substring(7, 11);
        stepWithRole("Ввести номер телефона: " + formatPhoneNumber , () -> {
            inputPhoneLocator.setValue(phone);
            System.out.println("phone: " + formatPhoneNumber + " " + phone);
        });
    }
    public void byWrongFormatPhone (String phone) {
        stepWithRole("Ввести невалидный номер телефона: " + phone , () -> {
            inputPhoneLocator.setValue(phone);
            System.out.println("phone: " + phone);
        });
    }

    public void byEmail (String email) {
        stepWithRole("Ввести электронную почту: " + email, () -> {
            inputEmailLocator.setValue(email);
            System.out.println("email: " + email);
        });
    }

    public void checkInvalidEmailError(String invalidEmail, String errorText) {
        stepWithRole("Убедиться, что при вводе некорректного email: " + invalidEmail + " отображается ошибка: " + errorText , () -> {
            errorMessageLocator.shouldHave(text(errorText));
        });
    }

    public void checkInvalidPhoneNumberError(String invalidPhoneNumber, String errorText) {
        stepWithRole("Убедиться, что при вводе некорректного номера телефона: " + invalidPhoneNumber + " отображается ошибка: " + errorText , () -> {
            errorMessageLocator.shouldHave(text(errorText));
        });
    }

}
