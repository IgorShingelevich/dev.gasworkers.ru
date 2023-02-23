package ru.gasworkers.dev.pages.components.sharedComponent.tabOrderCardComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class TableInfoMasterTabOrderCardComponent extends BaseComponent {
    public TableInfoMasterTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
    tableContainer = driver.$("div.table-div-body"),
    saveButton = driver.$(byTagAndText("span", "Сохранить")).as("Кнопка сохранить"),
    addPositionButton = driver.$(byTagAndText("span", "Добавить позицию")).as("Кнопка добавить позицию");
    ElementsCollection
    itemList = driver.$$("div.table-div-body__td--table-tr.col-12-prevent").as("Список позиций"),
    itemType = driver.$$("div.w-50.table-div-body__td--table-tr-td.h-100.border-bottom-0 span").as("Тип позиции"),
    itemQuantity = driver.$$("div.table-div-body__td--table-tr-td.x-2 span").as("Количество позиции"),
    itemDescription = driver.$$("div.table-div-body__td--table-tr-td.x-4 .text-flex").as("Перечень выполненных работ");


    public Integer getQuantityItem() {
        return itemList.size();
    }

    public SelenideElement getCell(int row, int column) {
         itemList.get(row).$$("td").get(column);
        return itemList.get(row).$$("td").get(column);
    }

    public SelenideElement getTypeCell(int row) {
        return getCell(row, 0);
    }

    public SelenideElement getBrandCell(int row) {
        return getCell(row, 1);
    }

    public SelenideElement getQuantityCell(int row) {
        return getCell(row, 2);
    }

    public SelenideElement getDescriptionCell(int row) {
        return getCell(row, 3);
    }



    public void checkFinishLoading() {
        itemList.shouldBe(CollectionCondition.sizeGreaterThan(0));
        String firstItemNoVar = driver.$$("div.table-div-body__td--table-tr.col-12-prevent").get(0).$$("div.w-50.table-div-body__td--table-tr-td.h-100.border-bottom-0 span").get(1).getText();
        String firstItemVar = itemList.get(1).$$("div.w-50.table-div-body__td--table-tr-td.h-100.border-bottom-0 span").get(1).getText();
        String secondItemVar = itemList.get(2).$$("div.w-50.table-div-body__td--table-tr-td.h-100.border-bottom-0 span").get(1).getText();
        int preLastItemIndex = itemList.size()-1;
        String preLastItem = itemList.get(itemList.size()-2).$$("div.w-50.table-div-body__td--table-tr-td.h-100.border-bottom-0 span").get(0).getText();
        String lastItem = itemList.get(itemList.size()-1).$$("div.w-50.table-div-body__td--table-tr-td.h-100.border-bottom-0 span").get(0).getText();
        System.out.println("firstItemNoVar = " + firstItemNoVar);
        System.out.println("firstItemVar = " + firstItemVar);
        System.out.println("secondItemVar = " + secondItemVar);
        System.out.println("itemList.size() = " + itemList.size());
        System.out.println("preLastItemIndex = " + preLastItemIndex);
        System.out.println("preLastItem = " + preLastItem);
        System.out.println("lastItem = " + lastItem);

    }

    public void checkDefaultState () {
        stepWithRole("Проверка состояния  таблицы до начала редактирования", () -> {
            checkFinishLoading();
            saveButton.ancestor("button").shouldBe(disabled);
            addPositionButton.shouldBe(visible);
        });
    }






}
