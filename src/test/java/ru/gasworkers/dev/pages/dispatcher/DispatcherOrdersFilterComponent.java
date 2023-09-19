package ru.gasworkers.dev.pages.dispatcher;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class DispatcherOrdersFilterComponent extends BaseComponent {
    ElementsCollection filterButtonCollection = driver.$$("div.item.mt-0").as("Коллекция кнопок фильтра");

    public DispatcherOrdersFilterComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkButtonLoading() {
        stepWithRole("Убедиться, что кнопка  фильтр заказов отображается", () -> {
            driver.$("button[data-test-id=info]").shouldBe(visible);
        });
    }

    public void clickFilterButton() {
        stepWithRole("Нажать на кнопку фильтр заказов", () -> {
            driver.$("button[data-test-id=info]").click();
        });
    }

    public void isFilterActive(SelenideElement filterButtonLocator) {
        stepWithRole("Убедиться, что " + filterButtonLocator.getText() + " активна", () -> {
            filterButtonLocator.shouldHave(cssClass("active"));
        });
    }

    public void checkFinishLoadingModal() {
        stepWithRole("Убедиться, что модальное окно фильтра загружено", () -> {
            selfModalLocator.shouldBe(visible);
            searchInputLocator.shouldBe(visible);
            searchIconLocator.shouldBe(visible);
            searchButtonLocator.shouldBe(visible);
            clearButtonLocator.shouldBe(visible);
            closeFilterButtonLocator.shouldBe(visible);
            closeModalButtonLocator.shouldBe(visible);
            maintenanceButtonFilterLocator.shouldBe(visible);
            repairButtonFilterLocator.shouldBe(visible);
            newTenderButtonFilterLocator.shouldBe(visible);
            inProgressTenderButtonFilterLocator.shouldBe(visible);
            completedTenderButtonFilterLocator.shouldBe(visible);
            archiveTenderButtonFilterLocator.shouldBe(visible);
            todayDateButtonFilterLocator.shouldBe(visible);
            last3DaysDateButtonFilterLocator.shouldBe(visible);
            last7DaysDateButtonFilterLocator.shouldBe(visible);
            sortDateButtonFilterLocator.shouldBe(visible);
            sortImportanceButtonFilterLocator.shouldBe(visible);
            sortCostButtonFilterLocator.shouldBe(visible);
        });
    }    SelenideElement
            selfModalLocator = driver.$("div.modal-content-wrapper").as("модальное окно фильтра"),
            searchInputLocator = selfModalLocator.$("input.search-input").as("Поле поиска"),
            searchIconLocator = selfModalLocator.$("button.search-btn").as("иконка поиска"),
            searchButtonLocator = selfModalLocator.$(byTagAndText("span", "Найти")).as("Кнопка найти"),
            clearButtonLocator = selfModalLocator.$(byTagAndText("span", "Очистить")).as("Кнопка очистить"),
            closeFilterButtonLocator = selfModalLocator.$(byTagAndText("span", "Закрыть")).as("Кнопка закрыть"),
            closeModalButtonLocator = selfModalLocator.$("div.close-btn").as("Кнопка закрыть модальное окно"),
            maintenanceButtonFilterLocator = filterButtonCollection.findBy(text("Договор ТО")).as("Кнопка фильтра договор ТО"),
            repairButtonFilterLocator = filterButtonCollection.findBy(text("Ремонт")).as("Кнопка фильтра ремонт"),
            newTenderButtonFilterLocator = filterButtonCollection.findBy(text("Новый тендер")).as("Кнопка фильтра новый тендер"),
            inProgressTenderButtonFilterLocator = filterButtonCollection.findBy(text("В работе")).as("Кнопка фильтра в работе"),
            completedTenderButtonFilterLocator = filterButtonCollection.findBy(text("Выполнен")).as("Кнопка фильтра выполнен"),
            archiveTenderButtonFilterLocator = filterButtonCollection.findBy(text("В архиве")).as("Кнопка фильтра в архиве"),
            todayDateButtonFilterLocator = filterButtonCollection.findBy(text("Сегодня")).as("Кнопка фильтра сегодня"),
            last3DaysDateButtonFilterLocator = filterButtonCollection.findBy(text("Последние 3 дня")).as("Кнопка фильтра последние 3 дня"),
            last7DaysDateButtonFilterLocator = filterButtonCollection.findBy(text("Последние 7 дней")).as("Кнопка фильтра последние 7 дней"),
            sortDateButtonFilterLocator = filterButtonCollection.findBy(text("дата добавления")).as("Кнопка фильтра дата добавления"),
            sortImportanceButtonFilterLocator = filterButtonCollection.findBy(text("важность")).as("Кнопка фильтра важность"),
            sortCostButtonFilterLocator = filterButtonCollection.findBy(text("стоимость")).as("Кнопка фильтра стоимость");

    public void checkDefaultFilterState() {
        stepWithRole("Убедиться, что фильтр по умолчанию", () -> {
            newTenderButtonFilterLocator.shouldHave(cssClass("active"));
            sortDateButtonFilterLocator.shouldHave(cssClass("active"));
            repairButtonFilterLocator.shouldNotHave(cssClass("active"));
            inProgressTenderButtonFilterLocator.shouldNotHave(cssClass("active"));
            completedTenderButtonFilterLocator.shouldNotHave(cssClass("active"));
            archiveTenderButtonFilterLocator.shouldNotHave(cssClass("active"));
            todayDateButtonFilterLocator.shouldNotHave(cssClass("active"));
            last3DaysDateButtonFilterLocator.shouldNotHave(cssClass("active"));
            last7DaysDateButtonFilterLocator.shouldNotHave(cssClass("active"));
            sortImportanceButtonFilterLocator.shouldNotHave(cssClass("active"));
            sortCostButtonFilterLocator.shouldNotHave(cssClass("active"));
        });
    }

    public void searchByNumber(String orderNumber) {
        stepWithRole("Поиск заказа по номеру: " + orderNumber, () -> {
            searchInputLocator.setValue(orderNumber);
            searchButtonLocator.click();
        });
    }


}
