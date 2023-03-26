package ru.gasworkers.dev.pages.components.selfEmployedComponent.certificateAndEquipmentComponent;

import com.codeborne.selenide.ElementsCollection;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.CollectionCondition.size;

public class ListCertificateAndEquipmentSelfEmployedComponent extends BaseComponent {
    public ListCertificateAndEquipmentSelfEmployedComponent(RoleBrowser browser) {
        super(browser);
    }

    ElementsCollection
            selfCollection = driver.$$("div").as("Коллекция карточек оборудования");

    public void checkInitialState() {
        stepWithRole("Убедиться, что в списке нет карточек оборудования", () -> {
            selfCollection.shouldHave(size(0));
        });
    }

    public void checkSize(int size) {
        stepWithRole("Убедиться, что в списке: " + size + " карточек оборудования", () -> {
            selfCollection.shouldHave(size(size));
        });
    }
}
