package ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent;

import com.codeborne.selenide.ElementsCollection;
import ru.gasworkers.dev.model.OrderType;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import static com.codeborne.selenide.Condition.text;

public class OrderDetailsCommonTabComponent extends BaseTabOrderCardComponent {
    public OrderDetailsCommonTabComponent(RoleBrowser browser) {
        super(browser);
    }

    ElementsCollection
            orderDetailsCollection = driver.$$("div.order-details-item").as("Информация о заказе");

    public void currentType(OrderType orderType) {
        stepWithRole("Убедиться, что тип заказа: " + orderType.toString(), () -> {
            orderDetailsCollection.findBy(text("Тип заказа")).shouldHave(text(orderType.toString()));
        });
    }
    
    
}
