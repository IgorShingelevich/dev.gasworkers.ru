package ru.gasworkers.dev.pages.components.sharedComponent.tabOrderCardComponent;

import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.masterComponent.CheckListMasterComponent;
import ru.gasworkers.dev.pages.components.masterComponent.FillUpCheckListBannerComponent;

public class NavCheckListTabOrderCardComponent extends BaseTabOrderCardComponent {
    public final OrderStatusCardComponent orderState;
    public final CheckListMasterComponent checkListComponent;
    public final FillUpCheckListBannerComponent fillUpBanner;
    public NavCheckListTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
        orderState = new OrderStatusCardComponent(browser);
        checkListComponent = new CheckListMasterComponent(browser);
        fillUpBanner = new FillUpCheckListBannerComponent(browser);
    }



    public void checkFinishLoading(OrderStatus orderStatus) {
            orderState.currentStatus(orderStatus);
            checkListComponent.checkFinishLoading();
    }

}
