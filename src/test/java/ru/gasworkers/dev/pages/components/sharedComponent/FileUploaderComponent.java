package ru.gasworkers.dev.pages.components.sharedComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;

public class FileUploaderComponent extends BaseComponent {
    public FileUploaderComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
            uploadLinkText = "Прикрепить файл";

    public void uploadFile(SelenideElement closestBox, File file) {
        stepWithRole("Загрузить файл к боксу: " + closestBox.getAlias(), () -> {
            closestBox.$("input[type='file']").uploadFile(file);
            stepWithRole("Убедиться, что файл загружен", () -> {
                closestBox.$$("img.files-list__logo.pointer").shouldHave(sizeGreaterThanOrEqual(1));
            });
            stepWithRole("Убедиться, что файл загружен признак 2", () -> {
                closestBox.$(" img[src*='https']").shouldBe(visible);
            });
            stepWithRole("Убедиться, что кнопка закрыть картинку отображается", () -> {
                closestBox.$(".close").shouldBe(visible);
            });
        });
    }


    public void checkInitialState(SelenideElement closestBox) {
        stepWithRole("Убедиться, что файл не загружен  в боксе: " + closestBox.getAlias(), () -> {
            closestBox.$$("img.files-list__logo.pointer").shouldHave(size(0));
            stepWithRole("Убедиться, что файл не загружен признак 2", () -> {
                closestBox.$(" img[src*='https']").shouldNotBe(visible);
            });
            stepWithRole("Убедиться, что кнопка закрыть картинку не  отображается", () -> {
                closestBox.$(".close").shouldNotBe(visible);
            });
            stepWithRole("Убедиться, что текст ссылки отображается", () -> {
                closestBox.$("button.upload-button").shouldHave(text(uploadLinkText));
            });
        });
    }

    // todo delete file method
//todo    public void checkFilledState
    // todo add pics counter for multiple pics to one box


}
