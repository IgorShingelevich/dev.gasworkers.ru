package ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

public class NavCheckListTabOrderCardComponent extends BaseTabOrderCardComponent {
    public final OrderStateCardComponent orderState;
    public NavCheckListTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
        orderState = new OrderStateCardComponent(browser);
    }
}
