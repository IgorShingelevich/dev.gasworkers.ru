package ru.gasworkers.dev.pages.components.clientComponent.videoComponent.approveMasterComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;

public class AttachmentsOrderVideoClientComponent extends BaseComponent {
    public AttachmentsOrderVideoClientComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
        titleText = "Прикрепите аудио/видео описание вашей проблемы",
        subtitleText = "Запишите небольшое видео или аудио описание вашей поломки, это поможет нашему мастеру быстрее её устранить.";

    SelenideElement
        titleLocator = driver.$("div.medium.w-100.mb-20.d-flex").as("Заголовок компонента Прикрепленные файлы и описание"),
        subtitleLocator = driver.$("p.small.mb-0").as("Подзаголовок компонента Прикрепленные файлы и описание"),
        uploadButtonLocator = driver.$("button.upload-button").as("Кнопка Прикрепить файл"),
        malfunctionDescriptionInputLocator = driver.$("input[placeholder='Опишите вашу проблему']").as("Поле Описание поломки");


    public void checkFinishLoading() {
        stepWithRole("Убедиться, что компонент Прикрепленные файлы и описание загрузился", () -> {
            titleLocator.shouldHave(text(titleText));
            subtitleLocator.shouldHave(text(subtitleText));
            uploadButtonLocator.shouldBe(visible);
            malfunctionDescriptionInputLocator.shouldBe(visible);
        });
    }

    public void checkDescription (String  descriptionText) {
        stepWithRole("Убедиться, что в поле Описание поломки введен текст", () -> {
            malfunctionDescriptionInputLocator.shouldHave(value(descriptionText));
        });
    }

    public void checkBGRightNowState(String  descriptionText) {
        checkDescription(descriptionText);
    }


}

//todo add file
