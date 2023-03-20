package ru.gasworkers.dev.pages.selfEmployed;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class FillProfileInfoSelfEmployedPage extends BaseSelfEmployedPage{
    public FillProfileInfoSelfEmployedPage(RoleBrowser browser) {
        super(browser);
    }


    private final String
    titleText = "Заполните профиль",
    descriptionText = "Для участия в тендере необходимо заполнить персональные данные в профиле.",
    toProfileButtonText = "В профиль",
    cancelButtonText = "Отмена";

    SelenideElement
    titleLocator = driver.$("div h3").as("Заголовок"),
    descriptionLocator = driver.$("div p").as("Описание"),
    toProfileButtonLocator = driver.$(byTagAndText("span", toProfileButtonText)).as("Кнопка перехода в профиль"),
    cancelButtonLocator = driver.$(byTagAndText("span", cancelButtonText)).as("Кнопка отмены");


    public void checkFinishLoading() {
        stepWithRole("Убедиться, что страница информации о заполнении профиля загрузилась", () -> {
           titleLocator.shouldHave(text(titleText));
              descriptionLocator.shouldHave(text(descriptionText));
                toProfileButtonLocator.shouldBe(visible);
                  cancelButtonLocator.shouldBe(visible);
        });
    }

    public void toProfileButton() {
        stepWithRole("Нажать на кнопку перехода в профиль", () -> {
            toProfileButtonLocator.click();
        });
    }

    public void cancelButton() {
        stepWithRole("Нажать на кнопку отмены", () -> {
            cancelButtonLocator.click();
        });
    }


}
