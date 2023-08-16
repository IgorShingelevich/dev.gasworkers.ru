package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs;

import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.masterComponent.CheckListMasterComponent;
import ru.gasworkers.dev.pages.components.masterComponent.FillUpCheckListBannerComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.BaseOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.StatusOrderCardPageComponent;

public class CheckListTabOrderCardComponent extends BaseOrderCardComponent {
    public final StatusOrderCardPageComponent orderState;
    public final CheckListMasterComponent checkListComponent;
    public final FillUpCheckListBannerComponent fillUpBanner;

    public CheckListTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
        orderState = new StatusOrderCardPageComponent(browser);
        checkListComponent = new CheckListMasterComponent(browser);
        fillUpBanner = new FillUpCheckListBannerComponent(browser);
    }



    public void checkFinishLoading(OrderStatus orderStatus) {
        orderState.checkCurrentStatus(orderStatus);
            checkListComponent.checkFinishLoading();
    }

}
