package ru.gasworkers.dev.pages.components.selfEmployedComponent.certificateAndEquipmentComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.SearchComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class AddCertificateAndEquipmentSelfEmployedComponent extends BaseComponent {

    public final SearchComponent search;

    public AddCertificateAndEquipmentSelfEmployedComponent(RoleBrowser browser) {
        super(browser);
        search = new SearchComponent(browser);
    }

    private final String
            markDescriptionText = "Укажите марки оборудования, с которыми вы работаете",
            modelDescriptionText = "Укажите модели к данному оборудованию",
            markPlaceholderText = "Введите название марки",
            modelPlaceholderText = "Введите модель оборудования";


    SelenideElement
            markAndModelBox = driver.$$("div.d-flex.my-3").get(0).as("Компонент добавить оборудование"),
            addEquipmentButton = driver.$(byTagAndText("span", "Добавить оборудование")).as("Кнопка добавить оборудование");

    ElementsCollection
            descriptionCollection = markAndModelBox.$$("span.bold").as("Коллекция описаний"),
            markItemsCollection = markAndModelBox.$$("div.gas-button-select div.item").as("Коллекция марок оборудования");

    SelenideElement
            markDescription = descriptionCollection.get(0).as("Описание марки"),
            modelDescription = descriptionCollection.get(1).as("Описание модели"),
            uploadCertificateBox = driver.$("div.px-3.my-3").as("Бокс загрузить сертификат"),
            acceptButton = driver.$(byTagAndText("span", "Подтвердить")).as("Кнопка подтвердить"),
            cancelButton = driver.$(byTagAndText("span", "Отменить")).as("Кнопка отменить");

    public void  checkCollapsedState() {
        stepWithRole("Убедиться, что компонент добавить оборудование в начальном состоянии", ()-> {
            addEquipmentButton.shouldBe(visible);
            stepWithRole("Убедиться, что компонент добавить оборудование в свернутом состоянии", () -> {
            markAndModelBox.shouldNotBe(visible);
            markDescription.shouldNotBe(visible);
            modelDescription.shouldNotBe(visible);
            uploadCertificateBox.shouldNotBe(visible);
            acceptButton.shouldNotBe(visible);
                cancelButton.shouldNotBe(visible);
            });
        });
    }

    public void checkExpandedState() {
        stepWithRole("Убедиться, что компонент добавить оборудование в начальном состоянии", () -> {
            stepWithRole("Убедиться, что описание марки: " + markDescriptionText, () -> {
                markDescription.shouldHave(text(markDescriptionText));
            });
            stepWithRole("Убедиться, что описание модели: " + modelDescriptionText, () -> {
                modelDescription.shouldHave(text(modelDescriptionText));
            });
            stepWithRole("Убедиться, что строка поиска: " + markPlaceholderText + " в начальном состоянии", () -> {
                search.checkInitialState(markPlaceholderText);
            });
            stepWithRole("Убедиться, что строка поиска: " + modelPlaceholderText + " в начальном состоянии", () -> {
                search.checkInitialState(modelPlaceholderText);
            });
            stepWithRole("Убедиться, что коллекция марок оборудования не пустая", () -> {
                markItemsCollection.shouldHave(CollectionCondition.sizeGreaterThan(1), Duration.ofSeconds(10));
            });
            stepWithRole("Убедиться, что кнопка добавить оборудование не отображается", () -> {
                addEquipmentButton.shouldNotBe(visible);
            });
            stepWithRole("Убедиться, что бокс загрузить сертификат в начальном состоянии", () -> {
                uploadCertificateBox.shouldBe(visible);
            });
            stepWithRole("Убедиться, что кнопка подтвердить отображается", () -> {
                acceptButton.shouldBe(visible);
            });
            stepWithRole("Убедиться, что кнопка отменить отображается", () -> {
                cancelButton.shouldBe(visible);
            });
        });
    }

}
// todo validation