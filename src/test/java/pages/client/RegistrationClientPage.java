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
    FIRST_DESCRIPTION = "Для регистрации",
    CHECKBOX_TEXT = "Я даю согласие на обработку персональных данных и принимаю условия",
    SECOND_TITLE = "Введите код подтверждения",
    SECOND_DESCRIPTION = "Введите код подтверждения из SMS или письма пришедшего к вам на электронную почту. Пожалуйста, проверьте папку СПАМ в почте",
    REPEAT_TEXT = "Запросить повторно",
    THIRD_TITLE = "Придумайте себе пароль",
    FOURTH_TITLE = "Заполните личные данные";

    ElementsCollection
            stageStepsCollection = driver.$$("div.stage").as("Stage stepsCollection");
    SelenideElement
        titleLocator = driver.$("div h4").as("Title"),
        descriptionLocator = driver.$("div.description").as("Description"),
        alreadyRegisteredLocator = driver.$("a.link-gray").as("Already registered"),
        inputPhoneLocator = driver.$("input[placeholder*=Номер]").as("Input phone"),
        inputEmailLocator = driver.$("input[placeholder*=почта]").as("Input email"),
        checkboxLocator = driver.$("div input[type=checkbox]").as("Checkbox"),
        userAgreementLinkLocator = driver.$("a[href*=user_agreement]").as("User agreement link"),
        sendAgainActiveLinkLocator = driver.$("a.link-dark-blue").as("Send again active link"),
        generateCodeButtonLocator =  driver.$("div button.lock-icon.btn.btn-link.disable-outline").as("Generate strong password button"),
        passwordInputLocator = driver.$("input[placeholder*=Пароль]").as("Password"),
        confirmPasswordInputLocator = driver.$("input[placeholder*=Подтвердить]").as("Repeat password"),



        forwardButtonLocator = driver.$("div button.btn.btn-primary").as("Forward button"),
        backButtonLocator = driver.$("div button.btn.btn-outline-primary").as("Back button");

    public void checkFirstStepFinishLoading () {
        stepWithRole("Убедиться, что представлены компоненты первого шага регистрации: " , () -> {
            stepWithRole("Убедиться, что на таймлайне выделен первый шаг" , () -> {
                stageStepsCollection.get(0).shouldHave(cssClass("active"));
            });
            stepWithRole("Убедиться, что отображается заголовок" , () -> {
                titleLocator.shouldHave(text(FIRST_TITLE));
            });
            stepWithRole("Убедиться, что отображается ссылка на страницу входа зарегистрированного пользователя" , () -> {
                alreadyRegisteredLocator.shouldHave(text("Уже зарегистрированы?"));
            });
            stepWithRole("Убедиться, что отображается описание" , () -> {
                descriptionLocator.shouldHave(text(FIRST_DESCRIPTION));
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
            stepWithRole("Убедиться, что отображается текст чекбокса" , () -> {
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
                stageStepsCollection.get(1).shouldHave(cssClass("active"));
            });
            stepWithRole("Убедиться, что отображается ссылка на страницу входа зарегистрированного пользователя" , () -> {
                alreadyRegisteredLocator.shouldHave(text("Уже зарегистрированы?"));
            });
            stepWithRole("Убедиться, что отображается заголовок" , () -> {
                titleLocator.shouldHave(text(SECOND_TITLE));
            });
            stepWithRole("Убедиться, что отображается описание" , () -> {
                descriptionLocator.shouldHave(text(SECOND_DESCRIPTION));
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
            //TODO
            System.out.println("code: " + code);
        });
    }

    public void fillPassword (String password) {
        stepWithRole("Ввести пароль: " + password, () -> {
            //TODO
            passwordInputLocator.setValue(password);
        });
    }

    public void fillPasswordConfirmation (String password) {
        stepWithRole("Ввести подтверждение пароля: " + password, () -> {
            //TODO
            confirmPasswordInputLocator.setValue(password);
            System.out.println("passwordConfirm: " + password);
        });
    }


    public void clickNext () {
        stepWithRole("Нажать на кнопку Далее" , () -> {
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
                stageStepsCollection.get(2).shouldHave(cssClass("active"));
            });
            stepWithRole("Убедиться, что отображается заголовок" , () -> {
                titleLocator.shouldHave(text(THIRD_TITLE));
            });
            stepWithRole("Убедиться, что отображается подсказка к паролю" , () -> {
                driver.$("div span.text").shouldHave(text("Минимум 4 символа")).as("Подсказка к паролю");
            });
            stepWithRole("Убедиться, что отображается плейсхолдер в поле ввода пароля" , () -> {
                passwordInputLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что отображается плейсхолдер в поле ввода подтверждения пароля" , () -> {
                confirmPasswordInputLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что отображается кнопка Сгенерировать надежный пароль" , () -> {
                generateCodeButtonLocator.shouldBe(visible).shouldHave(text("Сгенерировать надежный пароль"));
            });
            stepWithRole("Убедиться, что отображается кнопка Далее" , () -> {
                forwardButtonLocator.shouldHave(text("Далее"));
            });
            stepWithRole("Убедиться, что отображается кнопка Назад" , () -> {
                backButtonLocator.shouldHave(text("Назад"));
            });
        });

    }

    public void checkFourthStepFinishLoading(String phone) {
        // turn 77778711855 to "+7(777)-871-1855"
        String formatPhoneNumber = phone.substring(0, 1) + "(" + phone.substring(1, 4) + ")-" + phone.substring(4, 7) + "-" + phone.substring(7, 11);
        stepWithRole("Убедиться, что представлены компоненты четвертого шага регистрации: " , () -> {
            stepWithRole("Убедиться, что на таймлайне выделен четвертый шаг" , () -> {
                stageStepsCollection.get(3).shouldHave(cssClass("active"));
            });
            stepWithRole("Убедиться, что отображается заголовок" , () -> {
                titleLocator.shouldHave(text(FOURTH_TITLE));
            });
            stepWithRole("Убедиться, что отображается описание: " , () -> {
                stepWithRole("Подзаголовок" , () -> {
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
    }

    public void fillName(String name) {
        stepWithRole("Ввести имя: " + name , () -> {
            driver.$("input[placeholder*=Имя]").setValue(name);
            System.out.println("name = " + name);
        });
    }

    public void fillSurname(String surname) {
        stepWithRole("Ввести фамилию: " + surname , () -> {
            driver.$("input[placeholder*=Фамилия]").setValue(surname);
            System.out.println("surname = " + surname);
        });
    }

    public void fillPatronymicName(String patronymicName) {
        stepWithRole("Ввести отчество: " + patronymicName , () -> {
            driver.$("input[placeholder*=Отчество]").setValue(patronymicName);
            System.out.println("patronymicName = " + patronymicName);
        });
    }

    public void fillEmail(String email) {
        stepWithRole("Ввести email: " + email , () -> {
            driver.$("input[placeholder*=почта]").setValue(email);
            System.out.println("email = " + email);
        });
    }

    public void fillPhoneNumber(String phoneNumber) {
        stepWithRole("Ввести номер телефона: " + phoneNumber , () -> {
            driver.$("input[placeholder*=Номер]").setValue(phoneNumber);
            System.out.println("phoneNumber = " + phoneNumber);
        });
    }

    public void checkFinishState() {
        driver.$("div.logo-small img").should(appear);
        stepWithRole("Убедиться, что представлены компоненты страницы Успешная регистрация: " , () -> {
            stepWithRole("Убедиться, что отображается заголовок", () -> {
                driver.$("div.page-content h3").shouldHave(text("Поздравляем!"));
            });
            stepWithRole("Убедиться, что отображается информационный текст ", () -> {
                driver.$("div.page-content").$$("p").get(0).shouldHave(text("Вы успешно зарегистрировались в сервисе Gasworkers."));
                driver.$("div.page-content").$$("p").get(1).shouldHave(text("Через 5 секунд вы будете автоматически перенаправлены в личный кабинет"));
            });
            driver.$("div.logo-small img").should(disappear, Duration.ofSeconds(20));
        });
    }

    public void checkInvalidEmailError(String invalidEmail, String errorText) {
        stepWithRole("Убедиться, что при вводе некорректного email: " + invalidEmail + " отображается ошибка: " + errorText , () -> {
           driver.$("div.gas-input__error").shouldHave(text(errorText));
        });
    }

    public void checkInvalidPhoneNumberError(String invalidPhoneNumber, String errorText, String errorDescription) {
        stepWithRole("Убедиться, что при вводе некорректного номера телефона: " + invalidPhoneNumber + " отображается ошибка: " + errorText , () -> {
            if (!driver.$("div.gas-input__error").isDisplayed()) {
                throw new AssertionError(errorDescription);
            }
        });
    }
}
