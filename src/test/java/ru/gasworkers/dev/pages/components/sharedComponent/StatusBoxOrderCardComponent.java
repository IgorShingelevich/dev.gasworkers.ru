package ru.gasworkers.dev.pages.components.sharedComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

public class StatusBoxOrderCardComponent extends BaseComponent {
    public StatusBoxOrderCardComponent(RoleBrowser browser) {
        super(browser);
    }

private final String
    orderStatusTitleText = "Статус заказа:",
    prePaymentTitleText = "Предоплата",
    payMaintenanceFullPriceTitleText = "Полная оплата",
    payRepairMaterialTitleText = "Оплата материалов",
    getPayRepairWorkTitleText = "Оплата ремонтных работ",
    paidStatusText = "Оплачено",
    notPaidStatusText = "Не оплачено";

    ElementsCollection
    paymentDetailsCollection = driver.$$("div.gas-box  .order-details__prices--status .bold").as("Детали оплаты заказа");

    SelenideElement
            orderStatusBlockLocator = driver.$("div.gas-box").as("Блок статуса заказа"),
            orderStatusTitleLocator = orderStatusBlockLocator.$(".item-flex .bold").as("Заголовок статуса заказа"),
            orderStatus = orderStatusBlockLocator.$(" .item-flex .text").as("Статус заказа");




    public void checkInitialState() {

    }
}
