package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.infoMaster;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.BaseOrderCardComponent;
import ru.gasworkers.dev.tests.web.orderProcess.repair.StateBuilder;
import ru.gasworkers.dev.tests.web.orderProcess.repair.StateRepairHelper;

import static com.codeborne.selenide.Condition.*;

public class RepairDetailsInfoMasterTabOrderCardComponent extends BaseOrderCardComponent {
    public RepairDetailsInfoMasterTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
    }

    StateRepairHelper helper = new StateRepairHelper();


    public void checkFinishLoading() {
        stepWithRole("Убедиться, что информация о заказе загрузилась", () -> {
            self.shouldBe(visible);
            materialsTitleTextLocator.shouldBe(visible);
            actionsTitleTextLocator.shouldBe(visible);
            materialsTableLocator.shouldBe(visible);
            actionsTableLocator.shouldBe(visible);
        });
    }

    public void checkMaterialsPrice(String expectedMaterialsTotalPrice) {
        stepWithRole("Убедиться, что итоговая цена запасных частей и расходных материалов: " + expectedMaterialsTotalPrice, () -> {
            materialsTotalPriceLocator.shouldHave(partialText(expectedMaterialsTotalPrice));
        });
    }
    public void checkActionsPrice(String expectedActionsTotalPrice) {
        stepWithRole("Убедиться, что итоговая цена выполненных работ и услуг: " + expectedActionsTotalPrice, () -> {
            actionsTotalPriceLocator.shouldHave(partialText(expectedActionsTotalPrice));
        });
    }SelenideElement
            self = driver.$("div.order-details").as("Информация о заказе"),
            materialsTitleTextLocator = self.$$("div.bold.mb-2").get(0).as("Запасные части и расходные материалы"),
            actionsTitleTextLocator = self.$$("div.bold.mb-2").get(1).as("Выполненные работы и услуги"),
            materialsTableLocator = self.$$("div.table-div-scroll").get(0).as("Таблица запасных частей и расходных материалов"),
            actionsTableLocator = self.$$("div.table-div-scroll").get(1).as("Таблица выполненных работ и услуг"),
            materialsTotalPriceLocator1 = materialsTableLocator.$x(".//div[@class='table-div-body-td x-2'][contains(.,'₽')][1]").as("Итоговая цена запасных частей и расходных материалов"),
            materialsTotalPriceLocator = materialsTableLocator.$$("div[class*='tr']").last()
                    .$$("div[class*='td']").last().as("Итоговая цена запасных частей и расходных материалов"),
            actionsTotalPriceLocator1 = actionsTableLocator.$x(".//div[@class='table-div-body-td x-2'][contains(.,'₽')][1]").as("Итоговая цена выполненных работ и услуг"),
            actionsTotalPriceLocator = actionsTableLocator.$$("div[class*='tr']").last()
                    .$$("div[class*='td']").last().as("Итоговая цена выполненных работ и услуг");

    public void checkMaterialsPrice(OrdersIdResponseDto dto, StateBuilder.OrderIdData data) {
        materialsTableLocator.$$(".table-div-body-tr").last()
                .$(".bold").as(" Итого материалы")
                .shouldHave(partialText(helper.priceFormatter(String.valueOf(dto.getData().getReceipts().get(1).getAmount()))));
    }

    public void checkActionsPrice(OrdersIdResponseDto dto, StateBuilder.OrderIdData data) {
        int price = (int) ((dto.getData().getReceipts().get(2).getAmount()) + (dto.getData().getReceipts().get(0).getAmount())); //  activation fee + actions
        actionsTableLocator.$$(".table-div-body-tr").last()
                .$(".bold").as(" Итого работы")
                .shouldHave(partialText(helper.priceFormatter(String.valueOf(price))));
    }

    public void noTables() {
        stepWithRole("Убедиться, что таблицы с  работами и материалами о заказе отсутствует", () -> {
            materialsTableLocator.shouldNotBe(visible);
            actionsTableLocator.shouldNotBe(visible);
        });
    }

    public void checkMaterialsLogisticFeeAdded(OrdersIdResponseDto dto) {
        stepWithRole("Убедиться, что в таблице запасных частей и расходных материалов добавлена логистическая услуга", () -> {
            materialsTableLocator.$$(".table-div-body-tr")
                    .filterBy(text("Подбор и доставка материалов. Логистика")).first()
                    .$$(".bold").last().as("Логистические услуги")
                    .shouldHave(partialText(String.valueOf(dto.getData().getMaterialValues().get(1).getPrice())));
        });
    }

    public void checkMaterialsFirstEquipmentPrice(OrdersIdResponseDto dto, Integer equipmentIndex) {
        stepWithRole("Убедиться, что в таблице запасных частей и расходных материалов добавлена первая запчасть", () -> {
            Integer price = (int) ((dto.getData().getMaterialValues().get(0).getPrice()) * (dto.getData().getMaterialValues().get(0).getQty()));
            materialsTableLocator.$$(".table-div-body-tr")
                    .get(equipmentIndex)
                    .$$("div[class*='td']").last()
                    .shouldHave(partialText(String.valueOf(price)));
        });
    }


    public void checkActionsFirstItemPrice(OrdersIdResponseDto dto, int actionItemIndex) {
        stepWithRole("Убедиться, что в таблице выполненных работ и услуг добавлена первая работа", () -> {
            int price = (int) ((dto.getData().getActions().get(0).getPrice()) * (dto.getData().getActions().get(0).getQty()));
            actionsTableLocator.$$(".table-div-body-tr")
                    .get(actionItemIndex)
                    .$$("div[class*='td']").last()
                    .shouldHave(partialText(String.valueOf(price)));
        });
    }
}
