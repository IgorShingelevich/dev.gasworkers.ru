package pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RegistrationClientPage extends BaseClientPage {

    public RegistrationClientPage(RoleBrowser browser) {
        super(browser);
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
        confirmationCodeCollection = driver.$$("div.code-input input").as("Коллекция цифр кода подтверждения"),
        eyeIconCollection = driver.$$("div.eye-icon").as("Коллекция иконок отображения пароля");
    SelenideElement
        titleLocator = driver.$("div h4").as("Заголовок"),
        subtitleLocator = driver.$("div.description").as("Подзаголовок"),
        alreadyRegisteredLocator = driver.$("a.link-gray").as("Ссылка на страницу входа зарегистрированного пользователя"),
        inputPhoneLocator = driver.$("input[placeholder*=Номер]").as("Поле ввода номера телефона"),
        inputEmailLocator = driver.$("input[placeholder*=почта]").as("Поле ввода электронной почты"),
        checkboxLocator = driver.$("div input[type=checkbox]").as("Чекбокс"),
        userAgreementLinkLocator = driver.$("a[href*=user_agreement]").as("Ссылка на пользовательское соглашение"),
        sendAgainActiveLinkLocator = driver.$("a.link-dark-blue").as("Ссылка на повторную отправку кода"),
        generatePasswordButtonLocator =  driver.$("div button.lock-icon.btn.btn-link.disable-outline").as("Кнопка генерации кода"),
        passwordInputLocator = driver.$("input[placeholder*=Пароль]").as("Поле ввода пароля"),
        confirmPasswordInputLocator = driver.$("input[placeholder*=Подтвердить]").as("Поле ввода подтверждения пароля"),
        suggestedPasswordLocator = driver.$("input[readonly=readonly]").as("Предложенный пароль"),
        errorMessageLocator = driver.$("div.gas-input__error").as("Сообщение об ошибке"),
        forwardButtonLocator = driver.$("div button.btn.btn-primary").as("Кнопка перехода к следующему шагу"),
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
            stepWithRole("Убедиться, что отображается неактивная кнопка Далее" , () -> {
                forwardButtonLocator.shouldHave(text("Далее")).shouldBe(disabled);
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

    public void byEmail (String email) {
        stepWithRole("Ввести электронную почту: " + email, () -> {
            inputEmailLocator.setValue(email);
            System.out.println("email: " + email);
        });
    }




    public void checkSecondStepFinishLoading () {
        stepWithRole("Убедиться, что представлены компоненты второго шага регистрации: " , () -> {
            stepWithRole("Убедиться, что на таймлайне выделен второй шаг" , () -> {
                stepsCollection.get(1).shouldHave(cssClass("active"));
            });
            stepWithRole("Убедиться, что отображается ссылка на страницу входа зарегистрированного пользователя: " + ALREADY_REGISTERED_TEXT , () -> {
                alreadyRegisteredLocator.shouldHave(text(ALREADY_REGISTERED_TEXT));
            });
            stepWithRole("Убедиться, что отображается заголовок: " + SECOND_TITLE , () -> {
                titleLocator.shouldHave(text(SECOND_TITLE));
            });
            stepWithRole("Убедиться, что отображается описание: " + SECOND_SUBTITLE , () -> {
                subtitleLocator.shouldHave(text(SECOND_SUBTITLE));
            });
    //        TODO sendAgainPassiveLinkLocator
    //        driver.$("div.mb-3").shouldHave(text(REPEAT_TEXT));
    //         sendAgainActiveLinkLocator.shouldBe(visible);
            stepWithRole("Убедиться, что отображается неактивная кнопка Далее" , () -> {
                forwardButtonLocator.shouldHave(text("Далее")).shouldHave(attribute("disabled"));
            });
            stepWithRole("Убедиться, что отображается кнопка Назад" , () -> {
                backButtonLocator.shouldHave(text("Назад"));
            });
        });
    }

    public void clickAlreadyRegistered () {
        alreadyRegisteredLocator.click();
    }



    public void clickSendAgain () {
        sendAgainActiveLinkLocator.shouldHave(text("код подтверждения")).click();
        driver.$(".small.text-secondary").shouldHave(text("Повторный код можно выслать через"));

    }

    public void fillCode (String code) {
        stepWithRole("Ввести код подтверждения: " + code, () -> {
            confirmationCodeCollection.get(0).setValue(code);
            System.out.println("code: " + code);
        });
    }

    public void fillPassword (String password) {
        stepWithRole("Ввести пароль: " + password, () -> {
            //TODO
            passwordInputLocator.setValue(password);
            System.out.println("password: " + password);
        });
    }

    public void fillPasswordConfirmation (String password) {
        stepWithRole("Ввести подтверждение пароля: " + password, () -> {
            //TODO
            confirmPasswordInputLocator.setValue(password);
        });
    }


    public void clickNext () {
        stepWithRole("Нажать на активную кнопку Далее" , () -> {
            forwardButtonLocator.shouldHave(text("Далее")).shouldNotHave(attribute("disabled"));
            forwardButtonLocator.click();
        });
    }

    public void clickCancel () {
        backButtonLocator.click();
    }


    public void checkThirdStepFinishLoading() {
        stepWithRole("Убедиться, что представлены компоненты третьего шага регистрации: " , () -> {
            stepWithRole("Убедиться, что на таймлайне выделен третий шаг" , () -> {
                stepsCollection.get(2).shouldHave(cssClass("active"));
            });
            stepWithRole("Убедиться, что отображается заголовок: " + THIRD_TITLE , () -> {
                titleLocator.shouldHave(text(THIRD_TITLE));
            });
            stepWithRole("Убедиться, что отображается подсказка к паролю: " + PASSWORD_HINT , () -> {
                driver.$("div span.text").shouldHave(text(PASSWORD_HINT)).as("Подсказка к паролю");
            });
            stepWithRole("Убедиться, что отображается плейсхолдер в поле ввода пароля" , () -> {
                passwordInputLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что отображается плейсхолдер в поле ввода подтверждения пароля" , () -> {
                confirmPasswordInputLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что отображается кнопка Сгенерировать надежный пароль" , () -> {
                generatePasswordButtonLocator.shouldBe(visible).shouldHave(text("Сгенерировать надежный пароль"));
            });
            stepWithRole("Убедиться, что отображается активная кнопка Далее" , () -> {
                forwardButtonLocator.shouldHave(text("Далее")).shouldBe(enabled);
            });
            stepWithRole("Убедиться, что отображается кнопка Назад" , () -> {
                backButtonLocator.shouldHave(text("Назад"));
            });
        });

    }

    public void checkFourthStepByPhoneFinishLoading(String phone) {
        // turn 77778711855 to "+7(777)-871-1855"
        String formatPhoneNumber = phone.substring(0, 1) + "(" + phone.substring(1, 4) + ")-" + phone.substring(4, 7) + "-" + phone.substring(7, 11);
        stepWithRole("Убедиться, что представлены компоненты четвертого шага регистрации: " , () -> {
            stepWithRole("Убедиться, что на таймлайне выделен четвертый шаг" , () -> {
                stepsCollection.get(3).shouldHave(cssClass("active"));
            });
            stepWithRole("Убедиться, что отображается заголовок: " + FOURTH_TITLE , () -> {
                titleLocator.shouldHave(text(FOURTH_TITLE));
            });
            stepWithRole("Убедиться, что отображается описание: " , () -> {
                stepWithRole("Подзаголовок: " + driver.$("div p.small.mb-20").getText() , () -> {
                    driver.$("div p.small.mb-20").shouldHave(text("Общие данные")).as("Общие данные");
                });
                stepWithRole("Плейсхолдер ввода имени" , () -> {
                    driver.$("input[placeholder*=Имя]").shouldBe(visible).as("Имя");
                });
                stepWithRole("Плейсхолдер ввода фамилии" , () -> {
                    driver.$("input[placeholder*=Фамилия]").shouldBe(visible).as("Фамилия");
                });
                stepWithRole("Плейсхолдер ввода отчества" , () -> {
                    driver.$("input[placeholder*=Отчество]").shouldBe(visible).as("Отчество");
                });
                stepWithRole("Подзаголовок и телефон: " + formatPhoneNumber , () -> {
                    driver.$("div.d-flex.justify-content-between.mb-20").$$("div").get(0).shouldHave(text("Ваш номер телефона")).as("Ваш номер телефона");

                    driver.$("div.d-flex.justify-content-between.mb-20").$$("div").get(1).shouldHave(text(formatPhoneNumber)).as("formatPhoneNumber");
                });
                stepWithRole("Плейсхолдер ввода Электронная почта" , () -> {
                    driver.$("input[placeholder*=почта]").shouldBe(visible).as("Электронная почта");
                });
            });
            stepWithRole("Убедиться, что отображается неактивная кнопка Далее" , () -> {
                forwardButtonLocator.shouldHave(text("Далее")).shouldHave(attribute("disabled"));
            });
            stepWithRole("Убедиться, что отображается кнопка Назад" , () -> {
                backButtonLocator.shouldHave(text("Назад"));
            });
        });
    }  public void checkFourthStepByEmailFinishLoading(String email) {
        // turn 77778711855 to "+7(777)-871-1855"
        stepWithRole("Убедиться, что представлены компоненты четвертого шага регистрации: " , () -> {
            stepWithRole("Убедиться, что на таймлайне выделен четвертый шаг" , () -> {
                stepsCollection.get(3).shouldHave(cssClass("active"));
            });
            stepWithRole("Убедиться, что отображается заголовок: " + FOURTH_TITLE , () -> {
                titleLocator.shouldHave(text(FOURTH_TITLE));
            });
            stepWithRole("Убедиться, что отображается описание: " , () -> {
                stepWithRole("Подзаголовок: " + driver.$("div p.small.mb-20").getText() , () -> {
                    driver.$("div p.small.mb-20").shouldHave(text("Общие данные")).as("Общие данные");
                });
                stepWithRole("Плейсхолдер ввода имени" , () -> {
                    driver.$("input[placeholder*=Имя]").shouldBe(visible).as("Имя");
                });
                stepWithRole("Плейсхолдер ввода фамилии" , () -> {
                    driver.$("input[placeholder*=Фамилия]").shouldBe(visible).as("Фамилия");
                });
                stepWithRole("Плейсхолдер ввода отчества" , () -> {
                    driver.$("input[placeholder*=Отчество]").shouldBe(visible).as("Отчество");
                });
                stepWithRole("Подзаголовок и почта: " + email , () -> {
                    driver.$("div.d-flex.justify-content-between.mb-20").$$("div").get(0).shouldHave(text("Электронная почта")).as("Электронная почта");

                    driver.$("div.d-flex.justify-content-between.mb-20").$$("div").get(1).shouldHave(text(email)).as("email");
                });
                stepWithRole("Плейсхолдер ввода Номер телефона" , () -> {
                    driver.$("input[placeholder*=Номер]").shouldBe(visible).as("Номер телефона");
                });
            });
            stepWithRole("Убедиться, что отображается неактивная кнопка Далее" , () -> {
                forwardButtonLocator.shouldHave(text("Далее")).shouldHave(attribute("disabled"));
            });
            stepWithRole("Убедиться, что отображается кнопка Назад" , () -> {
                backButtonLocator.shouldHave(text("Назад"));
            });
        });
    }

    public void fillName(String name) {
        stepWithRole("Ввести имя: " + name , () -> {
            driver.$("input[placeholder*=Имя]").setValue(name);
            System.out.println("name: " + name);
        });
    }

    public void fillSurname(String surname) {
        stepWithRole("Ввести фамилию: " + surname , () -> {
            driver.$("input[placeholder*=Фамилия]").setValue(surname);
            System.out.println("surname: " + surname);
        });
    }

    public void fillPatronymicName(String patronymicName) {
        stepWithRole("Ввести отчество: " + patronymicName , () -> {
            driver.$("input[placeholder*=Отчество]").setValue(patronymicName);
            System.out.println("patronymicName: " + patronymicName);
        });
    }

    public void fillEmail(String email) {
        stepWithRole("Ввести email: " + email , () -> {
            driver.$("input[placeholder*=почта]").setValue(email);
            System.out.println("email: " + email);
        });
    }

    public void fillPhoneNumber(String phoneNumber) {
        stepWithRole("Ввести номер телефона: " + phoneNumber , () -> {
            driver.$("input[placeholder*=Номер]").setValue(phoneNumber);
            System.out.println("phoneNumber: " + phoneNumber);
        });
    }

    public void checkFinishState() {
        driver.$("div.logo-small img").should(appear);
        stepWithRole("Убедиться, что представлены компоненты страницы Успешная регистрация: " , () -> {
            stepWithRole("Убедиться, что отображается заголовок: " + driver.$("div.page-content h3").getText() , () -> {
                driver.$("div.page-content h3").shouldHave(text("Поздравляем!"));
            });
            stepWithRole("Убедиться, что отображается информационный текст "+ driver.$("div.page-content").getText() , () -> {
                driver.$("div.page-content").$$("p").get(0).shouldHave(text("Вы успешно зарегистрировались в сервисе Gasworkers."));
                driver.$("div.page-content").$$("p").get(1).shouldHave(text("Через 5 секунд вы будете автоматически перенаправлены в личный кабинет"));
            });
            driver.$("div.logo-small img").should(disappear, Duration.ofSeconds(20));
        });
    }

    public void checkInvalidEmailError(String invalidEmail, String errorText) {
        stepWithRole("Убедиться, что при вводе некорректного email: " + invalidEmail + " отображается ошибка: " + errorText , () -> {
            errorMessageLocator.shouldHave(text(errorText));
        });
    }

    public void checkInvalidPhoneNumberError(String invalidPhoneNumber, String errorText, String errorDescription) {
        stepWithRole("Убедиться, что при вводе некорректного номера телефона: " + invalidPhoneNumber + " отображается ошибка: " + errorText , () -> {
            if (!errorMessageLocator.isDisplayed()) {
                throw new AssertionError(errorDescription);
            }
        });
    }

    public void checkInvalidPasswordNotification() {
            stepWithRole("Убедиться, что при несоответствии пароля и подтверждения пароля отображается уведомление: " + errorMessageLocator.getText() , () -> {
                errorMessageLocator.shouldHave(text("Пароли не совпадают"));
            });
    }

    public void generatePassword() {
        stepWithRole("Сгенерировать пароль" , () -> {
            stepWithRole("Нажать на кнопку Сгенерировать пароль" , () -> {
                generatePasswordButtonLocator.click();
            });
            stepWithRole("Убедиться, что сгенерирован Надежный  пароль: " + suggestedPasswordLocator.getText() , () -> {
                suggestedPasswordLocator.shouldBe(visible);
                System.out.println("suggestedPassword: " + suggestedPasswordLocator.getText());
            });
            stepWithRole("Убедиться что появился подзаголовок поля Надежный пароль " + driver.$$("p.small").get(0).getText() , () -> {
                driver.$$("p.small").get(0).as("подзаголовок поля Надежный пароль").shouldHave(text("Мы сгенерировали вам пароль"));
            });
            stepWithRole("Убедиться что появилось описание поля Надежный пароль " + driver.$$("p.small").get(1).getText() , () -> {
                driver.$$("p.small").get(1).as("описание поля Надежный пароль").shouldHave(text("Пароль должен содержать не менее 4 знаков"));
            });
            stepWithRole("Убедиться, что поле Пароль и Подтверждение пароля заполнены сгенерированным паролем: " + suggestedPasswordLocator.getText() , () -> {
                stepWithRole("Нажать на иконку Показать  скрытый пароль в поле Пароль и Подтверждение Пароля" , () -> {
                    stepWithRole("Убедиться, что иконки переходят из состояния Скрытый пароль в состояние Пароль Отображается" , () -> {
                        eyeIconCollection.get(0).shouldNotHave(cssClass("visible")).click();
                        eyeIconCollection.get(0).shouldHave(cssClass("visible"));
                        eyeIconCollection.get(1).shouldNotHave(cssClass("visible")).click();
                        eyeIconCollection.get(1).shouldHave(cssClass("visible"));
                    });
                });
                stepWithRole("Убедиться, что в поле Пароль и Подтверждение Пароля отображается сгенерированный пароль: " + suggestedPasswordLocator.getText() , () -> {
                    passwordInputLocator.shouldHave(value(suggestedPasswordLocator.getText()));
                    confirmPasswordInputLocator.shouldHave(value(suggestedPasswordLocator.getText()));
                });
                stepWithRole("Нажать на иконку Скрыть пароль в поле Пароль и Подтверждение Пароля" , () -> {
                    eyeIconCollection.get(0).shouldHave(cssClass("visible")).click();
                    eyeIconCollection.get(0).shouldNotHave(cssClass("visible"));
                    eyeIconCollection.get(1).shouldHave(cssClass("visible")).click();
                    eyeIconCollection.get(1).shouldNotHave(cssClass("visible"));
                });
            });
        });

    }
}
