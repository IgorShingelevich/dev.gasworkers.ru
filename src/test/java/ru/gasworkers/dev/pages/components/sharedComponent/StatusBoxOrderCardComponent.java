package ru.gasworkers.dev.pages.components.sharedComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

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
            orderPricesDetailsBlockLocator = orderStatusBlockLocator.$("ul.order-details__prices").as("Блок деталей оплаты заказа"),
            orderStatusTitleLocator = orderStatusBlockLocator.$(".item-flex .bold").as("Заголовок статуса заказа"),
            orderStatusLocator = orderStatusBlockLocator.$(" .item-flex .text").as("Статус заказа");




    public void newTenderState() {
        stepWithRole("Убедиться, что компонент статуса заказа  с состоянии Новый тендер", () -> {
            orderStatusBlockLocator.shouldBe(visible);
            orderStatusTitleLocator.shouldHave(text(orderStatusTitleText));
            stepWithRole("Убедиться, что статус заказа отображается", () -> {
                orderStatusLocator.shouldHave(text(OrderStatus.NEW_TENDER.toString()));
            });
            stepWithRole("Убедиться, что блок деталей оплаты заказа не отображается", () -> {
                orderPricesDetailsBlockLocator.shouldNotBe(visible);
            });
        });

    }
}
