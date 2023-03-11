package ru.gasworkers.dev.pages.components.registrationStepComponent.selfEmployedRegistrationStepComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.registrationStepComponent.HeaderRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationStepComponent.StepNumberRegistrationComponent;

import static com.codeborne.selenide.Condition.*;

public class SecondStepSelfEmployedRegistrationComponent extends BaseComponent {
public final HeaderRegistrationComponent header;
    public final StepNumberRegistrationComponent stepNumber;
    public SecondStepSelfEmployedRegistrationComponent(RoleBrowser browser) {
        super(browser);
        header = new HeaderRegistrationComponent(browser);
        stepNumber = new StepNumberRegistrationComponent(browser);
    }

    private final String
        titleText = "Регистрация самозанятого",
        subtitleText = "Для регистрации",
        personnelDataProcessingCheckboxText = "Я даю согласие на обработку персональных данных и принимаю условия";

    SelenideElement
        alreadyRegisteredLinkLocator = driver.$("a.link-gray").as("Ссылка уже зарегистрированы"),
        titleLocator = driver.$("div h4").as("Заголовок"),
        descriptionLocator = driver.$("div.description").as("Описание"),
        phoneNumberInputLocator = driver.$("input[placeholder='Номер телефона']").as("Поле ввода номера телефона"),
        emailInputLocator = driver.$("input[placeholder='Электронная почта']").as("Поле ввода электронной почты"),
        personnelDataProcessingCheckboxLocator = driver.$("div.p-default.p-curve.p-smooth.pretty input").as("Чекбокс согласия на обработку персональных данных"),
        personnelDataProcessingCheckboxTextLocator = driver.$("div.checkbox-input.gas-checkbox-wrapper span").as("Текст чекбокса согласия на обработку персональных данных"),
        nextButtonLocator = driver.$("div button.btn.btn-primary").as("Кнопка перехода к следующему шагу"),
        errorMessageLocator = driver.$("div.gas-input__error").as("Сообщение об ошибке"),
        cancelButtonLocator = driver.$("div button.btn.btn-outline-primary").as("Кнопка отмены регистрации");

    ElementsCollection
            stepsCollection = driver.$$("div.stage").as("Коллекция шагов регистрации");

    public void checkFinishLoading () {
        stepWithRole("Убедиться, что представлены компоненты второго шага регистрации: ", () -> {
            stepNumber.checkStepNumber(2);
           header.checkFinishLoading();
            stepWithRole("Убедиться, что отображается заголовок", () -> {
                titleLocator.shouldHave(text(titleText));
            });
            stepWithRole("Убедиться, что отображается описание", () -> {
                descriptionLocator.shouldHave(text(subtitleText));
            });
            stepWithRole("Убедиться, что отображается поле ввода номера телефона", () -> {
                phoneNumberInputLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что отображается поле ввода электронной почты", () -> {
                emailInputLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что отображается текст чекбокса согласия на обработку персональных данных", () -> {
                personnelDataProcessingCheckboxTextLocator.shouldHave(text(personnelDataProcessingCheckboxText));
            });
            stepWithRole("Убедиться, что чекбокс не отмечен", () -> {
                personnelDataProcessingCheckboxLocator.shouldHave(attribute("value", "false"));
            });
            stepWithRole("Убедиться, что  кнопка перехода к следующему шагу активна", () -> {
                nextButtonLocator.shouldBe(enabled);
            });
            stepWithRole("Убедиться, что  кнопка отмены регистрации отображается", () -> {
                cancelButtonLocator.shouldBe(visible);
            });
        });
    }

    public void fillPhoneNumberInput (String phoneNumber) {
        stepWithRole("Заполнить поле ввода номера телефона: " + phoneNumberInputLocator.getValue(), () -> {
            phoneNumberInputLocator.setValue(phoneNumber);
        });
        stepWithRole("Убедиться, что поле ввода почты не активно", () -> {
            emailInputLocator.shouldBe(disabled);
        });
    }

    public void fillEmailInput (String email) {
        stepWithRole("Заполнить поле ввода электронной почты: " + emailInputLocator.getValue(), () -> {
            emailInputLocator.setValue(email);
        });
        stepWithRole("Убедиться, что поле ввода номера телефона не активно", () -> {
            phoneNumberInputLocator.shouldBe(disabled);
        });
    }

    public void fillPersonnelDataProcessingCheckbox () {
        stepWithRole("Отметить чекбокс согласия на обработку персональных данных: ", () -> {
            personnelDataProcessingCheckboxLocator.shouldHave(attribute("value", "false"));
            personnelDataProcessingCheckboxLocator.click();
            personnelDataProcessingCheckboxLocator.shouldHave(attribute("value", "true"));
        });
    }

    public void nextButton () {
        stepWithRole("Нажать кнопку Далее ", () -> {
            nextButtonLocator.click();
            checkNoErrors();
        });
    }

    public void cancelButton () {
        stepWithRole("Нажать кнопку Отмена ", () -> {
            cancelButtonLocator.click();
        });
    }

    public void checkNoErrors () {
        stepWithRole("Убедиться, что отсутствуют сообщения об ошибках", () -> {
            errorMessageLocator.shouldNotBe(visible);
        });
    }

    public void byPhone (String phoneNumber) {
        stepWithRole("Заполнить второй шаг регистрации самозанятого по номеру телефона: ", () -> {
            fillPhoneNumberInput(phoneNumber);
            fillPersonnelDataProcessingCheckbox();
        });
    }

    public void byEmail (String email) {
        stepWithRole("Заполнить второй шаг регистрации самозанятого по электронной почте: ", () -> {
            fillEmailInput(email);
            fillPersonnelDataProcessingCheckbox();
            nextButton();
        });
    }







}
