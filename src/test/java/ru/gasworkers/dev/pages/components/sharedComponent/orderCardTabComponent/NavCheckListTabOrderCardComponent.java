package ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;

public class NavCheckListTabOrderCardComponent extends BaseTabOrderCardComponent {
    public final OrderStatusCardComponent orderState;
    public NavCheckListTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
        orderState = new OrderStatusCardComponent(browser);
    }

    ElementsCollection
            checklistRadioButtonItemCollection = driver.$$("div.item-flex .row").as("Радио кнопки чек листа");

    public void checkFinishLoading(OrderStatus orderStatus) {
            orderState.currentStatus(orderStatus);
        stepWithRole("Убедиться, что чек лист загрузился", () -> {
            checklistRadioButtonItemCollection.should(CollectionCondition.sizeGreaterThan(0));
        });

    }

}
