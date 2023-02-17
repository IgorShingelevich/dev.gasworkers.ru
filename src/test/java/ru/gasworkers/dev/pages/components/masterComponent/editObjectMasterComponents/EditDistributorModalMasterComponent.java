package ru.gasworkers.dev.pages.components.masterComponent.editObjectMasterComponents;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class EditDistributorModalMasterComponent extends BaseComponent {
    public EditDistributorModalMasterComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
        EDIT_DISTRIBUTOR_TITLE = "Редактирование дистрибьютора";

    SelenideElement
        titleLocator = driver.$("div h3 .branch-company-title").as("Заголовок страницы Редактирование дистрибьютора"),
        companyFieldLocator = driver.$$("div.gas-select__header").get(0).as("Поле Компания"),
        branchFieldLocator = driver.$$("div.gas-select__header").get(1).as("Поле Филиал"),
        accountNumberLocator = driver.$("div.gas-input.primary input").as("Поле Лицевой счет"),
        saveButtonLocator = driver.$(byTagAndText("span", "Сохранить")).as("Кнопка Сохранить"),
        cancelButtonLocator = driver.$(byTagAndText("span", "Отмена")).as("Кнопка Отмена"),
        closeModalButtonLocator = driver.$("div.close-btn").as("Кнопка Закрыть");
    ElementsCollection
        companyCollection = driver.$$("div.gas-select-wrap").get(0).$$(".item").as("Коллекция  имя компаний"),
        branchCollection = driver.$$("div.gas-select-wrap").get(1).$$(".item").as("Коллекция  имя филиалов");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что страница Редактирование дистрибьютора загружена", () -> {
            titleLocator.shouldBe(visible);
            // TODO fix modalTitleLocator
        });
    }

    public void checkModalForm(String company, String branch, String accountNumber) {
        stepWithRole("Убедиться, что дистрибьютор, филал и лицевой счет совпадают с переданными", () -> {
            companyFieldLocator.shouldHave(text(company));
            branchFieldLocator.shouldHave(text(branch));
            accountNumberLocator.shouldHave(text(accountNumber));
            stepWithRole("Дистрибьютор : " + companyFieldLocator.getText(), () -> {
            });
            stepWithRole("Филиал : " + branchFieldLocator.getText(), () -> {
            });
            stepWithRole("Лицевой счет : " + accountNumberLocator.getText(), () -> {
            });
        });
    }

    public void setCompanyAndBranch(String company, String branch) {
        stepWithRole("Установить компанию и филиал", () -> {
            companyFieldLocator.click();
            companyCollection.findBy(text(company)).click();
            branchFieldLocator.click();
            branchCollection.findBy(text(branch)).click();
        });
    }

    public void setAccountNumber(String accountNumber) {
        stepWithRole("Установить лицевой счет", () -> {
            accountNumberLocator.setValue(accountNumber);
        });
    }
    public void save () {
        stepWithRole("Нажать кнопку Сохранить", () -> {
            saveButtonLocator.click();
        });
    }

    public void cancel () {
        stepWithRole("Нажать кнопку Отмена", () -> {
            cancelButtonLocator.click();
        });
    }

    public void closeModal () {
        stepWithRole("Нажать кнопку Закрыть", () -> {
            closeModalButtonLocator.click();
        });
    }

}
