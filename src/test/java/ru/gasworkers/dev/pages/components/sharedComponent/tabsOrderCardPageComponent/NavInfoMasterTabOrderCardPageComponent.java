package ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent;

import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.masterComponent.FillUpCheckListBannerComponent;

public class NavInfoMasterTabOrderCardPageComponent extends BaseTabOrderCardComponent {
    public final OrderStatusOrderCardPageComponent orderState;
    public final FillUpCheckListBannerComponent fillUpBanner;
    public final TableInfoMasterTabComponent table;

    public NavInfoMasterTabOrderCardPageComponent(RoleBrowser browser) {
        super(browser);
        orderState = new OrderStatusOrderCardPageComponent(browser);
        fillUpBanner = new FillUpCheckListBannerComponent(browser);
        table = new TableInfoMasterTabComponent(browser);
    }

    public void checkFinishLoading(OrderStatus orderStatus) {
        orderState.currentStatus(orderStatus);
    }

}
