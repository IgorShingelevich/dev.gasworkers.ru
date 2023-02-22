package ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent;

import io.netty.handler.codec.spdy.SpdyWindowUpdateFrame;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.masterComponent.FillUpCheckListBannerComponent;

public class NavInfoMasterTabOrderCardComponent extends BaseTabOrderCardComponent {
    public final OrderStatusCardComponent orderState;
    public final FillUpCheckListBannerComponent fillUpBanner;
    public final TableInfoMasterTabOrderCardComponent table;

    public NavInfoMasterTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
        orderState = new OrderStatusCardComponent(browser);
        fillUpBanner = new FillUpCheckListBannerComponent(browser);
        table = new TableInfoMasterTabOrderCardComponent(browser);
    }

    public void checkFinishLoading(OrderStatus orderStatus) {
        orderState.currentStatus(orderStatus);
    }
}
