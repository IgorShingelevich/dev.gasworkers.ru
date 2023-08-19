package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.infoMaster;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.BaseOrderCardComponent;

import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Condition.visible;

public class RepairDetailsInfoMasterTabOrderCardComponent extends BaseOrderCardComponent {
    public RepairDetailsInfoMasterTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что информация о заказе загрузилась", () -> {
            self.shouldBe(visible);
            materialsTitleTextLocator.shouldBe(visible);
            actionsTitleTextLocator.shouldBe(visible);
            materialsTableLocator.shouldBe(visible);
            actionsTableLocator.shouldBe(visible);
        });
    }    SelenideElement
            self = driver.$("div.order-details").as("Информация о заказе"),
            materialsTitleTextLocator = self.$$("div.bold.mb-2").get(0).as("Запасные части и расходные материалы"),
            actionsTitleTextLocator = self.$$("div.bold.mb-2").get(1).as("Выполненные работы и услуги"),
            materialsTableLocator = self.$$("div.table-div-scroll").get(0).as("Таблица запасных частей и расходных материалов"),
            actionsTableLocator = self.$$("div.table-div-scroll").get(1).as("Таблица выполненных работ и услуг"),
            materialsTotalPriceLocator = materialsTableLocator.$x(".//div[@class='table-div-body-td x-2'][contains(.,'₽')][1]").as("Итоговая цена запасных частей и расходных материалов"),
            actionsTotalPriceLocator = actionsTableLocator.$x(".//div[@class='table-div-body-td x-2'][contains(.,'₽')][1]").as("Итоговая цена выполненных работ и услуг");

    public void checkMaterialsTotalPrice(String expectedMaterialsTotalPrice) {
        stepWithRole("Убедиться, что итоговая цена запасных частей и расходных материалов: " + expectedMaterialsTotalPrice, () -> {
            materialsTotalPriceLocator.shouldHave(partialText(expectedMaterialsTotalPrice));
        });
    }

    public void checkActionsTotalPrice(String expectedActionsTotalPrice) {
        stepWithRole("Убедиться, что итоговая цена выполненных работ и услуг: " + expectedActionsTotalPrice, () -> {
            actionsTotalPriceLocator.shouldHave(partialText(expectedActionsTotalPrice));
        });
    }

    public void checkNoRepairDetails() {
        stepWithRole("Убедиться, что информация о заказе отсутствует", () -> {
            self.shouldNotBe(visible);
        });
    }
}
