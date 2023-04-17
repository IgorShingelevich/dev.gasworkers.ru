package ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.navProfile;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.text;

public class AddDiplomaProfileTabComponent extends BaseComponent {
    public AddDiplomaProfileTabComponent(RoleBrowser browser) {
        super(browser);
    }

    ElementsCollection
            diplomaPreviewCollection = driver.$$("div.preview img").as("Превью дипломов"),
            closeButtonsCollection = driver.$$("div.close").as("Кнопки закрытия диплома");
    SelenideElement
            uploadLinkLocator = driver.$("div.gas-textarea-wrapper.mb-2").sibling(0).$("input[type='file']").as("Ссылка загрузки диплома");

    public void uploadDiploma(File diploma) {
        stepWithRole("Загрузить диплом", () -> {
            uploadLinkLocator.shouldHave(text("Добавить дипломы, сертификаты"));
            uploadLinkLocator.uploadFile(diploma);
        });
    }

    public void checkInitialState() {
        stepWithRole("Проверить начальное состояние", () -> {
            driver.$("div.gas-textarea-wrapper.mb-2").sibling(0).shouldHave(text("Добавить документы о профессиональном обучении")); // changed "Добавить дипломы, сертификаты"
            diplomaPreviewCollection.shouldHave(CollectionCondition.size(0));
            closeButtonsCollection.shouldHave(CollectionCondition.size(0));
        });
    }


}
