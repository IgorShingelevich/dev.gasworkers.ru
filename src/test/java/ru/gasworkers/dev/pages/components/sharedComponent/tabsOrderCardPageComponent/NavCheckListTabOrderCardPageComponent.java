package ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent;

import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.masterComponent.CheckListMasterComponent;
import ru.gasworkers.dev.pages.components.masterComponent.FillUpCheckListBannerComponent;

public class NavCheckListTabOrderCardPageComponent extends BaseTabOrderCardComponent {
    public final OrderStatusOrderCardPageComponent orderState;
    public final CheckListMasterComponent checkListComponent;
    public final FillUpCheckListBannerComponent fillUpBanner;
    public NavCheckListTabOrderCardPageComponent(RoleBrowser browser) {
        super(browser);
        orderState = new OrderStatusOrderCardPageComponent(browser);
        checkListComponent = new CheckListMasterComponent(browser);
        fillUpBanner = new FillUpCheckListBannerComponent(browser);
    }



    public void checkFinishLoading(OrderStatus orderStatus) {
            orderState.currentStatus(orderStatus);
            checkListComponent.checkFinishLoading();
    }

}
