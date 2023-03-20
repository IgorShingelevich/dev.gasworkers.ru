package ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import static com.codeborne.selenide.Condition.text;

public class OrderStatusOrderCardPageComponent extends BaseTabOrderCardComponent{
    public OrderStatusOrderCardPageComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
            orderStateLocator = driver.$(".item-flex p.text").as("Статус заказа");

    public void currentStatus(OrderStatus orderStatus) {
        stepWithRole("Убедиться, что статус заказа " + orderStatus.toString(), () -> {
            orderStateLocator.shouldHave(text(orderStatus.toString()));
        });
    }
    //TODO - nav tabs for all roles



}
