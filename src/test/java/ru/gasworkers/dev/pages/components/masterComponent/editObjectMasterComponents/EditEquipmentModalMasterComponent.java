package ru.gasworkers.dev.pages.components.masterComponent.editObjectMasterComponents;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class EditEquipmentModalMasterComponent extends BaseComponent {
    public EditEquipmentModalMasterComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
            TITLE = "Добавление нового оборудования для объекта";
    SelenideElement
        titleLocator = driver.$("p.h4.text-center").as("Заголовок  модального окна Редактирование оборудования"),
        saveButtonLocator = driver.$(byTagAndText("span", "Сохранить")).as("Кнопка Сохранить"),
        cancelButtonLocator = driver.$(byTagAndText("span", "Отмена")).as("Кнопка Отмена");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что модальное окно Редактировать оборудование загружено", () -> {
            String urlEditEquipmentModalMasterComponent = driver.url();
            titleLocator.shouldBe(visible);
            //TODO check title with object name
            saveButtonLocator.shouldBe(visible);
            cancelButtonLocator.shouldBe(visible);
        });
    }

    public void clickSaveButton() {
        stepWithRole("Нажать кнопку Сохранить", () -> {
            saveButtonLocator.click();
        });
    }

    public void clickCancelButton() {
        stepWithRole("Нажать кнопку Отмена", () -> {
            cancelButtonLocator.click();
        });
    }

}
