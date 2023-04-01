package ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;

public class UploadPhotoCutterModalWindowComponent extends AllRolesSharedComponent {
    public UploadPhotoCutterModalWindowComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
    modalWindowLocator = driver.$("div.modal-content-wrapper").as("Модальное окно обрезки фото"),
    approveButtonLocator = modalWindowLocator.$("button.btn-primary").as("Кнопка подтверждения"),
    cancelButtonLocator = modalWindowLocator.$("button.btn-outline-primary").as("Кнопка отмены");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что модальное окно обрезки фото загружено", () -> {
            modalWindowLocator.shouldBe(visible);
            approveButtonLocator.shouldHave(text("Подтвердить")).shouldBe(enabled);
            cancelButtonLocator.shouldHave(text("Отменить"));
        });
    }

    public void clickApproveButton() {
        stepWithRole("Нажать кнопку Подтвердить", () -> {
            approveButtonLocator.click();
            stepWithRole("Убедиться, что модальное окно обрезки фото закрыто", () -> {
                modalWindowLocator.shouldNotBe(visible);
            });
        });
    }

    public void clickCancelButton() {
        stepWithRole("Нажать кнопку Отменить", () -> {
            cancelButtonLocator.click();
            stepWithRole("Убедиться, что модальное окно обрезки фото закрыто", () -> {
                modalWindowLocator.shouldNotBe(visible);
            });
        });
    }

}
