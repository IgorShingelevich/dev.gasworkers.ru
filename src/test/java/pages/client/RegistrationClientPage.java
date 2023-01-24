package pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
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
    REPEAT_TEXT = "Запросить повторно";

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
        forwardButtonLocator = driver.$("div button.btn.btn-primary").as("Forward button"),
        backButtonLocator = driver.$("div button.btn.btn-outline-primary").as("Back button");

    public void checkFirstStepFinishLoading () {
        stepWithRole("Убедиться что представлены компоненты первого шага регистрации" , () -> {
            stepWithRole("Убедиться что на таймлайне выделен первый шаг" , () -> {
                stageStepsCollection.get(0).shouldHave(cssClass("active"));
            });
            stepWithRole("Убедиться что отображается заголовок" , () -> {
                titleLocator.shouldHave(text(FIRST_TITLE));
            });
            stepWithRole("Убедиться что отображается ссылка на страницу входа зарегистрированного пользователя" , () -> {
                alreadyRegisteredLocator.shouldHave(text("Уже зарегистрированы?"));
            });
            stepWithRole("Убедиться что отображается описание" , () -> {
                descriptionLocator.shouldHave(text(FIRST_DESCRIPTION));
            });
            stepWithRole("Убедиться что отображается поле ввода номера телефона" , () -> {
                inputPhoneLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться что отображается поле ввода электронной почты" , () -> {
                inputEmailLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться что чекбокс не отмечен" , () -> {
                checkboxLocator.shouldNotBe(checked);
            });
            stepWithRole("Убедиться что отображается текст чекбокса" , () -> {
                driver.$$("div span").get(5).shouldHave(text(CHECKBOX_TEXT));
            });
            stepWithRole("Убедиться что отображается ссылка на пользовательское соглашение" , () -> {
                userAgreementLinkLocator.shouldHave(text("Пользовательского соглашения"));
            });
            stepWithRole("Убедиться что отображается кнопка Далее" , () -> {
                forwardButtonLocator.shouldHave(text("Далее"));
            });
            stepWithRole("Убедиться что отображается кнопка Отменить" , () -> {
                backButtonLocator.shouldHave(text("Отменить"));
            });

        });
    }

    public void byPhone (String phone) {
        stepWithRole("Регистрация по номеру телефона: " + phone, () -> {
            stepWithRole("Ввести номер телефона" , () -> {
                inputPhoneLocator.setValue(phone);
            });
            stepWithRole("Нажать на незаполненный чекбокс" , () -> {
                checkboxLocator.shouldNotBe(checked);
                checkboxLocator.click();
            });
            stepWithRole("Убедиться что чекбокс отмечен" , () -> {
                checkboxLocator.shouldBe(checked);
            });
            stepWithRole("Нажать на кнопку Далее" , () -> {
                forwardButtonLocator.click();
            });
        });
    }

    public void byEmail (String email) {
        stepWithRole("Регистрация по электронной почте: " + email, () -> {
            stepWithRole("Ввести электронную почту" , () -> {
                inputEmailLocator.setValue(email);
            });
            stepWithRole("Нажать на незаполненный чекбокс" , () -> {
                checkboxLocator.shouldNotBe(checked);
                checkboxLocator.click();
            });
            stepWithRole("Убедиться что чекбокс отмечен" , () -> {
                checkboxLocator.shouldBe(checked);
            });
            stepWithRole("Нажать на кнопку Далее" , () -> {
                forwardButtonLocator.click();
            });
        });

    }

    public void checkSecondStepFinishLoading () {
        stepWithRole("Убедиться что представлены компоненты второго шага регистрации" , () -> {
            stepWithRole("Убедиться что на таймлайне выделен второй шаг" , () -> {
                stageStepsCollection.get(1).shouldHave(cssClass("active"));
            });
            stepWithRole("Убедиться что отображается ссылка на страницу входа зарегистрированного пользователя" , () -> {
                alreadyRegisteredLocator.shouldHave(text("Уже зарегистрированы?"));
            });
            stepWithRole("Убедиться что отображается заголовок" , () -> {
                titleLocator.shouldHave(text(SECOND_TITLE));
            });
            stepWithRole("Убедиться что отображается описание" , () -> {
                descriptionLocator.shouldHave(text(SECOND_DESCRIPTION));
            });
    //        TODO sendAgainPassiveLinkLocator
    //        driver.$("div.mb-3").shouldHave(text(REPEAT_TEXT));
    //         sendAgainActiveLinkLocator.shouldBe(visible);
            stepWithRole("Убедиться что отображается неактивная кнопка Далее" , () -> {
                forwardButtonLocator.shouldHave(text("Далее")).shouldHave(attribute("disabled"));
            });
            stepWithRole("Убедиться что отображается кнопка Назад" , () -> {
                backButtonLocator.shouldHave(text("Назад"));
            });
        });
    }

    public void clickAlreadyRegistered () {
        alreadyRegisteredLocator.click();
    }

    public void clickCheckbox () {

        checkboxLocator.click();
    }

    public void clickSendAgain () {
        sendAgainActiveLinkLocator.shouldHave(text("код подтверждения")).click();
        driver.$(".small.text-secondary").shouldHave(text("Повторный код можно выслать через"));

    }

    public void clickNext () {
        forwardButtonLocator.click();
    }

    public void clickCancel () {
        backButtonLocator.click();
    }




}
