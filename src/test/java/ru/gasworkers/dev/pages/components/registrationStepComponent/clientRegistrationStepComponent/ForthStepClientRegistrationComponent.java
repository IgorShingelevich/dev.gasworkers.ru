package ru.gasworkers.dev.pages.components.registrationStepComponent.clientRegistrationStepComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.registrationStepComponent.HeaderRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationStepComponent.StepNumberRegistrationComponent;

import static com.codeborne.selenide.Condition.*;

public class ForthStepClientRegistrationComponent extends BaseComponent {
    public final HeaderRegistrationComponent header;
    public final StepNumberRegistrationComponent stepNumber;

    public ForthStepClientRegistrationComponent(RoleBrowser browser) {
        super(browser);
        header = new HeaderRegistrationComponent(browser);
        stepNumber = new StepNumberRegistrationComponent(browser);
    }

    private final String
            FIRST_TITLE = "Регистрация пользователя",
            ALREADY_REGISTERED_TEXT = "Уже зарегистрированы?",
            FIRST_SUBTITLE = "Для регистрации",
            CHECKBOX_TEXT = "Я даю согласие на обработку персональных данных и принимаю условия",
            SECOND_TITLE = "Введите код подтверждения",
            SECOND_SUBTITLE = "Введите код подтверждения из SMS или письма пришедшего к вам на электронную почту. Пожалуйста, проверьте папку СПАМ в почте",
            REPEAT_TEXT = "Запросить повторно",
            THIRD_TITLE = "Придумайте себе пароль",
            PASSWORD_HINT = "Минимум 4 символа",
            FOURTH_TITLE = "Заполните личные данные";

    ElementsCollection
            stepsCollection = driver.$$("div.stage").as("Коллекция шагов регистрации"),
            eyeIconCollection = driver.$$("div.eye-icon").as("Коллекция иконок отображения пароля");
    SelenideElement
            titleLocator = driver.$("div h4").as("Заголовок"),
            subtitleLocator = driver.$("div.description").as("Подзаголовок"),
            alreadyRegisteredLocator = driver.$("a.link-gray").as("Ссылка на страницу входа зарегистрированного пользователя"),
            inputPhoneLocator = driver.$("input[placeholder*=Номер]").as("Поле ввода номера телефона"),
            inputEmailLocator = driver.$("input[placeholder*=почта]").as("Поле ввода электронной почты"),
            checkboxLocator = driver.$("div input[type=checkbox]").as("Чекбокс"),
            userAgreementLinkLocator = driver.$("a[href*=user_agreement]").as("Ссылка на пользовательское соглашение"),
            generatePasswordButtonLocator = driver.$("div button.lock-icon.btn.btn-link.disable-outline").as("Кнопка генерации кода"),
            passwordInputLocator = driver.$("input[placeholder*=Пароль]").as("Поле ввода пароля"),
            confirmPasswordInputLocator = driver.$("input[placeholder*=Подтвердить]").as("Поле ввода подтверждения пароля"),
            suggestedPasswordLocator = driver.$("input[readonly=readonly]").as("Предложенный пароль"),
            errorMessageLocator = driver.$("div.gas-input__error").as("Сообщение об ошибке"),
            forwardButtonLocator = driver.$("div button.btn.btn-primary").as("Кнопка перехода к следующему шагу"),
            backButtonLocator = driver.$("div button.btn.btn-outline-primary").as("Кнопка перехода к предыдущему шагу");


    public void checkFourthStepByPhoneFinishLoading(String phone) {
        // turn 77778711855 to "+7(777)-871-1855"
        String formatPhoneNumber = phone.substring(0, 1) + "(" + phone.substring(1, 4) + ")-" + phone.substring(4, 7) + "-" + phone.substring(7, 11);
        stepWithRole("Убедиться, что представлены компоненты четвертого шага регистрации: ", () -> {
            header.checkFinishLoading();
            stepNumber.checkStepNumber(4);
            stepWithRole("Убедиться, что отображается заголовок: " + FOURTH_TITLE, () -> {
                titleLocator.shouldHave(text(FOURTH_TITLE));
            });
            stepWithRole("Убедиться, что отображается описание: ", () -> {
                stepWithRole("Подзаголовок: " + driver.$("div p.small.mb-20").getText(), () -> {
                    driver.$("div p.small.mb-20").shouldHave(text("Общие данные")).as("Общие данные");
                });
                stepWithRole("Плейсхолдер ввода имени", () -> {
                    driver.$("input[placeholder*=Имя]").shouldBe(visible).as("Имя");
                });
                stepWithRole("Плейсхолдер ввода фамилии", () -> {
                    driver.$("input[placeholder*=Фамилия]").shouldBe(visible).as("Фамилия");
                });
                stepWithRole("Плейсхолдер ввода отчества", () -> {
                    driver.$("input[placeholder*=Отчество]").shouldBe(visible).as("Отчество");
                });
                //removed for business reason
                /*stepWithRole("Подзаголовок и телефон: " + formatPhoneNumber , () -> {
                    driver.$("div.d-flex.justify-content-between.mb-20").$$("div").get(0).shouldHave(text("Ваш номер телефона")).as("Ваш номер телефона");
                    driver.$("div.d-flex.justify-content-between.mb-20").$$("div").get(1).shouldHave(text(formatPhoneNumber)).as("formatPhoneNumber");
                });*/
                stepWithRole("Плейсхолдер ввода Электронная почта", () -> {
                    driver.$("input[placeholder*=почта]").shouldBe(visible).as("Электронная почта");
                });
            });
            stepWithRole("Убедиться, что отображается неактивная кнопка Далее", () -> {
                forwardButtonLocator.shouldHave(text("Далее")).shouldHave(attribute("disabled"));
            });
            stepWithRole("Убедиться, что отображается кнопка Назад", () -> {
                backButtonLocator.shouldHave(text("Назад"));
            });
        });
    }

