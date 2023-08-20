package ru.gasworkers.dev.pages.sharedPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.BasePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;

public final class LoginPage extends BasePage {

    ElementsCollection
            credentialsInputCollection = driver.$$(".gas-input div input").as("Коллекция полей ввода");
    SelenideElement
            userNameFieldLocator = credentialsInputCollection.get(0).as("Поле ввода логина"),
            passwordFieldLocator = credentialsInputCollection.get(1).as("Поле ввода пароля");

    public LoginPage(RoleBrowser browser) {
        super(browser);
    }

    public LoginPage open() {
        stepWithRole("Открыть страницу авторизации", () -> {
            driver.open("/login");
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/login");
        });
        return this;
    }

    public void login(String userName, String password) {
        stepWithRole("Проверить, что логин произведен успешно ", () -> {
            driver.$(".title h3").shouldHave(text("Войдите в личный кабинет"));
            stepWithRole("Ввести имя пользователя: " + userName, () -> {
                Selenide.sleep(3000);
                // todo reset all input fields without sleep
                userNameFieldLocator.click();
                userNameFieldLocator.setValue(userName);
                userNameFieldLocator.shouldHave(value(userName));
                userNameFieldLocator.pressEnter();
            });
            stepWithRole("Ввести пароль: " + password, () -> {
                passwordFieldLocator.click();
                passwordFieldLocator.setValue(password);
                passwordFieldLocator.pressEnter();
                passwordFieldLocator.shouldHave(value(password));
            });
            primaryButton();
        });

    }

}