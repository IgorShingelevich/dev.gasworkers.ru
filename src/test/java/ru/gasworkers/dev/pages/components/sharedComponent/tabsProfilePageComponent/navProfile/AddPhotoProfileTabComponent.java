package ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.navProfile;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.UploadPhotoCutterModalWindowComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;

public class AddPhotoProfileTabComponent extends BaseComponent {

    public final UploadPhotoCutterModalWindowComponent uploadPhotoCutterModalWindow;

    public AddPhotoProfileTabComponent(RoleBrowser browser) {
        super(browser);
        uploadPhotoCutterModalWindow = new UploadPhotoCutterModalWindowComponent(browser);
    }

    SelenideElement
            addPhotoBoxLocator = driver.$("div.avatar-update-block").as("Блок добавления фото"),
            addPhotoButtonLocator = addPhotoBoxLocator.$("input[type='file']").as("Кнопка добавления фото"),
            imageLocator = addPhotoBoxLocator.$("div.image img").as("Загруженое фото");

    public void uploadPhoto (File photo) {
        stepWithRole("Загрузить фото", () -> {
            addPhotoButtonLocator.uploadFile(photo);
            imageLocator.shouldBe(visible);
        });
    }

    public void checkInitialState () {
        stepWithRole("Проверить начальное состояние", () -> {
            addPhotoBoxLocator.shouldBe(visible);
            addPhotoButtonLocator.shouldBe(hidden);
            imageLocator.shouldNotBe(visible);
        });
    }

}