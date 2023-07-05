package ru.gasworkers.dev.pages.master;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;

public class ResumeConferenceMasterPage extends BaseMasterPage {
    private final String
            title = "Напишите заключение по консультации",
            description = "Описание заключения",
            placeholder = "Напишите текст заключения по видеоконсультации";

    SelenideElement
            titleLocator = driver.$("p.h3").as("Заголовок"),
            descriptionLocator = driver.$("p.h4").as("Описание"),
            textAreaLocator = driver.$("div.gas-textarea textarea").as("Текстовое поле");

    public ResumeConferenceMasterPage(RoleBrowser browser) {
        super(browser);
    }

    public void checkFinishLoading() {
        stepWithRole("Проверка загрузки страницы", () -> {
            checkUrl();
            titleLocator.shouldHave(text(title));
            descriptionLocator.shouldHave(text(description));
            textAreaLocator.shouldHave(attribute("placeholder", placeholder));
        });
    }

    public void checkUrl() {
        stepWithRole("Проверка URL страницы", () -> {
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/conference/");
            urlChecker.urlContains("/resume");
        });
    }

    public void fillResume(String text) {
        stepWithRole("Заполнить текстовое поле", () -> {
            textAreaLocator.setValue(text);
        });
    }
}
