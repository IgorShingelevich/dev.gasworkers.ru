package ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent;

import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;

public class NavInfoMasterTabOrderCardComponent extends BaseTabOrderCardComponent {
    public final OrderStatusCardComponent orderState;

    public NavInfoMasterTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
        orderState = new OrderStatusCardComponent(browser);
    }

    public void checkFinishLoading(OrderStatus orderStatus) {
        orderState.currentState(orderStatus);
    }
}