    public void checkFourthStepByEmailFinishLoading(String email) {
        // turn 77778711855 to "+7(777)-871-1855"
        stepWithRole("Убедиться, что представлены компоненты четвертого шага регистрации: ", () -> {
            stepWithRole("Убедиться, что на таймлайне выделен четвертый шаг", () -> {
                stepsCollection.get(3).shouldHave(cssClass("active"));
            });
            stepWithRole("Убедиться, что отображается заголовок: " + FOURTH_TITLE, () -> {
                titleLocator.shouldHave(text(FOURTH_TITLE));
            });
            stepWithRole("Убедиться, что отображается описание: ", () -> {
                stepWithRole("Подзаголовок: " + driver.$("div p.small.mb-20").getText(), () -> {
                    driver.$("div p.small.mb-20").shouldHave(text("Общие данные")).as("Общие данные");
                });
                stepWithRole("Плейсхолдер ввода имени", () -> {
                    driver.$("input[placeholder*=Имя]").shouldBe(visible).as("Имя");
                });
                stepWithRole("Плейсхолдер ввода фамилии", () -> {
                    driver.$("input[placeholder*=Фамилия]").shouldBe(visible).as("Фамилия");
                });
                stepWithRole("Плейсхолдер ввода отчества", () -> {
                    driver.$("input[placeholder*=Отчество]").shouldBe(visible).as("Отчество");
                });
                //removed for business reason
                /*stepWithRole("Подзаголовок и почта: " + email , () -> {
                    driver.$("div.d-flex.justify-content-between.mb-20").$$("div").get(0).shouldHave(text("Электронная почта")).as("Электронная почта");
                    driver.$("div.d-flex.justify-content-between.mb-20").$$("div").get(1).shouldHave(text(email)).as("email");
                });*/
                stepWithRole("Плейсхолдер ввода Номер телефона", () -> {
                    driver.$("input[placeholder*=Номер]").shouldBe(visible).as("Номер телефона");
                });
            });
            stepWithRole("Убедиться, что отображается неактивная кнопка Далее", () -> {
                forwardButtonLocator.shouldHave(text("Далее")).shouldHave(attribute("disabled"));
            });
            stepWithRole("Убедиться, что отображается кнопка Назад", () -> {
                backButtonLocator.shouldHave(text("Назад"));
            });
        });
    }

    public void fillName(String name) {
        stepWithRole("Ввести имя: " + name, () -> {
            driver.$("input[placeholder*=Имя]").setValue(name);
            System.out.println("name: " + name);
        });
    }

    public void fillSurname(String surname) {
        stepWithRole("Ввести фамилию: " + surname, () -> {
            driver.$("input[placeholder*=Фамилия]").setValue(surname);
            System.out.println("surname: " + surname);
        });
    }

    public void fillPatronymicName(String patronymicName) {
        stepWithRole("Ввести отчество: " + patronymicName, () -> {
            driver.$("input[placeholder*=Отчество]").setValue(patronymicName);
            System.out.println("patronymicName: " + patronymicName);
        });
    }

    public void fillEmail(String email) {
        stepWithRole("Ввести email: " + email, () -> {
            driver.$("input[placeholder*=почта]").setValue(email);
            System.out.println("email: " + email);
        });
    }

    public void fillPhoneNumber(String phoneNumber) {
        stepWithRole("Ввести номер телефона: " + phoneNumber, () -> {
            driver.$("input[placeholder*=Номер]").setValue(phoneNumber);
            System.out.println("phoneNumber: " + phoneNumber);
        });
    }


}

