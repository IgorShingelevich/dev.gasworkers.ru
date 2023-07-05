package ru.gasworkers.dev.pages.sharedPages.conference;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.BasePage;

import static com.codeborne.selenide.Condition.visible;

public class ConferenceSharedPage extends BasePage {
    public ConferenceSharedPage(RoleBrowser browser) {
        super(browser);
    }

    public void checkFinishLoading() {
        stepWithRole("Проверка загрузки видеоконференции", () -> {
            checkUrl();
            self.shouldBe(visible);
            microphoneButtonLocator.shouldBe(visible);
            endButtonLocator.shouldBe(visible);
            chatButtonLocator.shouldBe(visible);
        });
    }

    public void checkUrl() {
        stepWithRole("Проверка URL страницы", () -> {
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/conference/");
        });
    }    SelenideElement self = driver.$("div.video-wrap").as("Контейнер Видеоконференции"),
            microphoneButtonLocator = self.$("button.video-footer__mic").as("Кнопка микрофона"),
            endButtonLocator = self.$("button.video-footer__end").as("Кнопка завершения видеоконференции"),
            chatButtonLocator = self.$("button.video-footer__chat").as("Кнопка чата");

    public void microphoneButton() {
        stepWithRole("Нажать по кнопке микрофона", () -> {
            microphoneButtonLocator.click();
        });
    }

    public void endButton() {
        stepWithRole("Нажать по кнопке завершения видеоконференции", () -> {
            endButtonLocator.click();
        });
    }

    public void chatButton() {
        stepWithRole("Нажать по кнопке чата", () -> {
            chatButtonLocator.click();
        });
    }



}
