package ru.gasworkers.dev.pages.client.conference;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.client.BaseClientPage;

import static com.codeborne.selenide.Condition.visible;

public class EndConferenceClientPage extends BaseClientPage {
    SelenideElement self = driver.$("div.video-wrap").as("Контейнер Видеоконференции");

    public EndConferenceClientPage(RoleBrowser browser) {
        super(browser);
    }

    public void checkUrl() {
        stepWithRole("Проверка URL страницы", () -> {
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/conference/");
            urlChecker.urlContains("/end");
        });
    }

    public void checkFinishLoading() {
        stepWithRole("Проверка загрузки страницы", () -> {
            checkUrl();
            self.shouldBe(visible);
        });
    }
}
