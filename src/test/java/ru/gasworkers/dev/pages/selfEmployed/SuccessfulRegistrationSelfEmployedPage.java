package ru.gasworkers.dev.pages.selfEmployed;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SuccessfulRegistrationSelfEmployedPage extends BaseSelfEmployedPage {
    public SuccessfulRegistrationSelfEmployedPage(RoleBrowser browser) {
        super(browser);
    }

    private final String
            titleText = "Вы зарегистрированы в системе Gasworkers",
            descriptionText = "Теперь Вы можете получать заказы на видеоконсультации, ремонт и техническое обслуживание ВДГО.";

    ElementsCollection
            textCollection = driver.$$("p.text-center").as("Коллекция текстов");

    SelenideElement
            titleLocator = textCollection.get(0).as("Заголовок"),
            descriptionLocator = textCollection.get(1).as("Описание"),
            forwardButtonLocator = driver.$("button.btn.btn-primary").as("Кнопка перехода к заказам"),
            cancelButtonLocator = driver.$("button.btn-outline-primary").as("Кнопка отмены");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что все элементы страницы отображаются", () -> {
            stepWithRole("Убедиться, что отображается заголовок: " + titleText, () -> {
                titleLocator.shouldHave(text(titleText));
            });
            stepWithRole("Убедиться, что отображается описание: " + descriptionText, () -> {
                descriptionLocator.shouldHave(text(descriptionText));
            });
            stepWithRole("Убедиться, что  кнопка перехода к заказам активна", () -> {
                forwardButtonLocator.shouldBe(enabled);
            });
            stepWithRole("Убедиться, что отображается кнопка отмены", () -> {
                cancelButtonLocator.shouldBe(visible);
            });
        });
    }

    public void clickForwardButton() {
        stepWithRole("Нажать кнопку перехода к заказам", () -> {
            forwardButtonLocator.shouldHave(text("Перейти к заказам")).click();
        });
    }

    public void clickCancelButton() {
        stepWithRole("Нажать кнопку отмены", () -> {
            cancelButtonLocator.shouldHave(text("Отменить")).click();
        });
    }

}
