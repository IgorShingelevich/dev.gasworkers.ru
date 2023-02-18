package ru.gasworkers.dev.pages.components.masterComponent.editObjectMasterComponents;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class EditDistributorMasterComponent extends BaseComponent {

    public final EditDistributorModalMasterComponent distributorModal;
    public EditDistributorMasterComponent(RoleBrowser browser) {
        super(browser);
        distributorModal = new EditDistributorModalMasterComponent(browser);
    }

    ElementsCollection
        infoCollection = driver.$$("ul.branch-company-list .branch-company__r").as("Коллекция информации о газораспределительной компании");

    SelenideElement

        nameLocator = infoCollection.get(0).as("Название компании"),
        branchLocator = infoCollection.get(1).as("Название филиала"),
        accountLocator = infoCollection.get(2).as("Лицевой счет"),
        editButtonLocator = driver.$(byTagAndText("span", "Редактировать")).as("Кнопка Редактировать"),
        toOrderButtonLocator = driver.$(byTagAndText("span", "К заказу")).as("Кнопка К заказу");


    public void checkFinishLoading() {
        stepWithRole("Убедиться, что компонент Редактирование дистрибьютора загружен", () -> {
            String urlEditDistributorMasterComponent = driver.url();
            nameLocator.shouldBe(visible);
            branchLocator.shouldBe(visible);
            accountLocator.shouldBe(visible);
            editButtonLocator.shouldBe(visible);
            toOrderButtonLocator.shouldBe(visible);
        });
    }

    public void toOrder () {
        stepWithRole("Нажать кнопку К заказу", () -> {
            toOrderButtonLocator.click();
        });
    }

    public void editDistributor () {
        stepWithRole("Нажать кнопку Редактировать", () -> {
            editButtonLocator.click();
        });
    }

}
