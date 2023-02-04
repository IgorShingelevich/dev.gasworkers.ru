package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import java.util.List;

import static com.codeborne.selenide.Condition.*;

public final class InfoTypeOrderClientPage extends BaseClientPage {

    public InfoTypeOrderClientPage(RoleBrowser browser) {
        super(browser);
    }

    @Step("Проверить заголовок страницы {expectedTitle}")
    public InfoTypeOrderClientPage checkTitle(String expectedTitle) {
        driver.$("p.h4").shouldHave(Condition.text(expectedTitle));
        return this;
    }

    @Step("Проверить, что на странице есть {expectedCount} шаги")
    public InfoTypeOrderClientPage checkStepSequence(List<String> expectedSteps) {
        driver.$$(".gas-box li").shouldHave(CollectionCondition.texts(expectedSteps));
        return this;
    }

//    @Step("Нажать на кнопку {buttonName}")
    public void clickNextButton() {

        stepWithRole("Нажать на кнопку Далее ", () -> {
            driver.$(".btn-wrap button").shouldHave(text("Далее")).click();
        });

    }

}

