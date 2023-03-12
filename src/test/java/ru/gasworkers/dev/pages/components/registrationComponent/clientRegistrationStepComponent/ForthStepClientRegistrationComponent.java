package ru.gasworkers.dev.pages.components.registrationComponent.clientRegistrationStepComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.registrationComponent.HeaderRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationComponent.StepNumberRegistrationComponent;

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
            FOURTH_TITLE = "Заполните личные данные";

    SelenideElement
            titleLocator = driver.$("div h4").as("Заголовок"),
            subtitleLocator = driver.$("div.description").as("Подзаголовок"),
            subtitleCommonLocator = driver.$("div p.small.mb-20").as("Подзаголовок общие данные"),
            inputPhoneLocator = driver.$("input[placeholder*=Номер]").as("Поле ввода номера телефона"),
            inputEmailLocator = driver.$("input[placeholder*=почта]").as("Поле ввода электронной почты"),
            nameInputLocator = driver.$("input[placeholder*=Имя]").as("Поле ввода имени"),
            surnameInputLocator = driver.$("input[placeholder*=Фамилия]").as("Поле ввода фамилии"),
            patronymicInputLocator = driver.$("input[placeholder*=Отчество]").as("Поле ввода отчества"),

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
                stepWithRole("Подзаголовок: " + subtitleCommonLocator.getText(), () -> {
                    subtitleCommonLocator.shouldHave(text("Общие данные")).as("Общие данные");
                });
                stepWithRole("Плейсхолдер ввода имени", () -> {
                    nameInputLocator.shouldBe(visible).as("Имя");
                });
                stepWithRole("Плейсхолдер ввода фамилии", () -> {
                    surnameInputLocator.shouldBe(visible).as("Фамилия");
                });
                stepWithRole("Плейсхолдер ввода отчества", () -> {
                    patronymicInputLocator.shouldBe(visible).as("Отчество");
                });
                //removed for business reason
                /*stepWithRole("Подзаголовок и телефон: " + formatPhoneNumber , () -> {
                    driver.$("div.d-flex.justify-content-between.mb-20").$$("div").get(0).shouldHave(text("Ваш номер телефона")).as("Ваш номер телефона");
                    driver.$("div.d-flex.justify-content-between.mb-20").$$("div").get(1).shouldHave(text(formatPhoneNumber)).as("formatPhoneNumber");
                });*/
                stepWithRole("Плейсхолдер ввода Электронная почта", () -> {
                    inputEmailLocator.shouldBe(visible).as("Электронная почта");
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
            header.checkFinishLoading();
            stepNumber.checkStepNumber(4);
            stepWithRole("Убедиться, что отображается заголовок: " + FOURTH_TITLE, () -> {
                titleLocator.shouldHave(text(FOURTH_TITLE));
            });
            stepWithRole("Убедиться, что отображается описание: ", () -> {
                stepWithRole("Подзаголовок: " + subtitleCommonLocator.getText(), () -> {
                    subtitleCommonLocator.shouldHave(text("Общие данные")).as("Общие данные");
                });
                stepWithRole("Плейсхолдер ввода имени", () -> {
                    nameInputLocator.shouldBe(visible).as("Имя");
                });
                stepWithRole("Плейсхолдер ввода фамилии", () -> {
                    surnameInputLocator.shouldBe(visible).as("Фамилия");
                });
                stepWithRole("Плейсхолдер ввода отчества", () -> {
                    patronymicInputLocator.shouldBe(visible).as("Отчество");
                });
                //removed for business reason
                /*stepWithRole("Подзаголовок и почта: " + email , () -> {
                    driver.$("div.d-flex.justify-content-between.mb-20").$$("div").get(0).shouldHave(text("Электронная почта")).as("Электронная почта");
                    driver.$("div.d-flex.justify-content-between.mb-20").$$("div").get(1).shouldHave(text(email)).as("email");
                });*/
                stepWithRole("Плейсхолдер ввода Номер телефона", () -> {
                    inputPhoneLocator.shouldBe(visible).as("Номер телефона");
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
            nameInputLocator.setValue(name);
            System.out.println("name: " + name);
        });
    }

    public void fillSurname(String surname) {
        stepWithRole("Ввести фамилию: " + surname, () -> {
            surnameInputLocator.setValue(surname);
            System.out.println("surname: " + surname);
        });
    }

    public void fillPatronymicName(String patronymicName) {
        stepWithRole("Ввести отчество: " + patronymicName, () -> {
            patronymicInputLocator.setValue(patronymicName);
            System.out.println("patronymicName: " + patronymicName);
        });
    }

    public void fillEmail(String email) {
        stepWithRole("Ввести email: " + email, () -> {
            inputEmailLocator.setValue(email);
            System.out.println("email: " + email);
        });
    }

    public void fillPhoneNumber(String phoneNumber) {
        stepWithRole("Ввести номер телефона: " + phoneNumber, () -> {
            inputPhoneLocator.setValue(phoneNumber);
            System.out.println("phoneNumber: " + phoneNumber);
        });
    }


}

