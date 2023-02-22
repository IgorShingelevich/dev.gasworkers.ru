package ru.gasworkers.dev.pages.components.clientComponent.registrationStepClientComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.checked;

public class FirstStepRegistrationClientComponent extends BaseComponent {
    public FirstStepRegistrationClientComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
            FIRST_TITLE = "Регистрация пользователя",
            ALREADY_REGISTERED_TEXT = "Уже зарегистрированы?",
            FIRST_SUBTITLE = "Для регистрации",
            CHECKBOX_TEXT = "Я даю согласие на обработку персональных данных и принимаю условия";


    ElementsCollection
            stepsCollection = driver.$$("div.stage").as("Коллекция шагов регистрации");
    SelenideElement
            titleLocator = driver.$("div h4").as("Заголовок"),
            subtitleLocator = driver.$("div.description").as("Подзаголовок"),
            alreadyRegisteredLocator = driver.$("a.link-gray").as("Ссылка на страницу входа зарегистрированного пользователя"),
            inputPhoneLocator = driver.$("input[placeholder*=Номер]").as("Поле ввода номера телефона"),
            inputEmailLocator = driver.$("input[placeholder*=почта]").as("Поле ввода электронной почты"),
            checkboxLocator = driver.$("div input[type=checkbox]").as("Чекбокс"),
            userAgreementLinkLocator = driver.$("a[href*=user_agreement]").as("Ссылка на пользовательское соглашение"),
            forwardButtonLocator = driver.$("div button.btn.btn-primary").as("Кнопка перехода к следующему шагу"),
            errorMessageLocator = driver.$("div.gas-input__error").as("Сообщение об ошибке"),
            backButtonLocator = driver.$("div button.btn.btn-outline-primary").as("Кнопка перехода к предыдущему шагу");

    public void checkFirstStepFinishLoading () {
        stepWithRole("Убедиться, что представлены компоненты первого шага регистрации: " , () -> {
            stepWithRole("Убедиться, что на таймлайне выделен первый шаг" , () -> {
                stepsCollection.get(0).shouldHave(cssClass("active"));
            });
            stepWithRole("Убедиться, что отображается ссылка на страницу входа зарегистрированного пользователя: " + ALREADY_REGISTERED_TEXT , () -> {
                alreadyRegisteredLocator.shouldHave(text(ALREADY_REGISTERED_TEXT));
            });
            stepWithRole("Убедиться, что отображается заголовок: " + FIRST_TITLE , () -> {
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
        String formatPhoneNumber ="+ " + phone.substring(0, 1) + "(" + phone.substring(1, 4) + ")-" + phone.substring(4, 7) + "-" + phone.substring(7, 11);
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
